package pl.edu.agh.mwo.java.ReadData;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import pl.edu.agh.mwo.java.DataModel.RecordEntry;

public class ReadData {
	private static ArrayList<String> errorLogList;
	public static ArrayList<String> folderExplorer(String myDirectoryPath) {

		ArrayList<String> pathList = new ArrayList<String>();
		
		File dir = new File(myDirectoryPath);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
		    for (File subDir1 : directoryListing) {
		    	if (subDir1.isDirectory()) {
		            for (File subDir2 : subDir1.listFiles()) {
		            	if (subDir2.isDirectory()) {
		            	//System.out.println(file1.toString());
		            		for (File file1 : subDir2.listFiles()) {
		            				pathList.add(file1.toString());
		            		}
		            	}
		            }
		    	}
		    }
		} 
		else {
		    // Handle the case where dir is not really a directory.
		}
		
		return pathList;
	}
	
	public static ArrayList<RecordEntry> readXls(String path) {

		ArrayList<RecordEntry> recordEntryList = new ArrayList<RecordEntry>();
		double hours;

		try {
			Workbook wb = WorkbookFactory.create(new File(path));
			String fullPath = path;
			

			int endOfname = fullPath.lastIndexOf(".");
			int startOfSurname = fullPath.lastIndexOf("\\")+1;		
			String name = new String(fullPath.substring(startOfSurname, endOfname)).replace("_", " ");

			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				Sheet sheet = wb.getSheetAt(i);
				String project = sheet.getSheetName();
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {
					Row r = sheet.getRow(j);
					if (r.getCell(0).getCellType() == CellType.NUMERIC && r.getCell(0).getCellType() != CellType.BLANK ) {
						Date date = r.getCell(0).getDateCellValue();
						LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

						String description = r.getCell(1).getStringCellValue();

						try{
							hours = r.getCell(2).getNumericCellValue();
						}catch (Exception e){
							hours = 0;
							errorLogList.add("Błąd odczytu godzin: " + fullPath + " - " + sheet + " wiersz " + String.valueOf(r.getRowNum()) + " >> wartość ustawiona na zero");
						}
						RecordEntry toAdd = new RecordEntry();
						toAdd.setDate(localDate);
						toAdd.setDescription(description);
						toAdd.setWorkingHours(hours);
						toAdd.setProjectName(project);
						toAdd.setWorkerName(name);

						recordEntryList.add(toAdd);
					} else {
						errorLogList.add("Błąd odczytu godzin: " + fullPath + " - " + sheet + " wiersz " + String.valueOf(r.getRowNum()) + " >> rekord pominięto" );
					}
					
			}
		}
		
			
        } catch (EncryptedDocumentException | IOException e) {
        	e.printStackTrace();
        }

		return recordEntryList;
	}
	public static void printErrorLog(){
		for (String itm : errorLogList){
			System.out.println(itm);
		}
	}
	public static ArrayList<RecordEntry> readAllFromFolder(String myDirectoryPath){
		errorLogList = new ArrayList<>();
		ArrayList<String> pathList = folderExplorer(myDirectoryPath);
		ArrayList<RecordEntry> retVal = new ArrayList<>();
		ArrayList<RecordEntry> recordList;

		for(String path1: pathList) {
			recordList = readXls(path1);
			for(RecordEntry r: recordList) {
				retVal.add(r);
			}
		}
		return retVal;
	}


	public static boolean errorsOccured() {
		if (errorLogList.size() > 0 ){
			return true;
		} else {
			return false;
		}

	}
}