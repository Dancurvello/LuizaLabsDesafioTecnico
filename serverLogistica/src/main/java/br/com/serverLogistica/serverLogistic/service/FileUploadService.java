package br.com.serverLogistica.serverLogistic.service;

import br.com.serverLogistica.serverLogistic.config.DataBaseConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.logging.Logger;

@Service
public class FileUploadService {
    private static final Logger LOG = Logger.getLogger(DataBaseConfig.class.getName());

    public String processFile(MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            DataBaseConfig.insertDataIntoDatabase(content.toString());
            DataBaseConfig.allData();
            return "Process file successful!";

        } catch (IOException | SQLException e) {
            LOG.severe(e.getMessage());
            return "No was possible process the archive: " + e.getMessage();
        }
    }
}
