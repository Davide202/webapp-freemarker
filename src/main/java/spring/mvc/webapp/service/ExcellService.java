package spring.mvc.webapp.service;


import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import spring.mvc.webapp.model.DatiExcell;
import spring.mvc.webapp.model.RigaExcellComuni;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class ExcellService {

    private static String filePath = "src/main/resources/Comuni.xlsx";
    private static DatiExcell datiExcell = null;

    public static DatiExcell readFileExcell() throws IOException {

        if (datiExcell != null) return datiExcell;

        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        DatiExcell excell = new DatiExcell();
        for (Row row : sheet) {
            String regione = row.getCell(0).getStringCellValue();
            String provincia = row.getCell(1).getStringCellValue();
            String comune = row.getCell(2).getStringCellValue();
//            log.info(" regione: {} - provincia {} - comune {} ",regione,provincia,comune);
            excell.addRiga(RigaExcellComuni.of(regione, provincia, comune));

        }
        datiExcell = excell;
        return excell;

    }

    public static List<String> regioni() throws IOException {
        List<String> result = new ArrayList<>();
        DatiExcell dati = datiExcell != null ? datiExcell : readFileExcell();
        dati.getRigaExcellComuniList().forEach(rigaExcellComuni -> {
            String regione = rigaExcellComuni.getRegione();
            if (!result.contains(regione)) result.add(regione);
        });
        return result;
    }
    public static List<String> provincie(String regione) throws IOException {
        List<String> result = new ArrayList<>();
        DatiExcell dati = datiExcell != null ? datiExcell : readFileExcell();
        dati.getRigaExcellComuniList().forEach(rigaExcellComuni -> {
            String r = rigaExcellComuni.getRegione();
            if (r.equals(regione)){
                String provincia = rigaExcellComuni.getProvincia();
                if (!result.contains(provincia)) result.add(provincia);
            }
        });
        return result;
    }
    public static List<String> comuni(String provincia) throws IOException {
        List<String> result = new ArrayList<>();
        DatiExcell dati = datiExcell != null ? datiExcell : readFileExcell();
        dati.getRigaExcellComuniList().forEach(rigaExcellComuni -> {
            String p = rigaExcellComuni.getProvincia();
            if (p.equals(provincia)){
                String comune = rigaExcellComuni.getProvincia();
                if (!result.contains(comune)) result.add(comune);
            }
        });
        return result;
    }
}
