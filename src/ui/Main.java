/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import business.StudentManagement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import entity.Student;
import java.util.ArrayList;

/**
 *
 * @author cauch
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void menu() {
        System.out.println(" 1.	Create.");
        System.out.println(" 2.	Find and Sort.");
        System.out.println(" 3.	Update/Delete.");
        System.out.println(" 4.	Report.");
        System.out.println(" 5.	Save File.");
        System.out.println(" 6.	Exit.");
        System.out.print(" Enter your choice:  ");
    }

    public static String validatorInputString(String msg) throws IOException {
        String inputString = null;
        do {
            System.out.println(msg);
            //replace all space > 2 to one space
            inputString = in.readLine().replaceAll("\\s+", " ");

            if (inputString.isEmpty()) {
                System.out.println("Input cannot is empty!!!");
            } else if (inputString.equals(" ")) {
                System.out.println("Input cannot is empty!!!");
            } else {
                break;
            }
        } while (true);
        return inputString;
    }

    public static String validatorInputCourse() throws IOException {
        //loop until user input correct
        while (true) {
            String result = validatorInputString("Enter Course Name:(java/ .net/ c/c++)");
            //check input course in java/ .net/ c/c++
            if (result.equalsIgnoreCase("java")) {
                result = "Java";
                return result;
            }
            if (result.equalsIgnoreCase(".net")) {
                result = ".Net";
                return result;
            }
            if (result.equalsIgnoreCase("c/c++")) {
                result = "C/C++";
                return result;
            }
            System.out.println("There are only three courses: Java, .Net, C/C++");

        }
    }

    public static Student inputStudent() throws IOException {

        String id = validatorInputString("Enter id: ");
        String studentName = validatorInputString("Enter Name: ");
        String sem = validatorInputString("Enter sem: ");
        String courseName = validatorInputCourse();
        Student st = new Student(id, studentName, sem, courseName);

        return st;
    }

    public static Student inputUpdateStudent(String id) throws IOException {
        String studentName = validatorInputString("Enter Name: ");
        String sem = validatorInputString("Enter sem: ");
        String courseName = validatorInputString("Enter Course Name: ");
        Student st2 = new Student(id, studentName, sem, courseName);
        return st2;
    }

    public static void printList(ArrayList lc) { 
        for (int i = 0; i < lc.size(); i++) {
            System.out.println(lc.get(i).toString());
        }
    }

    public static void main(String[] args) {
        try {
            // TODO code application logic here

            StudentManagement sm = new StudentManagement();
//            sm.add(new Student("1", "Nguyen Van An", "Spring", "Java"));            
//            sm.add(new Student("1", "Nguyen Van An", "Summer", "Java"));
//            sm.add(new Student("1", "Nguyen Van An", "Summer", "Java"));        
//            sm.add(new Student("1", "Nguyen Van An", "Winter", ".Net"));
//            sm.add(new Student("4", "Nguyen Van B", "Winter", ".Net"));
//            sm.add(new Student("8", "Nguyen Van B", "Summer", ".Net"));
//            sm.add(new Student("5", "Nguyen Van B", "Spring", "Java"));
//            sm.add(new Student("6", "Nguyen Van C", "Summer", "Java"));
            sm.loadFile();
            while (true) {
                //Show menu option
                menu();
                String choice = in.readLine();
                switch (choice) {
                    case "1":
                        int count = 0;
                        for (int i = 0; i < 100; i++) {
                            count++;
                            if (count <= 2) {

                                try {
                                    // add student
                                    Student st = inputStudent();
                                    //input student detail
                                    sm.add(st);
                                } catch (Exception ex) {
                                    System.out.println("Error: " + ex.getMessage());
                                }
                            } else if (count > 2) {
                                System.out.println("Do you want to continue (Y/N)?");
                                String yn = in.readLine();
                                if (yn.equalsIgnoreCase("y")) {
                                    try {
                                        // add student
                                        Student st = inputStudent();

                                        //input student detail
                                        sm.add(st);
                                    } catch (Exception ex) {
                                        System.out.println("Error: " + ex.getMessage());
                                    }
                                } else if (yn.equalsIgnoreCase("n")) {
                                    break;
                                }
                                System.out.println("Please input y/Y or n/N.");

                            }

                        }

                        break;

                    case "2":    
                        String stfind = validatorInputString("Enter Student Name: ");
                        
                        printList(sm.find(stfind));

                        break;
                    case "3":
                        String id = validatorInputString("Enter id: ");
                        System.out.println("Do you want to update (U) or delete (D) student.");
                        String choice2 = in.readLine().toLowerCase();
                        switch (choice2) {
                            case "u":
                                Student st = new Student(id);

                                if (sm.update(st)) {
                                    sm.update(inputUpdateStudent(id));
                                    System.out.println("Update Successful !!!");
                                } else {
                                    System.out.println("Cannot find ID in List !!!");

                                }
                                break;
                            case "d":

                                if (sm.delete(id)) {
                                    sm.delete(id);
                                    System.out.println("Delete Successful !!!");
                                } else {
                                    System.out.println("Cannot find id in List !!!");

                                }

                                break;
                            default:
                                System.out.println("Must U or D");

                                break;
                        }
                        break;
                    case "4":
                        printList(sm.report());
                        break;
                    case "5":
                        sm.outputFile();

                        break;
                    case "6":

                        return;
                }

            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
