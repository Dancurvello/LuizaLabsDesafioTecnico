package br.com.serverLogistica.serverLogistic.controller;

import br.com.serverLogistica.serverLogistic.config.DataBaseConfig;
import br.com.serverLogistica.serverLogistic.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class FileUploadController {
    private static final Logger LOG = Logger.getLogger(FileUploadController.class.getName());
    private final FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        return fileUploadService.processFile(file);
    }

    @GetMapping("/response")
    public ResponseEntity<String> getJsonData() {
        try {
            // Chama o método que obtém os dados do banco de dados
            String jsonData = DataBaseConfig.allData();

            // Retorna os dados em formato JSON como resposta
            return ResponseEntity.ok(jsonData);
        } catch (Exception e) {
            LOG.severe("Erro ao obter dados do banco de dados: " + e.getMessage());
            return ResponseEntity.status(500).body("Erro ao obter dados do banco de dados");
        }
    }
}
