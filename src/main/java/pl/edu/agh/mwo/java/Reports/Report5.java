package pl.edu.agh.mwo.java.Reports;

import pl.edu.agh.mwo.java.DataModel.RecordEntry;
import pl.edu.agh.mwo.java.DataModel.RecordFilter;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Report5 extends ReportSimple {
    private ArrayList<RecordEntry> recordEntries;
    public Report5(ArrayList<RecordEntry> recordEntries, String projectName){
        RecordFilter recordFilter = new RecordFilter(recordEntries);
        this.recordEntries =  recordFilter.byProject(projectName);
        this.headerCol1 = "Projekt";
        this.headerCol2 = "Ilość godzin";
        this.reportName = "Raport5";
    }

    @Override
    public TreeMap<String, Double> getReport(){
        TreeMap<String, Double> retVal = new TreeMap();
        for(int i=0; i < recordEntries.size(); i++){
        	if (retVal.containsKey(recordEntries.get(i).getWorkerName())) {
                retVal.put(recordEntries.get(i).getWorkerName(), retVal.get(recordEntries.get(i).getWorkerName()) + recordEntries.get(i).getWorkingHours());
            }else{
                retVal.put(recordEntries.get(i).getWorkerName(),recordEntries.get(i).getWorkingHours());
            }
        }
        return retVal;
    }

    public boolean printOnConsoleReport5(){
 
    	
    	int lp = 0; double sum = 0.0;
        TreeMap<String, Double> reportGet = getReport();
        if(recordEntries.size() > 0) {
        	System.out.println(" ______________________________________________________");       	
        	System.out.printf("%5s %21s %25s", "| Lp.   ", "|     Imie i nazwisko", "| Liczba godzin |");
        	System.out.println("\n|________|_____________________________|_______________|");
            for (Map.Entry<String, Double> entry : reportGet.entrySet()) {
                String key = entry.getKey();
                Double value = entry.getValue();
                lp++;
                sum += value;
                System.out.printf("%5d %24s %13s %.2f %1s", lp,key,"",value," h ");
                System.out.println("\n|________|_____________________________|_______________|");
            }
            System.out.printf("%7s %35s %.2f %1s ","Suma: ","", sum, "h"," |");
            System.out.println("\n|______________________________________________________|");
            return true;
        }else{
            System.out.println("Projekt o podanej nazwie nie istnieje. Sprawdź, czy wpisaleś prawidłowa nazwe!");
        }return false;
    }

}
