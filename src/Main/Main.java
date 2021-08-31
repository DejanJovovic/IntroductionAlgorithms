package Main;

import java.io.File;

import GUI.LoginGUI;
import Taksi_sluzba.Taksi_sluzba;

public class Main {
	
	static File file_korisnik = new File("korisnik.txt");
    static File file_musterija = new File("musterija.txt");
    static File file_dispecer = new File("dispecer.txt");
    static File file_vozac = new File("vozac.txt");
    static File file_automobil = new File("automobil.txt");
    static File file_voznja = new File("voznja.txt");
    static File file_ponuda = new File("ponuda.txt");
    static File file_ocena = new File("ocena.txt");
	
	public static void main(String[] args) {
		Taksi_sluzba ts = new Taksi_sluzba(1234567989,"Dejan_sluzba","Adresa",120,60);
		ts.UcitajKorisnika(file_korisnik);
		ts.UcitajVozaca(file_vozac);
		ts.UcitajAutomobil(file_automobil);
		ts.UcitajVoznju(file_voznja);
		ts.UcitajPonude(file_ponuda);
		ts.UcitajOcene(file_ocena);
		LoginGUI l = new LoginGUI(ts);
		//Taksi_sluzba.SortiranjeFileVozac();
	}
}
