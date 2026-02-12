public class Location {
	private Film unFilm;
	private int nbJours;
	
	public Location(Film unFilm, int nbJours) {
		this.unFilm = unFilm;
		this.nbJours = nbJours;
	}

	public int getNbJours() {
		return this.nbJours;
	}

	public Film getFilm() {
		return this.unFilm;
	}

	public double getMontant() {
		double du = 0;
		switch (getFilm().getCodePrix()) {
			case Film.NORMAL:
				du += 2;
				if (getNbJours() > 2)
					du += (getNbJours() - 2) * 1.5;
				break;
			case Film.NOUVEAUTE:
				du += getNbJours() * 3;
				break;
			case Film.ENFANT:
				du += 1.5;
				if (getNbJours() > 3)
					du += (getNbJours() - 3) * 1.5;
				break;
			case Film.COFFRET_SERIES_TV:
				du += getNbJours() * 0.5;
				break;
			case Film.CINEPHILE:
				du += 2;
				if (getNbJours() > 1)
					du += (getNbJours() - 1) * 4;
				break;
		}
		return du;
	}

	public int getPointsFidelite() {
		int pointsFidelites = 0;
		int code = getFilm().getCodePrix();

		if (code == Film.CINEPHILE) {
			if (getNbJours() == 1) pointsFidelites += 3;
		} else if (code != Film.COFFRET_SERIES_TV) {
			pointsFidelites++;
			if (code == Film.NOUVEAUTE && getNbJours() > 1) pointsFidelites++;
		}
		return pointsFidelites;
	}
}