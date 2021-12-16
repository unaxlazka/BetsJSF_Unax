package gui.bean;

import javax.faces.context.FacesContext;

public class Button {

	private boolean hide;
	
	public Button(){
//		LoginBean lg = new LoginBean();
		LoginBean lg = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{login}", LoginBean.class);

		this.hide = lg.getU()==null;
	}

	public boolean isHide() {
		LoginBean lg = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{login}", LoginBean.class);
//		LoginBean lg = new LoginBean();
		this.hide = lg.getU()==null;
		if (lg.getU()!=null)
			System.out.println("Honera iritsi da " + lg.getU().getErabIzena());

		return hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}
	
	
}
