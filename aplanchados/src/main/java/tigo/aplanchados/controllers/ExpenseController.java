package tigo.aplanchados.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import tigo.aplanchados.dtos.ExpenseDTO;
import tigo.aplanchados.mappers.ExpenseMapper;
import tigo.aplanchados.model.Expense;
import tigo.aplanchados.model.PaymentMethod;
import tigo.aplanchados.model.PaymentType;
import tigo.aplanchados.model.Person;
import tigo.aplanchados.model.User;
import tigo.aplanchados.repositories.PaymentMethodRepository;
import tigo.aplanchados.repositories.PaymentTypeRepository;
import tigo.aplanchados.repositories.PersonRepository;
import tigo.aplanchados.services.interfaces.ExpenseService;
import tigo.aplanchados.repositories.UserRepository;
import tigo.aplanchados.repositories.ExpenseConceptRepository;
import tigo.aplanchados.model.ExpenseConcept;

import java.time.LocalDateTime;
import java.util.List;

@RestController
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

    @Autowired
    private ExpenseConceptRepository expenseConceptRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO) {
        System.out.println("Creating expense with person: " + expenseDTO.getPersonDocumentNumber());
        System.out.println("Fecha recibida: " + expenseDTO.getDate());

        // Convert DTO to entity and set current date
        Expense expense = ExpenseMapper.INSTANCE.toEntity(expenseDTO);
        expense.setDate(LocalDateTime.now());

        // Retrieve related entities and set to expense
        Person person = personRepository.findAll().stream()
            .filter(p -> p.getPersonPK().getDocumentNumber().equals(expenseDTO.getPersonDocumentNumber()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        expense.setPerson(person);

        PaymentMethod paymentMethod = paymentMethodRepository.findById(expenseDTO.getPaymentMethodCode()).orElse(null);
        PaymentType paymentType = paymentTypeRepository.findById(expenseDTO.getPaymentTypeCode()).orElse(null);
        expense.setPaymentMethod(paymentMethod);
        expense.setPaymentType(paymentType);

        ExpenseConcept expenseConcept = expenseConceptRepository.findById(expenseDTO.getExpenseConceptCode()).orElse(null);
        expense.setExpenseConcept(expenseConcept);

        User user = userRepository.findById(expenseDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        expense.setUser(user);

        // Save expense and convert to DTO for response
        Expense createdExpense = expenseService.createExpense(expense);
        ExpenseDTO createdExpenseDTO = ExpenseMapper.INSTANCE.toDTO(createdExpense);

        return ResponseEntity.ok(createdExpenseDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        List<Expense> expenses = expenseService.findAllExpenses();
        List<ExpenseDTO> expenseDTOs = ExpenseMapper.INSTANCE.toDTOs(expenses);
        return ResponseEntity.ok(expenseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable("id") Long id) {
        Expense expense = expenseService.findExpenseById(id)
            .orElseThrow(() -> new RuntimeException("Expense not found"));
        ExpenseDTO expenseDTO = ExpenseMapper.INSTANCE.toDTO(expense);
        return ResponseEntity.ok(expenseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok().build();
    }
}
