package com.intern.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intern.dto.usersrequest;
import com.intern.model.users;
import com.intern.repository.usersrepository;

@Service
public class userserviceimpl implements usersservice {

	@Autowired
	private usersrepository Usersrepository;

	@Override
	public void useradd(users Users) {
		Usersrepository.useradd(Users);
	}

	@Override
	public List<usersrequest> viewall() {
		return Usersrepository.viewall();
	}

	@Override
	public void deleteuser(Long User_id) {
		Usersrepository.deleteuser(User_id);

	}

	public usersrequest viewbyid(Long User_id) {
		return Usersrepository.viewbyid(User_id);
	}

	@Override
	public boolean ValidateUser(String username, String Password) {
		usersrequest users=Usersrepository.FindUsername(username);
		return users !=null && users.getPassword().equals(Password);
	}
	
	

}
