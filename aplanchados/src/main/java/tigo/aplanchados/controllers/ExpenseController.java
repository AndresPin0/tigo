package tigo.aplanchados.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import tigo.aplanchados.model.Expense;
import tigo.aplanchados.model.PaymentMethod;
import tigo.aplanchados.model.PaymentType;
import tigo.aplanchados.model.Person;
import tigo.aplanchados.repositories.PaymentMethodRepository;
import tigo.aplanchados.repositories.PaymentTypeRepository;
import tigo.aplanchados.repositories.PersonRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Controller;
import tigo.aplanchados.services.interfaces.ExpenseService;


@Controller
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @GetMapping("/create")
    public String addPage() {
        return "register-expense";
    }

    @PostMapping("/create")
    public ResponseEntity<Expense> createExpense(@RequestParam String personID, @RequestParam String paymentMet, @RequestParam String paymentTyp,Expense expense) {
        Person person = new Person();
        LocalDateTime dateTime = LocalDateTime.now();
        for(Person p : personRepository.findAll()){
        if (p.getPersonPK().getDocumentNumber().equals(personID)) {
            person = p;
        }}
        expense.setPerson(person);
        expense.setDate(dateTime);
        PaymentMethod paymentMethod = paymentMethodRepository.findById(Long.valueOf(paymentMet)).orElse(null);
        PaymentType paymentType = paymentTypeRepository.findById(Long.valueOf(paymentTyp)).orElse(null);
        expense.setPaymentMethod(paymentMethod);
        expense.setPaymentType(paymentType);
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