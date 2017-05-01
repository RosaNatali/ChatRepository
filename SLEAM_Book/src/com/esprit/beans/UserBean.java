package com.esprit.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@ApplicationScoped
public class UserBean
{
	
	private Map<String, User> usersMap;
	
	@PostConstruct
	public void Init()
	{
		usersMap = new HashMap<String, User>();
	}
	
	public Set<User> getUsers()
	{
		Set<User> users = new HashSet<User>();
		for(String pseudo : usersMap.keySet())
		{
			users.add(usersMap.get(pseudo));
		}
		return users;
	}
	
	public User getUser(String receiverPseudo)
	{
		return usersMap.get(receiverPseudo);
	}
	
	public void addUser(User user)
	{
		usersMap.put(user.getPseudo(), user);
	}
	
	public void removeUser(User connectedUser)
	{
		usersMap.remove(connectedUser.getPseudo());
	}

	
	
	
	public Map<String, User> getUsersMap() {
		return usersMap;
	}

	public void setUsersMap(Map<String, User> usersMap) {
		this.usersMap = usersMap;
	}
	
	
	
	
	
	
}
