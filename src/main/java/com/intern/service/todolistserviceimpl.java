package com.intern.service;

import java.util.Collections;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.intern.dto.todolisttrequest;
import com.intern.model.Todolist;
import com.intern.repository.todolistrepository;

@Service
public class todolistserviceimpl implements todoservice {

	@Autowired
	private todolistrepository todolistrepository;

	@Override
	public void addtodo(Todolist Todolist) {
		todolistrepository.addtodo(Todolist);
	}

	@Override
	public List<todolisttrequest> viewbyid(Long user_id) {

		return todolistrepository.viewbyid(user_id);
	}

//	@Override
//	public List<todolisttrequest> findallbyrole(Long user_id) {
//		
//			String role = todolistrepository.findroles(user_id);
//			if ("ADMIN".equalsIgnoreCase(role)) {
//				return todolistrepository.viewalltodo();
//			}
//		
//			else {
//				return null;
//			}
//
//		}

	public List<todolisttrequest> findallbyrole(Long userId) {

		String role = todolistrepository.findroles(userId);

		if ("ADMIN".equalsIgnoreCase(role)) {
			return todolistrepository.viewalltodo();
		}

		return Collections.emptyList();
	}

	@Override
	public void Update(Long user_id,Long id,todolisttrequest Todolisttrequest) {
		todolistrepository.Update(user_id,id,Todolisttrequest);
	}

	@Override
	public void deletebyid(Long user_id,Long id) {
		todolistrepository.deletebyid(user_id,id);
	}

}
