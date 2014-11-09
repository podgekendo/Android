package com.example.practical3;

import java.io.Serializable;

public class Module implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String Name;
	private String CreditInfo;
	private String Description;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCreditInfo() {
		return CreditInfo;
	}

	public void setCreditInfo(String creditInfo) {
		CreditInfo = creditInfo;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
