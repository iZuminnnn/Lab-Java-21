/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;

/**
 *
 * @author cauch
 */
public class ReportDetails {
    //Nguyen Van A | Java | 2
    private String _name;
    private String _course;
    private int _count;

    public ReportDetails(String _name, String _course, int _count) {
        this._name = _name;
        this._course = _course;
        this._count = _count;
    }

    public ReportDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getCourse() {
        return _course;
    }

    public void setCourse(String _course) {
        this._course = _course;
    }

    public int getCount() {
        return _count;
    }

    public void setCount(int _count) {
        this._count = _count;
    }
    @Override
    public String toString() {
        return _name+"\t"+" | "+_course +"\t"+ " | "+_count ;
    }
}