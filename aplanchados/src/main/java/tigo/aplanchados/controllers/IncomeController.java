package tigo.aplanchados.controllers;

import org.springframework.web.bind.annotation.*;
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
import tigo.aplanchados.repositories.UserRepository;
import tigo.aplanchados.services.interfaces.IncomeService;
import tigo.aplanchados.model.IncomeConcept;
import tigo.aplanchados.repositories.IncomeConceptRepository;
import tigo.aplanchados.model.User;

import java.time.LocalDateTime;
import java.util.List;

@RestController
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

    @Autowired
    private IncomeConceptRepository incomeConceptRepository;

    @Autowired
    private UserRepository userRepository; 

    @PostMapping("/create")
    public ResponseEntity<IncomeDTO> createIncome(@RequestBody IncomeDTO incomeDTO) {
        System.out.println("Creating income: with concept: " + incomeDTO.getIncomeConceptCode() + " and person: " + incomeDTO.getPersonDocumentNumber());

        Income income = IncomeMapper.INSTANCE.toEntity(incomeDTO);
        income.setDate(LocalDateTime.now());

        // Retrieve Person from the database
        Person person = personRepository.findAll().stream()
            .filter(p -> p.getPersonPK().getDocumentNumber().equals(incomeDTO.getPersonDocumentNumber()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        income.setPerson(person);

        // Retrieve PaymentMethod and PaymentType the database
        PaymentMethod paymentMethod = paymentMethodRepository.findById(incomeDTO.getPaymentMethodCode()).orElse(null);
        PaymentType paymentType = paymentTypeRepository.findById(incomeDTO.getPaymentTypeCode()).orElse(null);
        income.setPaymentMethod(paymentMethod);
        income.setPaymentType(paymentType);

        // Retrieve IncomeConcept from the database
        IncomeConcept incomeConcept = incomeConceptRepository.findById(incomeDTO.getIncomeConceptCode()).orElse(null);
        income.setIncomeConcept(incomeConcept);

        // Retrieve User from the database
        User user = userRepository.findById(incomeDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        income.setUser(user);

        // Save Income entity
        Income createdIncome = incomeService.createIncome(income);
        IncomeDTO createdIncomeDTO = IncomeMapper.INSTANCE.toDTO(createdIncome);

        return ResponseEntity.ok(createdIncomeDTO);
    }


    @GetMapping("/all")
    public ResponseEntity<List<IncomeDTO>> getAllIncomes() {
        List<Income> incomes = incomeService.findAllIncomes();
        List<IncomeDTO> incomeDTOs = IncomeMapper.INSTANCE.toDTOs(incomes);
        return ResponseEntity.ok(incomeDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeDTO> getIncomeById(@PathVariable("id") Long id) {
        Income income = incomeService.findIncomeById(id)
            .orElseThrow(() -> new RuntimeException("Ingreso no encontrado"));
        IncomeDTO incomeDTO = IncomeMapper.INSTANCE.toDTO(income);
        return ResponseEntity.ok(incomeDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.ok().build();
    }
}
