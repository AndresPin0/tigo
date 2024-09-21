package tigo.aplanchados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
