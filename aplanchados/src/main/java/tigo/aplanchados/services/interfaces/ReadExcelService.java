package tigo.aplanchados.services.interfaces;


import java.io.IOException;

import java.io.File;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadExcelService {

    void ReadExcel(File file) throws InvalidFormatException, IOException;
}