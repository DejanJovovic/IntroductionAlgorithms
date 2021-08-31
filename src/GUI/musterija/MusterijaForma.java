package GUI.musterija;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GUI.LoginGUI;


public class MusterijaForma {
	
	public static JFrame frame_musterija;
	private static JPanel panel_musterija;
	private static JButton btnTelefon;
	private static JButton btnAplikacija;
	private static JButton btnOdjava;
	private static JButton btnMojeVoznje;
	
	public MusterijaForma() {
		MusterijaFormaGUI();
	}
	
	public void MusterijaFormaGUI() {
		frame_musterija = new JFrame("Pocetna");
		panel_musterija = new JPanel();
		
		frame_musterija.add(panel_musterija);
		frame_musterija.setSize(400,250);
		frame_musterija.setVisible(true);
		frame_musterija.setLocation(700,320);
		frame_musterija.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		btnTelefon = new JButton("Narucivanje putem telefona");
		btnAplikacija = new JButton("Narucivanje putem aplikacije");
		btnMojeVoznje = new JButton("Oceni...");
		
		btnTelefon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame_musterija.disable();
				TelefonForma tf = new TelefonForma();
	        }
	    });
		
		btnAplikacija.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Nemoguce naruciti voznju putem aplikacije.", "Greska", JOptionPane.WARNING_MESSAGE);
	        }
	    });
		
		btnMojeVoznje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				musterijineVoznje mv = new musterijineVoznje();
	        }
	    });
		
		
		btnOdjava = new JButton( new AbstractAction("Odjava") {
		       @Override
		       public void actionPerformed( ActionEvent e ) {
		    	   LoginGUI lg = new LoginGUI();
		    	   frame_musterija.dispose();
		       }
		   });
		
		
		btnTelefon.setBounds(100, 40, 200, 30);
		btnMojeVoznje.setBounds(100, 80, 200, 30);
		btnAplikacija.setBounds(100, 120, 200, 30);
		btnOdjava.setBounds(280, 5, 100, 15);
		panel_musterija.setLayout(null);
		panel_musterija.add(btnTelefon);
		panel_musterija.add(btnAplikacija);
		panel_musterija.add(btnOdjava);
		panel_musterija.add(btnMojeVoznje);
	}
}
