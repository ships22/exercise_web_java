package com.spring_security_exmp.Address_Person.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int no;
	private String street;
	private String town;
	private String district;
	private String country;
	
	public Address() {
		super();
	}
	
	public Address(int id, int no, String street, String town, String district, String country) {
		super();
		this.id = id;
		this.no = no;
		this.street = street;
		this.town = town;
		this.district = district;
		this.country = country;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

}
