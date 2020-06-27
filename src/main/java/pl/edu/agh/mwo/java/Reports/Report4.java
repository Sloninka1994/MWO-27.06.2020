package pl.edu.agh.mwo.java.Reports;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.TreeMap;

import pl.edu.agh.mwo.java.DataModel.RecordEntry;
import pl.edu.agh.mwo.java.DataModel.RecordFilter;
import pl.edu.agh.mwo.java.Helpers.ReportFunctions;

public class Report4 extends ReportSimple{
	ArrayList<String> headerList = new ArrayList<>();
    private ArrayList<RecordEntry> recordEntries;
	private TreeMap<String, Double> projects = new TreeMap<String, Double>();
	private TreeMap<String, TreeMap<String, Double>> results =  new TreeMap<String, TreeMap<String, Double>>();
	private TreeMap<String, ArrayList<String>> out = new TreeMap<String, ArrayList<String>>();
    
    
    public Report4(ArrayList<RecordEntry> recordEntries, int Year){
        RecordFilter recordFilter = new RecordFilter(recordEntries);
        this.recordEntries =  recordFilter.byYear(Year);
        this.reportName = "Raport4";
    }
    
    
    public TreeMap<String, TreeMap<String, Double>> getTemporaryReport(){
    	
    	TreeMap<String, TreeMap<String, Double>> temporary = new TreeMap<String, TreeMap<String, Double>>();
    	for(int i=0; i < recordEntries.size(); i++){
    		if (projects.containsKey(recordEntries.get(i).getProjectName())){
    			double sum = projects.get(recordEntries.get(i).getProjectName()) + recordEntries.get(i).getWorkingHours();
				projects.put(recordEntries.get(i).getProjectName(), sum);
    		}
    		else {
    			projects.put(recordEntries.get(i).getProjectName(), recordEntries.get(i).getWorkingHours());
    		}
    		
    		if (temporary.containsKey(recordEntries.get(i).getWorkerName())) {
    			TreeMap<String, Double> componentMap = temporary.get(recordEntries.get(i).getWorkerName());
    			if(componentMap.containsKey(recordEntries.get(i).getProjectName())) {
					double sum = componentMap.get(recordEntries.get(i).getProjectName()) + recordEntries.get(i).getWorkingHours();
					componentMap.put(recordEntries.get(i).getProjectName(), sum);
    			}
    			else {
    				componentMap.put(recordEntries.get(i).getProjectName(), recordEntries.get(i).getWorkingHours());
    				}
			} 
    		else {
				TreeMap<String, Double> componentMap = new TreeMap<String, Double>();
				componentMap.put(recordEntries.get(i).getProjectName(),recordEntries.get(i).getWorkingHours());
				temporary.put(recordEntries.get(i).getWorkerName(), componentMap);
			}
		} 
        return temporary;
    }
    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
    public TreeMap<String, ArrayList<String>> getReport4(){
    	results = getTemporaryReport();
		TreeMap<String, ArrayList<String>> out = new TreeMap<String, ArrayList<String>>();
		
		if(recordEntries.size() > 0) {
        	
        	for (Entry<String, TreeMap<String, Double>> entry1 : results.entrySet()) {
        		String key1 = entry1.getKey();
        		TreeMap<String, Double> value1 = entry1.getValue();
        		for (Map.Entry<String, Double> entry2 : value1.entrySet()) {
                    String key2 = entry2.getKey();
                    Double value2 = entry2.getValue();
                    Double result = (value2/(double)projects.get(key2))*100;
                    result = round(result,2);
                    value1.put(key2, result);
                }
        	}
        	
        	for (Entry<String, Double> entry : projects.entrySet()) {
            	String p = entry.getKey();
            	if(!headerList .contains(p.toString())) {
            		headerList .add(p);
            	}
            }
        	
        	for (Entry<String, TreeMap<String, Double>> entry : results.entrySet()) {
        		String mainKey = entry.getKey();
        		TreeMap<String, Double> projectPercentage = entry.getValue();
        		if(!out.containsKey(mainKey)) {
					out.put(mainKey, (ArrayList<String>) headerList.clone());
    			}
        	}
        	
        	for (Entry<String, ArrayList<String>> entry : out.entrySet()) {
        		String name = entry.getKey();
	        	ArrayList<String> projectsList = entry.getValue();

	        	for (int i = 0; i < projectsList.size(); i ++) {
	        		for (Entry<String, TreeMap<String, Double>> entry1 : results.entrySet()) {
	        			if (entry1.getKey().equals(name)){
	        				TreeMap<String, Double> projectPercentage = entry1.getValue();
	        				for (Entry<String, Double> entry2 : projectPercentage.entrySet()) {
	        					if (entry2.getKey().equals(projectsList.get(i))) {
	        						projectsList.set(i, entry2.getValue().toString() + "%");
	        					}	
	        				}
	        			}
	        		}
	        	}
        	}
        	for (Entry<String, ArrayList<String>> entry : out.entrySet()) {
        		String name = entry.getKey();
	        	ArrayList<String> projectsList = entry.getValue();
	        	for (int i = 0; i < projectsList.size(); i ++) {
	        		if(!projectsList.get(i).contains("%")) {
	        			projectsList.set(i, "0.0%");
	        		}
	        	}
        	}
		}
    	return out;
    }
    
