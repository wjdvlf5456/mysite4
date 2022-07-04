package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;

@Service
public class RboardService {
	
	@Autowired
	private RboardDao rboardDao;

}
