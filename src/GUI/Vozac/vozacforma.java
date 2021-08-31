package GUI.Vozac;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Enum.Status_voznje;
import GUI.LoginGUI;
import Taksi_sluzba.Taksi_sluzba;
import korisnici.Voznja;

public class vozacforma {
	
	public vozacforma() {
		vozacformaGUI();
	}
	
	protected static String VozacKorisnicko_ime = null;
	static JFrame frame_vozac;
	static JPanel panel_vozac;
	private static JButton prihvati = new JButton("Prihvati");
	private static JButton odbij = new JButton("Odbij");
	private static JButton zavrsi = new JButton("Zavrsi");
	private static JButton ponudi = new JButton("Aukcija");
	private static JButton odjava = new JButton("Odjava");
	static DefaultTableModel tabelaModel;
	static JTable tabela;
	

	
	public static void vozacformaGUI() {
		frame_vozac = new JFrame();
		panel_vozac = new JPanel();
		frame_vozac.add(panel_vozac);
		panel_vozac.add(prihvati);
		panel_vozac.add(odbij);
		panel_vozac.add(zavrsi);
		panel_vozac.add(ponudi);
		panel_vozac.add(odjava);
		prihvati.setBounds(10,10,50,20);
		odbij.setBounds(50,10,50,20);
		zavrsi.setBounds(90,10,50,20);
		ponudi.setBounds(120,10,50,20);
		odjava.setBounds(140,10,50,20);
		frame_vozac.setVisible(true);
		frame_vozac.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame_vozac.setSize(700,200);

		String[] kolona = new String[] {"id","vreme", "adresa1", "adresa2", "musterija", "vozac", "predjeni km", "trajanje", "status"};
		tabelaModel = new DefaultTableModel(kolona,0);
		tabelaModel.addRow(kolona);

		tabela = new JTable(tabelaModel);
		
		String korisnik = LoginGUI.korisnicko_ime_txt.getText();
		
		
		for(int i = 0; i < Taksi_sluzba.ListaVoznji.size();i++) {
			if(Taksi_sluzba.ListaVoznji.get(i).getVozac().equals(korisnik) || Taksi_sluzba.ListaVoznji.get(i).getStatus_voznje()==Status_voznje.KREIRANA_NA_CEKANJU) {
				if (Taksi_sluzba.ListaVoznji.get(i).getStatus_voznje() == Status_voznje.DODELJENA || 
					Taksi_sluzba.ListaVoznji.get(i).getStatus_voznje() == Status_voznje.PRIHVACENA || Taksi_sluzba.ListaVoznji.get(i).getStatus_voznje()==Status_voznje.KREIRANA_NA_CEKANJU) {
					String id=Taksi_sluzba.ListaVoznji.get(i).getId()+"";
					String vreme = Taksi_sluzba.ListaVoznji.get(i).getDatumString();
					String adresa1 = Taksi_sluzba.ListaVoznji.get(i).getAdresa1();
					String adresa2 = Taksi_sluzba.ListaVoznji.get(i).getAdresa2();
					String musterija = Taksi_sluzba.ListaVoznji.get(i).getMusterija();
					String vozac = Taksi_sluzba.ListaVoznji.get(i).getVozac();
					int predjeni_km = Taksi_sluzba.ListaVoznji.get(i).getBroj_km();
					int trajanje = Taksi_sluzba.ListaVoznji.get(i).getTrajanje_voznje();
					Status_voznje status = Taksi_sluzba.ListaVoznji.get(i).getStatus_voznje();
					
				
					Object[] sadrzaj = {id,vreme,adresa1,adresa2,musterija,vozac,predjeni_km,trajanje,status};
					tabelaModel.addRow(sadrzaj);
				}
			}
			else {
				continue;
			}
		}
		panel_vozac.add(tabela);
		
		prihvati.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int id= Integer.parseInt(tabela.getValueAt(red, 0).toString());
					Voznja v=Taksi_sluzba.pronadjiVoznjuPoId(id);
					v.setStatus_voznje(Status_voznje.PRIHVACENA);
					frame_vozac.dispose();
					vozacformaGUI();
					Taksi_sluzba.sacuvajVoznjuFajl();	
				}
			}
		});
		
		odbij.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int id=Integer.parseInt(tabela.getValueAt(red, 0).toString()) ;
					Voznja v=Taksi_sluzba.pronadjiVoznjuPoId(id);
					v.setStatus_voznje(Status_voznje.ODBIJENA);
					frame_vozac.dispose();
					vozacformaGUI();
					Taksi_sluzba.sacuvajVoznjuFajl();	
				}
			}
		});
		
		zavrsi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int id=Integer.parseInt(tabela.getValueAt(red, 0).toString()) ;
					Voznja v=Taksi_sluzba.pronadjiVoznjuPoId(id);
					o_voznji o = new o_voznji(v);
				}
			}
		});
		
		ponudi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
					return;
				}
				else {
					int id=Integer.parseInt(tabela.getValueAt(red, 0).toString()) ;
					Voznja v=Taksi_sluzba.pronadjiVoznjuPoId(id);
					if(v.getStatus_voznje()!=Status_voznje.KREIRANA_NA_CEKANJU) {
						JOptionPane.showMessageDialog(null, "Ova voznja nije na aukciji.", "Greska", JOptionPane.WARNING_MESSAGE);
						return;
					}
					vozacPonudaAukcija vpa = new vozacPonudaAukcija(v);
				}
			}
		});
		
		odjava.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame_vozac.dispose();
				LoginGUI loginGUI = new LoginGUI();
			}
		});
	}	
}
