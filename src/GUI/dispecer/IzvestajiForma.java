package GUI.dispecer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;


import Taksi_sluzba.Taksi_sluzba;
import korisnici.Korisnik;
import korisnici.Vozac;

public class IzvestajiForma {
	
	public static JFrame frameIzvestaj;
	static JPanel panelIzvestaj;

	private static JButton nazad = new JButton("Nazad");
	
	public static JRadioButton godisnji=new JRadioButton("Godisnji izvestaj");
	public static JRadioButton mesecni=new JRadioButton("Mesecni izvestaj");
	public static JRadioButton dnevni=new JRadioButton("Dnevni izvestaj");
	public static JTextArea izvestaj = new JTextArea();
	


	
	public IzvestajiForma() {
		izvestajGUI();
	}
	
	public static void izvestajGUI() {
		
		frameIzvestaj = new JFrame();
		panelIzvestaj = new JPanel();
		
		frameIzvestaj.add(panelIzvestaj);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(godisnji);
		bg.add(mesecni);
		bg.add(dnevni);
		dodajListenereZaRadio();
		izvestaj.setEditable(false);
		panelIzvestaj.add(godisnji);
		panelIzvestaj.add(mesecni);
		panelIzvestaj.add(dnevni);
		panelIzvestaj.add(izvestaj);
		panelIzvestaj.add(nazad);


		frameIzvestaj.setVisible(true);
		frameIzvestaj.setSize(400,250);
		frameIzvestaj.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


		
	}


	private static void dodajListenereZaRadio() {
		
		godisnji.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				izvestaj.setText(Taksi_sluzba.generisiGodisnjiIzvestaj());
			}
		});
		
		mesecni.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				izvestaj.setText(Taksi_sluzba.generisiMesecniIzvestaj());
			}
		});

		dnevni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				izvestaj.setText(Taksi_sluzba.generisiDnevniIzvestaj());
			}
		});
		
	}
	
	
}
