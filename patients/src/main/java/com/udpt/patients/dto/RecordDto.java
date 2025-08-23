package com.udpt.patients.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecordDto {

    private LocalDateTime ngayKham;
    private String chanDoan;
    private String trieuChung;
    private String ghiChu;

}
