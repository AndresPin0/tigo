package tigo.aplanchados.controllers;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tigo.aplanchados.services.interfaces.ReadExcelService;

@RestController
@RequestMapping("/read-excel")
public class ExcelUploadController {

    @Autowired
    private ReadExcelService readExcelService;

    @PostMapping
    public ResponseEntity<String> uploadExcelFile(MultipartFile file) {
        try {
            // Convertir MultipartFile a File
            File tempFile = File.createTempFile("uploaded", ".xls");
            file.transferTo(tempFile);

            // Leer el archivo Excel
            readExcelService.ReadExcel(tempFile);

            // Eliminar el archivo temporal después de procesarlo
            tempFile.delete();

            return ResponseEntity.ok("Archivo procesado exitosamente.");
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar el archivo: " + e.getMessage());
        }
    }
}
