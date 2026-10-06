package fr.efrei.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Fournit un jeu de données de démonstration : 20 collaborateurs (C001–C020).
 * Classe utilitaire — constructeur privé, uniquement des méthodes statiques.
 */
public class DonneesDemo {

    private DonneesDemo() { }

    /**
     * Crée et retourne une liste de 20 collaborateurs répartis sur 3 sites.
     * Mix de Programmeur et Testeur, avec des langages variés.
     */
    public static List<Collaborateur> creerCollaborateurs() {

        Adresse paris     = new Adresse("12 rue des Lilas",          "75000", "Paris",     "France");
        Adresse villejuif = new Adresse("3 avenue de la République", "94800", "Villejuif", "France");
        Adresse lyon      = new Adresse("7 place Bellecour",         "69002", "Lyon",      "France");

        List<Collaborateur> liste = new ArrayList<>();

        liste.add(new Programmeur("C001", "Alice",    "Martin",    42000, paris,     "Java"));
        liste.add(new Testeur(   "C002", "Bob",       "Dupont",    38000, paris));
        liste.add(new Programmeur("C003", "Clara",    "Bernard",   45000, villejuif, "Python"));
        liste.add(new Testeur(   "C004", "David",     "Moreau",    39000, villejuif));
        liste.add(new Programmeur("C005", "Emma",     "Simon",     47000, lyon,      "JavaScript"));
        liste.add(new Testeur(   "C006", "François",  "Laurent",   36000, paris));
        liste.add(new Programmeur("C007", "Grace",    "Thomas",    50000, paris,     "Java"));
        liste.add(new Testeur(   "C008", "Hugo",      "Petit",     40000, lyon));
        liste.add(new Programmeur("C009", "Iris",     "Robert",    43000, villejuif, "Python"));
        liste.add(new Testeur(   "C010", "Julien",    "Richard",   37000, paris));
        liste.add(new Programmeur("C011", "Karim",    "Durand",    48000, paris,     "TypeScript"));
        liste.add(new Testeur(   "C012", "Laura",     "Leroy",     41000, villejuif));
        liste.add(new Programmeur("C013", "Marc",     "Moreau",    46000, lyon,      "Java"));
        liste.add(new Testeur(   "C014", "Nina",      "Simon",     38500, paris));
        liste.add(new Programmeur("C015", "Oscar",    "Michel",    44000, paris,     "Kotlin"));
        liste.add(new Testeur(   "C016", "Pauline",   "Garcia",    39500, lyon));
        liste.add(new Programmeur("C017", "Quentin",  "Martinez",  52000, villejuif, "Java"));
        liste.add(new Testeur(   "C018", "Rachel",    "Dubois",    40000, paris));
        liste.add(new Programmeur("C019", "Samuel",   "Lopez",     49000, paris,     "Scala"));
        liste.add(new Testeur(   "C020", "Théo",      "Gonzalez",  37500, lyon));

        return liste;
    }
}
