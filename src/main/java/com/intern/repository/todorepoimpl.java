package com.intern.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.intern.dto.todolisttrequest;
import com.intern.dto.usersrequest;
import com.intern.model.Todolist;
import com.intern.model.todolistrowmapper;

@Repository
public class todorepoimpl implements todolistrepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
//	@Override
//	public void addtodo(todolisttrequest Todolisttrequest) {
//		String sql="INSERT INTO todo(title,description,status,user_id) VALUES(?,?,?,?)";
//		jdbcTemplate.update(sql,Todolisttrequest.getTitle(),Todolisttrequest.getDescription(),Todolisttrequest.getStatus(),Todolisttrequest.getUserId());
//		
//	}

	@SuppressWarnings("deprecation")
	public void addtodo(Todolist Todolist) {
		String userCheckSql = "SELECT COUNT(*) FROM user_data WHERE user_id = ?";
		Integer count = jdbcTemplate.queryForObject(userCheckSql, new Object[] { Todolist.getUser_id() },
				Integer.class);

		if (count == null || count == 0) {
			throw new IllegalArgumentException("User ID does not exist.");
		}

		String sql = "INSERT INTO todo (title, description, status, user_id) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, Todolist.getTitle(), Todolist.getDescription(), Todolist.getStatus(),
				Todolist.getUser_id());
	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public List<todolisttrequest> viewbyid(Long user_id) {
		String sql = "SELECT * FROM todo WHERE user_id=?";

		return (List<todolisttrequest>) jdbcTemplate.query(sql, new Object[] { user_id }, new todolistrowmapper());

	}

	@SuppressWarnings("deprecation")
	@Override
	public String findroles(Long user_id) {
		String sql = "SELECT role FROM user_data WHERE user_id=?";

		return jdbcTemplate.queryForObject(sql, new Object[] { user_id }, String.class);
	}

	@Override
	public List<todolisttrequest> viewalltodo() {
		String sql = "SELECT * FROM todo";
		return jdbcTemplate.query(sql, new todolistrowmapper());
	}

	@Override
	public void Update(Long user_id,Long id, todolisttrequest Todolisttrequest) {
		String sql = "UPDATE todo SET title=?,description=?,status=? WHERE user_id=? AND id=?";
		jdbcTemplate.update(sql, Todolisttrequest.getTitle(), Todolisttrequest.getDescription(),
				Todolisttrequest.getStatus(), user_id,id);
	}

	@Override
	public void deletebyid(Long user_id,Long id) {
		String sql = "DELETE FROM todo WHERE user_id=? AND id=?";
		jdbcTemplate.update(sql, user_id,id);
	}

}
