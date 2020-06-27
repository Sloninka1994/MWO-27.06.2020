package pl.edu.agh.mwo.java.DataModel;

import java.time.LocalDate;
import java.util.Date;

public class RecordEntry {
    private String workerName;
    private String projectName;
    private LocalDate date;
    private String description;
    private double workingHours;

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
    }

    public String toString(){
        return  workerName + " spędził " + Double.toString( workingHours) + " godz. na projekcie " + projectName + " zajmując się " + description + " (" + date.toString() + ")";
    }
}
