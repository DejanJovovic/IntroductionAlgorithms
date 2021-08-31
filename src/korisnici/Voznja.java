package korisnici;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import Enum.Status_voznje;

public class Voznja {
	
	private static int sledeciId=1;
	private int id;
	private String datum;
	private String adresa1;
	private String adresa2;
	private String musterija;
	private String vozac;
	private int broj_km;
	private int trajanje_voznje;
	private Status_voznje status_voznje;
	
	public Voznja() {
		
	}
	
	public String getDatumString() {
		return datum;
	}
	
	public String getDatum() {
		return datum;
	}
	
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getAdresa1() {
		return adresa1;
	}
	public void setAdresa1(String adresa1) {
		this.adresa1 = adresa1;
	}
	public String getAdresa2() {
		return adresa2;
	}
	public void setAdresa2(String adresa2) {
		this.adresa2 = adresa2;
	}
	public String getMusterija() {
		return musterija;
	}
	public void setMusterija(String musterija) {
		this.musterija = musterija;
	}
	public String getVozac() {
		return vozac;
	}
	public void setVozac(String vozac) {
		this.vozac = vozac;
	}
	public int getBroj_km() {
		return broj_km;
	}
	public void setBroj_km(int broj_km) {
		this.broj_km = broj_km;
	}
	public int getTrajanje_voznje() {
		return trajanje_voznje;
	}
	public void setTrajanje_voznje(int trajanje_voznje) {
		this.trajanje_voznje = trajanje_voznje;
	}
	public Status_voznje getStatus_voznje() {
		return status_voznje;
	}
	public void setStatus_voznje(Status_voznje status_voznje) {
		this.status_voznje = status_voznje;
	}
	
	public Voznja(int id,String datum, String adresa1, String adresa2, String musterija, String vozac, int broj_km,
			int trajanje_voznje, Status_voznje status_voznje) {
		super();
		this.id=id;
		if(id>=sledeciId) sledeciId=id+1;
		this.datum=datum;
		this.adresa1 = adresa1;
		this.adresa2 = adresa2;
		this.musterija = musterija;
		this.vozac = vozac;
		this.broj_km = broj_km;
		this.trajanje_voznje = trajanje_voznje;
		this.status_voznje = status_voznje;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getSledeciId() {
		return sledeciId;
	}

	public String stringZaUpisFajl() {
		return ""+id+"|"+getDatumString()+"|"+adresa1+"|"+adresa2+"|"+musterija+"|"+vozac+"|"+broj_km+"|"+trajanje_voznje+"|"+status_voznje;
	}

}
