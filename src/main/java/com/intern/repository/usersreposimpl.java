package com.intern.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.intern.dto.usersrequest;
import com.intern.model.users;
import com.intern.model.usersrowmapper;

@Repository
public class usersreposimpl implements usersrepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void useradd(users Users)
	{
		String sql="INSERT INTO user_data(username,password,role)VALUES(?,?,?)";
		jdbcTemplate.update(sql,Users.getUsername(),Users.getPassword(),Users.getRole());
	}

	@Override
	public List<usersrequest> viewall() {
		String sql="SELECT * FROM user_data";
		return jdbcTemplate.query(sql,new usersrowmapper());
	}

	public void deleteuser(Long User_id) {
		String sql="DELETE FROM user_data WHERE User_id=?";
		jdbcTemplate.update(sql,User_id);
		
	}
	
	public usersrequest FindUsername(String username)  {
		String sql="SELECT * FROM user_data WHERE username=?";
		return jdbcTemplate.queryForObject(sql,new usersrowmapper(),username);
	}

	@SuppressWarnings("deprecation")
	@Override
	public usersrequest viewbyid(Long User_id) {
		String sql="SELECT * FROM user_data WHERE User_id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {User_id},new usersrowmapper());
	}

	
	
	
}
