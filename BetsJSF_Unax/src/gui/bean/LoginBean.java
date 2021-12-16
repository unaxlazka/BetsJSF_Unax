package gui.bean;

import javax.faces.event.AjaxBehaviorEvent;

import bl.bean.FacadeBean;
import businessLogic.BLFacade;
import domain.Erabiltzailea;

public class LoginBean {

	private String erabIzena;
	private String pasahitza;
	private String mezua;
	private Erabiltzailea u;

	private boolean disable;

	private BLFacade blFacade;

	public LoginBean() {

		this.erabIzena = "";
		this.pasahitza = "";
		this.mezua = "";
		this.u = null;

		this.disable = true;

		this.blFacade = FacadeBean.getBusinessLogic();
	}

	public String getErabIzena() {
		return erabIzena;
	}

	public void setErabIzena(String erabIzena) {
		this.erabIzena = erabIzena;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public String getMezua() {
		return mezua;
	}

	public void setMezua(String mezua) {
		this.mezua = mezua;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}
	
	

	public Erabiltzailea getU() {
		return u;
	}

	public void setU(Erabiltzailea u) {
		this.u = u;
	}

	public String erabiltzaileaLogeatu() {
		this.u = blFacade.login(this.erabIzena, this.pasahitza);
		return "loged";
	}
	
	public void erabiltzaileaLogout() {
		this.u = null;
	}

	public void disableAztertu(AjaxBehaviorEvent event) {
		if (this.erabIzena != "" && this.pasahitza != "")
			this.disable = false;
		else
			this.disable = true;
	}
}
