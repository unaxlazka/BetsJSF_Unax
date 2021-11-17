package gui.bean;

import java.util.Date;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import bl.bean.FacadeBean;
import businessLogic.BLFacade;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateQuestionBean {

	private Date data;

	private String dataString;
	private Vector<Event> gertaerak;
	private Event gertaera;
	private String galBer;
	private double aposMin;
	private boolean disable;

	public double getAposMin() {
		return aposMin;
	}

	public void setAposMin(double aposMin) {
		this.aposMin = aposMin;
	}

	public String getGalBer() {
		return galBer;
	}

	public void setGalBer(String galBer) {
		this.galBer = galBer;
	}

	private String hutsa;

	private BLFacade blFacade;

	public CreateQuestionBean() {
		this.dataString = "Gertaerak";
		this.gertaerak = new Vector<Event>();

		this.hutsa = "Hutsa";

		this.disable = true;

		this.galBer = "";
		this.aposMin = 0;

		this.blFacade = FacadeBean.getBusinessLogic();
	}

	public String getHutsa() {
		return hutsa;
	}

	public void setHutsa(String hutsa) {
		this.hutsa = hutsa;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDataString() {
		return dataString;
	}

	public void setDataString(String dataString) {
		this.dataString = dataString;
	}

	public Vector<Event> getGertaerak() {
		return gertaerak;
	}

	public void setGertaerak(Vector<Event> gertaerak) {
		this.gertaerak = gertaerak;
	}

	public Event getGertaera() {
		return gertaera;
	}

	public void setGertaera(Event gertaera) {
		this.gertaera = gertaera;
	}

	public void getEvents() {
		Integer eg = data.getDate();
		String hil = getHilabeteIzena();
		Integer urt = data.getYear() + 1900;
		Vector<Event> gerList = blFacade.getEvents(data);
		if (gerList.size() != 0) {
			// Taularen gaineko testuaren balioa aldatu behar diot
			this.dataString = "Gertaerak: " + urt + " " + hil + " " + eg;
			this.gertaerak = gerList;
		} else {// Taularen gaineko testuaren balioa aldatu behar diot
			this.gertaera=null;//Hau egiten dut data berri bat aukeratzerakoan aurreko gertaera jada gorde ez dadin.
			this.dataString = "Ez daude gertaerak: " + urt + " " + hil + " " + eg;
			this.gertaerak = new Vector<Event>();// Aurretik zeuden gertaerak ezabatu
		}
	}

	public void eguneratzeaIkusi(AjaxBehaviorEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				"Gertaera eguneratu da:" + gertaera.getDescription() + "/" + gertaera.getEventNumber()));
	}

	public void disableAztertu(AjaxBehaviorEvent event) {
		if (this.aposMin != 0 && this.galBer != "" && this.gertaera != null) {
			this.disable = false;
		} else {
			this.disable = true;
		}
	}
	
	public void eguneratzeaIkusiEtaDisableAztertu(AjaxBehaviorEvent event) {
		eguneratzeaIkusi(event);
		disableAztertu(event);
	}

	public void galderaSortu() throws EventFinished, QuestionAlreadyExist {
		this.blFacade.createQuestion(gertaera, galBer, (float) aposMin);
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public String getHilabeteIzena() {
		int hil = data.getMonth();
		if (hil == 0)
			return "Urt";
		if (hil == 1)
			return "Ots";
		if (hil == 2)
			return "Mrt";
		if (hil == 3)
			return "Apr";
		if (hil == 4)
			return "Mai";
		if (hil == 5)
			return "Ekn";
		if (hil == 6)
			return "Uzt";
		if (hil == 7)
			return "Abz";
		if (hil == 8)
			return "Irl";
		if (hil == 9)
			return "Urr";
		if (hil == 10)
			return "Azr";
		if (hil == 11)
			return "Abd";
		return "Errorea";
	}
}
