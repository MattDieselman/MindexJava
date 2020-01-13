package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    //Tracker for the total number of people in this employee's reports
    private int totalNumberReports;

    //Gather and reset all direct reports for this employee and all under them. Resets "DirectReports" to be employee objects rather than single ids
    private List<Employee> refreshDirectReports(Employee employee){

        List<Employee> directReports = employee.getDirectReports();
        List<Employee> tempReports = new ArrayList<Employee>();

        if(directReports != null) {
            for (Employee element : directReports) {
                //Find the proper employee and add them to the tempReports list
                tempReports.add( employeeRepository.findByEmployeeId( element.getEmployeeId() ));
                //Increment the total number of employees we've hit so far
                totalNumberReports++;
            }
        }
        return tempReports;
    }

    private Employee getAllReports(Employee employee) {
        //reset the current employee's direct reports to be proper employee objects
        employee.setDirectReports(refreshDirectReports(employee));

        //recursively loop through all children of this employee
        for (Employee element : employee.getDirectReports()) {
            getAllReports(element);
        }
        return employee;
    }

    @Override
    public ReportingStructure read(String id) {
        //reset the total number each time we recall the endpoint
        totalNumberReports=0;

        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        ReportingStructure reportingStructure = new ReportingStructure(getAllReports(employee), totalNumberReports);

        return reportingStructure;
    }

}
