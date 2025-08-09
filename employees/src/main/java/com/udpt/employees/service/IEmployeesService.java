package com.udpt.employees.service;

import com.udpt.employees.dto.EmployeeDto;
import com.udpt.employees.request.DoctorInsertRequest;
import com.udpt.employees.request.EmployeeInsertRequest;

public interface IEmployeesService {
    void createEmployee(EmployeeInsertRequest request);
    void createDoctor(DoctorInsertRequest request);
    EmployeeDto getDoctorDetails(String doctorId);
    EmployeeDto getEmployeeDetails(String employeeId);

}
