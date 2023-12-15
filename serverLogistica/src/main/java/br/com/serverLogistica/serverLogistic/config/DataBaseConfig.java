package br.com.serverLogistica.serverLogistic.config;

import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataBaseConfig {
    private static final Logger LOG = Logger.getLogger(DataBaseConfig.class.getName());
    private static String nameDB = "localdb.db";
    private static String urlConnection = "jdbc:sqlite:" + nameDB;
    private int idPessoa;
    private String nome;
    private int pedido;
    private int produto;
    private double valorProduto;
    private int dataCompra;

    public DataBaseConfig(int idPessoa, String nome, int pedido, int produto, double valorProduto, int dataCompra) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.pedido = pedido;
        this.produto = produto;
        this.valorProduto = valorProduto;
        this.dataCompra = dataCompra;
    }

    public static void dataBaseConfiguration() {
        try (Connection conexao = DriverManager.getConnection(urlConnection)){
            Class.forName("org.sqlite.JDBC");

            Statement instructionSQL = conexao.createStatement();

            String createTable = "CREATE TABLE IF NOT EXISTS user ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "idPessoa INTEGER,"
                    + "nome TEXT NOT NULL,"
                    + "pedido INTEGER,"
                    + "produto INTEGER,"
                    + "valor_do_produto DECIMAL,"
                    + "data_compra INTEGER);";

            instructionSQL.execute(createTable);

            LOG.info("database created successful.");

        } catch (Exception e) {
            LOG.severe("Error: " + e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void insertDataIntoDatabase(String content) {
        try (Connection conexao = DriverManager.getConnection(urlConnection)) {

            Pattern pattern = Pattern.compile("^\\s*(\\d+)\\s+([^\\d]+\\s+[^\\d]+)\\s*(\\d{10})\\s*(\\d{10})\\s*([\\d.]+)\\s*(\\d{4})(\\d{2})(\\d{2})\\s*$");

            String[] lines = content.split("\n");

            for (String line : lines) {
                Matcher matcher = pattern.matcher(line.trim());

                if (matcher.find()) {
                    try {
                        int idPessoa = Integer.parseInt(matcher.group(1));
                        String nomeCompleto = matcher.group(2).trim();
                        int pedido = Integer.parseInt(matcher.group(3).trim());
                        int produto = Integer.parseInt(matcher.group(4).trim());
                        double valorProduto = Double.parseDouble(matcher.group(5));

                        int ano = Integer.parseInt(matcher.group(6));
                        int mes = Integer.parseInt(matcher.group(7));
                        int dia = Integer.parseInt(matcher.group(8));

                        int dataCompra = ano * 10000 + mes * 100 + dia;

                        // LOG.info("Inserting data: " + idPessoa + ", " + nomeCompleto + ", " + pedido + ", " + valorProduto + ", " + dataCompra);

                        DataBaseConfig.insertDataIntoTable(conexao, idPessoa, nomeCompleto, pedido, produto, valorProduto, dataCompra);
                    } catch (NumberFormatException e) {
                        LOG.warning("Failed to parse line due to invalid number format: " + line);
                    }
                } else {
                    LOG.warning("Failed to parse line: " + line);
                }
            }

        } catch (SQLException e) {
            LOG.severe("Error: " + e.getMessage());
        }
    }

    private static void insertDataIntoTable(Connection connection, int idPessoa, String nome, int pedido, int produto, double valorProduto, int dataCompra) throws SQLException {
        String sqlCheck = "SELECT COUNT(*) FROM user WHERE idPessoa = ? AND nome = ? AND pedido = ? AND produto = ? AND valor_do_produto = ? AND data_compra = ?";

        try (PreparedStatement checkStatement = connection.prepareStatement(sqlCheck)) {
            checkStatement.setInt(1, idPessoa);
            checkStatement.setString(2, nome);
            checkStatement.setInt(3, pedido);
            checkStatement.setInt(4, produto);
            checkStatement.setDouble(5, valorProduto);
            checkStatement.setInt(6, dataCompra);

            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    LOG.warning("Data duplicated.");
                    return;
                }
            }
        }

        String sqlInsert = "INSERT INTO user (idPessoa, nome, pedido, produto, valor_do_produto, data_compra) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(sqlInsert)) {
            insertStatement.setInt(1, idPessoa);
            insertStatement.setString(2, nome);
            insertStatement.setInt(3, pedido);
            insertStatement.setInt(4, produto);
            insertStatement.setDouble(5, valorProduto);
            insertStatement.setInt(6, dataCompra);

            insertStatement.executeUpdate();
        }
    }

    public static String allData() throws SQLException {
        String sql = "SELECT * FROM user";

        try (Connection conexao = DriverManager.getConnection(urlConnection)) {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Map<Integer, Map<String, Object>> userMap = new HashMap<>();

                while (resultSet.next()) {
                    int idPessoa = resultSet.getInt("idPessoa");
                    String nome = resultSet.getString("nome");
                    int pedido = resultSet.getInt("pedido");
                    double valorProduto = resultSet.getDouble("valor_do_produto");
                    int dataCompra = resultSet.getInt("data_compra");

                    if (!userMap.containsKey(idPessoa)) {
                        Map<String, Object> userObject = new HashMap<>();
                        userObject.put("user_id", idPessoa);
                        userObject.put("name", nome);
                        userObject.put("orders", new ArrayList<>());

                        userMap.put(idPessoa, userObject);
                    }

                    Map<String, Object> orderObject = new HashMap<>();

                    orderObject.put("order_id", pedido);
                    orderObject.put("total", String.format("%.2f", valorProduto));
                    orderObject.put("date", convertDataCompra(dataCompra));
                    orderObject.put("products", new ArrayList<>());

                    Map<String, Object> productObject = new HashMap<>();

                    productObject.put("product_id", resultSet.getInt("produto"));
                    productObject.put("value", String.format("%.2f", valorProduto));

                    ((List<Map<String, Object>>) orderObject.get("products")).add(productObject);
                    ((List<Map<String, Object>>) userMap.get(idPessoa).get("orders")).add(orderObject);
                }

                Gson gson = new Gson();
                String jsonData = gson.toJson(userMap.values());

                LOG.info(jsonData);

                return jsonData;
            }
        }
    }
    private static String convertDataCompra(int dataCompra) {
        String dataCompraStr = String.valueOf(dataCompra);
        return dataCompraStr.substring(0, 4) + "-" + dataCompraStr.substring(4, 6) + "-" + dataCompraStr.substring(6);
    }
}
