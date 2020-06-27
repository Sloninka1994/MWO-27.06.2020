package pl.edu.agh.mwo.java.Helpers;

import java.util.Map;
import java.util.TreeMap;

public class ReportFunctions {

    public static int maxLengthOfMapTreeKey2(TreeMap<Integer, TreeMap<String, Double>> m){
        int retVal = 0;
        int tmpVal;
        for (Map.Entry<Integer, TreeMap<String, Double>> entry : m.entrySet()) {
            tmpVal = maxLengthOfMapTreeKey(entry.getValue());
            if (tmpVal > retVal) {
                retVal = tmpVal;
            }
        }
        return retVal;
    }

    public static int maxLengthOfMapTreeKey(TreeMap<String, Double> m){
        int retVal = 0;
        int tmpVal;
        for (Map.Entry<String, Double> entry : m.entrySet()) {
            tmpVal = entry.getKey().length();
            if (tmpVal > retVal) {
                retVal = tmpVal;
            }
        }
        return retVal;
    }

    public static int maxLengthOfMapTreeValue2(TreeMap<Integer, TreeMap<String, Double>> m){
        int retVal = 0;
        int tmpVal;
        for (Map.Entry<Integer, TreeMap<String, Double>> entry : m.entrySet()) {
            tmpVal = maxLengthOfMapTreeValue(entry.getValue());
            if (tmpVal > retVal) {
                retVal = tmpVal;
            }
        }
        return retVal;
    }

    public static int maxLengthOfMapTreeValue(TreeMap<String, Double> m){
        int retVal = 0;
        int tmpVal;
        for (Map.Entry<String, Double> entry : m.entrySet()) {
            tmpVal = String.valueOf(entry.getValue()).length();
            if (tmpVal > retVal) {
                retVal = tmpVal;
            }
        }
        return retVal;
    }
    public static String adjustTextToLength(String text, int maxLength){
        int lengthDiff = maxLength - text.length();
        for (int i = 0; i < lengthDiff; i++) {
            text += " ";
        }
        return text;
    }

    public static String[] extractLabels(TreeMap<String, Double> m){
        String[] retVal = new String[m.size()];
        int i = 0;
        for (Map.Entry<String, Double> entry : m.entrySet()) {
            retVal[i] = entry.getKey();
            i++;
        }
        return retVal;
    }
    public static Double[] extractValues(TreeMap<String, Double> m){
        Double[] retVal = new Double[m.size()];
        int i = 0;
        for (Map.Entry<String, Double> entry : m.entrySet()) {
            retVal[i] = entry.getValue();
            i++;
        }
        return retVal;
    }
    public static String convertToMonthName(Integer m){
        if (m < 12 && m > -1) {
            String[] str = {"Styczeń",
                    "Luty",
                    "Marzec",
                    "Kwiecień",
                    "Maj",
                    "Czerwiec",
                    "Lipiec",
                    "Sierpień",
                    "Wrzesień",
                    "Październik",
                    "Listopad",
                    "Grudzień"};
            return str[m - 1];
        }else{
            return "";
        }
    }


}
