package com.bach.restapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bach.restapi.model.User;

@RestController
public class UserController {

	private List<User> users = new ArrayList<>();
	
	@PostMapping("/user")
	public User create(@RequestBody User user) {
		
		users.add(user);
		
		return user;
	}
	
	@GetMapping("users")
	public List<User> getAll(){	
		return users;
	}
	
	@DeleteMapping("/user")
	public void delete(@RequestParam(name = "id") int id) {
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getId() == id) {
				users.remove(i);
				break;
			}
		}
	}
	
	@PutMapping("/user")
	public void update(@RequestBody User user) {
		for(int i = 0; i < users.size() ; i++) {
			if(users.get(i).getId() == user.getId()) {
				// Thay thằng có id trùng với id mà ta tìm thấy bằng 
				// thằng đối tượng mới ta gửi đi trong body.
				users.set(i, user);
				break;
			}
		}
	}
}
