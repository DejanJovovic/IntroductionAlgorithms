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
import korisnici.Voznja;

public class o_voznji {
	
	public o_voznji(Voznja v) {
		voznjaZaIzmenu=v;
		o_voznjiGUI();
	}

	private static JFrame frame;
	private static JPanel panel;
	private static JLabel km;
	private static JLabel trajanje;
	public static JTextField km_txt;
	public static JTextField trajanje_txt;
	private static JButton btn_zavrsi;
	
	public static int km1;
	public static int trajanje1;
	
	public static Voznja voznjaZaIzmenu=null;
	
	public static void o_voznjiGUI() {
		frame = new JFrame();
		panel = new JPanel();
		btn_zavrsi = new JButton("Zavrsi voznju");

		km = new JLabel("Predjeni kilometri:");
		trajanje = new JLabel("Trajanje voznje:");

		km_txt = new JTextField();
		trajanje_txt = new JTextField();
		
		frame.setSize(600,450);
		frame.setVisible(true);
		
		
		frame.add(panel);
		panel.add(btn_zavrsi);
		panel.add(km);
		panel.add(trajanje);
		panel.add(km_txt);
		panel.add(trajanje_txt);

		km.setBounds(40,35,140,25);
		km_txt.setBounds(155,35,165,25);
		trajanje.setBounds(40,75,150,25);
		trajanje_txt.setBounds(155,75,165,25);
		btn_zavrsi.setBounds(135,125,130,25);
		panel.setLayout(null);
		
		btn_zavrsi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				km1 = Integer.parseInt(km_txt.getText());
				trajanje1 = Integer.parseInt(trajanje_txt.getText());
				int red = vozacforma.tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					voznjaZaIzmenu.setStatus_voznje(Status_voznje.ZAVRSENA);
					voznjaZaIzmenu.setBroj_km(km1);
					voznjaZaIzmenu.setTrajanje_voznje(trajanje1);
					frame.dispose();
					vozacforma.frame_vozac.dispose();
					vozacforma.vozacformaGUI();
					Taksi_sluzba.sacuvajVoznjuFajl();	 
				}
			}
			});	
	}
}
