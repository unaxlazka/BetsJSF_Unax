package gui.bean;

import domain.Erabiltzailea;

public class UserBean {

	private Erabiltzailea erab;
	
	public UserBean() {
		this.erab = null;
	}

	public Erabiltzailea getErab() {
		return erab;
	}

	public void setErab(Erabiltzailea erab) {
		this.erab = erab;
	}
	
}
