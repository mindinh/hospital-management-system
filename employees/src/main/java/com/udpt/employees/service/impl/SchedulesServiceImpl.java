package com.udpt.employees.service.impl;

import com.udpt.employees.dto.DoctorScheduleDto;
import com.udpt.employees.dto.ScheduleDto;
import com.udpt.employees.entity.EmployeeEntity;
import com.udpt.employees.exception.ResourceNotFoundException;
import com.udpt.employees.repository.EmployeesRepository;
import com.udpt.employees.repository.ScheduleRepository;
import com.udpt.employees.service.ISchedulesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SchedulesServiceImpl implements ISchedulesService {

    private EmployeesRepository employeesRepository;
    private ScheduleRepository scheduleRepository;

    @Override
    public DoctorScheduleDto getDoctorSchedule(String maBacSi) {
        EmployeeEntity employee = employeesRepository.findByMaNV(maBacSi).orElseThrow(
                () -> new ResourceNotFoundException("Bac Si", "Ma Bac Si", maBacSi)
        );

        List<ScheduleDto> scheduleDtoList = scheduleRepository.findByBacsi_MaNV(maBacSi).stream().map(
                entity -> {
                    ScheduleDto scheduleDto = new ScheduleDto();
                    scheduleDto.setMaBacSi(entity.getBacsi().getMaNV());
                    scheduleDto.setNgayLamViec(entity.getNgayLamViec());
                    scheduleDto.setGioBatDau(entity.getGioBatDau());
                    scheduleDto.setGioKetThuc(entity.getGioKetThuc());
                    scheduleDto.setPhong(entity.getSoPhong());

                    return scheduleDto;
                }
        ).toList();

        DoctorScheduleDto doctorScheduleDto = new DoctorScheduleDto();
        doctorScheduleDto.setMaBacSi(employee.getMaNV());
        doctorScheduleDto.setHoTenBacSi(employee.getHoTenNV());
        doctorScheduleDto.setLichLamViec(scheduleDtoList);

        return doctorScheduleDto;
    }

    @Override
    public void createSchedule(String maBacSi, LocalDate ngayLamViec, LocalTime batDau, LocalTime ketThuc) {

    }

}
