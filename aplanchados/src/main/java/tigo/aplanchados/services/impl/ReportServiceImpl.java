package tigo.aplanchados.services.impl;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import tigo.aplanchados.model.Expense;
import tigo.aplanchados.model.Income;
import tigo.aplanchados.repositories.ExpenseRepository;
import tigo.aplanchados.repositories.IncomeRepository;
import tigo.aplanchados.services.interfaces.IncomeService;
import tigo.aplanchados.services.interfaces.ReportService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ExpenseRepository expenseRepository;

    private ExpenseServiceImpl expenseServiceImpl;


    @Override
    public void generateDailyExcel(HttpServletResponse response) {
     
        List<Expense> expenses =  expenseServiceImpl.findAllExpensesToday();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Daily expenses");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Person");
        row.createCell(2).setCellValue("User");
        row.createCell(3).setCellValue("Value");
        row.createCell(4).setCellValue("Payment Method");
        row.createCell(5).setCellValue("Payment Type");
        row.createCell(6).setCellValue("Expense Concept");
        row.createCell(7).setCellValue("Date");
        row.createCell(8).setCellValue("Additional Detail");

        int dataRowIndex = 1;

        for(Expense e : expenses){
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(e.getId());
            dataRow.createCell(1).setCellValue(e.getPerson().getName());
            dataRow.createCell(2).setCellValue(e.getUser().getName());
            dataRow.createCell(3).setCellValue(e.getValue());
            dataRow.createCell(4).setCellValue(e.getPaymentMethod().getCode());
            dataRow.createCell(5).setCellValue(e.getPaymentType().getCode());
            dataRow.createCell(6).setCellValue(e.getExpenseConcept().getDescription());
            dataRow.createCell(7).setCellValue(e.getDate().toString());
            dataRow.createCell(8).setCellValue(e.getAdditionalDetail());
            dataRowIndex++;
        }

        try (ServletOutputStream ops = response.getOutputStream()) {
            workbook.write(ops);
            ops.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        
    }




    
}
