package com.udpt.appointments.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CreateServiceDesignationCommand {

    private int maDichVu;
    private String maLichKham;
    private String tenDichVu;
    private String moTa;
    private String soPhong;

}
