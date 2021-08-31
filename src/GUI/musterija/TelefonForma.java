package GUI.musterija;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Enum.Status_voznje;
import GUI.LoginGUI;
import Taksi_sluzba.Taksi_sluzba;
import korisnici.Voznja;

public class TelefonForma {
	
	private static JFrame frame_telefon;
	private static JPanel panel_telefon;
	private static JLabel vreme;
	private static JLabel adresa1;
	private static JLabel adresa2;
	private static JTextField adresa1_txt;
	private static JTextField adresa2_txt;
	private static JTextField vreme_txt;
	private static JButton naruci;
	private static JButton odustani;
	
	public TelefonForma() {
		TelefonFormaGUI();
	}
	
	public void TelefonFormaGUI() {
		
		frame_telefon = new JFrame();
		panel_telefon = new JPanel();
		vreme = new JLabel("Datum (u formatu dan-mesec-godina sa crticom): ");
		adresa1 = new JLabel("Polazna adresa: ");
		adresa2 = new JLabel("Konacna adresa: ");
		adresa1_txt = new JTextField();
		adresa2_txt = new JTextField();
		vreme_txt = new JTextField();
		naruci = new JButton("Naruci");
		odustani = new JButton("Odustani");
		
		frame_telefon.add(panel_telefon);
		frame_telefon.setVisible(true);
		panel_telefon.setLayout(null);
		frame_telefon.setSize(450,300);
		
		panel_telefon.add(adresa1);
		panel_telefon.add(adresa2);
		panel_telefon.add(vreme);
		panel_telefon.add(adresa1_txt);
		panel_telefon.add(adresa2_txt);
		panel_telefon.add(vreme_txt);
		panel_telefon.add(naruci);
		panel_telefon.add(odustani);
		
		vreme.setBounds(50,40,120,25);
		adresa1.setBounds(50,80,120,25);
		adresa2.setBounds(50,120,120,25);
		vreme_txt.setBounds(190,40,120,25);
		adresa1_txt.setBounds(190,80,120,25);
		adresa2_txt.setBounds(190,120,120,25);
		naruci.setBounds(100,170,100,25);
		odustani.setBounds(230,170,100,25);
		
		naruci.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String vreme = vreme_txt.getText();
				String adresa1 = adresa1_txt.getText();
				String adresa2 = adresa1_txt.getText();
				String musterija = LoginGUI.korisnicko_ime_txt.getText();
				Status_voznje status = Status_voznje.KREIRANA;
				
				Voznja voznja = new Voznja(Voznja.getSledeciId(),vreme,adresa1,adresa2,musterija,"(vozac)",0,0,status);
				Taksi_sluzba.DodavanjeVoznjeUListu(voznja);
				Taksi_sluzba.sacuvajVoznjuFajl();
			}
		});
		odustani.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MusterijaForma.frame_musterija.enable();
				frame_telefon.dispose();
			}
		});
	}
}
