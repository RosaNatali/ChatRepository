package com.esprit.chat.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.esprit.chat.domain.User;

@ManagedBean
@SessionScoped
public class ConnectedUserBean {

	@ManagedProperty("#{userBeans}")
	UserBeans userBeans;

	private User receiver;

	public String contacter(User receiver) {
		this.receiver = receiver;

		return "envoi?faces-redirect=true";
	}

	public User getReceiver() {
		return receiver;
	}

	public void setUserBeans(UserBeans userBeans) {
		this.userBeans = userBeans;
	}

	private User connectedUser;

	private String pseudo;

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String join() {

		User user = new User();

		user.setPseudo(pseudo);
		if (userBeans.getUsers().contains(user)) {

			FacesContext.getCurrentInstance().addMessage("form1",
					new FacesMessage("error", "le pseudo existe déjà !"));
			return null;
		}

		userBeans.addUser(user);
		connectedUser = user;
		pseudo = "";
		return "secure/chat?faces-redirect=true";

	}

	
	
	public Set<User> getUsers(){
		
		
		Set<User> connectedUsers=new HashSet<User>(userBeans.getUsers());
		
		connectedUsers.remove(connectedUser);
		
		
		return connectedUsers ;
		
		
	}
	
	
	
	public List<User> getUsersList(){
		
		return new ArrayList<User>(getUsers());
	}
	
	
	public String quitter() {
		
		
		userBeans.remove(connectedUser);
		
		//connectedUser=null;
		HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext()
		.getSession(false);
		
		session.invalidate();
		
		
		return "/inscription?faces-redirect=true";
	}

	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
	}

}
