package tigo.aplanchados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tigo.aplanchados.services.interfaces.MonthReportService;

import java.time.Month;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class MonthReportController {

    @Autowired
    private MonthReportService monthReportService;

    @GetMapping("/monthly")
    public Map<String, Object> getMonthlyReport(@RequestParam("month") int monthNumber) {
        try {
            // Convertir el número del mes (1-12) a java.time.Month
            if (monthNumber < 1 || monthNumber > 12) {
                throw new IllegalArgumentException("Número de mes inválido. Debe estar entre 1 y 12.");
            }
            Month month = Month.of(monthNumber);
            return monthReportService.getMonthlyReport(month);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error procesando el reporte: " + e.getMessage());
        }
    }
}
