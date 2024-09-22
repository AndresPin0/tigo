package tigo.aplanchados.services.impl;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import tigo.aplanchados.model.Expense;
import tigo.aplanchados.model.Income;
import tigo.aplanchados.services.interfaces.ReportService;

import java.io.IOException;
import java.util.List;
import java.time.LocalDate;

@Service
public class ReportServiceImpl implements ReportService {

    /*@Autowired
    private ExpenseRepository expenseRepository;*/

    private ExpenseServiceImpl expenseServiceImpl;
    private IncomeServiceImpl incomeServiceImpl;


    @Override
    public void generateDailyExcel(HttpServletResponse response) {
     
        List<Expense> expense =  expenseServiceImpl.findAllExpenseForDate( LocalDate.now() );
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheetExpense = workbook.createSheet("Daily expenses");
        HSSFRow rowExpenses = sheetExpense.createRow(0);

        rowExpenses.createCell(0).setCellValue("ID");
        rowExpenses.createCell(1).setCellValue("Person");
        rowExpenses.createCell(2).setCellValue("User");
        rowExpenses.createCell(3).setCellValue("Value");
        rowExpenses.createCell(4).setCellValue("Payment Method");
        rowExpenses.createCell(5).setCellValue("Payment Type");
        rowExpenses.createCell(6).setCellValue("Expense Concept");
        rowExpenses.createCell(7).setCellValue("Date");
        rowExpenses.createCell(8).setCellValue("Additional Detail");

        int dataRowIndex = 1;

        for(Expense e : expense){
            HSSFRow dataRow = sheetExpense.createRow(dataRowIndex);
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

        List<Income> income =  incomeServiceImpl.findAllIncomesForDate( LocalDate.now() );
        HSSFSheet sheetIncome = workbook.createSheet("Daily incomes");
        HSSFRow rowIncome = sheetIncome.createRow(0);

        rowIncome.createCell(0).setCellValue("ID");
        rowIncome.createCell(1).setCellValue("Person");
        rowIncome.createCell(2).setCellValue("User");
        rowIncome.createCell(3).setCellValue("Value");
        rowIncome.createCell(4).setCellValue("Payment Method");
        rowIncome.createCell(5).setCellValue("Payment Type");
        rowIncome.createCell(6).setCellValue("income Concept");
        rowIncome.createCell(7).setCellValue("Date");
        rowIncome.createCell(8).setCellValue("Additional Detail");

        dataRowIndex = 1;

        for(Income e : income){
            HSSFRow dataRow = sheetIncome.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(e.getId());
            dataRow.createCell(1).setCellValue(e.getPerson().getName());
            dataRow.createCell(2).setCellValue(e.getUser().getName());
            dataRow.createCell(3).setCellValue(e.getValue());
            dataRow.createCell(4).setCellValue(e.getPaymentMethod().getCode());
            dataRow.createCell(5).setCellValue(e.getPaymentType().getCode());
            dataRow.createCell(6).setCellValue(e.getIncomeConcept().getDescription());
            dataRow.createCell(7).setCellValue(e.getDate().toString());
            dataRow.createCell(8).setCellValue(e.getAdditionalDetail());
            dataRowIndex++;
         }


        HSSFSheet sheetSummary = workbook.createSheet("Summary");
        HSSFRow rowSummary = sheetSummary.createRow(0);

        rowSummary.createCell(0).setCellValue("Initial");
        rowSummary.createCell(1).setCellValue("Final");
        rowSummary.createCell(2).setCellValue("Total incones");
        rowSummary.createCell(3).setCellValue("Total Expenses");
        rowSummary.createCell(4).setCellValue("Values to gerency");
        rowSummary.createCell(5).setCellValue("Total earnings");

        HSSFRow dataRow = sheetSummary.createRow(1);
        dataRow.createCell(0).setCellValue(calculateByDate(LocalDate.now().minusDays(1)));
        dataRow.createCell(1).setCellValue(calculateByDate(LocalDate.now()));
        dataRow.createCell(2).setCellValue(income.size());
        dataRow.createCell(3).setCellValue(expense.size());
        dataRow.createCell(4).setCellValue("");
        dataRow.createCell(5).setCellValue(calculateEarnings(LocalDate.now()));
    

        try (ServletOutputStream ops = response.getOutputStream()) {
            workbook.write(ops);
            ops.close();
            workbook.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        
    }


    private int calculateByDate(LocalDate date) {
        
        List<Income> income =  incomeServiceImpl.findAllIncomesForDate(  date );
        List<Expense> expense = expenseServiceImpl.findAllExpenseForDate( date );
        int total=0;
        for(Income e : income){
            total+=e.getValue();
        }

        for(Expense e : expense){
            total+=e.getValue();
        }

        return total;

    }

    private int calculateEarnings(LocalDate date){
        List<Income> income =  incomeServiceImpl.findAllIncomesForDate(  date );
        List<Expense> expense = expenseServiceImpl.findAllExpenseForDate( date );
        int total=0;
        for(Income e : income){
            total+=e.getValue();
        }

        for(Expense e : expense){
            total-=e.getValue();
        }

        return total;
    }




    
}
