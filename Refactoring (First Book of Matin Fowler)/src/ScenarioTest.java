import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class ScenarioTest {

	private Client client;

	@BeforeEach
	void setUp() {
		client = new Client("John Doe");
	}

	@Test
	@DisplayName("Film normal - Location courte (<= 2 jours)")
	void testNormalCourt() {
		client.addLocation(new Location(new Film("Taxi Driver", Film.NORMAL), 2));
		String attendu = "Situation du client: John Doe\n"
				+ "\tTaxi Driver\t2.0\n"
				+ "Total du 2.0\n"
				+ "Vous gagnez 1 points de fidelite\n";
		assertEquals(attendu, client.situation());
	}

	@Test
	@DisplayName("Film normal - Location longue (> 2 jours)")
	void testNormalLong() {
		client.addLocation(new Location(new Film("Taxi Driver", Film.NORMAL), 3));
		// 2.0 (base) + (1 jour sup * 1.5) = 3.5
		String attendu = "Situation du client: John Doe\n"
				+ "\tTaxi Driver\t3.5\n"
				+ "Total du 3.5\n"
				+ "Vous gagnez 1 points de fidelite\n";
		assertEquals(attendu, client.situation());
	}

	@Test
	@DisplayName("Film nouveauté - 1 jour")
	void testNouveauteCourt() {
		client.addLocation(new Location(new Film("Avatar 2", Film.NOUVEAUTE), 1));
		String attendu = "Situation du client: John Doe\n"
				+ "\tAvatar 2\t3.0\n"
				+ "Total du 3.0\n"
				+ "Vous gagnez 1 points de fidelite\n";
		assertEquals(attendu, client.situation());
	}

	@Test
	@DisplayName("Film nouveauté - Bonus fidélité (> 1 jour)")
	void testNouveauteBonus() {
		client.addLocation(new Location(new Film("Avatar 2", Film.NOUVEAUTE), 2));
		// 2 jours * 3.0 = 6.0 | Points: 1 (base) + 1 (bonus) = 2
		String attendu = "Situation du client: John Doe\n"
				+ "\tAvatar 2\t6.0\n"
				+ "Total du 6.0\n"
				+ "Vous gagnez 2 points de fidelite\n";
		assertEquals(attendu, client.situation());
	}

	@Test
	@DisplayName("Film enfant - Location courte (<= 3 jours)")
	void testEnfantCourt() {
		client.addLocation(new Location(new Film("Cendrillon", Film.ENFANT), 3));
		String attendu = "Situation du client: John Doe\n"
				+ "\tCendrillon\t1.5\n"
				+ "Total du 1.5\n"
				+ "Vous gagnez 1 points de fidelite\n";
		assertEquals(attendu, client.situation());
	}

	@Test
	@DisplayName("Film enfant - Location longue (> 3 jours)")
	void testEnfantLong() {
		client.addLocation(new Location(new Film("Cendrillon", Film.ENFANT), 4));
		// 1.5 (base) + (1 jour sup * 1.5) = 3.0
		String attendu = "Situation du client: John Doe\n"
				+ "\tCendrillon\t3.0\n"
				+ "Total du 3.0\n"
				+ "Vous gagnez 1 points de fidelite\n";
		assertEquals(attendu, client.situation());
	}

	@Test
	@DisplayName("Test cumul de plusieurs locations")
	void testCumulLocations() {
		client.addLocation(new Location(new Film("Taxi Driver", Film.NORMAL), 2));
		client.addLocation(new Location(new Film("Avatar 2", Film.NOUVEAUTE), 2));

		// Taxi Driver: 2.0€, 1pt
		// Avatar 2: 6.0€, 2pts
		String attendu = "Situation du client: John Doe\n"
				+ "\tTaxi Driver\t2.0\n"
				+ "\tAvatar 2\t6.0\n"
				+ "Total du 8.0\n"
				+ "Vous gagnez 3 points de fidelite\n";
		assertEquals(attendu, client.situation());
	}
}