package com.udpt.employees.service;

import com.udpt.employees.dto.DepartmentDto;
import com.udpt.employees.request.DepartmentInsertRequest;

import java.util.List;

public interface IDepartmentsService {
    List<DepartmentDto> getAllDepartment();
    void addNewDepartment(DepartmentInsertRequest request);
}
