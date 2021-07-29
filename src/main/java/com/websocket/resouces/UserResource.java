package com.websocket.resouces;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.websocket.storage.UserStorage;

@CrossOrigin("*")
@RestController
public class UserResource {

	@GetMapping("/registration/{userName}")
	public ResponseEntity<?> register(@PathVariable String userName){
		System.out.println("Save user "+userName);
		try {
			UserStorage.getInstance().setUsers(userName);
			return ResponseEntity.ok(userName);
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/fetchAllUsers")
	public Set<String> fetchAll(){
		return UserStorage.getInstance().getUsers();
	}
}
