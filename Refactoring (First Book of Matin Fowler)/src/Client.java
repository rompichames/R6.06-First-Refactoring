
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

	public double getTotalDu() {
		double total = 0;
		for (Location location : this.locations) {
			total += location.getMontant();
		}
		return total;
	}

	public int getPointsFideliteTotal() {
		int points = 0;
		for (Location location : this.locations) {
			points += location.getPointsFidelite();
		}
		return points;
	}

	public String situation() {
		String result = "Situation du client: " + getNom() + "\n";

		for (Location each : locations) {
			result += "\t" + each.getFilm().getTitre() + "\t" + each.getMontant() + "\n";

			// ajout recapitulatif client
			result += "Total du " + getTotalDu() + "\n";
			result += "Vous gagnez " + getPointsFideliteTotal() + " points de fidelite\n";
		}
		return result;
	}

	public String situationHTML() {
		String result = "<h1>Situation du client: " + getNom() + "</h1><ul>\n";

		for (Location each : locations) {
			result += "<li>" + each.getFilm().getTitre() + " : " + each.getMontant() + "€</li>\n";
			result += "</ul><p>Total dû : <b>" + getTotalDu() + "€</b></p>\n";
			result += "<p>Points gagnés : <b>" + getPointsFideliteTotal() + "</b></p>";
		}

		return result;
	}

}
