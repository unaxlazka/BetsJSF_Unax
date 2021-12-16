package gui.bean;

import javax.faces.event.AjaxBehaviorEvent;

import bl.bean.FacadeBean;
import businessLogic.BLFacade;

public class RegisterBean {

	private String erabIzena;
	private String pasahitza;
	private String mezua;

	private boolean disable;

	private BLFacade blFacade;

	public RegisterBean() {

		this.erabIzena = "";
		this.pasahitza = "";
		this.mezua = "";

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

	public String erabiltzaileaSortu() {
		blFacade.register(this.erabIzena, this.pasahitza);
		return "registered";
	}

	public void disableAztertu(AjaxBehaviorEvent event) {
		if (this.erabIzena != "" && this.pasahitza != "")
			this.disable = false;
		else
			this.disable = true;
	}
}
