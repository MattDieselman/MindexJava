package com.mindex.challenge.data;

import com.mindex.challenge.data.Employee;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Compensation {
    //Just using double as a simple large number. BigDecimal could also work, as well as the Money API.
    private double salary;
    private Employee employee;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date effectiveDate;

    public Compensation(){

    }

    public double getSalary() {
        return salary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}