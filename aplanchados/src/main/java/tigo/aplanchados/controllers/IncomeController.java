package tigo.aplanchados.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import tigo.aplanchados.dtos.IncomeDTO;
import tigo.aplanchados.mappers.IncomeMapper;
import tigo.aplanchados.model.Income;
import tigo.aplanchados.model.PaymentMethod;
import tigo.aplanchados.model.PaymentType;
import tigo.aplanchados.model.Person;
import tigo.aplanchados.repositories.PaymentMethodRepository;
import tigo.aplanchados.repositories.PaymentTypeRepository;
import tigo.aplanchados.repositories.PersonRepository;

import java.util.List;
import org.springframework.stereotype.Controller;
import tigo.aplanchados.services.interfaces.IncomeService;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @GetMapping("/create")
    public String addPage() {
        return "income";
    }
    
    @PostMapping("/create")
    public ResponseEntity<Income> createIncome(@RequestParam String personID, @RequestParam String paymentMet, @RequestParam String paymentTyp,Income income) {
        Person person = new Person();
        LocalDateTime dateTime = LocalDateTime.now();
        income.setDate(dateTime);
        PaymentMethod paymentMethod = paymentMethodRepository.findById(Long.valueOf(paymentMet)).orElse(null);
        PaymentType paymentType = paymentTypeRepository.findById(Long.valueOf(paymentTyp)).orElse(null);
        income.setPaymentMethod(paymentMethod);
        income.setPaymentType(paymentType);
        for(Person p : personRepository.findAll()){
            if (p.getPersonPK().getDocumentNumber().equals(personID)) {
                person = p;
        }}
        income.setPerson(person);
        Income createdIncome = incomeService.createIncome(income);
        return ResponseEntity.ok(createdIncome);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Income>> getAllIncomes() {
        List<Income> incomes = incomeService.findAllIncomes();
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeDTO> getIncomeById(Long id) {
        IncomeDTO incomeDTO = IncomeMapper.INSTANCE.toDTO(incomeService.findIncomeById(id).get());
        return ResponseEntity.ok(incomeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.ok().build();
    }

    
}
