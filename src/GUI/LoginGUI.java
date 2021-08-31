package GUI;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Enum.TipKorisnika;
import GUI.Vozac.vozacforma;
import GUI.dispecer.DispecerForma;
import GUI.musterija.MusterijaForma;
import Taksi_sluzba.Taksi_sluzba;
import korisnici.Korisnik;


public class LoginGUI {
	
	public LoginGUI() {
		loginGUI();
	}
	
	File file_korisnik = new File("korisnik.txt");
    File file_musterija = new File("musterija.txt");
    File file_dispecer = new File("dispecer.txt");
    File file_vozac = new File("vozac.txt");
	private Taksi_sluzba taksi_sluzba;

	public static JLabel ispis;
	private static JFrame frame_prijava;
	private static JPanel panel_prijava;
	private static JLabel korisnicko_ime_label;
	private static JLabel lozinka_label;
	public static JTextField korisnicko_ime_txt;
	public static JPasswordField lozinka_txt;
	private static JButton btn_prijava;

	public LoginGUI(Taksi_sluzba taksi_sluzba) {
		this.taksi_sluzba = taksi_sluzba;
		loginGUI();
	}

	public void loginGUI() {
	frame_prijava = new JFrame("Prijava");
	panel_prijava = new JPanel();

	korisnicko_ime_label = new JLabel("Korisnicko ime:");
	lozinka_label = new JLabel("Lozinka:");
	ispis = new JLabel();

	korisnicko_ime_txt = new JTextField();
	lozinka_txt = new JPasswordField();

	korisnicko_ime_label.setBounds(40,35,150,25);
	korisnicko_ime_txt.setBounds(155,35,165,25);

	lozinka_label.setBounds(40,75,150,25);
	lozinka_txt.setBounds(155,75,165,25);

	btn_prijava = new JButton( new AbstractAction("Prijava") {
	       @Override
	       public void actionPerformed( ActionEvent e ) {
	    	   Korisnik k = Taksi_sluzba.PrijavaKorisnika();
	    	   
    		   TipKorisnika TipK = k.getTip_korisnika();
	    	   if(TipK != null) {
	    		   switch(TipK) {
		    	   case MUSTERIJA:
		    		   frame_prijava.dispose();
		    		   MusterijaForma mf = new MusterijaForma();
		    		   break;
		    	   case DISPECER:
		    		   frame_prijava.dispose();
		    		   DispecerForma dp = new DispecerForma();
		    		   break;
		    	   case VOZAC:
		    		   frame_prijava.dispose();
		    		   vozacforma v = new vozacforma();
		    		   break;
	    		   }
    		   }
	    	   else {
	    		   ispis.setText("Korisnik nije prijavljen");
		    	   korisnicko_ime_txt.setText("");
		    	   lozinka_txt.setText("");
	    	   }
	       	}
	   });

	btn_prijava.setBounds(135,125,80,25);

	ispis.setBounds(120,165,250,25);

	frame_prijava.setSize(400,250);

	frame_prijava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame_prijava.add(panel_prijava);

	panel_prijava.setLayout(null);
	panel_prijava.add(korisnicko_ime_label);
	panel_prijava.add(korisnicko_ime_txt);
	panel_prijava.add(lozinka_label);
	panel_prijava.add(lozinka_txt);
	panel_prijava.add(btn_prijava);
	panel_prijava.add(ispis);

	frame_prijava.setVisible(true);
	frame_prijava.setLocation(700,320);
	}
}
