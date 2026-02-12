import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CinephileTest {
    private Client client;

    @BeforeEach
    public void setup() {
        client = new Client("John Doe");
    }

    @Test
    public void testCinephileDureeInitiale() {
        client.addLocation(new Location(new Film("Transformeurt",4),1));
        // Transformeurt : prix 2 euros
        // Points : 3
        String expected = "Situation du client: John Doe\n"
                + "\tTransformeurt\t2.0\n"
                + "Total du 2.0\n"
                + "Vous gagnez 3 points de fidelite\n";
        assertEquals(expected, client.situation());
    }

    @Test
    public void testCinephileDureeDepassee() {
        client.addLocation(new Location(new Film("Age de glaçon",4),3));
        // Age de glaçon : prix 10 euros
        // Points : 0
        String expected = "Situation du client: John Doe\n"
                + "\tAge de glaçon\t10.0\n"
                + "Total du 10.0\n"
                + "Vous gagnez 0 points de fidelite\n";
        assertEquals(expected, client.situation());
    }

    @Test
    public void testCinephileHtml() {
        client.addLocation(new Location(new Film("Age de glaçon", 4), 3));
        String obtenu = client.situationHTML();

        // Vérification (2euros + 2j * 4euros = 10euros)
        assertTrue(obtenu.contains("<li>Age de glaçon : 10.0€</li>"));
        assertTrue(obtenu.contains("<b>10.0€</b>"));
        assertTrue(obtenu.contains("Points gagnés : <b>0</b>"));
    }
}