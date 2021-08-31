package GUI.dispecer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Enum.Pol;
import Enum.TipKorisnika;
import korisnici.Korisnik;
import korisnici.Vozac;
import Taksi_sluzba.Taksi_sluzba;

public class VozaciIzmenaProzor2 {
	
	public static JFrame frame_prozor;
	private static JPanel panel_prozor;
	private static JLabel korisnicko_ime;
	private static JLabel lozinka;
	private static JLabel ime;
	private static JLabel prezime;
	private static JLabel jmbg;
	private static JLabel adresa;
	private static JLabel pol_l;
	private static JLabel broj_telefona;
	private static JLabel plata;
	private static JLabel clanska_karta;
	private static JLabel Automobil;
	public static JTextField korisnicko_ime_txt;
	public static JTextField lozinka_txt;
	public static JTextField ime_txt;
	public static JTextField prezime_txt;
	public static JTextField jmbg_txt;
	public static JTextField adresa_txt;
	public static JCheckBox pol_c;
	public static JTextField broj_telefona_txt;
	public static JTextField plata_txt;
	public static JTextField clanska_karta_txt;
	public static JTextField automobil_txt;
	public static Pol pol;
	private static JButton ok;
	private static JButton odustani;
	private static Vozac izmenjeniVozac;
	private static Korisnik izmenjeniKorisnik;
	
	public VozaciIzmenaProzor2(String korisnicko) {
		izmenjeniVozac=Taksi_sluzba.pronadjiKorisnicko_ime(korisnicko);
		izmenjeniKorisnik=Taksi_sluzba.pronadjiKorisnikaPoKorisnickom(korisnicko);
		VozaciIzmenaProzor2GUI();
	}
	
