package com.account.customer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customers")

public class CustomerModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id

	private String cpf;
	private String firstName;
	private String lastName;
	private String sex;
	private String email;
	private String nationality;
	private String naturalness;
	private String address;
	private Date dateOfBirth;
	private Date createdDate;
	private Date updatedDate;
	
	public CustomerModel(String cpf, String firstName, String lastName, String sex, String email, String nationality, String naturalness, String address, Date dateOfBirth, Date createdDate, Date updatedDate) {
		this.cpf = cpf;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.email = email;
		this.nationality = nationality;
		this.naturalness = naturalness;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
		
	public CustomerModel() {
	
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNaturalness() {
		return naturalness;
	}

	public void setNaturalness(String naturalness) {
		this.naturalness = naturalness;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
