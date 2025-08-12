package com.udpt.employees.repository;

import com.udpt.employees.dto.DepartmentDto;
import com.udpt.employees.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
    Optional<DepartmentEntity> findById(int id);
    Optional<DepartmentEntity> findByTenKhoa(String tenKhoa);

    @Query("SELECT new com.udpt.employees.dto.DepartmentDto(d.tenKhoa, d.gioiThieu, h.hoTenNV, COUNT(e.maNV)) " +
            "FROM DepartmentEntity d JOIN EmployeeEntity h ON d.truongKhoa = h.maNV " +
            "LEFT JOIN EmployeeEntity e ON e.khoa.id = d.id " +
            "GROUP BY d.tenKhoa, d.gioiThieu, h.hoTenNV")
    List<DepartmentDto> findAllKhoaWithTruongKhoaAndSoBacSi();


}
