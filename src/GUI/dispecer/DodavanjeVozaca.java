package GUI.dispecer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Enum.Pol;
import Enum.TipKorisnika;
import Taksi_sluzba.Taksi_sluzba;
import korisnici.Vozac;
import korisnici.Automobil;
import korisnici.Korisnik;

public class DodavanjeVozaca {

	public static JFrame frame_dodavanje;
	private static JPanel panel_dodavanje;
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
	public static JRadioButton pol_m;
	public static JRadioButton pol_z;
	public static JTextField broj_telefona_txt;
	public static JTextField plata_txt;
	public static JTextField clanska_karta_txt;
	public static JTextField automobil_txt;
	public static Pol pol;
	
	private static JButton dodaj;
	private static JButton odustani;

	public DodavanjeVozaca() {
		DodavanjeVozaca_GUI();
	}
	
	

	public void DodavanjeVozaca_GUI() {
		
		frame_dodavanje = new JFrame();
		panel_dodavanje = new JPanel();
		
		frame_dodavanje.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		korisnicko_ime = new JLabel("Korisnicko ime:");
		lozinka = new JLabel("Lozinka:");
		ime = new JLabel("Ime:");
		prezime = new JLabel("Prezime");
		jmbg = new JLabel("JMBG:");
		adresa = new JLabel("Adresa:");
		pol_l = new JLabel("Pol:");
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
		pol_m = new JRadioButton("Musko");
		pol_z = new JRadioButton("Zensko");
		broj_telefona_txt = new JTextField();
		plata_txt = new JTextField();
		clanska_karta_txt = new JTextField();
		automobil_txt = new JTextField();
		
		dodaj = new JButton("Dodaj");
		odustani = new JButton("Odustani");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(pol_m);
		bg.add(pol_z);
		
		pol = null;
		
		pol_m.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == pol_m) {
					pol = Pol.MUSKI;
					
				}
			}
		});
		pol_z.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == pol_z) {
					pol = Pol.ZENSKI;
				}
			}
		});
		
		
		dodaj.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
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
			int automobilID=Integer.parseInt(automobilStr);
			
			String poruka = "Ispravite unos: ";
			boolean provera = true;
			Vozac vozac = new Vozac();
			Automobil auto = new Automobil();
			vozac = Taksi_sluzba.pronadjiKorisnicko_ime(korisnicko_ime);
			if (vozac != null) {
					provera = false;
					poruka += "\n Korisnicko ime vec postoji";
					korisnicko_ime_txt.setText("");
				}
			vozac = Taksi_sluzba.pronadjiJMBG(jmbg);
			if (vozac != null) {
					provera = false;
					poruka += "\n Jmbg vec postoji";
					jmbg_txt.setText("");
				}
			vozac = Taksi_sluzba.pronadjiClanskuK(clanska_karta);
			if (vozac != null) {
					provera = false;
					poruka += "\n clanska karta vec postoji";
					clanska_karta_txt.setText("");
				}
			
			vozac = Taksi_sluzba.pronadjiVozacaPoAutomobilu(automobilID);
			if (vozac != null) {
					provera = false;
					poruka += "\n automobil je u upotrebi";
					automobil_txt.setText("");
				}
			
			auto = Taksi_sluzba.pronadjiAutomobilPoId(automobilID);
			if (auto == null) {
					provera = false;
					poruka += "\n automobil ne postoji";
					automobil_txt.setText("");
				}
			if(pol == null) {
				provera = false;
				poruka += "\n izaberite pol";
			}
			if(provera == false) {
				JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
				return;
				}
			else {
				TipKorisnika tp = TipKorisnika.VOZAC;
				Korisnik k = new Korisnik(korisnicko_ime,lozinka,ime,prezime,jmbg,adresa,pol,broj_telefona,tp,false);
				Vozac v = new Vozac(k, plata, clanska_karta, automobilID, 3);
				Taksi_sluzba.DodavanjeVozacauListu(v);
				Taksi_sluzba.DodavanjeKorisnikauListu(k);
				Taksi_sluzba.sacuvajVozaceFajl();
				Taksi_sluzba.sacuvajKorisnikaFajl();
				//Taksi_sluzba.SortiranjeFileVozac();
				JOptionPane.showMessageDialog(null, "Vozac dodat.", "Poruka", JOptionPane.INFORMATION_MESSAGE);
				frame_dodavanje.dispose();
				IzmenaVozacaForma iv = new IzmenaVozacaForma();
				}
			}
		});
		odustani.addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
				IzmenaVozacaForma.izmena_vozaca_formaGUI();
				frame_dodavanje.dispose();
			}
		});

				korisnicko_ime.setBounds(30,20,150,25);
				lozinka.setBounds(30,50,150,25);
				ime.setBounds(30,80,150,25);
				prezime.setBounds(30,110,150,25);
				jmbg.setBounds(30,140,150,25);
				adresa.setBounds(30,170,150,25);
				pol_l.setBounds(30,200,150,25);
				broj_telefona.setBounds(30,230,150,25);
				plata.setBounds(30,290,150,25);
				clanska_karta.setBounds(30,320,150,25);
				Automobil.setBounds(30,350,150,25);
				
				korisnicko_ime_txt.setBounds(130,20,150,25);
				lozinka_txt.setBounds(130,50,150,25);
				ime_txt.setBounds(130,80,150,25);
				prezime_txt.setBounds(130,110,150,25);
				jmbg_txt.setBounds(130,140,150,25);
				adresa_txt.setBounds(130,170,150,25);
				pol_z.setBounds(130,205,70,15);
				pol_m.setBounds(200,205,90,15);
				broj_telefona_txt.setBounds(130,230,150,25);
				plata_txt.setBounds(130,290,150,25);
				clanska_karta_txt.setBounds(130,320,150,25);
				automobil_txt.setBounds(130,350,150,25);
				
				dodaj.setBounds(400,180,250,55);
				odustani.setBounds(400,300,250,55);
				
				frame_dodavanje.setSize(700,450);
				frame_dodavanje.setVisible(true);
				
				panel_dodavanje.setLayout(null);
				
				frame_dodavanje.add(panel_dodavanje);
				panel_dodavanje.add(korisnicko_ime);
				panel_dodavanje.add(lozinka);
				panel_dodavanje.add(ime);
				panel_dodavanje.add(prezime);
				panel_dodavanje.add(jmbg);
				panel_dodavanje.add(adresa);
				panel_dodavanje.add(pol_l);
				panel_dodavanje.add(broj_telefona);
				panel_dodavanje.add(plata);
				panel_dodavanje.add(clanska_karta);
				panel_dodavanje.add(Automobil);
				panel_dodavanje.add(korisnicko_ime_txt);
				panel_dodavanje.add(lozinka_txt);
				panel_dodavanje.add(ime_txt);
				panel_dodavanje.add(prezime_txt);
				panel_dodavanje.add(jmbg_txt);
				panel_dodavanje.add(adresa_txt);
				panel_dodavanje.add(pol_z);
				panel_dodavanje.add(pol_m);
				panel_dodavanje.add(broj_telefona_txt);
				panel_dodavanje.add(plata_txt);
				panel_dodavanje.add(clanska_karta_txt);
				panel_dodavanje.add(automobil_txt);
				panel_dodavanje.add(dodaj);
				panel_dodavanje.add(odustani);
		}
	}