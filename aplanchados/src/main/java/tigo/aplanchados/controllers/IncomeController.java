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
    private IncomeService IncomeService;

    @GetMapping("/create")
    public String addPage() {
        return "register-income";
    }
    
    @PostMapping("/create")
    public ResponseEntity<Income> createIncome(Income income) {
        Income createdIncome = IncomeService.createIncome(income);
        return ResponseEntity.ok(createdIncome);
    }

    @GetMapping
    public List<Income> getAllIncomes() {
        return IncomeService.findAllIncomes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncomeById(Long id) {
        return ResponseEntity.ok(IncomeService.findIncomeById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(Long id) {
        IncomeService.deleteIncome(id);
        return ResponseEntity.ok().build();
    }

    
}
