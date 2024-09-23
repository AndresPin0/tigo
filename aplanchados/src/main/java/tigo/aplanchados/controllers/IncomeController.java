package tigo.aplanchados.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import tigo.aplanchados.model.Income;
import java.util.List;
import org.springframework.stereotype.Controller;
import tigo.aplanchados.services.interfaces.IncomeService;


@Controller
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping("/create")
    public String addPage() {
        return "register-income";
    }
    
    @PostMapping("/create")
    public ResponseEntity<Income> createIncome(Income income) {
        Income createdIncome = incomeService.createIncome(income);
        return ResponseEntity.ok(createdIncome);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Income>> getAllIncomes() {
        List<Income> incomes = incomeService.findAllIncomes();
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncomeById(Long id) {
        return ResponseEntity.ok(incomeService.findIncomeById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.ok().build();
    }

    
}
