package com.intern.repository;

import java.util.List;

import com.intern.dto.todolisttrequest;
import com.intern.model.Todolist;

public interface todolistrepository {
	
	public void addtodo(Todolist Todolist);
	
	public List<todolisttrequest> viewbyid(Long user_id);
	
	public String findroles(Long user_id);
	
	public List<todolisttrequest> viewalltodo();
	
	public void Update(Long user_id,Long id,todolisttrequest Todolisttrequest);
	
	public void deletebyid(Long user_id,Long id);

}
