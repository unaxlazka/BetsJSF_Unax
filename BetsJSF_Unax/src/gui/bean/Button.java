package gui.bean;

import javax.faces.context.FacesContext;

public class Button {

	private boolean hide;
	private boolean nothide;

	public Button() {
//		LoginBean lg = new LoginBean();
		LoginBean lg = FacesContext.getCurrentInstance().getApplication()
				.evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{login}", LoginBean.class);

		this.hide = lg.getU() == null;
		this.nothide = lg.getU() != null;
	}

	public boolean isHide() {
		LoginBean lg = FacesContext.getCurrentInstance().getApplication()
				.evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{login}", LoginBean.class);
//		LoginBean lg = new LoginBean();
		this.hide = lg.getU() == null;

		return hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}

	public boolean isNothide() {
		LoginBean lg = FacesContext.getCurrentInstance().getApplication()
				.evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{login}", LoginBean.class);
		this.nothide = lg.getU() != null;

		return nothide;
	}

	public void setNothide(boolean nothide) {
		this.nothide = nothide;
	}

}
