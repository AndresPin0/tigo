package tigo.aplanchados.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tigo.aplanchados.model.Expense;
import tigo.aplanchados.model.Income;
import tigo.aplanchados.repositories.IncomeRepository;
import tigo.aplanchados.services.interfaces.ExpenseService;
import tigo.aplanchados.services.interfaces.IncomeService;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.time.LocalDate;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;


    @Override
    public List<Income> findAllIncomes() {
        return incomeRepository.findAll();
    }

    @Override
    public Optional<Income> findIncomeById(Long id) {
        return incomeRepository.findById(id);
    }


    @Override
    public Income createIncome(Income income) {
        return incomeRepository.save(income);
    }

    @Override
    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }

    @Override
    public boolean updateIncome(Income income){
        Income incomeToUpdate= incomeRepository.findById(income.getId()).orElse(null);
        if(incomeToUpdate==null){
            return false;
        }
        incomeToUpdate.setId(income.getId());
        incomeToUpdate.setDate(income.getDate());
        incomeToUpdate.setIncomeConcept(income.getIncomeConcept());
        incomeToUpdate.setPaymentMethod(income.getPaymentMethod());
        incomeToUpdate.setAdditionalDetail(income.getAdditionalDetail());
        incomeToUpdate.setPaymentType(income.getPaymentType());
        incomeToUpdate.setPerson(income.getPerson());
        incomeToUpdate.setUser(income.getUser());
        incomeToUpdate.setValue(income.getValue());
        incomeRepository.save(incomeToUpdate);
        return true;
        
    }

     @Override
    public List<Income> findAllIncomesForDate(LocalDate date) {
        List<Income> income = incomeRepository.findAll();
        List<Income> incomeByDate= new ArrayList<Income>();
        for(Income e: income){
            if (e.getDate()!=null) 
                if(e.getDate().getDayOfYear() == date.getDayOfYear() && e.getDate().getYear()== date.getYear())
                incomeByDate.add(e);
        }
        return incomeByDate;
    }

    public List<Income> findAllIncomesByMonth(int month) {
        List<Income> allIncomes = incomeRepository.findAll();
        List<Income> monthlyIncomes = new ArrayList<>();

        for (Income income : allIncomes) {
            if (income.getDate() != null && income.getDate().getMonthValue() == month) {
                monthlyIncomes.add(income);
            }
        }

        return monthlyIncomes;
    }


    
}
