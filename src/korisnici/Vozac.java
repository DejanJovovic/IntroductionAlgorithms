package korisnici;

import Enum.Pol;
import Enum.TipKorisnika;
import Taksi_sluzba.Taksi_sluzba;

public class Vozac extends Korisnik{

	private int plata;
	private String broj_clanske_karte;
	private int automobilId;
	private float ocena;
	public Vozac() {
		
	}
	
	public Vozac(String korisnicko_ime, String lozinka, String ime, String prezime, String JMBG, String adresa, Pol pol,
			String broj_telefona, TipKorisnika tip_korisnika, boolean obrisan, int plata,
			String broj_clanske_karte, int automobilID) {
		super(korisnicko_ime, lozinka, ime, prezime, JMBG, adresa, pol, broj_telefona, tip_korisnika, obrisan);
		this.plata = plata;
		this.broj_clanske_karte = broj_clanske_karte;
		this.automobilId = automobilID;
	}
	
	public Vozac(Korisnik k,int plata,String clanska_karta,int automobilId,float ocena) {
		super(k);
		this.plata = plata;
		this.broj_clanske_karte = clanska_karta;
		this.automobilId = automobilId;
		this.ocena=ocena;
	}

	public int getPlata() {
		return plata;
	}
	public void setPlata(int plata) {
		this.plata = plata;
	}
	public String getBroj_clanske_karte() {
		return broj_clanske_karte;
	}
	public void setBroj_clanske_karte(String broj_clanske_karte) {
		this.broj_clanske_karte = broj_clanske_karte;
	}
	
	


	

	public int getAutomobilId() {
		return automobilId;
	}

	public void setAutomobilId(int automobilId) {
		this.automobilId = automobilId;
	}

	public float getOcena() {
		int brojOcena=0;
		double suma=0;
		for (Ocena o : Taksi_sluzba.ListaOcena) {
			if(o.getVozac().compareTo(this.korisnicko_ime)==0) {
				brojOcena++;
				suma+=o.getOcena();
			}
		}
		
		if(brojOcena>0) return (float) (suma/brojOcena);
		else return 3;
	}

	public void setOcena(float ocena) {
		this.ocena=ocena;
	}

	@Override
	public String stringZaUpisFajl() {
		String povratni="";
		povratni+=korisnicko_ime+"|"+plata+"|"+broj_clanske_karte+"|"+automobilId+"|"+ocena;
		return povratni;
	}
	
	
	public String stringZaUpisTabela() {
		String povratni="";
		povratni+=korisnicko_ime+"|"+ime+"|"+prezime+"|"+automobilId+"|"+plata+"|"+getOcena();
		return povratni;
	}
	
}
