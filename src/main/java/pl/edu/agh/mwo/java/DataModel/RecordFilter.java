package pl.edu.agh.mwo.java.DataModel;

import java.util.ArrayList;

public class RecordFilter {
    private ArrayList<RecordEntry> recordEntries;

    public RecordFilter(ArrayList<RecordEntry> recordEntries){
        this.setRecordEntries(recordEntries);
    }

    public ArrayList<RecordEntry> byYear(int year){
        ArrayList<RecordEntry> retVal = new ArrayList<RecordEntry>();

        for(int i = 0; i < getRecordEntries().size(); i++){
            if(year == getRecordEntries().get(i).getDate().getYear()){
                retVal.add(getRecordEntries().get(i));
            };
        }
        return retVal;
    }

    public ArrayList<RecordEntry> byProject(String projectName){
        ArrayList<RecordEntry> retVal = new ArrayList<RecordEntry>();
        for(int i = 0; i < getRecordEntries().size(); i++){
            if(getRecordEntries().get(i).getProjectName().equals(projectName)){
                retVal.add(getRecordEntries().get(i));
            };
        }
        return retVal;
    }

    public ArrayList<RecordEntry> byWorkerName(String workerName){
        ArrayList<RecordEntry> retVal = new ArrayList<RecordEntry>();
        for(int i = 0; i < getRecordEntries().size(); i++){
            if(getRecordEntries().get(i).getWorkerName().equals(workerName)){
                retVal.add(getRecordEntries().get(i));
            };
        }
        return retVal;
    }

    public ArrayList<RecordEntry> getRecordEntries() {
        return recordEntries;
    }

    public void setRecordEntries(ArrayList<RecordEntry> recordEntries) {
        this.recordEntries = recordEntries;
    }
}