	public void VozaciIzmenaProzor2GUI() {
		

		
		frame_prozor = new JFrame();
		panel_prozor = new JPanel();
		frame_prozor.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		korisnicko_ime = new JLabel("Korisnicko ime:");
		lozinka = new JLabel("Lozinka:");
		ime = new JLabel("Ime:");
		prezime = new JLabel("Prezime");
		jmbg = new JLabel("JMBG:");
		adresa = new JLabel("Adresa:");
		pol_l = new JLabel("Cekirati ako je Zensko:");
		broj_telefona = new JLabel("Broj telefona:");
		plata = new JLabel("Plata:");
		clanska_karta = new JLabel("Clanska karta:");
		Automobil = new JLabel("Automobil:");
		
		korisnicko_ime_txt = new JTextField();
		lozinka_txt = new JTextField();
		ime_txt = new JTextField();
		prezime_txt = new JTextField();
		jmbg_txt = new JTextField();
		adresa_txt = new JTextField();
		pol_c = new JCheckBox();
		broj_telefona_txt = new JTextField();
		plata_txt = new JTextField();
		clanska_karta_txt = new JTextField();
		automobil_txt = new JTextField();
		ok = new JButton("OK");
		odustani = new JButton("Odustani");
		
		korisnicko_ime.setBounds(30,20,150,25);
		lozinka.setBounds(30,50,150,25);
		ime.setBounds(30,80,150,25);
		prezime.setBounds(30,110,150,25);
		jmbg.setBounds(30,140,150,25);
		adresa.setBounds(30,170,150,25);
		broj_telefona.setBounds(30,200,150,25);
		plata.setBounds(30,260,150,25);
		clanska_karta.setBounds(30,290,150,25);
		Automobil.setBounds(30,320,150,25);
		
		korisnicko_ime_txt.setBounds(130,20,150,25);
		lozinka_txt.setBounds(130,50,150,25);
		ime_txt.setBounds(130,80,150,25);
		prezime_txt.setBounds(130,110,150,25);
		jmbg_txt.setBounds(130,140,150,25);
		adresa_txt.setBounds(130,170,150,25);
		broj_telefona_txt.setBounds(130,200,150,25);
		plata_txt.setBounds(130,260,150,25);
		clanska_karta_txt.setBounds(130,290,150,25);
		automobil_txt.setBounds(130,320,150,25);
		
		
		korisnicko_ime_txt.setText(izmenjeniVozac.getKorisnicko_ime());
		lozinka_txt.setText(izmenjeniVozac.getLozinka());
		ime_txt.setText(izmenjeniVozac.getIme());
		prezime_txt.setText(izmenjeniVozac.getPrezime());
		jmbg_txt.setText(String.valueOf(izmenjeniVozac.getJMBG()));
		adresa_txt.setText(izmenjeniVozac.getAdresa());
		broj_telefona_txt.setText(izmenjeniVozac.getBroj_telefona());
		plata_txt.setText(String.valueOf(izmenjeniVozac.getPlata()));
		clanska_karta_txt.setText(izmenjeniVozac.getBroj_clanske_karte());
		automobil_txt.setText(""+izmenjeniVozac.getAutomobilId());
		
		ok.setBounds(130,365,60,20);
		odustani.setBounds(210,365,120,20);
		
		frame_prozor.setSize(700,450);
		frame_prozor.setVisible(true);
		
		panel_prozor.setLayout(null);
		
		pol = izmenjeniVozac.getPol();
		
		ok.addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
				izmeniVozacaiSacuvaj();
				izmeniOdgovarajucegKorisnikaiSacuvaj();
				IzmenaVozacaForma.izmena_vozaca_formaGUI();
				frame_prozor.dispose();
				}
			});
		
		odustani.addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
				frame_prozor.dispose();
				IzmenaVozacaForma.izmena_vozaca_formaGUI();
			}
		});
		
		frame_prozor.add(panel_prozor);
		panel_prozor.add(korisnicko_ime);
		panel_prozor.add(lozinka);
		panel_prozor.add(ime);
		panel_prozor.add(prezime);
		panel_prozor.add(jmbg);
		panel_prozor.add(adresa);
		panel_prozor.add(broj_telefona);
		panel_prozor.add(plata);
		panel_prozor.add(clanska_karta);
		panel_prozor.add(Automobil);
		panel_prozor.add(korisnicko_ime_txt);
		panel_prozor.add(lozinka_txt);
		panel_prozor.add(ime_txt);
		panel_prozor.add(prezime_txt);
		panel_prozor.add(jmbg_txt);
		panel_prozor.add(adresa_txt);
		panel_prozor.add(broj_telefona_txt);
		panel_prozor.add(plata_txt);
		panel_prozor.add(clanska_karta_txt);
		panel_prozor.add(automobil_txt);
		panel_prozor.add(ok);
		panel_prozor.add(odustani);
	}
	
	private static void izmeniVozacaiSacuvaj() {
		Vozac vozac = izmenjeniVozac;
		String korisnicko_ime = korisnicko_ime_txt.getText();
		String lozinka = lozinka_txt.getText();
		String ime = ime_txt.getText();
		String prezime = prezime_txt.getText();
		String jmbg = jmbg_txt.getText();
		String adresa = adresa_txt.getText();
		String broj_telefona = broj_telefona_txt.getText();
		String plata1 = plata_txt.getText();
		int plata = Integer.parseInt(plata1);
		String clanska_karta = clanska_karta_txt.getText();
		String automobilStr = automobil_txt.getText();
		int automobilId=Integer.parseInt(automobilStr);
		
		TipKorisnika tp = TipKorisnika.VOZAC;
		
		vozac.setKorisnicko_ime(korisnicko_ime);
		vozac.setLozinka(lozinka);
		vozac.setIme(ime);
		vozac.setPrezime(prezime);
		vozac.setJMBG(jmbg);
		vozac.setAdresa(adresa);
		vozac.setBroj_telefona(broj_telefona);
		vozac.setPlata(plata);
		vozac.setBroj_clanske_karte(clanska_karta);
		vozac.setAutomobilId(automobilId);
		
		Taksi_sluzba.sacuvajVozaceFajl();
	//	Taksi_sluzba.ListaVozaca.set(IzmenaVozacaForma.tabela.getSelectedRow(),vozac);
	}
	
	private void izmeniOdgovarajucegKorisnikaiSacuvaj() {
		Korisnik k = izmenjeniKorisnik;
		String korisnicko_ime = korisnicko_ime_txt.getText();
		String lozinka = lozinka_txt.getText();
		String ime = ime_txt.getText();
		String prezime = prezime_txt.getText();
		String jmbg = jmbg_txt.getText();
		String adresa = adresa_txt.getText();
		String broj_telefona = broj_telefona_txt.getText();
		
		k.setKorisnicko_ime(korisnicko_ime);
		k.setLozinka(lozinka);
		k.setIme(ime);
		k.setPrezime(prezime);
		k.setJMBG(jmbg);
		k.setAdresa(adresa);
		k.setBroj_telefona(broj_telefona);
		
		Taksi_sluzba.sacuvajKorisnikaFajl();
	}
}
