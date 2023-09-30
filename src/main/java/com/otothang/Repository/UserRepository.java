package com.otothang.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.otothang.models.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer>{
	@Transactional
    @Modifying
    
    @Query("UPDATE User u SET u.userName = :newUsername, u.email = :newEmail,u.telephone = :newPhone,u.address = :newAddress WHERE u.id = :userId")
    void updateUserById(@Param("userId") Long userId,@Param("newUsername") String newUsername, @Param("newEmail") String newEmail,@Param("newPhone") String newPhone,@Param("newAddress") String newAddress);
}
