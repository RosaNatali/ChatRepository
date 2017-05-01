package com.esprit.beans;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class MessageBean
{
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	private User receiver;
	private String bodyMessage;
	
	
	public String send()
	{
		Map<String, String> usersMap =  FacesContext.getCurrentInstance()
				   									.getExternalContext().getRequestParameterMap();
		String receiverPseudo = usersMap.get("receiverPseudo");
		String senderPseudo = usersMap.get("senderPseudo");
		Message message = new Message();
		
		User user = new User();
		user.setPseudo(senderPseudo);
		User receiver = userBean.getUser(receiverPseudo);
		
		message.setSender(user);
		message.setContent(bodyMessage);
		receiver.getMessages().add(message);
		
		return "chat?faces-redirect=true";
	}


	public UserBean getUserBean() {
		return userBean;
	}


	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}


	public User getReceiver() {
		return receiver;
	}


	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}


	public String getBodyMessage() {
		return bodyMessage;
	}


	public void setBodyMessage(String bodyMessage) {
		this.bodyMessage = bodyMessage;
	}
	
	
	
}
