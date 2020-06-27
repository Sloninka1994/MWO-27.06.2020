package pl.edu.agh.mwo.java.Reports;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.poi.hssf.usermodel.*;
import pl.edu.agh.mwo.java.DataModel.RecordEntry;
import pl.edu.agh.mwo.java.DataModel.RecordFilter;
import pl.edu.agh.mwo.java.Helpers.ReportFunctions;

public class Report3 {
	private ArrayList<RecordEntry> recordEntries;
	private String headerCol1 = "Miesiąc";
	private String headerCol2 = "Projekt";
	private String headerCol3 = "Ilość godzin";
	private String reportName = "Raport3";
	
	
	public Report3(ArrayList<RecordEntry> recordEntries, Integer year, String name_surname){
        RecordFilter recordFilter = new RecordFilter(recordEntries);
        this.recordEntries = recordFilter.byYear(year);
        recordFilter.setRecordEntries(this.recordEntries);
        this.recordEntries = recordFilter.byWorkerName(name_surname);
    }
	
	public TreeMap<Integer,TreeMap<String,Double>> getReport(){
		TreeMap<Integer, TreeMap<String, Double>> retVal2 = new TreeMap();
        TreeMap<String, Double> retVal = new TreeMap();
        
        for(int i=0; i < recordEntries.size(); i++){
        	
        	if (retVal2.containsKey(recordEntries.get(i).getDate().getMonthValue())) {
        		retVal = retVal2.get(recordEntries.get(i).getDate().getMonthValue());
	            if (retVal.containsKey(recordEntries.get(i).getProjectName())) {
	            	retVal.put(recordEntries.get(i).getProjectName(), retVal.get(recordEntries.get(i).getProjectName()) + recordEntries.get(i).getWorkingHours());
	            }
	            else{
	                retVal.put(recordEntries.get(i).getProjectName(),recordEntries.get(i).getWorkingHours());
	            }
        	} else {
        		retVal = new TreeMap<String, Double>();
        		retVal.put(recordEntries.get(i).getProjectName(), recordEntries.get(i).getWorkingHours());
        		retVal2.put(recordEntries.get(i).getDate().getMonthValue(), retVal );
        	}
        }
        return retVal2;
    }
	
	public boolean printOnConsole(){
        TreeMap<Integer,TreeMap<String,Double>> a = getReport();
        int lp = 1;
		int maxLenKey;
		int maxLenVal;
        if(recordEntries.size() > 0) {
        	maxLenKey = ReportFunctions.maxLengthOfMapTreeKey2(a);
        	maxLenVal = ReportFunctions.maxLengthOfMapTreeValue2(a);

			System.out.println("LP  " + ReportFunctions.adjustTextToLength(headerCol1, 14) + " => " + ReportFunctions.adjustTextToLength(headerCol2, maxLenKey) + " => " + ReportFunctions.adjustTextToLength(headerCol3, maxLenVal) + " h");

            for (Map.Entry<Integer, TreeMap<String, Double>> entry : a.entrySet()) {
            	for (Map.Entry<String, Double> entry2 : entry.getValue().entrySet()) {
            		Integer key = entry.getKey();
            		String project = entry2.getKey();
            		Double hours = entry2.getValue();

                    System.out.println(ReportFunctions.adjustTextToLength(String.valueOf(lp), 3) + " " + ReportFunctions.adjustTextToLength(ReportFunctions.convertToMonthName(key), 14) + " => " + ReportFunctions.adjustTextToLength(project, maxLenKey) + " => " + ReportFunctions.adjustTextToLength(String.valueOf(hours), maxLenVal) + " h");
                    lp++;
            	}
            }
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
			TreeMap<Integer, TreeMap<String, Double>> a = getReport();
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Report");
			HSSFRow rowhead = sheet.createRow((short)0);

			HSSFCellStyle style = wb.createCellStyle();
			HSSFFont font=wb.createFont();

			font.setBold(true);
			style.setFont(font);
			rowhead.createCell(0).setCellValue("Lp");
			rowhead.getCell(0).setCellStyle(style);
			rowhead.createCell(1).setCellValue(headerCol1);
			rowhead.getCell(1).setCellStyle(style);
			rowhead.createCell(2).setCellValue(headerCol2);
			rowhead.getCell(2).setCellStyle(style);
			rowhead.createCell(3).setCellValue(headerCol3);
			rowhead.getCell(3).setCellStyle(style);

			int i = 0;
			for (Map.Entry<Integer, TreeMap<String, Double>> entry_parent : a.entrySet()){

				for (Map.Entry<String, Double> entry : entry_parent.getValue().entrySet()) {
					i++;
					HSSFRow row = sheet.createRow((short)i);

					row.createCell(0).setCellValue(i);
					row.createCell(1).setCellValue(ReportFunctions.convertToMonthName(entry_parent.getKey()));
					row.createCell(2).setCellValue(entry.getKey());
					row.createCell(3).setCellValue(entry.getValue());
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