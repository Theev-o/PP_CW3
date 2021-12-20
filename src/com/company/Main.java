package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.time.*;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        //zad1();
        //zad2(1, 100);
        //zad3();
        //zad4();
        //zad5();
        //zad6();
        SupportTicket ticket1 = new SupportTicket(1, LocalDate.now(), LocalTime.now());
        SupportTicket ticket2 = new SupportTicket(2, LocalDate.now(), LocalTime.now(), "PC won't turn on");
        SupportTicket ticket3 = new SupportTicket(3, LocalDate.now(), LocalTime.now(), true, null);
        SupportTicket ticket4 = new SupportTicket(4, LocalDate.now(), LocalTime.now(), 'X', "PC on fire");
        SupportTicket ticket5 = new SupportTicket(null, LocalDate.now(), LocalTime.now(), "I don't know the ID");
        SupportTicket ticket6 = new SupportTicket(6, LocalDate.now(), LocalTime.now(), 'A', true, "virums");

        ticket1.setContent("Forgot to add content, sorry");
        ticket3.setContent("Forgot to add content but I'm important as well");
        ticket5.setTicketID(5);
    }

    public static void zad1() {
        System.out.print("Please enter the lower RNG boundary: ");
        Integer min = input.nextInt();
        System.out.print("Please enter the upper RNG boundary: ");
        Integer max = input.nextInt();

        Integer randNum = rand.nextInt((max - min) + 1) + min;
        System.out.println("Printing random number between " + min + " and " + max + ": " + randNum);
    }

    public static void zad2(Integer min, Integer max) {
        System.out.println("I'm thinking of a number between " + min + " and " + max + ". Try to guess what it is.");

        Integer numToGuess = rand.nextInt((max - min) + 1) + min;
        Integer guess = null;

        for (int attempts = 1; guess != numToGuess; attempts++) {
            guess = input.nextInt();
            if (guess < numToGuess) {
                System.out.println("Higher!");
            } else if (guess > numToGuess) {
                System.out.println("Lower!");
            } else {
                System.out.println("Congratulations, you guessed the number in " + attempts + " tries.");
            }
        }
    }

    public static void zad3() {
        HashSet<Integer> nums = new HashSet<Integer>();
        while (nums.size() != 6) {
            nums.add(rand.nextInt(48) + 1);
        }
        System.out.println("The 6 unique numbers drawn from 1 and 49 are:");
        for (Integer num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void zad4() {
        System.out.println("Please provide six numbers between 1 and 49: ");

        Integer[] usrNums = new Integer[6];
        Integer[] cpuNums = new Integer[6];
        Integer attempts = 0;

        for (int i = 0; i < 6; i++) {
            while (true) {
                Integer usrInput = input.nextInt();
                if (usrInput < 50 && usrInput > 0) {
                    usrNums[i] = usrInput;
                    break;
                } else {
                    System.out.println("Between 1 and 49, dumbass.");
                }
            }
        }
        System.out.println("Drawing sets of 6 numbers until they match the user's.");
        while (!helpFunc.cmpArrays(usrNums, cpuNums)) {
            ++attempts;
            for (int i = 0; i < cpuNums.length; i++) {
                cpuNums[i] = rand.nextInt(48) + 1;
            }
        }
        System.out.println("After " + attempts + " attempts the correct set of numbers was randomly drawn.");
        System.out.println("It would take " + (int) (attempts / 365.25f) + " years and " + attempts % 365 +
                " days if one attempt was made every day.");
    }

    public static void zad5() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        HashSet<String> list = new HashSet<String>();
        try {
            File myObj = new File("src/text.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (!map.containsKey(data)) {
                    map.put(data, 1);
                } else {
                    list.add(data);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Strings that appear more than once: ");
        for (String text : list) {
            System.out.println(text);
        }
    }

    public static void zad6() {
        try {
            File myObj = new File("src/text.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Integer previousChar = (int) (data.charAt(0));
                for (int i = 1; i < data.length(); i++) {
                    if ((int) data.charAt(i) < previousChar) {
                        break;
                    } else if ((int) data.charAt(i) >= previousChar && i == data.length() - 1) {
                        System.out.println(data);
                    }
                    previousChar = (int) data.charAt(i);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

class helpFunc {
    public static Boolean cmpArrays(Integer[] usrNums, Integer[] cpuNums) {
        for (int i = 0; i < usrNums.length; i++) {
            if (usrNums[i] != cpuNums[i]) {
                return false;
            }
        }
        return true;
    }
}

class SupportTicket {
    private Integer TicketID;
    private LocalDate Date;
    private LocalTime Time;
    private Character IssueSeverity = '-';
    private Boolean IsPriority = false;
    private String Content = "";
    SupportTicket(Integer ID, LocalDate iniDate, LocalTime iniTime){
        TicketID = ID;
        Date = iniDate;
        Time = iniTime;
    }
    SupportTicket(Integer ID, LocalDate iniDate, LocalTime iniTime, String text){
        this(ID, iniDate, iniTime);
        Content = text;
    }
    SupportTicket(Integer ID, LocalDate iniDate, LocalTime iniTime, Character sev, String text){
        this(ID, iniDate, iniTime, text);
        IssueSeverity = sev;
    }
    SupportTicket(Integer ID, LocalDate iniDate, LocalTime iniTime, Boolean prio, String text){
        this(ID, iniDate, iniTime, text);
        IsPriority = prio;
    }
    SupportTicket(Integer ID, LocalDate iniDate, LocalTime iniTime, Character sev, Boolean prio,  String text){
        this(ID, iniDate, iniTime, text);
        IssueSeverity = sev;
        IsPriority = prio;
    }

    public Integer getTicketID() {
        return TicketID;
    }

    public void setTicketID(Integer ticketID) {
        TicketID = ticketID;
    }

    public LocalTime getTime() {
        return Time;
    }

    public void setTime(LocalTime time) {
        Time = time;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public Character getIssueSeverity() {
        return IssueSeverity;
    }

    public void setIssueSeverity(Character issueSeverity) {
        IssueSeverity = issueSeverity;
    }

    public Boolean getPriority() {
        return IsPriority;
    }

    public void setPriority(Boolean priority) {
        IsPriority = priority;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}