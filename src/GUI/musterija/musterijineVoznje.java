package GUI.musterija;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Enum.Status_voznje;
import GUI.LoginGUI;
import Taksi_sluzba.Taksi_sluzba;
import korisnici.Ocena;
import korisnici.Voznja;

public class musterijineVoznje {
	
	public musterijineVoznje() {
		musterijineVoznjeGUI();
	}
	
	protected static String VozacKorisnicko_ime = null;
	static JFrame frame_vozac;
	static JPanel panel_vozac;
	private static JButton btnOceni = new JButton("Oceni");

	private static JButton odjava = new JButton("Odjava");
	private static JTextField ocenaTxt=new JTextField();
	static DefaultTableModel tabelaModel;
	static JTable tabela;
	

	
	public static void musterijineVoznjeGUI() {
		frame_vozac = new JFrame();
		panel_vozac = new JPanel();
		frame_vozac.add(panel_vozac);
		
	
		panel_vozac.add(odjava);
		
		
		odjava.setBounds(140,50,50,20);
		frame_vozac.setVisible(true);
		frame_vozac.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame_vozac.setSize(700,200);

		String[] kolona = new String[] {"id","vreme", "adresa1", "adresa2", "musterija", "vozac", "predjeni km", "trajanje", "status"};
		tabelaModel = new DefaultTableModel(kolona,0);
		tabelaModel.addRow(kolona);

		tabela = new JTable(tabelaModel);
		
		String korisnik = LoginGUI.korisnicko_ime_txt.getText();
		
		
		for(int i = 0; i < Taksi_sluzba.ListaVoznji.size();i++) {
			if(Taksi_sluzba.ListaVoznji.get(i).getMusterija().equals(korisnik) ) {
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
			else {
				continue;
			}
		}
		panel_vozac.add(tabela);
		
		panel_vozac.add(ocenaTxt);
		panel_vozac.add(btnOceni);
		btnOceni.setBounds(10,10,50,20);
		ocenaTxt.setPreferredSize(new Dimension(50,20));
		
		
		btnOceni.addActionListener(new ActionListener() {
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
					String vozac = v.getVozac();
					if(v.getStatus_voznje()!=Status_voznje.ZAVRSENA) {
						JOptionPane.showMessageDialog(null, "Voznja nije zavrsena.", "Greska", JOptionPane.WARNING_MESSAGE);
						return;
					}
					else {
						Ocena o = Taksi_sluzba.nadjiOcenuPoId(id);
						if(o!=null) {
							JOptionPane.showMessageDialog(null, "Voznja je vec ocenjena.", "Greska", JOptionPane.WARNING_MESSAGE);
							return;
						}
						else {
							double ocena;
							try {
								ocena=Double.parseDouble(ocenaTxt.getText());
							}
							catch (Exception exc) {
								JOptionPane.showMessageDialog(null, "Morate uneti ocenu.", "Greska", JOptionPane.WARNING_MESSAGE);
								return;
							}
							
							o = new Ocena(id,vozac,ocena);
							Taksi_sluzba.ListaOcena.add(o);
							Taksi_sluzba.sacuvajOceneFajl();
						}
					}
					
	
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
