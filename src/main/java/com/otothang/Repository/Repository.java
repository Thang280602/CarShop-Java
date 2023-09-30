package com.otothang.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otothang.models.User;


public interface Repository extends JpaRepository<User, Long>{
	User findByUserName(String userName);
}
