package tigo.aplanchados.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import tigo.aplanchados.model.Expense;
import java.util.List;
import org.springframework.stereotype.Controller;
import tigo.aplanchados.services.interfaces.ExpenseService;


@Controller
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/create")
    public String addPage() {
        return "register-expense";
    }
    
    @PostMapping("/create")
    public ResponseEntity<Expense> createExpense(Expense expense) {
        Expense createdExpense = expenseService.createExpense(expense);
        return ResponseEntity.ok(createdExpense);
    }

    @GetMapping
    public List<Expense> getAllExpense() {
        return expenseService.findAllExpenses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(Long id) {
        return ResponseEntity.ok(expenseService.findExpenseById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok().build();
    }

    
}
