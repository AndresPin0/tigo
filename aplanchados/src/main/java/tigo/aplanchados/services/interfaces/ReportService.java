package tigo.aplanchados.services.interfaces;


import java.io.IOException;

import org.springframework.stereotype.Repository;

import jakarta.servlet.http.HttpServletResponse;

@Repository
public interface ReportService {


    void generateDailyExcel(HttpServletResponse response) throws IOException;

}
