package pl.edu.agh.mwo.java.Reports;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import pl.edu.agh.mwo.java.DataModel.RecordEntry;
import pl.edu.agh.mwo.java.DataModel.RecordFilter;
import pl.edu.agh.mwo.java.Helpers.ReportFunctions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Report1 extends ReportSimple {
    public Report1(ArrayList<RecordEntry> recordEntries, int Year){
        RecordFilter recordFilter = new RecordFilter(recordEntries);
        this.recordEntries =  recordFilter.byYear(Year);
        this.headerCol1 = "Pracownik";
        this.headerCol2 = "Ilość godzin [h]";
        this.reportName = "Raport1";
    }

}
