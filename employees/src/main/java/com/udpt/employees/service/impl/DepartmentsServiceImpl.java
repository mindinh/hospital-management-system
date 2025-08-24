package com.udpt.employees.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udpt.employees.dto.DepartmentDto;
import com.udpt.employees.dto.EmployeeDto;
import com.udpt.employees.entity.DepartmentEntity;
import com.udpt.employees.entity.EmployeeEntity;
import com.udpt.employees.entity.Experience;
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

    private ObjectMapper objectMapper;
    private DepartmentRepository departmentRepository;
    private EmployeesRepository employeesRepository;

    @Override
    public List<DepartmentDto> getAllDepartment() {

        return departmentRepository.findAllKhoaWithTruongKhoaAndSoBacSi();
    }

    @Override
    public void addNewDepartment(DepartmentInsertRequest request) {
        EmployeeEntity headOfDepartment = new EmployeeEntity();
        if (request.truongKhoa() != null) {
             headOfDepartment = employeesRepository.findByMaNV(request.truongKhoa()).orElseThrow(
                    () -> new ResourceNotFoundException("Nhan Vien", "Ma Nhan Vien", request.truongKhoa())
            );
        }

        Optional<DepartmentEntity> department = departmentRepository.findByTenKhoa(request.tenKhoa());
        if (department.isPresent()) {
            throw new DepartmentAlreadyExistException("Department already exist");
        }

        DepartmentEntity newDepartment = new DepartmentEntity();
        newDepartment.setTenKhoa(request.tenKhoa());
        newDepartment.setMaTruongKhoa(headOfDepartment.getMaNV());
        newDepartment.setCreatedAt(LocalDateTime.now());
        newDepartment.setCreatedBy("employees-service");

        departmentRepository.save(newDepartment);

    }

    @Override
    public List<EmployeeDto> getAllEmployee(int departmentId) {
        DepartmentEntity department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Khoa", "Ma Khoa", String.valueOf(departmentId))
        );


        return employeesRepository.findByKhoa_Id(departmentId).stream().map(
                e -> {
                    EmployeeDto employeeDto = new EmployeeDto();
                    employeeDto.setMaNV(e.getMaNV());
                    employeeDto.setHoTen(e.getHoTenNV());
                    employeeDto.setNgaySinh(e.getNgaySinhNV());
                    try {
                        List<Experience> kinhNghiem = objectMapper.readValue(e.getKinhNghiem(), new TypeReference<>() {
                        });
                        employeeDto.setKinhNghiem(kinhNghiem);
                    } catch (JsonProcessingException ex) {
                        throw new RuntimeException(ex);
                    }

                    return employeeDto;
                }
        ).toList();
    }
}
