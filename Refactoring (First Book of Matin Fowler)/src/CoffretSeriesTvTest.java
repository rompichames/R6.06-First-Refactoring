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
        String obtenu = client.situationHTML(); //

        // Vérification (5j * 0.5 = 2.5euros), points (0)
        assertTrue(obtenu.contains("<td>Soda</td><td>2.5 €</td>")); //
        assertTrue(obtenu.contains("Total dû : <strong>2.5 €</strong>")); //
        assertTrue(obtenu.contains("<strong>0</strong> points de fidélité")); //
    }
}