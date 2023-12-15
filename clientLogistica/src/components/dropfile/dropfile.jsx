import { useState } from "react";
import "./dropfile.css";

// eslint-disable-next-line react/prop-types
const FileUploader = ({ onFileUpload }) => {
  const [selectedFile, setSelectedFile] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    setSelectedFile(file);

    if (file && !file.name.toLowerCase().endsWith(".txt")) {
      setError("Por favor, escolha um arquivo .txt válido.");
    } else {
      setError("");
    }

    console.log("Arquivo selecionado:", file);
  };

  const isFileValid = () => {
    const isValid =
      selectedFile && selectedFile.name.toLowerCase().endsWith(".txt");
    console.log("Arquivo é válido?", isValid);
    return isValid;
  };

  const handleUpload = async () => {
    if (isFileValid()) {
      setLoading(true);

      const formData = new FormData();
      formData.append("file", selectedFile);

      try {
        const response = await fetch("http://localhost:8080/api/upload", {
          method: "POST",
          body: formData,
        });

        if (!response.ok) {
          throw new Error(
            `Erro ao fazer o upload: ${response.status} - ${response.statusText}`
          );
        }

        const data = await response.text();
        console.log("Resposta do servidor:", data);
        onFileUpload(data);

        window.alert("Sucesso! Vá para a consulta de dados no menu.");
      } catch (error) {
        console.error("Erro ao fazer o upload:", error.message);
        setError(`Erro ao fazer o upload: ${error.message}`);
      } finally {
        setLoading(false);
      }
    }
  };

  return (
    <div className="file-uploader-container">
      <h1>Suba seus arquivos</h1>

      {error && <p className="error-message">{error}</p>}

      <div className="file-input-container">
        <label className="custom-file-input" htmlFor="uploadInput">
          Escolher arquivo
        </label>
        <input
          type="file"
          id="uploadInput"
          onChange={handleFileChange}
          className="file-input"
        />
        <span className="file-info">
          {selectedFile ? selectedFile.name : "Nenhum arquivo escolhido"}
        </span>
      </div>
      <div className="button-container">
        <button
          onClick={handleUpload}
          className="upload-button"
          disabled={!isFileValid() || loading}
        >
          {loading ? "Carregando..." : "Upload"}
        </button>
      </div>
    </div>
  );
};

export default FileUploader;
