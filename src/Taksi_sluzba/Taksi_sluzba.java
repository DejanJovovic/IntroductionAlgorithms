	package Taksi_sluzba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import korisnici.Korisnik;
import korisnici.Musterija;
import korisnici.Ocena;
import korisnici.Ponuda;
import korisnici.Automobil;
import korisnici.Dispecer;
import korisnici.Vozac;
import korisnici.Voznja;
import Enum.Pol;
import Enum.Status_voznje;
import Enum.TipKorisnika;
import Enum.Vrsta_automobila;
import korisnici.Korisnik;

public class Taksi_sluzba {

	public static ArrayList <Korisnik> ListaKorisnika = new ArrayList();
	public static ArrayList <Korisnik> ListaObrisanihKorisnika = new ArrayList();
    public static ArrayList <Musterija> ListaMusterija = new ArrayList();
    public static ArrayList <Dispecer> ListaDispecera = new ArrayList();
    public static ArrayList <Vozac> ListaVozaca = new ArrayList();
    public static ArrayList <Vozac> listaObrisanihVozaca = new ArrayList();
    public static ArrayList <Automobil> ListaAutomobila = new ArrayList();
    public static ArrayList <Voznja> ListaVoznji = new ArrayList();
	public static ArrayList<Ponuda> ListaPonuda = new ArrayList<>();
	public static ArrayList<Ocena> ListaOcena = new ArrayList<>();
	private static Korisnik ulogovani;
	
	private float PIB;
	private String Naziv;
	private String Adresa;
	private static double Cena_start;
	private static double Cena_km;
	
	public Taksi_sluzba(float pIB, String naziv, String adresa, double cena_start, double cena_km) {
		super();
		PIB = pIB;
		Naziv = naziv;
		Adresa = adresa;
		Cena_start = cena_start;
		Cena_km = cena_km;
	}

	public float getPIB() {
		return PIB;
	}

	public void setPIB(float pIB) {
		PIB = pIB;
	}

	public String getNaziv() {
		return Naziv;
	}

	public void setNaziv(String naziv) {
		Naziv = naziv;
	}

	public String getAdresa() {
		return Adresa;
	}

	public void setAdresa(String adresa) {
		Adresa = adresa;
	}

	public double getCena_start() {
		return Cena_start;
	}

	public void setCena_start(double cena_start) {
		Cena_start = cena_start;
	}

	public double getCena_km() {
		return Cena_km;
	}

	public void setCena_km(double cena_km) {
		Cena_km = cena_km;
	}

	
	
	public static Korisnik getUlogovani() {
		return ulogovani;
	}

	public static void setUlogovani(Korisnik ulogovani) {
		Taksi_sluzba.ulogovani = ulogovani;
	}

	@Override
	public String toString() {
		return "Taksi_sluzba [PIB=" + PIB + ", Naziv=" + Naziv + ", Adresa=" + Adresa + ", Cena_start=" + Cena_start
				+ ", Cena_km=" + Cena_km + "]";
	}
	
