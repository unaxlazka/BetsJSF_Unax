package gui.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import bl.bean.FacadeBean;
import businessLogic.BLFacade;
import domain.Event;
import domain.Question;

public class QueryQuestionsBean {
	private static Date data;

	private String dataString;
	private Vector<Event> gertaerak;
	private Event gertaera;

	private String gertaeraString;
	private Vector<Question> galderak;
	private Question galdera;

	private BLFacade blFacade;

	public QueryQuestionsBean() {
		this.dataString = "Gertaerak";
		this.gertaeraString = "Galderak";
		this.gertaerak = new Vector<Event>();
		this.galderak = new Vector<Question>();
		this.blFacade = FacadeBean.getBusinessLogic();
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
			this.dataString = "Ez daude gertaerak: " + urt + " " + hil + " " + eg;
			this.gertaerak = new Vector<Event>();// Aurretik zeuden gertaerak ezabatu
		}
	}

	public void getQuestions() {
		Vector<Question> galList = gertaera.getQuestions();
		if (galList.size() != 0) {
			this.gertaeraString = "Galderak hurrengo gertaerarako " + gertaera.getDescription();
			this.galderak = galList;
		} else {
			this.gertaeraString = "Ez daude galderak hurrengo gertaerarako : " + gertaera.getDescription();
			this.galderak = new Vector<Question>();
		}
	}

	public Vector<Question> getGalderak() {
		return galderak;
	}

	public void setGalderak(Vector<Question> galderak) {
		this.galderak = galderak;
	}

	public Question getGaldera() {
		return galdera;
	}

	public void setGaldera(Question galdera) {
		this.galdera = galdera;
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

	public String getGertaeraString() {
		return gertaeraString;
	}

	public void setGertaeraString(String gertaeraString) {
		this.gertaeraString = gertaeraString;
	}

	public static String getHilabeteIzena() {
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
