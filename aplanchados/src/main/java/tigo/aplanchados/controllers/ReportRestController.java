package tigo.aplanchados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import tigo.aplanchados.services.interfaces.ReportService;
import java.time.LocalDate;

@RestController
public class ReportRestController {
    
    @Autowired
    private ReportService reportService;

    @GetMapping("/excel")
    public void generateExcelReport(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");
     
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=dailyReport"+ LocalDate.now() +".xls";

        response.setHeader(headerKey, headerValue);

        reportService.generateDailyExcel(response);
    }

     @PostMapping("/upload-excel")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            // Call the ReplaceForName method to handle the Excel reading logic
            // reportService.ReplaceForMethodName(file);
            return ResponseEntity.ok("File uploaded and processed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("File upload failed");
        }
    }
}
