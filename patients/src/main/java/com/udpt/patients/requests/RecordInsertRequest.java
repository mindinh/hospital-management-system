package com.udpt.patients.requests;

import java.time.LocalDateTime;

public record RecordInsertRequest(
        String maBenhNhan,
        String maBacSi,
        String ngayKham,
        String trieuChung,
        String chanDoan,
        String ghiChu
) {
}
