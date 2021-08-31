package GUI.dispecer;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.LoginGUI;

public class DispecerForma {
	
	public static JFrame frame_dispecer;
	private static JPanel panel_dispecer;
	private static JButton btn_izmena;
	private static JButton btn_izvestaj;
	private static JButton btn_prikaz_svih_voznji;
	private static JButton btn_pretraga;
	private static JButton btnOdjava;

	public DispecerForma() {
		dispecerGUI();
	}
	
	public void dispecerGUI() {
		frame_dispecer = new JFrame();
		panel_dispecer = new JPanel();

		frame_dispecer.add(panel_dispecer);
		frame_dispecer.setSize(400,250);
		frame_dispecer.setVisible(true);
		frame_dispecer.setLocation(700,320);
		frame_dispecer.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


		btn_izmena = new JButton( new AbstractAction("Izmena vozaca") {
		       @Override
		       public void actionPerformed( ActionEvent e ) {
		    	   frame_dispecer.disable();
		    	   IzmenaVozacaForma iz = new IzmenaVozacaForma();
		       }
		   });

		btn_izvestaj = new JButton( new AbstractAction("Izvestaji") {
		       @Override
		       public void actionPerformed( ActionEvent e ) {
		    	   frame_dispecer.disable();
		    	   IzvestajiForma iz = new IzvestajiForma();
		       }
		   });		
		
		btn_prikaz_svih_voznji = new JButton( new AbstractAction("Prikaz svih voznji") {
		       @Override
		       public void actionPerformed( ActionEvent e ) {
		    	   frame_dispecer.disable();
		    	   prikaz_voznji_forma pv = new prikaz_voznji_forma();
		       }
		   });

		btn_pretraga = new JButton( new AbstractAction("Pretraga vozaca") {
		       @Override
		       public void actionPerformed( ActionEvent e ) {
		    	   PretragaVozaca p = new PretragaVozaca();
		       }
		   });
		
		btnOdjava = new JButton( new AbstractAction("Odjava") {
		       @Override
		       public void actionPerformed( ActionEvent e ) {
		    	   LoginGUI lg = new LoginGUI();
		    	   frame_dispecer.dispose();
		       }
		   });
		
		btn_izmena.setBounds(120, 35, 190, 30);
		btn_prikaz_svih_voznji.setBounds(120, 75, 190, 30);
		btn_pretraga.setBounds(120, 115, 190, 30);
		btn_izvestaj.setBounds(120, 155, 190, 30);
		btnOdjava.setBounds(280, 5, 100, 15);
		
		
		panel_dispecer.setLayout(null);

		panel_dispecer.add(btn_izmena);
		panel_dispecer.add(btn_prikaz_svih_voznji);
		panel_dispecer.add(btn_pretraga);
		panel_dispecer.add(btn_izvestaj);
		panel_dispecer.add(btnOdjava);
	}
}
