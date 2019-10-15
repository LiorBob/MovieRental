package myObjects;

import java.util.ArrayList;

public class UserEntry 
{
	private int userID;
	private boolean isManger;
	private String eMail;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
        private ArrayList<RentEntry> rents;

    public ArrayList<RentEntry> getRents() {
        return rents;
    }

    public void setRents(ArrayList<RentEntry> rents) {
        this.rents = rents;
    }
	
	public int getUserId() {
		return userID;
	}
	public void setUserId(int userId) {
		this.userID = userId;
	}
	public boolean isManger() {
		return isManger;
	}
	public void setManger(boolean isManger) {
		this.isManger = isManger;
	}
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String mail) {
		eMail = mail;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
