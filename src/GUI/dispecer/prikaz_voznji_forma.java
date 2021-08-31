package GUI.dispecer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Enum.Status_voznje;
import Taksi_sluzba.Taksi_sluzba;
import korisnici.Ponuda;
import korisnici.Vozac;
import korisnici.Voznja;

public class prikaz_voznji_forma {
	
	public prikaz_voznji_forma() {
		prikaz_voznji_formaGUI();
	}
	
	
	public static JFrame frame_voznje;
	private static JPanel panel_voznje;
	private static DefaultTableModel tabelaModel;
	public static JTable tabela;
	private static JButton dodaj;
	private static JButton nazad;
	private static JButton staviNaAukciju;
	private static JButton razresiAukcije;
	
	
	public static Voznja voznja = new Voznja();
	public static JRadioButton sortID = new JRadioButton("Sortiraj po ID");
	public static JRadioButton sortKM = new JRadioButton("Sortiraj po kilometrazi");
	public static JRadioButton sortTrajanje = new JRadioButton("Sortiraj po trajanju");
	public static JRadioButton sortStatus = new JRadioButton("Sortiraj po statusu");
	
	public static void prikaz_voznji_formaGUI() {
		frame_voznje = new JFrame();
		panel_voznje = new JPanel();
		dodaj = new JButton("Dodeli voznju");
		nazad = new JButton("Nazad");
		staviNaAukciju = new JButton("Stavu na aukciju");
		razresiAukcije = new JButton("Simuliraj aukcije");
		frame_voznje.add(panel_voznje);
		frame_voznje.setVisible(true);
		frame_voznje.setSize(700,350);
		
		String[] kolona = new String[] {"ID","VREME", "ADRESA POLASKA", "ADRESA DESTINACIJE", "MUSTERIJA", 
				"VOZAC", "BROJ PREDJENIH KM", "TRAJANJE", "STATUS VOZNJE"};
		
		tabelaModel = new DefaultTableModel(kolona,0);
		tabelaModel.addRow(kolona);
		tabela = new JTable(tabelaModel);
		
		for(int i = 0; i < Taksi_sluzba.ListaVoznji.size();i++) {
			String id = Taksi_sluzba.ListaVoznji.get(i).getId()+"";
			String datum = Taksi_sluzba.ListaVoznji.get(i).getDatumString();
			String adresa1 = Taksi_sluzba.ListaVoznji.get(i).getAdresa1();
			String adresa2 = Taksi_sluzba.ListaVoznji.get(i).getAdresa2();
			String musterija = Taksi_sluzba.ListaVoznji.get(i).getMusterija();
			String vozac = Taksi_sluzba.ListaVoznji.get(i).getVozac();
			int broj_km = Taksi_sluzba.ListaVoznji.get(i).getBroj_km();
			int trajanje_voznje = Taksi_sluzba.ListaVoznji.get(i).getTrajanje_voznje();
			Status_voznje status = Taksi_sluzba.ListaVoznji.get(i).getStatus_voznje();
			
				Object [] sadrzaj = {id,datum,adresa1,adresa2,musterija,vozac,broj_km,trajanje_voznje,status};

				
				tabelaModel.addRow(sadrzaj);
				
			}
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(sortID);
		bg.add(sortKM);
		bg.add(sortStatus);
		bg.add(sortTrajanje);
		dodajListenereZaRadio();
		
		panel_voznje.add(sortID);
		panel_voznje.add(sortKM);
		panel_voznje.add(sortStatus);
		panel_voznje.add(sortTrajanje);
		
		panel_voznje.add(tabela);
		panel_voznje.add(dodaj);
		panel_voznje.add(nazad);
		panel_voznje.add(staviNaAukciju);
		panel_voznje.add(razresiAukcije);
		dodaj.setBounds(70,200,30,30);
		nazad.setBounds(100,200,30,30);
		staviNaAukciju.setBounds(130,200,30,30);
		razresiAukcije.setBounds(160,200,30,30);
		
		
		staviNaAukciju.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int id=Integer.parseInt(tabela.getValueAt(red, 0).toString());
					Voznja v = Taksi_sluzba.pronadjiVoznjuPoId(id);
					if(v.getStatus_voznje()!=Status_voznje.KREIRANA) {
						JOptionPane.showMessageDialog(null, "Voznja je vec dodeljena ili je na aukciji.", "Greska", JOptionPane.WARNING_MESSAGE);
						return;
					}
					v.setStatus_voznje(Status_voznje.KREIRANA_NA_CEKANJU);
					Taksi_sluzba.sacuvajVoznjuFajl();;
					popuniTabelu();
				}
			}
		});
		
		razresiAukcije.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Voznja v : Taksi_sluzba.ListaVoznji) {
					if(v.getStatus_voznje()==Status_voznje.KREIRANA_NA_CEKANJU) {
						Ponuda.simulirajAukcijuIDodeliVoznju(v);
					}
				}
				popuniTabelu();
			}
		});
		
		dodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int id=Integer.parseInt(tabela.getValueAt(red, 0).toString());
					Voznja v = Taksi_sluzba.pronadjiVoznjuPoId(id);
					if(v.getStatus_voznje()!=Status_voznje.KREIRANA) {
						JOptionPane.showMessageDialog(null, "Voznja je vec dodeljena ili je na aukciji.", "Greska", JOptionPane.WARNING_MESSAGE);
						return;
					}
					dodelavozacimaforma d = new dodelavozacimaforma(v);
				}
			}
		});
		nazad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame_voznje.dispose();
				DispecerForma.frame_dispecer.enable();
			}
		});
	}
	
	private static void dodajListenereZaRadio() {
		sortID.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sortirajPoID();
			}

			private void sortirajPoID() {
				Object[] obj = Taksi_sluzba.ListaVoznji.toArray();
				Voznja[] voznje = new Voznja[obj.length];
				for(int i=0;i<obj.length;i++) voznje[i]=(Voznja) obj[i];
				for(int i=0;i<voznje.length;i++) {
					for(int k=0;k<voznje.length;k++) {
						if(voznje[i].getId()<voznje[k].getId()) {
							Voznja pom=voznje[i];
							voznje[i]=voznje[k];
							voznje[k]=pom;
						}
					}
				}
				
				popuniTabelu(voznje);
			}
		});
		
		sortKM.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sortirajPoKm();
			}

			private void sortirajPoKm() {
				Object[] obj = Taksi_sluzba.ListaVoznji.toArray();
				Voznja[] voznje = new Voznja[obj.length];
				for(int i=0;i<obj.length;i++) voznje[i]=(Voznja) obj[i];
				for(int i=0;i<voznje.length;i++) {
					for(int k=0;k<voznje.length;k++) {
						if(voznje[i].getBroj_km()<voznje[k].getBroj_km()) {
							Voznja pom=voznje[i];
							voznje[i]=voznje[k];
							voznje[k]=pom;
						}
					}
				}
				
				popuniTabelu(voznje);
			}
		});
		
		
		sortStatus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sortirajPoStatusu();
			}

			private void sortirajPoStatusu() {
				Object[] obj = Taksi_sluzba.ListaVoznji.toArray();
				Voznja[] voznje = new Voznja[obj.length];
				for(int i=0;i<obj.length;i++) voznje[i]=(Voznja) obj[i];
				
				for(int i=0;i<voznje.length;i++) {
					for(int k=0;k<voznje.length;k++) {
						String s1=voznje[k].getStatus_voznje().toString();
						String s2=voznje[i].getStatus_voznje().toString();
						if(s1.compareTo(s2)>0) {
							Voznja pom=voznje[i];
							voznje[i]=voznje[k];
							voznje[k]=pom;
						}
					}
				}
				
				popuniTabelu(voznje);
			}
		});
		
		sortTrajanje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sortirajPoTrajanju();
			}

			private void sortirajPoTrajanju() {
				Object[] obj = Taksi_sluzba.ListaVoznji.toArray();
				Voznja[] voznje = new Voznja[obj.length];
				for(int i=0;i<obj.length;i++) voznje[i]=(Voznja) obj[i];
				for(int i=0;i<voznje.length;i++) {
					for(int k=0;k<voznje.length;k++) {
						if(voznje[i].getTrajanje_voznje()<voznje[k].getTrajanje_voznje()) {
							Voznja pom=voznje[i];
							voznje[i]=voznje[k];
							voznje[k]=pom;
						}
					}
				}
				
				popuniTabelu(voznje);
			}
		});
	}
	
	private static void popuniTabelu(Voznja[] voznje) {
		while(tabelaModel.getRowCount()>0){
			tabelaModel.removeRow(0);
		}
		
		String[] kolona = new String[] {"ID","VREME", "ADRESA POLASKA", "ADRESA DESTINACIJE", "MUSTERIJA", 
				"VOZAC", "BROJ PREDJENIH KM", "TRAJANJE", "STATUS VOZNJE"};
		tabelaModel.addRow(kolona);
		for (Voznja v : voznje) {
			String[] red = {v.getId()+"",v.getDatumString(),v.getAdresa1(),v.getAdresa2(),v.getMusterija(),v.getVozac(),v.getBroj_km()+"",v.getTrajanje_voznje()+"",
					v.getStatus_voznje().toString()};
			tabelaModel.addRow(red);
		}
	}
	
	private static void popuniTabelu() {
		//praznjenje
		while(tabelaModel.getRowCount()>1) {
			tabelaModel.removeRow(1);
		}
		
		for(int i = 0; i < Taksi_sluzba.ListaVoznji.size();i++) {
			String id = Taksi_sluzba.ListaVoznji.get(i).getId()+"";
			String datum = Taksi_sluzba.ListaVoznji.get(i).getDatumString();
			String adresa1 = Taksi_sluzba.ListaVoznji.get(i).getAdresa1();
			String adresa2 = Taksi_sluzba.ListaVoznji.get(i).getAdresa2();
			String musterija = Taksi_sluzba.ListaVoznji.get(i).getMusterija();
			String vozac = Taksi_sluzba.ListaVoznji.get(i).getVozac();
			int broj_km = Taksi_sluzba.ListaVoznji.get(i).getBroj_km();
			int trajanje_voznje = Taksi_sluzba.ListaVoznji.get(i).getTrajanje_voznje();
			Status_voznje status = Taksi_sluzba.ListaVoznji.get(i).getStatus_voznje();
			
				Object [] sadrzaj = {id,datum,adresa1,adresa2,musterija,vozac,broj_km,trajanje_voznje,status};

				
				tabelaModel.addRow(sadrzaj);
				
			}
	}
	
	
	
	
}
