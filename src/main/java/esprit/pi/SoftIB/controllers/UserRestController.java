package esprit.pi.SoftIB.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import esprit.pi.SoftIB.entities.User;
import esprit.pi.SoftIB.services.IUserService;

@Controller
public class UserRestController {

	@Autowired
	private IUserService userService;

	@GetMapping(value = "/getUser/{id}")
	public ResponseEntity getUser(@PathVariable Long id) {
		User user = null;
		try {
			user = userService.getUser(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@GetMapping(value = "/getUsers")
	public ResponseEntity getUsers() {
		List<User> users = new ArrayList<>();
		try {
			users = userService.getUsers();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@PostMapping(value = "/addUser")
	public ResponseEntity addUser(@RequestBody User user) {
		User userPostSave = null;
		try {
			Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
			user.setPassword(pbkdf2PasswordEncoder.encode(user.getPassword()));
			userPostSave = userService.addUser(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(userPostSave);
	}

	@PutMapping(value = "/updateUser/{id}")
	public ResponseEntity updateUser(@RequestBody User user, @PathVariable Long id) {
		User userPostSave = null;
		try {
			user.setId(id);
			userPostSave = userService.updateUser(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(userPostSave);
	}

	@DeleteMapping(value = "/deleteUser/{id}")
	public ResponseEntity deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("User deleted");
	}

}
