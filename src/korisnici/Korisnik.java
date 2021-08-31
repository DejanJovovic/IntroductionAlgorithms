package korisnici;

import Enum.Pol;
import Enum.TipKorisnika;

public class Korisnik {
	
	static private int sledeciId=1; 
	protected String korisnicko_ime;
	protected String lozinka;
	protected String ime;
	protected String prezime;
	protected String JMBG;
	protected String adresa;
	protected Pol pol;
	protected String broj_telefona;
	protected TipKorisnika tip_korisnika;
	protected boolean obrisan;
	
	public Korisnik() {
		
	}
	
	public Korisnik(String korisnicko_ime, String lozinka, String ime, String prezime, String JMBG, String adresa,
			Pol pol, String broj_telefona, TipKorisnika tip_korisnika, boolean obrisan) {
		super();
		this.korisnicko_ime = korisnicko_ime;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.JMBG = JMBG;
		this.adresa = adresa;
		this.pol = pol;
		this.broj_telefona = broj_telefona;
		this.tip_korisnika = tip_korisnika;
		this.obrisan = obrisan;
	}
	
	public Korisnik(Korisnik k) {
		this.korisnicko_ime = k.korisnicko_ime;
		this.lozinka = k.lozinka;
		this.ime = k.ime;
		this.prezime = k.prezime;
		this.JMBG = k.JMBG;
		this.adresa = k.adresa;
		this.pol = k.pol;
		this.broj_telefona = k.broj_telefona;
		this.tip_korisnika = k.tip_korisnika;
		this.obrisan = k.obrisan;
	}
	
	
	public String getKorisnicko_ime() {
		return korisnicko_ime;
	}
	public void setKorisnicko_ime(String korisnicko_ime) {
		this.korisnicko_ime = korisnicko_ime;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getJMBG() {
		return JMBG;
	}
	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public Pol getPol() {
		return pol;
	}
	public void setPol(Pol pol) {
		this.pol = pol;
	}
	public String getBroj_telefona() {
		return broj_telefona;
	}
	public void setBroj_telefona(String broj_telefona) {
		this.broj_telefona = broj_telefona;
	}
	public TipKorisnika getTip_korisnika() {
		return tip_korisnika;
	}
	public void setTip_korisnika(TipKorisnika tip_korisnika) {
		this.tip_korisnika = tip_korisnika;
	}
	public boolean isObrisan() {
		return obrisan;
	}
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "Korisnik [korisnicko_ime=" + korisnicko_ime + ", lozinka=" + lozinka + ", ime=" + ime + ", prezime="
				+ prezime + ", JMBG=" + JMBG + ", adresa=" + adresa + ", pol=" + pol + ", broj_telefona="
				+ broj_telefona + ", tip_korisnika=" + tip_korisnika + ", obrisan=" + obrisan + "]";
	}
	


	public String stringZaUpisFajl() {
		return ""+korisnicko_ime+"|"+lozinka+"|"+ime+"|"+prezime+"|"+JMBG+"|"+adresa+"|"+pol+"|"+broj_telefona+"|"+tip_korisnika+"|"+obrisan;
	}
	
	/*kor,loz,ime,prez,jmbg,adresa,pol,telefon,tip,isObrisan*/
	
}

