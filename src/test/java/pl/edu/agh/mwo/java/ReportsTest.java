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
public class ReportsTest {
    /**
     * Rigorous Test :-)
     */

    @Test
    public void generatingReport1() {
        ArrayList<RecordEntry> re = new ArrayList<RecordEntry>();
        LocalDate date = LocalDate.of(2020, 6, 20);
        RecordEntry recordEntry = new RecordEntry();
        recordEntry.setWorkerName("Janek");
        recordEntry.setDate(date);
        recordEntry.setDescription("some important stuff");
        recordEntry.setProjectName("Project Manhattan");
        recordEntry.setWorkingHours(8);
        re.add(recordEntry);

        recordEntry = new RecordEntry();
        date = LocalDate.of(2019, 6, 20);
        recordEntry.setWorkerName("Tomek");
        recordEntry.setDate(date);
        recordEntry.setDescription("not important stuff");
        recordEntry.setProjectName("Project Manhattan");
        recordEntry.setWorkingHours(4);
        re.add(recordEntry);
        Report1 r1 = new Report1(re, 2020);

        String retVal = new String();

        TreeMap<String, Double> a = r1.getReport();

        for(Map.Entry<String,Double> entry : a.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            retVal = key + " => " + value + " h";
            System.out.println(key + " => " + value + " h");
        }
        assertTrue(retVal.equals("Janek => 8.0 h"));
    }
}
