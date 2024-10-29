package com.intern.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.intern.dto.usersrequest;

public class usersrowmapper implements RowMapper<usersrequest>{

	@Override
	public usersrequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		usersrequest Users = new usersrequest();
		Users.setUser_id(rs.getInt("user_id"));
		Users.setUsername(rs.getString("username"));
		Users.setPassword(rs.getString("Password"));
		Users.setRole(rs.getString("role"));
		return Users;
	}

}
