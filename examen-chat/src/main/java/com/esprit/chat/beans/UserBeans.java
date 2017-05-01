package com.esprit.chat.beans;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.esprit.chat.domain.User;

@ManagedBean
@ApplicationScoped
public class UserBeans {

	
	
	private Map<String, User> usersMap;
	

	
	@PostConstruct
	public void init() {

		usersMap = new HashMap<String, User>();

	}

	public Set<User> getUsers() {
		
		Set<User> users=new HashSet<User>();
		
		for(String pseudo:usersMap.keySet()){
			users.add(usersMap.get(pseudo));
		}
		
		
		
		return users;
	}

	public void addUser(User user) {
		usersMap.put(user.getPseudo(), user);
		
	}

	public User getUser(String receiverPseudo) {
		return usersMap.get(receiverPseudo);
		
	}

	public void remove(User connectedUser) {
	
		usersMap.remove(connectedUser.getPseudo());
		
		
	}

	
}
