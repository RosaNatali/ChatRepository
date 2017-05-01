package com.esprit.chat.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

	
	
	private String pseudo;
	private List<Message> messages;
	
	{
		messages=new ArrayList<Message>();
		
	}
	
	 public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (pseudo == null) {
			if (other.pseudo != null)
				return false;
		} else if (!pseudo.equals(other.pseudo))
			return false;
		return true;
	}

	public List<Message> getMessages() {
		
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
