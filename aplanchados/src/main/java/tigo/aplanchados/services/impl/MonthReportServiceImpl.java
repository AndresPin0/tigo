package tigo.aplanchados.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tigo.aplanchados.model.Expense;
import tigo.aplanchados.model.Income;
import tigo.aplanchados.services.interfaces.ExpenseService;
import tigo.aplanchados.services.interfaces.IncomeService;
import tigo.aplanchados.services.interfaces.MonthReportService;

import java.time.Month;
import java.util.*;

@Service
public class MonthReportServiceImpl implements MonthReportService {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private IncomeService incomeService;

    @Override
    public Map<String, Object> getMonthlyReport(Month month) {
        // Filtrar ingresos y gastos para el mes dado
        List<Income> monthlyIncomes = incomeService.findAllIncomesByMonth(month.getValue());
        List<Expense> monthlyExpenses = expenseService.findAllExpensesByMonth(month.getValue());

        // Calcular totales
        double totalIncome = monthlyIncomes.stream()
                .mapToDouble(Income::getValue)
                .sum();

        double totalExpense = monthlyExpenses.stream()
                .mapToDouble(Expense::getValue)
                .sum();

        // Preparar respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("month", month.name());
        response.put("totalIncome", totalIncome);
        response.put("totalExpense", totalExpense);

        return response;
    }
}
