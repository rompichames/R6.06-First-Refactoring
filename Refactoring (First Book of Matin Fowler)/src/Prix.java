public abstract class Prix {
    public abstract int getCodePrix();
    public abstract double getMontant(int nbJours);
    public int getPointsFidelite(int nbJours) {
        return 1;
    }

    public static class PrixNormal extends Prix {
        @Override
        public int getCodePrix() { return Film.NORMAL; }

        @Override
        public double getMontant(int nbJours) {
            double du = 2;
            if (nbJours > 2) du += (nbJours - 2) * 1.5;
            return du;
        }
    }

    public static class PrixNouveaute extends Prix {
        @Override
        public int getCodePrix() { return Film.NOUVEAUTE; }

        @Override
        public double getMontant(int nbJours) { return nbJours * 3; }

        @Override
        public int getPointsFidelite(int nbJours) {
            return (nbJours > 1) ? 2 : 1;
        }
    }

    public static class PrixEnfant extends Prix {
        @Override
        public int getCodePrix() { return Film.ENFANT; }

        @Override
        public double getMontant(int nbJours) {
            double du = 1.5;
            if (nbJours > 3) du += (nbJours - 3) * 1.5;
            return du;
        }
    }

    public static class PrixCinephile extends Prix {
        @Override
        public int getCodePrix() { return Film.CINEPHILE; }

        @Override
        public double getMontant(int nbJours) {
            // Logique spécifique extraite du switch de Client/Location
            double du = 2.0;
            if (nbJours > 1) {
                du += (nbJours - 1) * 4.0;
            }
            return du;
        }

        @Override
        public int getPointsFidelite(int nbJours) {
            return (nbJours == 1) ? 3 : 0;
        }
    }

    public static class PrixCoffretSeries extends Prix {
        @Override
        public int getCodePrix() { return Film.COFFRET_SERIES_TV; }

        @Override
        public double getMontant(int nbJours) {
            return nbJours * 0.5;
        }

        @Override
        public int getPointsFidelite(int nbJours) {
            return 0;
        }
    }
}