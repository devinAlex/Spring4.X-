package com.smart.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;

public class UserServiceTest {
	private UserService userService;
	private UserDao userDao;
	private LoginLogDao loginLogDao;
	
	@Before
	public void setUp(){
		userService = PowerMockito.spy(new UserService());
		userDao = PowerMockito.mock(UserDao.class);
		loginLogDao = PowerMockito.mock(LoginLogDao.class);
		Whitebox.setInternalState(userService, "userDao", userDao);
		Whitebox.setInternalState(userService, "loginLogDao", loginLogDao);
	}
	@Test
	public void testHasMatchUser(){
		PowerMockito.when(userDao.getMatchCount(Mockito.anyString(), Mockito.anyString())).thenReturn(2);
		userService.hasMatchUser("110", "22");
	}
	@Test
	public void testFindUserByUserName(){
		PowerMockito.when(userDao.findUserByUserName(Mockito.anyString())).thenReturn(new User());
		userService.findUserByUserName("11");
	}
	@Test
	public void testLoginSuccess() throws Exception{
		PowerMockito.doNothing().when(userDao, "updateLoginInfo", Mockito.any(User.class));
		PowerMockito.doNothing().when(loginLogDao, "insertLoginLog", Mockito.any(LoginLog.class));
		userService.loginSuccess(new User());
	}
	
}
