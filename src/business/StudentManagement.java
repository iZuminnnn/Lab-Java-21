/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entity.ReportDetails;
import java.util.ArrayList;
import java.util.Collections;
import entity.Student;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author cauch
 */
public class StudentManagement {

    private final ArrayList<Student> _listStud;

    public StudentManagement() {
        this._listStud = new ArrayList<>();
    }

    public ArrayList<Student> getList() {
        return _listStud;

    }

    /**
     * add object student to list _listStud
     *
     * @param stud object student input by user
     * @throws Exception if list _listStud null
     */
    public void add(Student stud) throws Exception {
        if (stud == null) {
            throw new Exception("Student can not null!");
        } else {
            this._listStud.add(stud);

        }
    }

    /**
     * Find student by Name
     *
     * @param studName , studName is String input by user
     * @return ArrayList contain Name input by user and sort them
     */
    //get student by name
    public ArrayList find(String studName) {
        ArrayList<Student> result = new ArrayList();
        //your code here

        for (Student s : _listStud) {
            if (s.getStudentName().trim().toLowerCase().contains(studName.trim().toLowerCase())) {
                result.add(s);
            }

        }

        //sort
        Collections.sort(result);

        return result;
    }

    /**
     * Delete student by id
     *
     * @param id , id input by user
     * @return true or false
     */
    public boolean delete(String id) {
        //your code here
        ArrayList<Student> _deletelist = new ArrayList();
        /*
        find the student with student.getId()=id
        this._listStud.remove(..)
         */
        int check = 0;

        for (Student student : _listStud) {
            //add element need delete of _listStud  to list _deletelist
            if (id.equals(student.getId())) {
                _deletelist.add(student);
                check = 1;
            }
        }
        //delete all element _listStude that _deletelist contain
        for (Student student : _deletelist) {
            _listStud.remove(student);
        }

        if (check == 1) {

            return true;
        } else {
            return false;
        }
        // return true;//true if found student, otherwise false
    }

    /**
     * Update student by id
     *
     * @param newStudent
     * @return true or false if id exist or not exist
     */
    public boolean update(Student newStudent) {
        //your code
        //find student with student.getId()=newSttudent.getId() then do change values  
        int check = 0;
        for (Student student : _listStud) {
            if (student.getId().equals(newStudent.getId())) {
                student.setStudentName(newStudent.getStudentName());
                student.setCourseName(newStudent.getCourseName());
                student.setSemester(newStudent.getCourseName());
                check = 1;
                break;
            }
        }
        if (check == 1) {
            return true;

        } else {
            return false;
        }
    }

    /**
     * Check Name and Course Name ,total Course Name in Arraylist Report
     *
     * @param lr ArrayList
     * @param name String name
     * @param course String Course
     * @param total Int Total CourseName
     * @return true if in ArrrayList Report .getName() and .getCourse()
     * ,getCount() do not contain like .getName() and .getCourse() ,getCount()
     */
    public static boolean checkNameDuplicateReport(ArrayList<ReportDetails> lr, String name, String course, int total) {
        for (ReportDetails report : lr) {
            if (name.equals(report.getName())
                    && course.equals(report.getCourse())
                    && total == report.getCount()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Show name student and CourseName
     *
     * @return ArrayList lr, lr is ArrayList contain Name ,CourseName and total
     * count courseName
     */
    public ArrayList report() {
        //your code here
        ArrayList<ReportDetails> lr = new ArrayList();
        int count = 0;
        for (Student st : _listStud) {
            for (Student studentCount : _listStud) {
                if (st.getStudentName().equals(studentCount.getStudentName())
                        && st.getCourseName().equals(studentCount.getCourseName())) {
                    count++;
                }
            }
            if (checkNameDuplicateReport(lr, st.getStudentName(), st.getCourseName(), count)) {
                lr.add(new ReportDetails(st.getStudentName(), st.getCourseName(), count));
            }
            count = 0;
        }

        return lr;
    }

    /**
     * Load Student.txt to ArrayList _listStud
     *
     * @throws Exception if cant not load File
     */
    public void loadFile() throws Exception {

        FileInputStream file = new FileInputStream("Student.txt");
        ObjectInputStream os = new ObjectInputStream(file);
        while (file.available() > 0) {
            Student obj = (Student) os.readObject();
            _listStud.add(obj);
        }
        os.close();
        file.close();

    }

    /**
     * Save ArrayList _listStud to File Student.txt
     *
     * @throws Exception if error
     */
    public void outputFile() throws Exception {
        FileOutputStream file = new FileOutputStream("Student.txt");
        ObjectOutputStream os = new ObjectOutputStream(file);
        for (Student op : _listStud) {
            os.writeObject((Student) op);
        }
        os.close();
        file.close();
    }
}
