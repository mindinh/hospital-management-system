package com.udpt.employees.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udpt.employees.dto.EmployeeDto;
import com.udpt.employees.entity.EmployeeEntity;
import com.udpt.employees.entity.Experience;
import com.udpt.employees.entity.Gender;
import com.udpt.employees.entity.Role;
import com.udpt.employees.exception.EmployeeAlreadyExistException;
import com.udpt.employees.exception.ResourceNotFoundException;
import com.udpt.employees.mapper.EmployeeMapper;
import com.udpt.employees.repository.EmployeesRepository;
import com.udpt.employees.request.DoctorInsertRequest;
import com.udpt.employees.request.EmployeeInsertRequest;
import com.udpt.employees.service.IEmployeesService;
import com.udpt.employees.utils.EmployeeIdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeesServiceImpl implements IEmployeesService {
    private ObjectMapper objectMapper;
    private EmployeesRepository employeesRepository;

    @Override
    public void createEmployee(EmployeeInsertRequest request) {
        Optional<EmployeeEntity> employeeEntity = employeesRepository.findBySoDTNV(request.soDT());

        if (employeeEntity.isPresent()) {
            throw new EmployeeAlreadyExistException(request.soDT());
        }

        EmployeeEntity newEmployee = new EmployeeEntity();
        newEmployee.setMaNV(request.maNV());
        newEmployee.setSoDTNV(request.soDT());
        newEmployee.setHoTenNV(request.hoTen());
        newEmployee.setGioiTinhNV(Gender.valueOf(request.gioiTinh()));
        newEmployee.setDiaChiNV(request.diaChi());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(request.ngaySinh(), formatter);
        newEmployee.setNgaySinhNV(dob);

        newEmployee.setChucVu(Role.valueOf(request.chucVu()));
        newEmployee.setKinhNghiem(request.kinhNghiem());

        newEmployee.setCreatedAt(LocalDateTime.now());
        newEmployee.setCreatedBy("employees-service");

        employeesRepository.save(newEmployee);
    }

    @Override
    public void createDoctor(DoctorInsertRequest request) {
        Optional<EmployeeEntity> employeeEntity = employeesRepository.findByMaChungChi(request.maChungChi());

        if (employeeEntity.isPresent()) {
            throw new EmployeeAlreadyExistException(request.maChungChi());
        }

        EmployeeEntity newEmployee = new EmployeeEntity();
        try {
            newEmployee.setMaNV(request.maNV());
            newEmployee.setMaChungChi(request.maChungChi());
            newEmployee.setHoTenNV(request.hoTen());
            newEmployee.setGioiTinhNV(Gender.valueOf(request.gioiTinh()));
            newEmployee.setDiaChiNV(request.diaChi());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dob = LocalDate.parse(request.ngaySinh(), formatter);
            newEmployee.setNgaySinhNV(dob);

            newEmployee.setChucVu(Role.BACSI);

            String json = objectMapper.writeValueAsString(request.kinhNghiem());
            newEmployee.setKinhNghiem(json);

            newEmployee.setCreatedAt(LocalDateTime.now());
            newEmployee.setCreatedBy("employees-service");

            employeesRepository.save(newEmployee);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public EmployeeDto getDoctorDetails(String doctorId) {
        EmployeeEntity employeeEntity = employeesRepository.findByMaNV(doctorId).orElseThrow(
                () -> new ResourceNotFoundException("Bac Si", "Ma Bac Si", doctorId)
        );

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employeeEntity, new EmployeeDto());
        if (employeeEntity.getKinhNghiem() != null) {

            try {
                List<Experience> experienceList = objectMapper.readValue(employeeEntity.getKinhNghiem(), new TypeReference<>() {
                });
                employeeDto.setKinhNghiem(experienceList);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return employeeDto;
    }

    @Override
    public EmployeeDto getEmployeeDetails(String employeeId) {
        EmployeeEntity employeeEntity = employeesRepository.findByMaNV(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Nhan Vien", "Ma Nhan Vien", employeeId)
        );

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employeeEntity, new EmployeeDto());
        if (employeeEntity.getKinhNghiem() != null) {

            try {
                List<Experience> experienceList = objectMapper.readValue(employeeEntity.getKinhNghiem(), new TypeReference<>() {
                });
                employeeDto.setKinhNghiem(experienceList);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return employeeDto;
    }
}
