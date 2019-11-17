package com.example.attendancemanager;

public class Student {
    String roll_no;
    String password;
    int attendance;

    public Student() {
        this.roll_no="DEFAULT";
        this.password="ABCXYZ";
        this.attendance=0;
    }

    public Student(String roll_no, String password, int attendance) {
        this.roll_no = roll_no;
        this.password = password;
        this.attendance = attendance;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public String getPassword() {
        return password;
    }

    public int getAttendance() {
        return attendance;
    }
}
