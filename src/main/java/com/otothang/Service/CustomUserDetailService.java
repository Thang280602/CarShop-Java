package com.otothang.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.otothang.models.CustomUserDetails;
import com.otothang.models.User;
import com.otothang.models.User_Role;
@Service
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userService.findByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException("Lỗi");
		}
		Collection<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
		Set<User_Role> roles = user.getUserRoles();
		for (User_Role userRole : roles) {
			grantedAuthoritySet.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
			
		}
		return new CustomUserDetails(user,grantedAuthoritySet);
	}

}
