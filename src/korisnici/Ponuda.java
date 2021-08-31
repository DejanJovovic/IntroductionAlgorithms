package korisnici;

import Enum.Status_voznje;
import Taksi_sluzba.Taksi_sluzba;

public class Ponuda {
	private int idVoznje;
	private String korisnickoImePonudjaca;
	private double ocenaPonude;
	
	public Ponuda(int idVoznje, Vozac ponudjac, int brojMinuta) {
		korisnickoImePonudjaca=ponudjac.getKorisnicko_ime();
		this.idVoznje=idVoznje;
		double koeficijentOcene = 1+(0.1*(ponudjac.getOcena()-3)); //koef in range (0.8 , 1.2)
		this.ocenaPonude = (120-brojMinuta)*koeficijentOcene;
	}
	
	public Ponuda(String ulaz) {
		String[] split = ulaz.split("\\|");
		idVoznje=Integer.parseInt(split[0]);
		korisnickoImePonudjaca=split[1];
		ocenaPonude=Double.parseDouble(split[2]);
	}
	
	public String stringZaUpisFajl() {
		return idVoznje+"|"+korisnickoImePonudjaca+"|"+ocenaPonude;
	}
	
	
	
	public static void simulirajAukcijuIDodeliVoznju(Voznja v) {
		int id = v.getId();
		Ponuda najbolja = null;
		for (Ponuda p : Taksi_sluzba.ListaPonuda) {
			if(p.idVoznje==id) {
				if(najbolja==null || p.ocenaPonude>najbolja.ocenaPonude) {
					najbolja=p;
				}
			}
		}
		
		if(najbolja!=null) {
			v.setVozac(najbolja.korisnickoImePonudjaca);
			v.setStatus_voznje(Status_voznje.DODELJENA);
		}
		else {
			System.out.print("Greska, nema ponuda");
		}
		return;
	}
}
