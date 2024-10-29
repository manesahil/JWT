package com.intern.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.intern.dto.todolisttrequest;

public class todolistrowmapper implements RowMapper<todolisttrequest>{

	@Override
	public todolisttrequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		todolisttrequest todolist = new todolisttrequest();
		todolist.setId(rs.getInt("id"));
		todolist.setTitle(rs.getString("title"));
		todolist.setDescription(rs.getString("description"));
		todolist.setStatus(rs.getString("status"));
		todolist.setUser_id(rs.getInt("user_id"));
		return todolist;
	}

}
