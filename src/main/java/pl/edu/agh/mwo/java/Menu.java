package pl.edu.agh.mwo.java;
import pl.edu.agh.mwo.java.DataModel.RecordEntry;
import pl.edu.agh.mwo.java.ReadData.ReadData;
import pl.edu.agh.mwo.java.Reports.Report1;
import pl.edu.agh.mwo.java.Reports.Report2;
import pl.edu.agh.mwo.java.Reports.Report3;
import pl.edu.agh.mwo.java.Reports.Report4;
import pl.edu.agh.mwo.java.Reports.Report5;
//import pl.edu.agh.mwo.java.Reports.Report5;

import java.util.ArrayList;
import java.util.Scanner;


public class Menu {
	static ArrayList<RecordEntry> re = new ArrayList<RecordEntry>();

	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_BLUE = "\u001b[36m";
	public static final String ANSI_YELLOW = "\u001b[33m";
	public static final String ANSI_GREEN = "\u001b[32m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_UNDERLINE = "\u001B[4m";
	public static final String ANSI_BOLD = "\u001B[1m";

	public static String getPath(){
		String path;
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println();
			System.out.println(ANSI_YELLOW + "*** "  + "Podaj ścieżkę do katalogu z arkuszami pracowników!: " + ANSI_YELLOW + "***" + ANSI_RESET);
			path = scan.nextLine();
			if (path.length() > 0) {
				return path;
			}
			else {
				System.out.println(ANSI_RED + "Pusta sciezka !" + ANSI_RESET);
			}
		}
	}

