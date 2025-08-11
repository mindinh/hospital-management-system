package com.udpt.patients.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordDto {

    private LocalDate ngayKham;
    private String chanDoan;
    private String trieuChung;
    private String ghiChu;

}
