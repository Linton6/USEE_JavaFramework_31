package com.useeinfo.demo.modules.security.vo;

/**
 * 存放从Authentication里面计算出的username和password信息
 * Author: 张建平  Date: 13-8-5 下午1:20
 */
public class UsernamePasswordVO {
	private String username;

	private String password;

	public UsernamePasswordVO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UsernamePasswordVO{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
