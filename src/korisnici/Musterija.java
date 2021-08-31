package korisnici;

import Enum.Pol;
import Enum.TipKorisnika;

public class Musterija extends Korisnik {

	public Musterija(String korisnicko_ime, String lozinka, String ime, String prezime, String JMBG, String adresa,
			Pol pol, String broj_telefona, TipKorisnika tip_korisnika, boolean obrisan, int id) {
		super(korisnicko_ime, lozinka, ime, prezime, JMBG, adresa, pol, broj_telefona, tip_korisnika, obrisan);
	}



	
	
}
