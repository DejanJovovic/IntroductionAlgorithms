package GUI.dispecer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Enum.Status_voznje;
import Taksi_sluzba.Taksi_sluzba;
import korisnici.Vozac;
import korisnici.Voznja;

public class dodelavozacimaforma {
	
	public static JFrame frame_dodela;
	static JPanel panel_dodela;
	private JButton dodela = new JButton("Izaberi vozaca");
	static DefaultTableModel tabelaModel;
	static JTable tabela;
	private static Voznja voznjaZaDodelu;
	
	public dodelavozacimaforma(Voznja v) {
		voznjaZaDodelu=v;
		dodelavozacimaformaGUI();
	}
	
	public void dodelavozacimaformaGUI() {
		
		
		
		frame_dodela = new JFrame();
		panel_dodela = new JPanel();
		frame_dodela.add(panel_dodela);
		panel_dodela.add(dodela);
		
		frame_dodela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		String[] kolona = new String[] {"ID", "Korisnicko ime", "Ime", "Prezime", "Automobil"};
		
		tabelaModel = new DefaultTableModel(kolona,0);
		
		tabela = new JTable(tabelaModel);
		
		frame_dodela.setVisible(true);
		frame_dodela.setSize(400,250);
		
		dodela.setBounds(100,100,50,50);
		
		for(int i = 0; i < Taksi_sluzba.ListaVozaca.size();i++) {
			if (Taksi_sluzba.ListaVozaca.get(i).isObrisan() == false) {
				
				String korisnicko_ime = Taksi_sluzba.ListaVozaca.get(i).getKorisnicko_ime();
				String ime = Taksi_sluzba.ListaVozaca.get(i).getIme();
				String prezime = Taksi_sluzba.ListaVozaca.get(i).getPrezime();
				String automobil = Taksi_sluzba.ListaVozaca.get(i).getAutomobilId()+"";
			
				Object[] sadrzaj = {korisnicko_ime,ime,prezime,automobil};
				tabelaModel.addRow(sadrzaj);
			}
			else {
				continue;
			}
		}
		panel_dodela.add(tabela);
		
		dodela.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					prikaz_voznji_forma.frame_voznje.dispose();
					String korisnicko_ime_vozaca = tabela.getValueAt(red, 0).toString();
					voznjaZaDodelu.setVozac(korisnicko_ime_vozaca);
					voznjaZaDodelu.setStatus_voznje(Status_voznje.DODELJENA);
					prikaz_voznji_forma.prikaz_voznji_formaGUI();
					Taksi_sluzba.sacuvajVoznjuFajl();
					frame_dodela.dispose();
				}
			}
		});
	}
}
