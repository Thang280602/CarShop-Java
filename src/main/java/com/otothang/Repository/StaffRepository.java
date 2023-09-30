package com.otothang.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.otothang.models.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
	@Query("SELECT c FROM Staff c where c.name like %?1%")
	List<Staff> searchStaff(String keyword);
}
