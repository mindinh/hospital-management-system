package com.udpt.employees.mapper;

import com.udpt.employees.dto.EmployeeDto;
import com.udpt.employees.entity.EmployeeEntity;

public class EmployeeMapper {
    private EmployeeMapper() {}

    public static EmployeeDto mapToEmployeeDto(EmployeeEntity employeeEntity, EmployeeDto employeeDto) {
        employeeDto.setHoTen(employeeEntity.getHoTenNV());
        employeeDto.setNgaySinh(employeeEntity.getNgaySinhNV());


        return employeeDto;
    }
}
