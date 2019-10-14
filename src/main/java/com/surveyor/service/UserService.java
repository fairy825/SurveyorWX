package com.surveyor.service;

import com.surveyor.pojo.Users;

public interface UserService {
	public boolean queryUsernameIsExist(String username);
	public void saveUser(Users user);
	public Users queryUserForLogin(String username,String password);
	public void updateUserInfo(Users user);
	public void addPoint(String userId,float point);
	public Users queryUserInfo(String userId);
}