	public void UcitajKorisnika(File file_korisnik) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file_korisnik));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String korisnicko_ime = split[0];
				String lozinka = split[1];
				String ime = split[2];
				String prezime = split[3];
				String JMBG = split[4];
				String adresa = split[5];
				String pol_korisnika = split[6];
				Pol pol = Pol.valueOf(pol_korisnika);
				String broj_telefona = split[7];
				String tip_korisnika = split[8];
				TipKorisnika tip = TipKorisnika.valueOf(tip_korisnika);
				String obrisan_korisnik = split[9];
				boolean obrisan = Boolean.parseBoolean(obrisan_korisnik);
				Korisnik korisnik = new Korisnik(korisnicko_ime, lozinka, ime, prezime, JMBG, 
						adresa, pol, broj_telefona, tip, obrisan);
				if(!korisnik.isObrisan()) {
					ListaKorisnika.add(korisnik);
				}
			}
			ListaKorisnika.sort(Comparator.comparing(Korisnik :: getKorisnicko_ime));
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static Korisnik PrijavaKorisnika() {
		boolean pronadjen = false;
		Korisnik k = new Korisnik();
		int brojac = ListaKorisnika.size();
		String username = GUI.LoginGUI.korisnicko_ime_txt.getText();
	    String password = GUI.LoginGUI.lozinka_txt.getText();
	    
	    try {
	    	while(pronadjen == false) {
		    	for(int i = 0; i < brojac; i++) {
		    		if(ListaKorisnika.get(i).getKorisnicko_ime().equals(username) 
		    				&& ListaKorisnika.get(i).getLozinka().equals(password)) {
		    		k =  ListaKorisnika.get(i);
		    		Taksi_sluzba.ulogovani=k;
		    		pronadjen = true;
		    		}
		    	}
		    	return k;
		    }
			return k;
	    }
	    catch(Exception e) {
	    	System.out.println(e);
	    }
		return k;
	}
	
	public static void UcitajVozaca(File file_vozac) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file_vozac));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String korisnicko_ime = split[0];
				String plataStr = split[1];
				int plata = Integer.parseInt(plataStr);
				String clanska_karta = split[2];
				int automobilId = Integer.parseInt(split[3]);
				String ocenaStr=split[4];
				float ocena=Float.parseFloat(ocenaStr);
				Korisnik k = pronadjiKorisnikaPoKorisnickom(korisnicko_ime);
				Vozac vozac = new Vozac(k,plata,clanska_karta,automobilId,ocena);
				if(!vozac.isObrisan()) {
					DodavanjeVozacauListu(vozac);
				}
				else {
					listaObrisanihVozaca.add(vozac);	
				}
			}
			
			ListaVozaca.sort(Comparator.comparing(Vozac :: getKorisnicko_ime));
			listaObrisanihVozaca.sort(Comparator.comparing(Vozac :: getKorisnicko_ime));
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void UcitajAutomobil(File file_automobil) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file_automobil));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				int id=Integer.parseInt(split[0]);
				String model = split[1];
				String proizvodjac = split[2];
				String godina_proizvodnje1 = split[3];
				int godina_proizvodnje = Integer.parseInt(godina_proizvodnje1);
				String registracija = split[4];
				String br_vozila = split[5];
				String tip_vozila1 = split[6];
				Vrsta_automobila tip_vozila = Vrsta_automobila.valueOf(tip_vozila1);
				
				Automobil auto = new Automobil(id,model, proizvodjac, godina_proizvodnje, registracija, br_vozila, tip_vozila);
				ListaAutomobila.add(auto);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void UcitajPonude(File file_ponuda) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file_ponuda));
			String line = null;
			while ((line = reader.readLine()) != null) {
				Ponuda p = new Ponuda(line);
				ListaPonuda.add(p);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Korisnik pronadjiKorisnikaPoKorisnickom(String korisnicko) {
		int low=0;
		int high=ListaKorisnika.size()-1;
		while((high-low)>1) {
			int mid=(high+low)/2;
			int rez = korisnicko.compareTo(ListaKorisnika.get(mid).getKorisnicko_ime());
			if(rez==0) return ListaKorisnika.get(mid);
			if(rez>0) low=mid;
			if(rez<0) high=mid;
		}
		
		if(ListaKorisnika.get(low).getKorisnicko_ime().compareTo(korisnicko)==0) return ListaKorisnika.get(low);
		else return ListaKorisnika.get(high);
	}
	
	public static Vozac pronadjiKorisnicko_ime(String username) {
		for (Vozac vozac : ListaVozaca) {
			if(vozac.getKorisnicko_ime().equals(username)) {
				return vozac;
			}
		}
		return null;
	}

	public static Vozac pronadjiJMBG(String jmbg) {
		for (Vozac vozac : ListaVozaca) {
			if(vozac.getJMBG().equals(jmbg)) {
				return vozac;
			}
		}
		return null;
	}

	public static Vozac pronadjiClanskuK(String ck) {
		for (Vozac vozac : ListaVozaca) {
			if(vozac.getBroj_clanske_karte().equals(ck)) {
				return vozac;
			}
		}
		return null;
	}
	

	public static Automobil pronadjiAutomobil2(String a) {
		for (Automobil auto : ListaAutomobila) {
			if(auto.getBr_vozila().equals(a)) {
				return auto;
			}
		}
		return null;
	}
	
	public static void sacuvajVozaceFajl() {
		try {
			File file_vozaci = new File("vozac.txt");
			String sadrzaj = "";
			for(int i = 0; i< ListaVozaca.size();i++) {
					sadrzaj += ListaVozaca.get(i).stringZaUpisFajl()+ "\n";
					
					
			}
			for(int i=0;i<listaObrisanihVozaca.size();i++) {
				sadrzaj+=listaObrisanihVozaca.get(i).stringZaUpisFajl();
			}
			FileWriter fw = new FileWriter(file_vozaci);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.append(sadrzaj);
			writer.close();
			
			
		} catch (IOException e) {
			System.out.println("Greska prilikom ubacivanja vozaca.");
		}
	}
	
	public static void sacuvajKorisnikaFajl() {
		try {
			File korisnik = new File("korisnik.txt");
			String sadrzaj = "";
			for(int i = 0; i< ListaKorisnika.size();i++) {
				sadrzaj += ListaKorisnika.get(i).stringZaUpisFajl() + "\n";
			}
			for(int i = 0; i< ListaObrisanihKorisnika.size();i++) {
				sadrzaj += ListaObrisanihKorisnika.get(i).stringZaUpisFajl() + "\n";
			}
			FileWriter fwk = new FileWriter(korisnik);
			BufferedWriter writer1 = new BufferedWriter(fwk);
			writer1.append(sadrzaj);
			writer1.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ubacivanja korisnika.");
		}
	}
	
	public static void DodavanjeVozacauListu(Vozac vozac) {
			ListaVozaca.add(vozac);
	}
	public static void DodavanjeKorisnikauListu(Korisnik korisnik) {
		ListaKorisnika.add(korisnik);
	}

	public void UcitajOcene(File file_ocena) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file_ocena));
			String line = null;
			while ((line = reader.readLine()) != null) {	
				Ocena o = new Ocena(line);
				ListaOcena.add(o);
			}
			reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sacuvajOceneFajl() {
		try {
			File ocena = new File("ocena.txt");
			String sadrzaj = "";
			
			ListaOcena.sort(Comparator.comparing(Ocena :: getVoznjaId));
			for(int i = 0; i< ListaOcena.size();i++) {
				sadrzaj += ListaOcena.get(i).stringZaUpisFajl()+ "\n";
			}
			
			FileWriter fwk = new FileWriter(ocena);
			BufferedWriter writer1 = new BufferedWriter(fwk);
			writer1.append(sadrzaj);
			writer1.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ubacivanja ocene.");
		}
	}
	
	
	public void UcitajVoznju(File file_voznja) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file_voznja));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				int id=Integer.parseInt(split[0]);
				String vreme = split[1];
				String adresa1 = split[2];
				String adresa2 = split[3];
				String musterija = split[4];
				String vozac = split[5];
				String km1 = split[6];
				int km = Integer.parseInt(km1);
				String trajanje1 = split[7];
				int trajanje = Integer.valueOf(trajanje1);
				String status1 = split[8];
				Status_voznje status = Status_voznje.valueOf(status1);
				Voznja voznja = new Voznja(id,vreme,adresa1,adresa2,musterija,vozac,km,trajanje,status);
				DodavanjeVoznjeUListu(voznja);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sacuvajVoznjuFajl() {
		try {
			File voznja = new File("voznja.txt");
			String sadrzaj = "";
			for(int i = 0; i< ListaVoznji.size();i++) {
				sadrzaj += ListaVoznji.get(i).stringZaUpisFajl()+ "\n";
			}
			
			FileWriter fwk = new FileWriter(voznja);
			BufferedWriter writer1 = new BufferedWriter(fwk);
			writer1.append(sadrzaj);
			writer1.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ubacivanja voznje.");
		}
	}
	
	public static void sacuvajPonudeFajl() {
		try {
			File voznja = new File("ponuda.txt");
			String sadrzaj = "";
			for(int i = 0; i< ListaPonuda.size();i++) {
				sadrzaj += ListaPonuda.get(i).stringZaUpisFajl()+ "\n";
			}
			
			FileWriter fwk = new FileWriter(voznja);
			BufferedWriter writer1 = new BufferedWriter(fwk);
			writer1.append(sadrzaj);
			writer1.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ubacivanja ponude.");
		}
	}
	
	public static void DodavanjeVoznjeUListu(Voznja voznja) {
		ListaVoznji.add(voznja);
	}
