package korisnici;

import Enum.Pol;
import Enum.Telefonsko_odeljenje;
import Enum.TipKorisnika;

public class Dispecer extends Korisnik{
	
	public Dispecer(String korisnicko_ime, String lozinka, String ime, String prezime, String JMBG, String adresa,
			Pol pol, String broj_telefona, TipKorisnika tip_korisnika, boolean obrisan) {
		super(korisnicko_ime, lozinka, ime, prezime, JMBG, adresa, pol, broj_telefona, tip_korisnika, obrisan);
	}

	private int id;
	private int plata;
	private String broj_telefonske_linije;
	private Telefonsko_odeljenje odeljenje;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlata() {
		return plata;
	}
	public void setPlata(int plata) {
		this.plata = plata;
	}
	public String getBroj_telefonske_linije() {
		return broj_telefonske_linije;
	}
	public void setBroj_telefonske_linije(String broj_telefonske_linije) {
		this.broj_telefonske_linije = broj_telefonske_linije;
	}
	public Telefonsko_odeljenje getOdeljenje() {
		return odeljenje;
	}
	public void setOdeljenje(Telefonsko_odeljenje odeljenje) {
		this.odeljenje = odeljenje;
	}

	@Override
	public String toString() {
		return "Dispecer [id=" + id + ", plata=" + plata + ", broj_telefonske_linije=" + broj_telefonske_linije
				+ ", odeljenje=" + odeljenje + "]";
	}
	
}
