package com.udpt.employees.service.impl;

import com.udpt.employees.dto.DepartmentDto;
import com.udpt.employees.entity.DepartmentEntity;
import com.udpt.employees.entity.EmployeeEntity;
import com.udpt.employees.exception.DepartmentAlreadyExistException;
import com.udpt.employees.exception.ResourceNotFoundException;
import com.udpt.employees.repository.DepartmentRepository;
import com.udpt.employees.repository.EmployeesRepository;
import com.udpt.employees.request.DepartmentInsertRequest;
import com.udpt.employees.service.IDepartmentsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentsServiceImpl implements IDepartmentsService {

    private DepartmentRepository departmentRepository;
    private EmployeesRepository employeesRepository;

    @Override
    public List<DepartmentDto> getAllDepartment() {

        return departmentRepository.findAllKhoaWithTruongKhoaAndSoBacSi();
    }

    @Override
    public void addNewDepartment(DepartmentInsertRequest request) {
        EmployeeEntity headOfDepartment = employeesRepository.findByMaNV(request.truongKhoa()).orElseThrow(
                () -> new ResourceNotFoundException("Nhan Vien", "Ma Nhan Vien", request.truongKhoa())
        );

        Optional<DepartmentEntity> department = departmentRepository.findByTenKhoa(request.tenKhoa());
        if (department.isPresent()) {
            throw new DepartmentAlreadyExistException("Department already exist");
        }

        DepartmentEntity newDepartment = new DepartmentEntity();
        newDepartment.setTenKhoa(request.tenKhoa());
        newDepartment.setTruongKhoa(request.truongKhoa());
        newDepartment.setCreatedAt(LocalDateTime.now());
        newDepartment.setCreatedBy("employees-service");

        departmentRepository.save(newDepartment);

    }
}
