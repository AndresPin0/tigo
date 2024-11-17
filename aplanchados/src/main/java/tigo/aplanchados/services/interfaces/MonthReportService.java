package tigo.aplanchados.services.interfaces;

import java.time.Month;
import java.util.Map;

public interface MonthReportService {
    Map<String, Object> getMonthlyReport(Month month);
}
