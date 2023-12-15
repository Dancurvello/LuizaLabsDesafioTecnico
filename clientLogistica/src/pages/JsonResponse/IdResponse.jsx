import { useState, useEffect } from "react";
import "./IdResponse.css";

const IdResponse = () => {
  const [responseData, setResponseData] = useState(null);
  const [idFilter, setIdFilter] = useState("");
  const [filteredData, setFilteredData] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/response");
        const data = await response.json();
        setResponseData(data);
      } catch (error) {
        console.error("Erro ao buscar dados da API:", error);
      }
    };

    fetchData();
  }, []);

  const handleIdFilterChange = (event) => {
    setIdFilter(event.target.value);
  };

  const filterDataById = () => {
    if (!responseData) return;

    const filteredUser = responseData.find(
      (user) => user.user_id.toString() === idFilter
    );

    setFilteredData(filteredUser ? [filteredUser] : []);
  };

  return (
    <div className="json-response-container">
      <h2 className="poppins-font">Dados da API:</h2>

      <div className="json-respons-filter">
        <label htmlFor="idFilter">Filtrar por ID:</label>
        <input
          type="text"
          id="idFilter"
          value={idFilter}
          onChange={handleIdFilterChange}
        />
        <button className="poppins-font" onClick={filterDataById}>
          Filtrar
        </button>
      </div>

      <div>
        <h3 className="poppins-font">Resultado Filtrado:</h3>
        <pre>{JSON.stringify(filteredData, null, 2)}</pre>
      </div>
    </div>
  );
};

export default IdResponse;
