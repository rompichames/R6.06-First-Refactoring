
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

	public List<Location> getLocations() { return this.locations; }
	
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
		return new Statement.TextStatement().value(this);
	}

	public String situationHTML() {
		return new Statement.HtmlStatement().value(this);
	}

}
