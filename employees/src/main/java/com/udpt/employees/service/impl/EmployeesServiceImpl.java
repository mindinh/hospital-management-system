package com.udpt.employees.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udpt.employees.dto.EmployeeDto;
import com.udpt.employees.entity.*;
import com.udpt.employees.exception.EmployeeAlreadyExistException;
import com.udpt.employees.exception.ResourceNotFoundException;
import com.udpt.employees.mapper.EmployeeMapper;
import com.udpt.employees.repository.DepartmentRepository;
import com.udpt.employees.repository.EmployeesRepository;
import com.udpt.employees.repository.ScheduleRepository;
import com.udpt.employees.request.DoctorInsertRequest;
import com.udpt.employees.request.EmployeeInsertRequest;
import com.udpt.employees.request.ScheduleInsertRequest;
import com.udpt.employees.service.IEmployeesService;
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
    private DepartmentRepository departmentRepository;
    private ScheduleRepository scheduleRepository;

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
        employeeDto.setChuyenKhoa(employeeEntity.getKhoa().getTenKhoa());
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

    @Override
    public boolean updateDepartment(String employeeId, int departmentId) {
        EmployeeEntity employee = employeesRepository.findByMaNV(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Bac Si", "Ma Bac Si", employeeId)
        );

        DepartmentEntity department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Khoa", "Ma Khoa", String.valueOf(departmentId))
        );

        employee.setKhoa(department);
        employeesRepository.save(employee);
        return true;
    }

    @Override
    public void addSchedule(ScheduleInsertRequest request) {
        EmployeeEntity employee = employeesRepository.findByMaNV(request.maBacSi()).orElseThrow(
                () -> new ResourceNotFoundException("Bac Si", "Ma Bac Si", request.maBacSi())
        );

        List<ScheduleEntity> schedules = request.lichLamViec().stream().map(
                dto -> {
                    ScheduleEntity schedule = new ScheduleEntity();
                    schedule.setBacsi(employee);
                    schedule.setNgayLamViec(dto.getNgayLamViec());
                    schedule.setGioBatDau(dto.getGioBatDau());
                    schedule.setGioKetThuc(dto.getGioKetThuc());
                    schedule.setSoPhong(dto.getPhong());

                    schedule.setCreatedAt(LocalDateTime.now());
                    schedule.setCreatedBy("employees-service");

                    return schedule;
                }
        ).toList();

        scheduleRepository.saveAll(schedules);
    }
}
