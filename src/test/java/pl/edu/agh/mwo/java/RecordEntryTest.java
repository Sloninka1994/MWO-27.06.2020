package pl.edu.agh.mwo.java;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import pl.edu.agh.mwo.java.DataModel.RecordEntry;
import pl.edu.agh.mwo.java.DataModel.RecordFilter;
import pl.edu.agh.mwo.java.Reports.Report1;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Unit test for simple App.
 */
public class RecordEntryTest {
    /**
     * Rigorous Test :-)
     */

    @Test
    public void toStringFromRecordEntry(){
        LocalDate date =  LocalDate.of(2020,6,20);
        RecordEntry  recordEntry = new RecordEntry();
        recordEntry.setWorkerName("Janek");
        recordEntry.setDate(date);
        recordEntry.setDescription("some important stuff");
        recordEntry.setProjectName("Project Manhattan");
        recordEntry.setWorkingHours(8);
        System.out.println(recordEntry.toString());
        assertTrue(recordEntry.toString().equals("Janek spent 8.0h on project Project Manhattan doing some important stuff (2020-06-20)"));
    }


}
