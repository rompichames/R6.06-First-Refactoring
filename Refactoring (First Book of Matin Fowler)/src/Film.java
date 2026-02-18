public class Film {
	public static final int CINEPHILE = 4;
	public static final int COFFRET_SERIES_TV = 3;
	public static final int ENFANT = 2;
	public static final int NOUVEAUTE = 1;
	public static final int NORMAL = 0;
	
	private String titre;
	private int codePrix;
	private Prix prix;
	
	public Film(String titre, int codePrix) {
		this.titre = titre;
		setCodePrix(codePrix);
	}

	public String getTitre() {
		return this.titre;
	}

	public void setCodePrix(int codePrix) {
		this.codePrix = codePrix;
		switch (codePrix) {
			case NORMAL: prix = new Prix.PrixNormal(); break;
			case NOUVEAUTE: prix = new Prix.PrixNouveaute(); break;
			case ENFANT: prix = new Prix.PrixEnfant(); break;
			case COFFRET_SERIES_TV: prix = new Prix.PrixCoffretSeries(); break;
			case CINEPHILE: prix = new Prix.PrixCinephile(); break;
		}
	}

	public int getCodePrix() {
		return this.codePrix;
	}

	public double getMontant(int nbJours) {
		return prix.getMontant(nbJours);
	}

	public int getPointsFidelite(int nbJours) {
		return prix.getPointsFidelite(nbJours);
	}
}