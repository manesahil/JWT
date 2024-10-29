package com.intern.repository;

import java.util.List;

import com.intern.dto.usersrequest;
import com.intern.model.users;

public interface usersrepository {

	public void useradd(users Users);
	
	public List<usersrequest> viewall();
	
	public void deleteuser(Long User_id);
	
	public usersrequest viewbyid(Long User_id);
	
	public usersrequest FindUsername(String username);
}
