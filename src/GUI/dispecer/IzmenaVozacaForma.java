package GUI.dispecer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import Taksi_sluzba.Taksi_sluzba;
import korisnici.Korisnik;
import korisnici.Vozac;

public class IzmenaVozacaForma {
	
	public static JFrame frame_izmena_vozaca;
	static JPanel panel_izmena_vozaca;
	private static JScrollPane jpane;
	private static JButton dodaj = new JButton("Dodaj");
	private static JButton izmeni = new JButton("Izmeni");
	private static JButton izbrisi = new JButton("Izbrisi");
	private static JButton nazad = new JButton("Nazad");
	
	public static JRadioButton sortirajKorisnicko=new JRadioButton("Sortiraj po korisnickom");
	public static JRadioButton sortirajIme=new JRadioButton("Sortiraj po imenu");
	public static JRadioButton sortirajPrezime=new JRadioButton("Sortiraj po prezimenu");

	
	static DefaultTableModel tabelaModel;
	static JTable tabela;

	
	public IzmenaVozacaForma() {
		izmena_vozaca_formaGUI();
	}
	
	public static void izmena_vozaca_formaGUI() {
		
		frame_izmena_vozaca = new JFrame();
		panel_izmena_vozaca = new JPanel();
		tabelaModel = new DefaultTableModel();
		tabela = new JTable(tabelaModel);
		jpane = new JScrollPane(tabela);
		frame_izmena_vozaca.add(panel_izmena_vozaca);
		panel_izmena_vozaca.add(sortirajKorisnicko);
		panel_izmena_vozaca.add(sortirajIme);
		panel_izmena_vozaca.add(sortirajPrezime);
		ButtonGroup bg = new ButtonGroup();
		bg.add(sortirajIme);
		bg.add(sortirajKorisnicko);
		bg.add(sortirajPrezime);
		dodajListenereZaRadio();
		panel_izmena_vozaca.add(jpane);
		panel_izmena_vozaca.add(dodaj);
		panel_izmena_vozaca.add(izmeni);
		panel_izmena_vozaca.add(izbrisi);
		panel_izmena_vozaca.add(nazad);
		panel_izmena_vozaca.add(tabela);
		//tabelaModel.addColumn("ID");
		tabelaModel.addColumn("Korisnicko ime");
		tabelaModel.addColumn("Ime");
		tabelaModel.addColumn("Prezime");
		tabelaModel.addColumn("Automobil");
		dodaj.setBounds(10,10,50,20);
		izmeni.setBounds(50,10,50,20);
		izbrisi.setBounds(90,10,50,20);
		izbrisi.setBounds(130,10,50,20);
		frame_izmena_vozaca.setVisible(true);
		frame_izmena_vozaca.setSize(400,250);
		frame_izmena_vozaca.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		String[] kolona = new String[] { "Korisnicko ime", "Ime", "Prezime", "Automobil"};
		tabelaModel.addRow(kolona);
		for(int i = 0; i < Taksi_sluzba.ListaVozaca.size();i++) {
			if (Taksi_sluzba.ListaVozaca.get(i).isObrisan() == false) {
				String korisnicko_ime = Taksi_sluzba.ListaVozaca.get(i).getKorisnicko_ime();
				String ime = Taksi_sluzba.ListaVozaca.get(i).getIme();
				String prezime = Taksi_sluzba.ListaVozaca.get(i).getPrezime();
				String automobil = ""+Taksi_sluzba.ListaVozaca.get(i).getAutomobilId();
			
				Object[] sadrzaj = {korisnicko_ime,ime,prezime,automobil};
				tabelaModel.addRow(sadrzaj);
			}
			else {
				continue;
			}
		}
		
		
		
		izbrisi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String VozacKorisnicko_ime = tabela.getValueAt(red, 0).toString();
					Vozac vozac = Taksi_sluzba.pronadjiKorisnicko_ime(VozacKorisnicko_ime);
					Korisnik k = Taksi_sluzba.pronadjiKorisnikaPoKorisnickom(VozacKorisnicko_ime);
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete vozaca?", 
							VozacKorisnicko_ime + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						vozac.setObrisan(true);
						k.setObrisan(true);
						Taksi_sluzba.ListaVozaca.remove(vozac);
						Taksi_sluzba.listaObrisanihVozaca.remove(vozac);
						Taksi_sluzba.ListaKorisnika.remove(k);
						Taksi_sluzba.ListaObrisanihKorisnika.add(k);
						tabelaModel.removeRow(red);
						Taksi_sluzba.sacuvajVozaceFajl();
						Taksi_sluzba.sacuvajKorisnikaFajl();
					}
				}
			}
		});
		
		dodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame_izmena_vozaca.dispose();
				DodavanjeVozaca dv = new DodavanjeVozaca();
			}
		});
		izmeni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String VozacKorisnicko_ime = tabela.getValueAt(red, 0).toString();
					frame_izmena_vozaca.dispose();
					VozaciIzmenaProzor2 viz = new VozaciIzmenaProzor2(VozacKorisnicko_ime);
				}
				
			}
		});
		nazad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame_izmena_vozaca.dispose();
				DispecerForma.frame_dispecer.enable();
			}
		});
	}

	private static void dodajListenereZaRadio() {
		sortirajKorisnicko.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sortirajPoKorisnickom();
			}

			private void sortirajPoKorisnickom() {
				Object[] obj = Taksi_sluzba.ListaVozaca.toArray();
				Vozac[] vozaci = new Vozac[obj.length];
				for(int i=0;i<obj.length;i++) vozaci[i]=(Vozac) obj[i];
				for(int i=0;i<vozaci.length;i++) {
					for(int k=0;k<vozaci.length;k++) {
						if(vozaci[i].getKorisnicko_ime().compareTo(vozaci[k].getKorisnicko_ime())<0) {
							Vozac pom=vozaci[i];
							vozaci[i]=vozaci[k];
							vozaci[k]=pom;
						}
					}
				}
				
				popuniTabelu(vozaci);
			}
		});
		
		sortirajIme.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sortirajPoImenu();
			}

			private void sortirajPoImenu() {
				Object[] obj = Taksi_sluzba.ListaVozaca.toArray();
				Vozac[] vozaci = new Vozac[obj.length];
				for(int i=0;i<obj.length;i++) vozaci[i]=(Vozac) obj[i];
				for(int i=0;i<vozaci.length;i++) {
					for(int k=0;k<vozaci.length;k++) {
						if(vozaci[i].getIme().compareTo(vozaci[k].getIme())<0) {
							Vozac pom=vozaci[i];
							vozaci[i]=vozaci[k];
							vozaci[k]=pom;
						}
					}
				}
				
				popuniTabelu(vozaci);
			}
		});
		
		sortirajPrezime.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sortirajPoPrezimenu();
			}
			
			private void sortirajPoPrezimenu() {
				Object[] obj = Taksi_sluzba.ListaVozaca.toArray();
				Vozac[] vozaci = new Vozac[obj.length];
				for(int i=0;i<obj.length;i++) vozaci[i]=(Vozac) obj[i];
				for(int i=0;i<vozaci.length;i++) {
					for(int k=0;k<vozaci.length;k++) {
						if(vozaci[i].getIme().compareTo(vozaci[k].getIme())<0) {
							Vozac pom=vozaci[i];
							vozaci[i]=vozaci[k];
							vozaci[k]=pom;
						}
					}
				}
				
				popuniTabelu(vozaci);
			}
		});
	}
	
	private static void popuniTabelu(Vozac[] vozaci) {
		while(tabelaModel.getRowCount()>0){
			tabelaModel.removeRow(0);
		}
		for (Vozac vozac : vozaci) {
			String[] red = {vozac.getKorisnicko_ime(),vozac.getIme(),vozac.getPrezime(),""+vozac.getAutomobilId()};
			tabelaModel.addRow(red);
		}
	}
	
	
}
