package GUI.dispecer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Enum.Status_voznje;
import Taksi_sluzba.Taksi_sluzba;
import korisnici.Ponuda;
import korisnici.Vozac;
import korisnici.Voznja;

public class PretragaVozaca {
	
	public PretragaVozaca() {
		PretragaVozacaGUI();
	}

	private static JFrame frame;
	private static JPanel panelGornji;
	private static JLabel ime;
	public static JTextField imeTxt;
	private static JLabel prezime;
	public static JTextField prezimeTxt;
	private static JLabel minPlata;
	public static JTextField minPlataTxt;
	private static JLabel maxPlata;
	public static JTextField maxPlataTxt;
	private static JLabel automobilId;
	public static JTextField automobilIdTxt;
	private static JScrollPane jpane;
	private static JButton btn_trazi;
	static DefaultTableModel tabelaModel;
	static JTable tabela;
	private static JTextArea ispis;
	 
	
	public static void PretragaVozacaGUI() {
		frame = new JFrame();
		JPanel panelVeliki = new JPanel(new GridLayout(2,1));
		panelGornji = new JPanel(new GridLayout(6,2));
		JPanel panelDonji = new JPanel(new GridLayout(2, 0));
		ime=new JLabel("Ime:");
		prezime=new JLabel("Prezime:");
		minPlata=new JLabel("minPlata:");
		maxPlata=new JLabel("maxPlata:");
		automobilId=new JLabel("automobilId:");
		
		imeTxt=new JTextField();
		prezimeTxt=new JTextField();
		minPlataTxt=new JTextField();
		maxPlataTxt=new JTextField();
		automobilIdTxt=new JTextField();
		btn_trazi=new JButton("Pretrazi");
		
		tabelaModel = new DefaultTableModel();
		tabela = new JTable(tabelaModel);
		jpane = new JScrollPane(tabela);
		frame.setSize(800,650);
		frame.setVisible(true);
		
		frame.add(panelVeliki);
		panelVeliki.add(panelGornji);
		panelGornji.add(ime);
		panelGornji.add(imeTxt);
		panelGornji.add(prezime);
		panelGornji.add(prezimeTxt);
		panelGornji.add(minPlata);
		panelGornji.add(minPlataTxt);
		panelGornji.add(maxPlata);
		panelGornji.add(maxPlataTxt);
		panelGornji.add(automobilId);
		panelGornji.add(automobilIdTxt);
		
		
		
		
		panelVeliki.add(panelDonji);
		panelDonji.add(btn_trazi);
		//panelDonji.add(tabela);
		//panelDonji.add(jpane);
		ispis = new JTextArea("ispis");
		ispis.setEditable(false);
		panelDonji.add(ispis);
		String[] kolona = new String[] { "Korisnicko ime", "Ime", "Prezime", "Automobil"};
		tabelaModel = new DefaultTableModel(kolona,0);
		tabelaModel.addRow(kolona);

		
		btn_trazi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pretrazi();
			}

		});


		
		//panel.setLayout();
		
	}


	protected static void pretrazi() {
		ispis.setText("");
		String ispisString="Korisnicko ime |Ime |Prezime |AutomobilID |Plata |Ocena\n";
		ArrayList<Vozac> nadjeni = new ArrayList<>();
		String ime = imeTxt.getText();
		String prezime = prezimeTxt.getText();
		int minPlata = 0;
		int maxPlata = 999999999;
		int autoId=-1;
		
		try {
			minPlata= Integer.parseInt(minPlataTxt.getText());
		}
		catch (Exception e) {
			//e.printStackTrace();
		}
		try {
			maxPlata= Integer.parseInt(maxPlataTxt.getText());
		}
		catch (Exception e) {
			//e.printStackTrace();
		}
		try {
			autoId= Integer.parseInt(automobilIdTxt.getText());
		}
		catch (Exception e) {
			//e.printStackTrace();
		}
		
		for (Vozac v : Taksi_sluzba.ListaVozaca) {
			if(v.getPlata()<minPlata || v.getPlata()>maxPlata) continue;
			if(ime.length()>0 && ime.compareTo(v.getIme())!=0) continue;
			if(prezime.length()>0 && prezime.compareTo(v.getPrezime())!=0) continue;
			if(autoId!=-1 && autoId!=v.getAutomobilId()) continue;
			
			nadjeni.add(v);
		}
		

		while(tabelaModel.getRowCount()>1) {
			tabelaModel.removeRow(1);
		}
		

		for(Vozac v : nadjeni) {	
				Object [] sadrzaj = {v.getKorisnicko_ime(),v.getIme(),v.getPrezime(),v.getAutomobilId()+""};

				ispisString+=v.stringZaUpisTabela()+"\n";
				tabelaModel.addRow(sadrzaj);
				
			}
		
		ispis.setText(ispisString);
		
	}
}