/*	
	public static void SortiranjeFileVozac() {
		try {
			
			ListaVozaca.sort(Comparator.comparing(Vozac :: getKorisnicko_ime));
			listaObrisanihVozaca.sort(Comparator.comparing(Vozac :: getKorisnicko_ime));
			
			String sadrzaj1 = "";
			String sadrzaj2 = "";
			
			for(int i = 0; i< ListaVozaca.size();i++) {
					sadrzaj1 += ListaVozaca.get(i).stringZaUpisFajl()+"\n";
			}
			
			for(int i = 0; i< listaObrisanihVozaca.size();i++) {
					sadrzaj2 += listaObrisanihVozaca.get(i).stringZaUpisFajl() + "\n";
			}
			
			File file_v = new File("vozac.txt");
			FileWriter fw1 = new FileWriter(file_v,false);
			FileWriter fw2 = new FileWriter(file_v,true);
				
			BufferedWriter writer1 = new BufferedWriter(fw1);
			BufferedWriter writer2 = new BufferedWriter(fw2);
				
			writer1.write(sadrzaj1);
			writer2.append(sadrzaj2);
				
				
			writer1.close();
			writer2.close();
			
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}
*/

	public static Vozac pronadjiVozacaPoAutomobilu(int automobilId) {
		for (Vozac v : ListaVozaca) {
			if(v.getAutomobilId()==automobilId) return v;
		}
		return null;
	}

	public static Automobil pronadjiAutomobilPoId(int automobilId) {
		int low=0;
		int high=ListaAutomobila.size()-1;
		while((high-low)>1) {
			int mid=(high+low)/2;
			Automobil a = ListaAutomobila.get(mid);
			if(a.getId()==automobilId) return ListaAutomobila.get(mid);
			if(a.getId()>automobilId) high=mid;
			else low=mid;
		}
		
		if(ListaAutomobila.get(high).getId()==automobilId) return ListaAutomobila.get(high);
		if(ListaAutomobila.get(low).getId()==automobilId) return ListaAutomobila.get(low);
		return null;
	}

	public static Voznja pronadjiVoznjuPoId(int id) {
		int low=0;
		int high=ListaVoznji.size()-1;
		while((high-low)>1) {
			int mid=(high+low)/2;
			Voznja v = ListaVoznji.get(mid);
			if(v.getId()==id) return ListaVoznji.get(mid);
			if(v.getId()>id) high=mid;
			else low=mid;
		}
		
		if(ListaVoznji.get(high).getId()==id) return ListaVoznji.get(high);
		if(ListaVoznji.get(low).getId()==id) return ListaVoznji.get(low);
		return null;
	}

	public static Ocena nadjiOcenuPoId(int id) {
		int low=0;
		int high=ListaOcena.size()-1;
		while((high-low)>1) {
			int mid=(high+low)/2;
			Ocena o = ListaOcena.get(mid);
			if(o.getVoznjaId()==id) return ListaOcena.get(mid);
			if(o.getVoznjaId()>id) high=mid;
			else low=mid;
		}
		
		if(ListaOcena.get(high).getVoznjaId()==id) return ListaOcena.get(high);
		if(ListaOcena.get(low).getVoznjaId()==id) return ListaOcena.get(low);
		return null;
	}
	
	
	public static String generisiDnevniIzvestaj() {
		ArrayList<Voznja> voznje = new ArrayList<>();
		LocalDate danasnji = LocalDate.now();
		
		for(Voznja v : ListaVoznji) {
			String[] split = v.getDatum().split("-");
			int dan=Integer.parseInt(split[0]);
			int mesec=Integer.parseInt(split[1]);
			int godina=Integer.parseInt(split[2]);
			
			if(dan==danasnji.getDayOfMonth() && mesec==danasnji.getMonthValue() && godina ==danasnji.getYear()) {
				voznje.add(v);
			}
		}
		
		return "Izvestaj za dan "+danasnji.toString()+": \n"+generisiIzvestaj(voznje);
	}

	public static String generisiMesecniIzvestaj() {
		ArrayList<Voznja> voznje = new ArrayList<>();
		LocalDate danasnji = LocalDate.now();
		
		for(Voznja v : ListaVoznji) {
			String[] split = v.getDatum().split("-");
			int dan=Integer.parseInt(split[0]);
			int mesec=Integer.parseInt(split[1]);
			int godina=Integer.parseInt(split[2]);
			
			if( mesec==danasnji.getMonthValue() && godina ==danasnji.getYear()) {
				voznje.add(v);
			}
		}
		
		return "Izvestaj za mesec "+danasnji.getMonth()+": \n"+generisiIzvestaj(voznje);
	}
	
	public static String generisiGodisnjiIzvestaj() {
		ArrayList<Voznja> voznje = new ArrayList<>();
		LocalDate danasnji = LocalDate.now();
		
		for(Voznja v : ListaVoznji) {
			String[] split = v.getDatum().split("-");
			int dan=Integer.parseInt(split[0]);
			int mesec=Integer.parseInt(split[1]);
			int godina=Integer.parseInt(split[2]);
			
			if( godina ==danasnji.getYear()) {
				voznje.add(v);
			}
		}
		
		return "Izvestaj za godinu "+danasnji.getYear()+": \n"+generisiIzvestaj(voznje);
	}
	
	
	public static String generisiIzvestaj(ArrayList<Voznja> voznje) {
		String povratni="";
		int ukupnoVoznji=0;
		double ukupnoTrajanje=0;
		double ukupnaZarada=0;
		double ukupnoPredjenoKilometara=0;
		Set<String> aktivniVozaci = new HashSet();
		for(Voznja v : voznje) {
			if(v.getStatus_voznje()!=Status_voznje.ZAVRSENA) continue;
			
			ukupnoVoznji++;
			ukupnoTrajanje+=v.getTrajanje_voznje();
			ukupnaZarada+=Cena_start+v.getBroj_km()*Cena_km;
			ukupnoPredjenoKilometara+=v.getBroj_km();
		}
		
		if(ukupnoVoznji==0) return "Nema zavrsenih voznji u ovom periodu";
		
		povratni+="Ukupno voznji: "+ukupnoVoznji+"\n";
		povratni+="Aktivnih vozaca: "+aktivniVozaci.size()+"\n";
		povratni+="Prosecno trajanje voznje: "+ukupnoTrajanje / ukupnoVoznji+"\n";
		povratni+="Prosecna duzina voznje: "+ukupnoPredjenoKilometara / ukupnoVoznji+"\n";
		povratni+="Ukupna zarada: "+ukupnaZarada+"\n";
		povratni+="Prosecna cena voznje: "+ukupnaZarada / ukupnoVoznji+"\n";
		return povratni;
	}
}
