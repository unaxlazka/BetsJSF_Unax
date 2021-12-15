package gui.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import bl.bean.FacadeBean;
import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateQuestionBean {

	private Date data;

	private String dataString;
	private List<Event> gertaerak;
	private Event gertaera;
	private String galBer;
//	private double aposMin;
	private String aposMin;
	private boolean disable;
	private String mezua;
//	private String lisau;//Hau erabiliko da taulan agertuko den balioa

	public String getAposMin() {
		return aposMin;
	}

	public void setAposMin(String aposMin) {
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
		this.gertaerak = new ArrayList<Event>();

		this.hutsa = "Hutsa";

		this.disable = true;

		this.galBer = "";
		this.aposMin = "";

//		this.listakoAukeratua = gertaeraEdoHutsa();

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

	public List<Event> getGertaerak() {
		return gertaerak;
	}

	public void setGertaerak(List<Event> gertaerak) {
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
		List<Event> gerList = blFacade.getEvents(data);
		if (gerList.size() != 0) {
			// Taularen gaineko testuaren balioa aldatu behar diot
			this.dataString = "Gertaerak: " + urt + " " + hil + " " + eg;
			this.gertaerak = gerList;
		} else {// Taularen gaineko testuaren balioa aldatu behar diot
			this.gertaera = null;// Hau egiten dut data berri bat aukeratzerakoan aurreko gertaera jada gorde ez
									// dadin.
			this.dataString = "Ez daude gertaerak: " + urt + " " + hil + " " + eg;
			this.gertaerak = new ArrayList<Event>();// Aurretik zeuden gertaerak ezabatu
		}
	}

	public void eguneratzeaIkusi(AjaxBehaviorEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				"Gertaera eguneratu da:" + gertaera.getDescription() + "/" + gertaera.getEventNumber()));
	}

	public void disableAztertu(AjaxBehaviorEvent event) {
		try {
			if (this.gertaerak.isEmpty() || this.gertaera == null) {// Kasu honetan esan nahi du gertaera ez dela
																	// existitzen edota jasota guguna jada ez dela
																	// egokia
				this.mezua = "Gertaera aukeratu behar duzu.";
				this.disable = true;
			} else if (this.galBer == "") {
				this.mezua = "Galdera bat idatzi behar duzu.";
				this.disable = true;
			} else if (galderaAztertu()) {
				this.mezua = "Gertaera honetan jada galdera hau existitzen da.";
				this.disable = true;
			} else if (!(Float.parseFloat(this.aposMin) > 0)) {
				this.mezua = "Sartu berri duzun apostu minimo balioa ez da nahikoa.";
				this.disable = true;
			} else {
				this.mezua = "";
				this.disable = false;
			}
		} catch (Exception e) {
			this.mezua = "Apostu minimoaren balioa zenbaki bat izan behar du.";
			this.disable = true;
		}
	}

	public String getMezua() {
		return mezua;
	}

	public void setMezua(String mezua) {
		this.mezua = mezua;
	}

	public void eguneratzeaIkusiEtaDisableAztertu(AjaxBehaviorEvent event) {
		eguneratzeaIkusi(event);
		disableAztertu(event);
	}

	public void getEventsEtaDisableAztertu(AjaxBehaviorEvent event) {
		getEvents();
		disableAztertu(event);
	}

	public String gertaeraEdoHutsa() {
		if (this.gertaerak.size() == 0)
			return getHutsa();
		else
			return getGertaera().getDescription();
	}

	public void galderaSortu() throws EventFinished, QuestionAlreadyExist {
		this.blFacade.createQuestion(gertaera, galBer, Float.parseFloat(aposMin));
		this.mezua="Galdera berria sortu da";
	}

	public boolean galderaAztertu() {
//		Vector<Question> galderak = gertaera.getQuestions();
		return gertaera.DoesQuestionExists(galBer);
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
