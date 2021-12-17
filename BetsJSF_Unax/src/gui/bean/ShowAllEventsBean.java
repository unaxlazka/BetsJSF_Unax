package gui.bean;

import java.util.List;

import bl.bean.FacadeBean;
import businessLogic.BLFacade;
import domain.Event;

public class ShowAllEventsBean {
	private List<Event> gertaerak;
	
	private BLFacade blFacade;
	
	public ShowAllEventsBean() {		
		this.blFacade = FacadeBean.getBusinessLogic();
		
		this.gertaerak = this.blFacade.getEvents();

	}

	public List<Event> getGertaerak() {
		return gertaerak;
	}

	public void setGertaerak(List<Event> gertaerak) {
		this.gertaerak = gertaerak;
	}
	
	
}
