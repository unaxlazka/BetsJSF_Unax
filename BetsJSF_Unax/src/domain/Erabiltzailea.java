package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Erabiltzailea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer erabId;
	private String erabIzena;
	private String pasahitza;
	
	public Erabiltzailea() {
		super();
	}
	
	public Erabiltzailea(String izena, String pasahitza) {
		this.erabIzena = izena;
		this.pasahitza = pasahitza;
	}

	public Integer getErabId() {
		return erabId;
	}

	public void setErabId(Integer erabId) {
		this.erabId = erabId;
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

}
