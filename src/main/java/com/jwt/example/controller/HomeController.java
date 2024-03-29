package com.jwt.example.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.example.models.User;
import com.jwt.example.repositories.UserRepository;
import com.jwt.example.services.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	private UserRepository userRepository;
	
	//http://localhost:8081/home/getUsers
	
	@GetMapping("/getUsers")
	public List<User> getUsers(){
		System.out.println("Getting users");
		
		List<User> users = userService.getUsers();
		return users;
	}
	
	@GetMapping("/current-user")
	public String getCurrentUser(Principal principal) {
		return principal.getName();
	}
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		user.getUsername();
		user.getPassword();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userService.addUser(user);
	}
	
//	@PostConstruct
//	public void initRolesAndUser() {
//		userService.initRolesAndUser();
//	}
	
	
	@GetMapping("/forAdmin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String forAdmin(Principal principal) {
		return "This url is only accesible to admin";
//		String email = principal.getName();
//		
//		User user = userRepository.findByEmail(email);
//		
//		return user;
		
		
		
	}
}
