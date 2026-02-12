
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Client {
	private String nom;
	private List<Location> locations = new LinkedList<Location>();
	
	public Client(String nom) {
		this.nom = nom;
	}
	
	public void addLocation(Location location) {
		this.locations.add(location);
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String situation() {
		double totalDu = 0;
		int pointsFidelites = 0;
		Iterator<Location> forEach = locations.iterator();
		String result = "Situation du client: " + getNom() + "\n";
		
		while (forEach.hasNext()) {
			double du = 0;
			Location each = (Location) forEach.next();
			
			//determine le montant de chaque location
			switch (each.getFilm().getCodePrix()) {
			case Film.NORMAL:
				du += 2;
				if (each.getNbJours() > 2) 
					du += (each.getNbJours() - 2) * 1.5;
				break;
			case Film.NOUVEAUTE:
				du += each.getNbJours() * 3;
				break;
			case Film.ENFANT:
				du += 1.5;
				if (each.getNbJours() > 3)
					du += (each.getNbJours() - 3) * 1.5;
				break;
			case Film.COFFRET_SERIES_TV:
				du += each.getNbJours() * 0.5;
				break;
			case Film.CINEPHILE:
				du += 2;
				if (each.getNbJours() > 1) {
					du += (each.getNbJours() - 1) * 4;
				}
				break;
			}

			// ajout des points de fidelite
			if (each.getFilm().getCodePrix() == Film.CINEPHILE) {
				if (each.getNbJours() == 1) {
					pointsFidelites += 3;
				}
			}
			else if (each.getFilm().getCodePrix() != Film.COFFRET_SERIES_TV) {
				// Cas standards
				pointsFidelites++;
				// ajout d'un bonus pour les nouveautes louees depuis au moins deux jours
				if ((each.getFilm().getCodePrix() == Film.NOUVEAUTE) && each.getNbJours() > 1)
					pointsFidelites++;
			}
			
			// mise en forme location
			result += "\t" + each.getFilm().getTitre() + "\t" + String.valueOf(du) + "\n";
			totalDu += du;
		}
		
		// ajout recapitulatif client
		result += "Total du " + String.valueOf(totalDu) + "\n";
		result += "Vous gagnez " + String.valueOf(pointsFidelites) + " points de fidelite\n";
		
		return result;
	}

	public String situationHTML() {
		double totalDu = 0;
		int pointsFidelites = 0;
		String result = "<html>\n<body>\n";
		result += "<h1>Situation du client : <em>" + getNom() + "</em></h1>\n";
		result += "<table border=\"1\">\n";
		result += "  <tr><th>Film</th><th>Prix</th></tr>\n";

		for (Location each : locations) {
			double du = 0;
			//determine le montant de chaque location
			switch (each.getFilm().getCodePrix()) {
				case Film.NORMAL:
					du += 2;
					if (each.getNbJours() > 2)
						du += (each.getNbJours() - 2) * 1.5;
					break;
				case Film.NOUVEAUTE:
					du += each.getNbJours() * 3;
					break;
				case Film.ENFANT:
					du += 1.5;
					if (each.getNbJours() > 3)
						du += (each.getNbJours() - 3) * 1.5;
					break;
				case Film.COFFRET_SERIES_TV:
					du += each.getNbJours() * 0.5;
					break;
				case Film.CINEPHILE:
					du += 2;
					if (each.getNbJours() > 1) {
						du += (each.getNbJours() - 1) * 4;
					}
					break;
			}

			// ajout des points de fidelite
			if (each.getFilm().getCodePrix() == Film.CINEPHILE) {
				if (each.getNbJours() == 1) {
					pointsFidelites += 3;
				}
			}
			else if (each.getFilm().getCodePrix() != Film.COFFRET_SERIES_TV) {
				// Cas standards
				pointsFidelites++;
				// ajout d'un bonus pour les nouveautes louees depuis au moins deux jours
				if ((each.getFilm().getCodePrix() == Film.NOUVEAUTE) && each.getNbJours() > 1)
					pointsFidelites++;
			}

			result += "  <tr><td>" + each.getFilm().getTitre() + "</td><td>" + du + " €</td></tr>\n";
			totalDu += du;
		}

		result += "</table>\n";
		result += "<p>Total dû : <strong>" + totalDu + " €</strong></p>\n";
		result += "<p>Vous gagnez <strong>" + pointsFidelites + "</strong> points de fidélité</p>\n";
		result += "</body>\n</html>";

		return result;
	}

}
