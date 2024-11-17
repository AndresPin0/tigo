package tigo.aplanchados.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import tigo.aplanchados.dtos.IncomeDTO;
import tigo.aplanchados.mappers.IncomeMapper;
import tigo.aplanchados.model.*;
import tigo.aplanchados.repositories.*;
import tigo.aplanchados.services.impl.DocumentTypeServiceImpl;
import tigo.aplanchados.services.interfaces.IncomeService;

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

    @Autowired
    private DocumentTypeServiceImpl documentTypeServiceImpl;

    @Autowired
    private IncomeRepository incomeRepository;

    @PostMapping("/create")
    public ResponseEntity<IncomeDTO> createIncome(@RequestBody IncomeDTO incomeDTO) {
        System.out.println("Creating income: with concept: " + incomeDTO.getIncomeConceptCode() + " and person: " + incomeDTO.getPersonDocumentNumber());


        Income income = IncomeMapper.INSTANCE.toEntity(incomeDTO);
        income.setDate(LocalDateTime.now());

        DocumentType documentType = documentTypeServiceImpl.getDefaultDocumentType();
        PersonPK personPK = new PersonPK();
        personPK.setDocumentNumber(incomeDTO.getPersonDocumentNumber());
        personPK.setDocumentType(documentType);

        // Retrieve Person from the database
        Person person = personRepository.findById(personPK).orElseGet(() -> {
            Person newPerson = new Person();
            newPerson.setPersonPK(personPK);
            newPerson.setName(incomeDTO.getPersonName());
            return personRepository.save(newPerson);
        });

        income.setPerson(person);

        // Retrieve PaymentMethod and PaymentType the database
        PaymentMethod paymentMethod = paymentMethodRepository.findById(incomeDTO.getPaymentMethodCode())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));

        PaymentType paymentType = paymentTypeRepository.findById(incomeDTO.getPaymentTypeCode())
                .orElseThrow(() -> new RuntimeException("Tipo de pago no encontrado"));

        IncomeConcept incomeConcept = incomeConceptRepository.findById(incomeDTO.getIncomeConceptCode())
                .orElseThrow(() -> new RuntimeException("Concepto de egreso no encontrado"));

        User user = userRepository.findById(incomeDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        income.setPaymentMethod(paymentMethod);
        income.setPaymentType(paymentType);
        income.setIncomeConcept(incomeConcept);
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