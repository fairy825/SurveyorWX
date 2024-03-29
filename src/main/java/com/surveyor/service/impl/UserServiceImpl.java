package com.surveyor.service.impl;
import com.surveyor.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import com.surveyor.mapper.UsersMapper;
import com.surveyor.pojo.Users;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UsersMapper usersMapper;
	@Autowired
	private Sid sid;
	
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public void addPoint(String userId,float point) {
		Users user = usersMapper.selectByPrimaryKey(userId);
		user.setPoint(user.getPoint()+point);
		usersMapper.updateByPrimaryKeySelective(user);
	}

	
	@Transactional(propagation= Propagation.SUPPORTS)
	@Override
	public boolean queryUsernameIsExist(String username) {
		Users user = new Users();
		user.setUsername(username);
		Users result = usersMapper.selectOne(user);
		return result==null?false:true;
	}

	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public void saveUser(Users user) {
		String userId = sid.nextShort();
		user.setId(userId);
		user.setPoint(20);
		usersMapper.insert(user);
	}
	
	@Transactional(propagation= Propagation.SUPPORTS)
	@Override
	public Users queryUserForLogin(String username,String password) {
		Example userExample = new Example(Users.class);
		Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("username", username);
		criteria.andEqualTo("password", password);
		Users result = usersMapper.selectOneByExample(userExample);
		
		return result;
	}
	
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public void updateUserInfo(Users user) {
		Example userExample = new Example(Users.class);
		Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("id",user.getId());
	    usersMapper.updateByExampleSelective(user, userExample);
	}
	
	@Transactional(propagation= Propagation.SUPPORTS)
	@Override
	public Users queryUserInfo(String userId) {
		Example userExample = new Example(Users.class);
		Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("id",userId);
		Users result = usersMapper.selectOneByExample(userExample);
		return result;
	}

}
