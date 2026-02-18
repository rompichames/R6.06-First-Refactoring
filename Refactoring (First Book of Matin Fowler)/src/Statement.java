import java.util.List;

public abstract class Statement {
    // La "Template Method"
    public String value(Client aClient) {
        String result = headerString(aClient);
        for (Location each : aClient.getLocations()) {
            result += eachLocationString(each);
        }
        result += footerString(aClient);
        return result;
    }

    protected abstract String headerString(Client aClient);
    protected abstract String eachLocationString(Location aLocation);
    protected abstract String footerString(Client aClient);

    public static class TextStatement extends Statement {
        protected String headerString(Client aClient) {
            return "Situation du client: " + aClient.getNom() + "\n";
        }

        protected String eachLocationString(Location aLocation) {
            return "\t" + aLocation.getFilm().getTitre() + "\t" + aLocation.getMontant() + "\n";
        }

        protected String footerString(Client aClient) {
            return "Total du " + aClient.getTotalDu() + "\n" +
                    "Vous gagnez " + aClient.getPointsFideliteTotal() + " points de fidelite\n";
        }
    }

    public static class HtmlStatement extends Statement {
        protected String headerString(Client aClient) {
            return "<html>\n<body>\n<h1>Situation du client : <em>" + aClient.getNom() + "</em></h1>\n" +
                    "<table border=\"1\">\n  <tr><th>Film</th><th>Prix</th></tr>\n";
        }

        protected String eachLocationString(Location aLocation) {
            return "  <tr><td>" + aLocation.getFilm().getTitre() + "</td><td>" + aLocation.getMontant() + " €</td></tr>\n";
        }

        protected String footerString(Client aClient) {
            return "</table>\n<p>Total dû : <strong>" + aClient.getTotalDu() + " €</strong></p>\n" +
                    "<p>Vous gagnez <strong>" + aClient.getPointsFideliteTotal() + "</strong> points de fidélité</p>\n" +
                    "</body>\n</html>";
        }
    }
}