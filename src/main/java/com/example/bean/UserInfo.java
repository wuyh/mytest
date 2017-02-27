package com.example.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class UserInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long uid;
	
	@Column(unique=true)
	private String userName;
	
	private String name;
	
	private String password;
	
	private String salt;
	
	private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="uid")},inverseJoinColumns={@JoinColumn(name="roleId")})
	private List<SysRole> roleList;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	public String getCredentialSalt(){
		return this.userName+this.salt;
	}
	
	@Override
	public String toString() {
		return "UserInfo [uId="+uid+",userName=" + userName + ", name=" + name + ", password=" + password
              + ", salt=" + salt + ", state=" + state + "]";
	}
	
}
