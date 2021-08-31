package korisnici;

public class Ocena {
	private String vozac;
	private int voznjaId;
	private double ocena;
	
	public Ocena(int voznjaID,String vozac, double ocena) {
		if(ocena>5) ocena=5;
		if(ocena<0) ocena=0;
		this.ocena=ocena;
		this.vozac=vozac;
		this.voznjaId=voznjaID;
	}
	
	public Ocena(String ulaz) {
		String[] split =ulaz.split("\\|");
		int voznjaId = Integer.parseInt(split[0]);
		String vozac=split[1];
		double ocena=Double.parseDouble(split[2]);
		this.ocena=ocena;
		this.vozac=vozac;
		this.voznjaId=voznjaId;
	}
	
	public String stringZaUpisFajl() {
		return ""+voznjaId+"|"+vozac+"|"+ocena;
	}

	public String getVozac() {
		return vozac;
	}

	public void setVozac(String vozac) {
		this.vozac = vozac;
	}

	public int getVoznjaId() {
		return voznjaId;
	}

	public void setVoznjaId(int voznjaId) {
		this.voznjaId = voznjaId;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}
	
	
}
