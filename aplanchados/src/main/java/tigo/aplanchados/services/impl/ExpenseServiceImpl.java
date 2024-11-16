package tigo.aplanchados.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tigo.aplanchados.model.Expense;
import tigo.aplanchados.repositories.ExpenseRepository;
import tigo.aplanchados.services.interfaces.ExpenseService;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository ExpenseService;


    @Override
    public List<Expense> findAllExpenses() {
        return ExpenseService.findAll();
    }

    @Override
    public Optional<Expense> findExpenseById(Long id) {
        return ExpenseService.findById(id);
    }


    @Override
    public Expense createExpense(Expense expense) {
        return ExpenseService.save(expense);
    }

    @Override
    public void deleteExpense(Long id) {
        ExpenseService.deleteById(id);
    }

    @Override
    public boolean updateExpense(Expense expense){
        Expense expenseToUpdate= ExpenseService.findById(expense.getId()).orElse(null);
        if(expenseToUpdate==null){
            return false;
        }
        expenseToUpdate.setId(expense.getId());
        expenseToUpdate.setDate(expense.getDate());
        expenseToUpdate.setExpenseConcept(expense.getExpenseConcept());
        expenseToUpdate.setPaymentMethod(expense.getPaymentMethod());
        expenseToUpdate.setAdditionalDetail(expense.getAdditionalDetail());
        expenseToUpdate.setPaymentType(expense.getPaymentType());
        expenseToUpdate.setPerson(expense.getPerson());
        expenseToUpdate.setUser(expense.getUser());
        expenseToUpdate.setValue(expense.getValue());
        ExpenseService.save(expenseToUpdate);
        return true;
        
    }

    @Override
    public List<Expense> findAllExpenseForDate(LocalDate date) {
        List<Expense> expenses = ExpenseService.findAll();
        List<Expense> expensesToday = new ArrayList<Expense>();
        for(Expense e: expenses){
            if(e.getDate()!=null)
                if(e.getDate().getDayOfYear() == date.getDayOfYear() && e.getDate().getYear()== date.getYear())
                expensesToday.add(e);
        }
        return expensesToday;
    }


    
}
