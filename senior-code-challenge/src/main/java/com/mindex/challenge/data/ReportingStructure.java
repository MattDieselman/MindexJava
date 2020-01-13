package com.mindex.challenge.data;

import com.mindex.challenge.data.Employee;

public class ReportingStructure{

    private Employee employee;
    private int numOfReports;

    public ReportingStructure(Employee employee_, int numberReports){
        employee = employee_;
        numOfReports = numberReports;
    }


    //Getters
    public Employee getEmployee(){
        return employee;
    }

    public int getNumOfReports(){
        return numOfReports;
    }


    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setNumOfReports(int numOfReports) {
        this.numOfReports = numOfReports;
    }
}