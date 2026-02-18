public class Film {

	public static final int CINEPHILE = 4;
	public static final int COFFRET_SERIES_TV = 3;
	public static final int ENFANT = 2;
	public static final int NOUVEAUTE = 1;
	public static final int NORMAL = 0;
	
	private String titre;
	private int codePrix;
	
	public Film(String titre, int codePrix) {
		this.titre = titre;
		this.codePrix = codePrix;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setCodePrix(int codePrix) {
		this.codePrix = codePrix;
	}

	public int getCodePrix() {
		return this.codePrix;
	}

	public double getMontant(int nbJours) {
		double du = 0;
		switch (getCodePrix()) {
			case Film.NORMAL:
				du += 2;
				if (nbJours > 2)
					du += (nbJours - 2) * 1.5;
				break;
			case Film.NOUVEAUTE:
				du += nbJours * 3;
				break;
			case Film.ENFANT:
				du += 1.5;
				if (nbJours > 3)
					du += (nbJours - 3) * 1.5;
				break;
			case Film.COFFRET_SERIES_TV:
				du += nbJours * 0.5;
				break;
			case Film.CINEPHILE:
				du += 2;
				if (nbJours > 1)
					du += (nbJours - 1) * 4;
				break;
		}
		return du;
	}

	public int getPointsFidelite(int nbJours) {
		int pointsFidelites = 0;

		if (codePrix == Film.CINEPHILE) {
			if (nbJours == 1) pointsFidelites += 3;
		} else if (codePrix != Film.COFFRET_SERIES_TV) {
			pointsFidelites++;
			if (codePrix == Film.NOUVEAUTE && nbJours > 1) pointsFidelites++;
		}
		return pointsFidelites;
	}
}