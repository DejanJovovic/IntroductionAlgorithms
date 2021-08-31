package GUI.Vozac;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Enum.Status_voznje;
import Taksi_sluzba.Taksi_sluzba;
import korisnici.Ponuda;
import korisnici.Vozac;
import korisnici.Voznja;

public class vozacPonudaAukcija {
	
	public vozacPonudaAukcija(Voznja v) {
		voznjaZaIzmenu=v;
		vozacPonudaAukcijaGUI();
	}

	private static JFrame frame;
	private static JPanel panel;
	private static JLabel trajanje;
	public static JTextField trajanje_txt;
	private static JButton btn_ponudi;
	
	public static int trajanje1;
	
	public static Voznja voznjaZaIzmenu=null;
	public static Vozac trenutniVozac=null;
	
	public static void vozacPonudaAukcijaGUI() {
		frame = new JFrame();
		panel = new JPanel();
		btn_ponudi = new JButton("Ponudi");

		trajanje = new JLabel("Potrebno vreme (u minutima):");

		trajanje_txt = new JTextField();
		
		frame.setSize(600,450);
		frame.setVisible(true);
		
		
		frame.add(panel);
		panel.add(btn_ponudi);
		
		panel.add(trajanje);
		panel.add(trajanje_txt);

		trajanje.setBounds(40,75,150,25);
		trajanje_txt.setBounds(155,75,165,25);
		btn_ponudi.setBounds(135,125,130,25);
		panel.setLayout(null);
		
		btn_ponudi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trajanje1 = Integer.parseInt(trajanje_txt.getText());
				if(trajanje1<0) {
					JOptionPane.showMessageDialog(null, "Trajanje mora biti vece od 0.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					voznjaZaIzmenu.setStatus_voznje(Status_voznje.KREIRANA_NA_CEKANJU);
					String korisnicko = Taksi_sluzba.getUlogovani().getKorisnicko_ime();
					Vozac v=Taksi_sluzba.pronadjiKorisnicko_ime(korisnicko);
					Ponuda p = new Ponuda(voznjaZaIzmenu.getId(),v , trajanje1);
					Taksi_sluzba.ListaPonuda.add(p);
					frame.dispose();
					vozacforma.frame_vozac.dispose();
					vozacforma.vozacformaGUI();
					Taksi_sluzba.sacuvajVoznjuFajl();	 
					Taksi_sluzba.sacuvajPonudeFajl();
				}
			}
			});	
	}
}