	public static void printMenu() {
		while (true) {

			Scanner scan = new Scanner(System.in);
			System.out.println();
			System.out.println(ANSI_YELLOW + "*** " +  "Wybierz, który raport chcesz wygenerować: "+ ANSI_YELLOW + "***" + ANSI_RESET);
			System.out.println(ANSI_BLUE + "1:"+ ANSI_RESET +" Alfabetycznej zestawienie listy pracowników <-> liczby godzin");
			System.out.println(ANSI_BLUE + "2:"+ ANSI_RESET +" Alfabetycznie zestawienie projektów <-> liczba godzin ");
			System.out.println(ANSI_BLUE + "3:"+ ANSI_RESET +" Szczegółowy wykaz pracy kazdego pracownika");
			System.out.println(ANSI_BLUE + "4:"+ ANSI_RESET +" Procentowe zaangazowanie danego pracownika w projektach w danym roku");
			System.out.println(ANSI_BLUE + "5:"+ ANSI_RESET +" Szczegółowy wykaz pracy w danym projekcie");
			System.out.println(ANSI_BLUE + "6:"+ ANSI_RESET +" Wygeneruj wykres słupkowy dla raportu 2");
			System.out.println(ANSI_BLUE + "7:"+ ANSI_RESET +" Pokaż wszystkie zaimportowane dane");
			System.out.println(ANSI_BLUE + "8:"+ ANSI_RESET + ANSI_RED +" Exit"+ ANSI_RESET);

			String option = scan.nextLine();

			switch (option) {
				case "0":
					for(RecordEntry itm : re){
						System.out.println(itm.toString());
					}
					break;
				case "1":
					System.out.println(ANSI_GREEN + "Generating Report 1" + ANSI_RESET);
					System.out.println(ANSI_YELLOW + "*** "  +"Podaj rok dla którego ma powstać raport:"+ ANSI_YELLOW + "***" + ANSI_RESET);
					String sYear = scan.nextLine();
					int Year;
					try {
						Year = Integer.parseInt(sYear);
					} catch (Exception e) {
						System.out.println(ANSI_RED + "Wprowadzono błędną wartość" + ANSI_RESET);
						break;
					}
					Report1 r1 = new Report1(re,Year);
					if(r1.printOnConsole()){
						System.out.println(ANSI_YELLOW + "Chcesz wyeksportować ten raport do Excela? T/N" + ANSI_RESET);
						String usrResp = scan.nextLine();
						if (usrResp.toUpperCase().equals("T")){
							r1.exportToExcel();
						}
					}

					break;
				case "2":
					System.out.println(ANSI_GREEN +"Generating Report 2"+ANSI_RESET);
					System.out.println(ANSI_YELLOW + "*** "  +"Podaj rok dla którego ma powstać raport:"+ ANSI_YELLOW + "***" + ANSI_RESET);
					String sYear2 = scan.nextLine();
					int Year2;
					try {
						Year2 = Integer.parseInt(sYear2);
					} catch (Exception e) {
						System.out.println(ANSI_RED + "Wprowadzono błędną wartość" + ANSI_RESET);
						break;
					}
					Report2 r2 = new Report2(re, Year2);
					if(r2.printOnConsole()){
						System.out.println(ANSI_YELLOW + "Chcesz wyeksportować ten raport do Excela? T/N" + ANSI_RESET);
						String usrResp = scan.nextLine();
						if (usrResp.toUpperCase().equals("T")){
							r2.exportToExcel();
						}
					}
					break;
				case "3":
					System.out.println(ANSI_YELLOW + "*** " + ANSI_RESET + "Generating Report 3" + ANSI_YELLOW + "***" + ANSI_RESET);
					System.out.println(ANSI_YELLOW + "*** "  + "Podaj imię i nazwisko pracownika dla którego ma powstać raport:"+ ANSI_YELLOW + "***" + ANSI_RESET);
					String surname_name = scan.nextLine();
					System.out.println(ANSI_YELLOW + "*** "  + "Podaj rok dla którego ma powstać raport:"+ ANSI_YELLOW + "***" + ANSI_RESET);
					String sYear3 = scan.nextLine();
					int Year3;
					try {
						Year3 = Integer.parseInt(sYear3);
					} catch (Exception e) {
						System.out.println(ANSI_RED + "Wprowadzono błędną wartość" + ANSI_RESET);
						break;
					}
					Report3 r3 = new Report3(re, Year3, surname_name);

					if(r3.printOnConsole()){
						System.out.println(ANSI_YELLOW + "Chcesz wyeksportować ten raport do Excela? T/N" + ANSI_RESET);
						String usrResp = scan.nextLine();
						if (usrResp.toUpperCase().equals("T")){
							r3.exportToExcel();
						}
					}
					break;
				case "4":
					System.out.println(ANSI_YELLOW + "*** " + ANSI_RESET +"Generating Report 4"+ ANSI_YELLOW + "***" + ANSI_RESET);
					System.out.println(ANSI_YELLOW + "*** "  + "Podaj rok dla którego ma powstać raport:"+ ANSI_YELLOW + "***" + ANSI_RESET);
					String sYear4 = scan.nextLine();
					int Year4;
					try {
						Year4 = Integer.parseInt(sYear4);
					} catch (Exception e) {
						System.out.println(ANSI_RED + "Wprowadzono błędną wartość" + ANSI_RESET);
						break;
					}
					Report4 r4 = new Report4(re, Year4);
//					r4.printOnConsole();

					if(r4.printOnConsole()){
						System.out.println(ANSI_YELLOW + "Chcesz wyeksportować ten raport do Excela? T/N" + ANSI_RESET);
						String usrResp = scan.nextLine();
						if (usrResp.toUpperCase().equals("T")){
							r4.exportToExcel();
						}
					}
					break;
				case "5":
					System.out.println(ANSI_YELLOW + "*** " + ANSI_RESET +"Generating Report 5"+ ANSI_YELLOW + "***" + ANSI_RESET);
                    System.out.println(ANSI_YELLOW + "*** "  +"Podaj projekt dla którego ma powstać raport:"+ ANSI_YELLOW + "***" + ANSI_RESET);
                    String project = scan.nextLine();
                    Report5 r5 = new Report5(re, project);
                    if(r5.printOnConsoleReport5()){
						System.out.println(ANSI_YELLOW + "Chcesz wyeksportować ten raport do Excela? T/N" + ANSI_RESET);
						String usrResp = scan.nextLine();
						if (usrResp.toUpperCase().equals("T")){
							r5.exportToExcel();
						}
					}

					break;
				case "6":
					System.out.println(ANSI_GREEN +"Generating Report 6"+ANSI_RESET);
					System.out.println(ANSI_YELLOW + "*** "  +"Podaj rok dla którego ma powstać raport:"+ ANSI_YELLOW + "***" + ANSI_RESET);
					String sYear6 = scan.nextLine();
					int Year6;
					try {
						Year6 = Integer.parseInt(sYear6);
					} catch (Exception e) {
						System.out.println(ANSI_RED + "Wprowadzono błędną wartość" + ANSI_RESET);
						break;
					}
					Report2 r6 = new Report2(re, Year6);
					r6.generateBarChart();
					break;
				case "7":
					System.out.println(ANSI_GREEN +"Generating Report 7"+ANSI_RESET);
					System.out.println(ANSI_YELLOW + "*** "  +"Podaj nazwisko pracownika, dla którego powstanie wykres raportu4:"+ ANSI_YELLOW + "***" + ANSI_RESET);

					String workerName = scan.nextLine();

					try {

					} catch (Exception e) {
						System.out.println(ANSI_RED + "Wprowadzono błędną wartość" + ANSI_RESET);
						break;
					}
					Report4 r7 = new Report4(re, workerName);
					r7.generatePieChart();

				case "8":
					System.out.println(ANSI_GREEN + "Zapraszamy ponownie! " + ANSI_RESET);
					return;
				default:
					System.out.println(ANSI_RED+ "Nie ma takiej opcji! Wybierz ponownie!" + ANSI_RESET);
					break;
			}
		}

	}