    public static int maxLengthOfMapTreeKey(TreeMap<String, TreeMap<String, Double>> m){
        int retVal = 0;
        int tmpVal;
        for (Entry<String, TreeMap<String, Double>> entry : m.entrySet()) {
            tmpVal = entry.getKey().length();
            if (tmpVal > retVal) {
                retVal = tmpVal;
            }
        }
        return retVal;
    }
    
    public static int maxLengthOfMapTreeValue(TreeMap<String, TreeMap<String, Double>> m){
        int retVal = 0;
        int tmpVal;
        for (Map.Entry<String, TreeMap<String, Double>> entry : m.entrySet()) {
        	TreeMap<String, Double> value1 = entry.getValue();
    		for (Map.Entry<String, Double> entry2 : value1.entrySet()) {
    			tmpVal = entry2.getKey().length();
                if (tmpVal > retVal) {
                    retVal = tmpVal;
                }
    		}
        }
        return retVal;
    }
    
    @Override
    public boolean printOnConsole(){
    	
		out = getReport4();
	    int maxLenKey;
	    int maxLenVal;
    	
        if(recordEntries.size() > 0) {
        	maxLenKey = maxLengthOfMapTreeKey(results);
            maxLenVal = maxLengthOfMapTreeValue(results);

        	int counter = 0;
        	for (Entry<String, ArrayList<String>>entry : out.entrySet()) {
        		String name = entry.getKey();
        		
        		if (counter == 0) {
        			System.out.print(ReportFunctions.adjustTextToLength("Name", maxLenKey+3));
        			for(String str:headerList) {
        				System.out.print(ReportFunctions.adjustTextToLength(str, maxLenVal+1));
        			}
        			counter = counter + 1;
        			}
        		System.out.println();
        		entry.getValue();
        		System.out.print(ReportFunctions.adjustTextToLength(name, maxLenKey+3));
        		
        		for(String s:entry.getValue()) {
        			System.out.print(ReportFunctions.adjustTextToLength(s, maxLenVal+1));
        		}
        		
        	}
        	System.out.println();
        	System.out.println();
        	return true;
        	
        }else{
            System.out.println("Brak danych za ten rok :(");
            return false;
        }
    }
    
    public void exportToExcel(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj folder zapisu:");
        String folderPath = scan.nextLine();
        File dir = new File(folderPath);
        if (dir.exists()){
//        	TreeMap<String, ArrayList<String>> a = getReport4();
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Report4");
            HSSFRow rowhead = sheet.createRow((short)0);

            HSSFCellStyle style = wb.createCellStyle();
            HSSFFont font=wb.createFont();

            font.setBold(true);
            style.setFont(font);
             
            rowhead.createCell(0).setCellValue("Name");
            rowhead.getCell(0).setCellStyle(style);
            for (int i =1; i <= headerList.size(); i++) {
            	rowhead.createCell(i).setCellValue(headerList.get(i-1));
                rowhead.getCell(i).setCellStyle(style);
            }
            
            int i = 0;
            for (Map.Entry<String, ArrayList<String>>  entry : out.entrySet()) {
                i++;
                HSSFRow row = sheet.createRow((short)i);
                row.createCell(0).setCellValue(entry.getKey());
                ArrayList<String> helpList = entry.getValue();
                for (int j = 1; j <= helpList.size(); j++) {
                	row.createCell(j).setCellValue(helpList.get(j-1));
                }
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();

            try  (FileOutputStream fileOut = new FileOutputStream(folderPath + "\\"+ reportName + "_" + dtf.format(now) + ".xls")) {
                wb.write(fileOut);
                fileOut.close();
                wb.close();
                System.out.println("Raport został wygenerowany poprawnie!");
            }catch (Exception e){

            }
        }
    }
}