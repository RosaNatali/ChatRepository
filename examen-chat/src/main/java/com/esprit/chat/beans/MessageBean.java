package com.esprit.chat.beans;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.esprit.chat.domain.Message;
import com.esprit.chat.domain.User;

@RequestScoped
@ManagedBean
public class MessageBean {

	private String messageBody;

	@ManagedProperty("#{userBeans}")
	private UserBeans userBeans;
	
	
	public void send() {

		Map<String, String> params=FacesContext.getCurrentInstance()
		.getExternalContext().getRequestParameterMap();
		
		String receiverPseudo=params.get("receiverPseudo");
		
		String senderPseudo=params.get("senderPseudo");
		
		Message message = new Message();

		User receiver=userBeans.getUser(receiverPseudo);
	
		User user=new User();
		user.setPseudo(senderPseudo);
		
		message.setSender(user);
		message.setContent(messageBody);
		
		receiver.getMessages().add(message);
		
	}

	
	public void setUserBeans(UserBeans userBeans) {
		this.userBeans = userBeans;
	}
	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

}
