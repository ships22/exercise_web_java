package com.spring_security_exmp.Address_Person.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring_security_exmp.Address_Person.config.SecurityConfig;
import com.spring_security_exmp.Address_Person.model.Address;
import com.spring_security_exmp.Address_Person.model.Person;
import com.spring_security_exmp.Address_Person.model.User;
import com.spring_security_exmp.Address_Person.repository.AddressRepository;
import com.spring_security_exmp.Address_Person.repository.PersonRepository;
import com.spring_security_exmp.Address_Person.repository.UserRepository;


@RestController
@RequestMapping(value = "/api")
public class AddressPersonController {
	
	@Autowired
	private SecurityConfig securityConfig;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value = "/person")
	public List<Person> getAllPerson() {
		System.out.println("here..");
		return personRepository.findAll();
	}
	
	@GetMapping(value = "/allAddress")
	public List<Address> getAllAddress() {
		return addressRepository.findAll();
	}
	
	@PostMapping(value = "/add_address", produces = "application/json")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Address addAddress(@RequestBody Address address) {
			return addressRepository.save(address);
	}
	
	@PostMapping(value = "/add_person", produces = "application/json")
	public String addPerson(@RequestBody Person person) {
		String encodedPassword = securityConfig.passwordEncoder().encode(person.getFirst_name());
		User userToAdd = new User(person.getEmail(),encodedPassword, "ROLE_USER");
		Optional<User> existing = userRepository.findByUserName(person.getEmail());
			if(existing.isPresent()) {
				return "an user already registered with e-mail.";
			} else {				
				User addedUser = userRepository.save(userToAdd);
				if(addedUser != null) {
					person.setUser(addedUser);
					personRepository.save(person);
					return "User added";
			}

		}
				return "Server is down";
	}

}
