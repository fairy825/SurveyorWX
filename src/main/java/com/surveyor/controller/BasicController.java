package com.surveyor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.surveyor.utils.RedisOperator;

@RestController
public class BasicController {
	
	@Autowired
	public RedisOperator redis;
	
	public static final String USER_REDIS_SESSION = "user-redis-session";
	public static final String START_TIME = "start-time";
		
		// 每页分页的记录数
		public static final Integer PAGE_SIZE = 7;
	
}
