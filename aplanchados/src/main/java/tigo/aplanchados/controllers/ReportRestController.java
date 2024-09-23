package tigo.aplanchados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;

import jakarta.servlet.http.HttpServletResponse;
import tigo.aplanchados.services.interfaces.ReadExcelService;
import tigo.aplanchados.services.interfaces.ReportService;
import java.time.LocalDate;

@RestController
public class ReportRestController {
    
    @Autowired
    private ReportService reportService;

    @Autowired
    private ReadExcelService readService;

    @GetMapping("/excel")
    public void generateExcelReport(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");
     
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=dailyReport"+ LocalDate.now() +".xls";

        response.setHeader(headerKey, headerValue);

        reportService.generateDailyExcel(response);
    }

     @GetMapping("/upload-excel")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            File fileConv = new File("src/main/resources/targetFile.tmp");
            OutputStream os = new FileOutputStream(fileConv);
            os.write(file.getBytes());
            readService.ReadExcel(fileConv);
            os.close();
            return ResponseEntity.ok("File uploaded and processed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("File upload failed");
        }
    }
}
