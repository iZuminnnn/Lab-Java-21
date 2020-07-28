/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author cauch
 */
public class Student  implements Comparable,Serializable {

    private String _id;
    private String _studentName;
    private String _semester;
    private String _courseName;

    public Student() {
    }

    public Student(String id, String studentName, String semester, String courseName) {
        this._id = id;
        this._studentName = studentName;
        this._semester = semester;
        this._courseName = courseName;
    }

    public Student(String id) {
        this._id = id;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getStudentName() {
        return _studentName;
    }

    public void setStudentName(String studentName) {
        this._studentName = studentName;
    }

    public String getSemester() {
        return _semester;
    }

    public void setSemester(String semester) {
        this._semester = semester;
    }

    public String getCourseName() {
        return _courseName;
    }

    public void setCourseName(String courseName) {
        this._courseName = courseName;
    }

    @Override
    public String toString() {
        return  _studentName + " | " + _semester + " | " + _courseName ;
    }
  
    //called by Collection.sort(..) in StudentManagement
    @Override
    public int compareTo(Object obj){
        Student s = (Student) obj;
        return this.getStudentName().compareTo(s.getStudentName());
    }
}
