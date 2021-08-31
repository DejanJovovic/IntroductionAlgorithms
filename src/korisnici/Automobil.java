package korisnici;

import Enum.Vrsta_automobila;

public class Automobil {
	private String model;
	private String proizvodjac;
	private int godina_proizvodnje;
	private String registracija;
	private String br_vozila;
	private Vrsta_automobila vrsta_automobila;
	private int id;
	static int sledeciId=1;
	
	public Automobil() {
		
	}
	
	public Automobil(String model, String proizvodjac, int godina_proizvodnje, String registracija, String br_vozila,
			Vrsta_automobila vrsta_automobila) {
		super();
		id=sledeciId+1;
		sledeciId++;
		this.model = model;
		this.proizvodjac = proizvodjac;
		this.godina_proizvodnje = godina_proizvodnje;
		this.registracija = registracija;
		this.br_vozila = br_vozila;
		this.vrsta_automobila = vrsta_automobila;
	}

	public Automobil(int id, String model, String proizvodjac, int godina_proizvodnje, String registracija, String br_vozila,
			Vrsta_automobila vrsta_automobila) {
		super();
		this.id=id;
		if(id>sledeciId) sledeciId++;
		this.model = model;
		this.proizvodjac = proizvodjac;
		this.godina_proizvodnje = godina_proizvodnje;
		this.registracija = registracija;
		this.br_vozila = br_vozila;
		this.vrsta_automobila = vrsta_automobila;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public int getGodina_proizvodnje() {
		return godina_proizvodnje;
	}

	public void setGodina_proizvodnje(int godina_proizvodnje) {
		this.godina_proizvodnje = godina_proizvodnje;
	}

	public String getRegistracija() {
		return registracija;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}

	public String getBr_vozila() {
		return br_vozila;
	}

	public void setBr_vozila(String br_vozila) {
		this.br_vozila = br_vozila;
	}

	public Vrsta_automobila getVrsta_automobila() {
		return vrsta_automobila;
	}

	public void setVrsta_automobila(Vrsta_automobila vrsta_automobila) {
		this.vrsta_automobila = vrsta_automobila;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Automobil [model=" + model + ", proizvodjac=" + proizvodjac + ", godina_proizvodnje="
				+ godina_proizvodnje + ", registracija=" + registracija + ", br_vozila=" + br_vozila
				+ ", vrsta_automobila=" + vrsta_automobila + "]";
	}
}
