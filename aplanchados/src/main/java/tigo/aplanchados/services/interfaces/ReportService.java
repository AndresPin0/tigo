package tigo.aplanchados.services.interfaces;

import tigo.aplanchados.model.Expense;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Repository
public interface ReportService {


    void generateDailyExcel(HttpServletResponse response);

}