	public static void main(String[] args) {

		String path;
		String option;
		System.out.println(ANSI_GREEN + ANSI_BOLD + "***** Witaj w programie do generowania raportów - MWO2020! *****" + ANSI_RESET);
		System.out.println(ANSI_RED + ANSI_BOLD +"      __    __      ___      __      ___   \n" +
				"     /  \\  /  \\     \\  \\    /  \\    /  /    ▄▄▄▄▄▄▄▄▄▄\n" +
				"    /    \\/    \\     \\  \\  /    \\  /  /   ▄▀         ▀▄\n" +
				"   /            \\     \\  \\/      \\/  /   ▄▀   █   █   ▀▄\n" +
				"  /   /\\    /\\   \\     \\     /\\     /    █             █\n" +
				" /   /  \\  /  \\   \\     \\   /  \\   /     █ █▄▄▄▄▄▄▄▄▄█ █\n" +
				"/___/    \\/    \\___\\     \\_/    \\_/       █           █    \n" +
				"                                           ▀▄▄▄▄▄▄▄▄▄▀      ...Twój przyjaciel w generowaniu raportów" + ANSI_RESET);
		System.out.println(ANSI_GREEN + ANSI_UNDERLINE+ "Manufactured by Iksde team. All rights reserved." + ANSI_RESET);
		System.out.println("____________________________________________________");

		path = getPath();
		System.out.println(path);

		re = ReadData.readAllFromFolder(path);
		if(ReadData.errorsOccured()){
			System.out.println(ANSI_RED+"Podczas wczytywania danych wystąpiły następujące problemy:"+ANSI_RESET);
			ReadData.printErrorLog();
		}
		if (re.size() > 0) {

			printMenu();
		}else{
			System.out.println(ANSI_RED+"Wczytywanie danych nie powiodło się. Proszę upewnić się że ścieżka do folderu jest poprawna."+ANSI_RESET);
		}
		//Funkcje odpowiednie do raportu -> Wczytywanie Roku, miesiąca, Imienia, etc;




	}
}