import java.io.Serializable;
import java.util.GregorianCalendar;

public class Criminal implements Serializable{
	private GregorianCalendar birthDate;
	private String name,surname,crimes,picturePath;
	public Criminal(String name,String surname,GregorianCalendar birthDate,String crimes,String picturePath){
		this.name=name;
		this.setSurname(surname);
		this.crimes=crimes;
		this.birthDate=birthDate;
		this.setPicturePath(picturePath);
	}
	
	public GregorianCalendar getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(GregorianCalendar birthDate) {
		this.birthDate = birthDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCrimes() {
		return crimes;
	}
	public void setCrimes(String crimes) {
		this.crimes = crimes;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
}