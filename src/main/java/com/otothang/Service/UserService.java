package com.otothang.Service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.otothang.models.User;

public interface UserService {
	List<User> getALL();
	User findByUserName(String userName);
	Boolean create(User user);
	User create1(User user);
	void updateUserById(Long userId,String newUsername,  String newEmail, String newPhone, String newAddress);
}
