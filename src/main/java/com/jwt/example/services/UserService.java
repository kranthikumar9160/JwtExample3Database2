package com.jwt.example.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.example.models.Role;
import com.jwt.example.models.User;
import com.jwt.example.repositories.RoleRepository;
import com.jwt.example.repositories.UserRepository;

@Service
public class UserService {
	private List<User> store = new ArrayList();
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserService() {
		store.add(new User(3, "ankit@gmail.com", "Ankit", "xyz"));
		store.add(new User(4, "gaurav@gmail.com", "Gaurav", "xyz"));
		store.add(new User(5, "durgesh@gmail.com", "durgesh", "xyz"));
		store.add(new User(6, "siva@gmail.com", "siva", "xyz"));
	}
	
	public List<User> getUsers(){
		return store;
	}
	
	public User addUser(User user) {
		User user1 = userRepository.save(user);
		
		return user1;
	}
	
//	public void initRolesAndUser() {
//
//		Role adminRole = new Role();
//		adminRole.setName("ADMIN");
//		roleRepository.save(adminRole);
//		
//		Role userRole = new Role();
//		userRole.setName("USER");
//		roleRepository.save(userRole);
//		
//		User adminUser = new User();
//		adminUser.setEmail("priya@gmail.com");
//		adminUser.setPassword(passwordEncoder.encode("123123"));
//		adminUser.setName("priya");
//		Set<Role> adminRoles = new HashSet<Role>();
//		adminRoles.add(adminRole);
//		
//		adminUser.setRoles(adminRoles);
//		
//		userRepository.save(adminUser);
//		
//		User user = new User();
//		user.setEmail("rakesh@gmail.com");
//		user.setName("rakesh");
//		user.setPassword(passwordEncoder.encode("123123123"));
//		Set<Role> userRoles = new HashSet<>();
//		userRoles.add(userRole);
//		
//		user.setRoles(userRoles);
//		
//		userRepository.save(user);
//		
//		
//	}
}
