package pl.edu.agh.mwo.java.Reports;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import pl.edu.agh.mwo.java.DataModel.RecordEntry;
import pl.edu.agh.mwo.java.DataModel.RecordFilter;
import pl.edu.agh.mwo.java.Helpers.ReportFunctions;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Report2 extends ReportSimple {
    public Report2(ArrayList<RecordEntry> recordEntries, int Year){
        RecordFilter recordFilter = new RecordFilter(recordEntries);
        this.recordEntries =  recordFilter.byYear(Year);
        this.headerCol1 = "Projekt";
        this.headerCol2 = "Ilość godzin";
        this.reportName = "Raport2";
    }
    @Override
    public TreeMap<String, Double> getReport(){
        TreeMap<String, Double> retVal = new TreeMap();
        for(int i=0; i < this.recordEntries.size(); i++){
            if (retVal.containsKey(this.recordEntries.get(i).getProjectName())) {
                retVal.put(this.recordEntries.get(i).getProjectName(), retVal.get(this.recordEntries.get(i).getProjectName()) + this.recordEntries.get(i).getWorkingHours());
            }else{
                retVal.put(this.recordEntries.get(i).getProjectName(),this.recordEntries.get(i).getWorkingHours());
            }
        }
        return retVal;
    }

}
