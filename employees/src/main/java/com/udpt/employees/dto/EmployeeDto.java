package com.udpt.employees.dto;

import com.udpt.employees.entity.Experience;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeDto {
    private String maNV;
    private String hoTen;
    private LocalDate ngaySinh;
    private String chuyenKhoa;
    private List<Experience> kinhNghiem;
}
