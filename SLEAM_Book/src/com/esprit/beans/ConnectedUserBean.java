package com.esprit.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class ConnectedUserBean
{
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	private String pseudo; 
	private User connectedUser;
	private User receiver;
	
	
	public String join()
	{
		User user = new User();
		user.setPseudo(pseudo);
		if(userBean.getUsers().contains(user))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", "Sorry, your identication has been failed. Please retry again !.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
		userBean.addUser(user);
		connectedUser = user;
			
		return "chat?faces-redirect=true";
	}
	
	public String contact(User receiver)
	{
		this.receiver = receiver;
		return "send?faces-redirect=true";
	}
	
	public String quit()
	{
		userBean.removeUser(connectedUser);
		HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "logIn?faces-redirect=true";
	}
	
	public Set<User> getUsers()
	{
		Set<User> sus = new HashSet<User>(userBean.getUsers());
		sus.remove(connectedUser);
		return sus;
	}

	
	
	
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	
	
	
	
}
