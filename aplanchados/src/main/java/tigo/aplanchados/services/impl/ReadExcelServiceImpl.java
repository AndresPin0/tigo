package tigo.aplanchados.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tigo.aplanchados.model.Expense;
import tigo.aplanchados.model.ExpenseConcept;
import tigo.aplanchados.model.Income;
import tigo.aplanchados.model.IncomeConcept;
import tigo.aplanchados.model.PaymentMethod;
import tigo.aplanchados.model.PaymentType;
import tigo.aplanchados.model.Person;
import tigo.aplanchados.model.User;
import tigo.aplanchados.repositories.ExpenseConceptRepository;
import tigo.aplanchados.repositories.ExpenseRepository;
import tigo.aplanchados.repositories.IncomeConceptRepository;
import tigo.aplanchados.repositories.IncomeRepository;
import tigo.aplanchados.repositories.PaymentMethodRepository;
import tigo.aplanchados.repositories.PaymentTypeRepository;
import tigo.aplanchados.repositories.PersonRepository;
import tigo.aplanchados.repositories.UserRepository;
import tigo.aplanchados.services.interfaces.ReadExcelService;

@Service
public class ReadExcelServiceImpl implements ReadExcelService {

        @Autowired
        private IncomeRepository incomeRepository;

        @Autowired
        private ExpenseRepository expenseRepository;

        @Autowired
        private PersonRepository personRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PaymentMethodRepository paymentMethodRepository;

        @Autowired
        private PaymentTypeRepository paymentTypeRepository;

        @Autowired
        private ExpenseConceptRepository expenseConceptRepository;

        @Autowired
        private IncomeConceptRepository incomeConceptRepository;

    @Override
    public void ReadExcel(File file) throws IOException, InvalidFormatException{

        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int numRows = sheet.getPhysicalNumberOfRows();
            
            for (int i = 1; i < numRows; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row==null) {
                    break;
                }
                String id = getCellValue(row.getCell(0));
                String person = getCellValue(row.getCell(1));
                String user = getCellValue(row.getCell(2));
                String value = getCellValue(row.getCell(3));
                String paymMet = getCellValue(row.getCell(4));  
                String paymMTyp = getCellValue(row.getCell(5));
                String concept = getCellValue(row.getCell(6));
                String date = getCellValue(row.getCell(7));
                String additionalDetail = getCellValue(row.getCell(8));
                String type = getCellValue(row.getCell(9));
                
                saveData(id,person,user,value,paymMet,paymMTyp,concept,date,additionalDetail,type);
            }
            
            workbook.close();
            fis.close();
        }
        
           private void saveData(String idStr, String personStr, String userStr, String valueStr, String paymMetStr, String paymMTypStr,
            String conceptStr, String dateStr, String additionalDetailStr, String type) {

                LocalDateTime date = null;
                try {
                    if (dateStr.contains(".")) {
                        double excelSerial = Double.parseDouble(dateStr);
                        date = convertExcelSerialToDate(excelSerial); // Convierte el número serial de Excel
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        date = LocalDateTime.parse(dateStr, formatter); // Si es una fecha en formato estándar
                    }
                } catch (Exception e) {
                    e.printStackTrace();  // Maneja cualquier error en la conversión
                }
                
                Long id = parseLongFromString(idStr);
                List<Person> persons = personRepository.findAll();
                Person person = new Person();
                for(Person p : persons)
                if (p.getName().equals(personStr)) {
                    person = p;
                }
                User user = userRepository.findById(parseLongFromString(userStr)).orElse(null);
                Integer value = parseIntegerFromString(valueStr);
                PaymentMethod paymentMethod = paymentMethodRepository.findById(parseLongFromString(paymMetStr)).orElse(null);
                PaymentType paymentType = paymentTypeRepository.findById(parseLongFromString(paymMetStr)).orElse(null);
                
                String additional = additionalDetailStr;
                
                if ( type.equals("EXPENSE")) {
                    ExpenseConcept concept = expenseConceptRepository.findById(parseLongFromString(conceptStr)).orElse(null);
                    expenseRepository.save(new Expense(id,value,additional,person,date,paymentMethod,paymentType,concept,user));
    
                }else if ( type.equals("INCOME")){
                    IncomeConcept concept = incomeConceptRepository.findById(parseLongFromString(conceptStr)).orElse(null);
                    incomeRepository.save(new Income(id,value,additional,person,date,paymentMethod,paymentType,concept,user));
                }
                
            
            }
        private String getCellValue(XSSFCell cell) {
                switch (cell.getCellType()) {
                    case NUMERIC:
                        return String.valueOf(cell.getNumericCellValue());
                    case BOOLEAN:
                        return String.valueOf(cell.getBooleanCellValue());
                    case STRING:
                        return cell.getStringCellValue();
                    default:
                        return cell.getStringCellValue();
                }
            }
            private Long parseLongFromString(String value) {
                if (value.contains(".")) {
                    value = value.substring(0, value.indexOf(".")); // Elimina la parte decimal
                }
                return Long.valueOf(value);
            }
            private Integer parseIntegerFromString(String value) {
                if (value.contains(".")) {
                    value = value.substring(0, value.indexOf(".")); // Elimina la parte decimal
                }
                return Integer.valueOf(value);
            }

            private LocalDateTime convertExcelSerialToDate(double serial) {
                // El valor 25569 es el número de días entre el 1 de enero de 1970 (epoch Unix) y el 1 de enero de 1900
                long daysSince1900 = (long) serial - 25569;
                // Obtener la fecha a partir del número de días
                return LocalDateTime.ofEpochSecond(daysSince1900 * 86400, 0, java.time.ZoneOffset.UTC);
            }
            
            
            
        }