import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffretSeriesTvTest {
    private Client client;

    @BeforeEach
    public void setup() {
        client = new Client("John Doe");
    }

    @Test
    public void testCoffretSeriesTV() {
        client.addLocation(new Location(new Film("Soda",3),5));
        // Soda : prix 2.5 euros
        // Points : 0
        String expected = "Situation du client: John Doe\n"
                + "\tSoda\t2.5\n"
                + "Total du 2.5\n"
                + "Vous gagnez 0 points de fidelite\n";
        assertEquals(expected, client.situation());
    }

    @Test
    public void testCoffretSeriesTvHtml() {
        client.addLocation(new Location(new Film("Soda", 3), 5));
        String obtenu = client.situationHTML();

        assertTrue(obtenu.contains("<li>Soda : 2.5€</li>"));
        assertTrue(obtenu.contains("Total dû : <b>2.5€</b>"));
        assertTrue(obtenu.contains("Points gagnés : <b>0</b>"));
    }
}