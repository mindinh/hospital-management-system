package com.udpt.employees.service;

import com.udpt.employees.dto.EmployeeDto;
import com.udpt.employees.request.DoctorInsertRequest;
import com.udpt.employees.request.EmployeeInsertRequest;
import com.udpt.employees.request.ScheduleInsertRequest;
import org.springframework.web.multipart.MultipartFile;

public interface IEmployeesService {
    void createEmployee(EmployeeInsertRequest request);
    void createDoctor(DoctorInsertRequest request);
    void uploadDoctorImage(String doctorId, MultipartFile file);
    EmployeeDto getDoctorDetails(String doctorId);
    EmployeeDto getEmployeeDetails(String employeeId);
    boolean updateDepartment(String employeeId, int departmentId);
    void addSchedule(ScheduleInsertRequest request);
}
