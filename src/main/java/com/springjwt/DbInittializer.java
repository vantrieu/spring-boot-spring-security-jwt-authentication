package com.springjwt;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.springjwt.models.ERole;
import com.springjwt.models.Role;
import com.springjwt.models.User;
import com.springjwt.repository.RoleRepository;
import com.springjwt.repository.UserRepository;

@Component
public class DbInittializer implements CommandLineRunner{
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public void run(String... Strings) throws Exception {
		this.userRepository.deleteAll();
		this.roleRepository.deleteAll();
		
		Role role1 = new Role(ERole.ROLE_ADMIN);
		Role role2 = new Role(ERole.ROLE_MODERATOR);
		Role role3 = new Role(ERole.ROLE_USER);
		
		this.roleRepository.save(role1);
		this.roleRepository.save(role2);
		this.roleRepository.save(role3);User user = new User("admin", "admin@gmail.com", encoder.encode("12345678"));
		Set<Role> roles = new HashSet<>();
		Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(adminRole);
		user.setRoles(roles);
		userRepository.save(user);
		user = this.userRepository.save(user);
	}
	
	
}
