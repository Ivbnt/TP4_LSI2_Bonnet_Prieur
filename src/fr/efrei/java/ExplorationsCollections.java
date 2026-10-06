package fr.efrei.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classe pédagogique — Explorations des Collections Java.
 *
 * Quatre missions progressives pour illustrer les notions clés du TP3 :
 *   Mission 1 : List et généricité
 *   Mission 2 : Recherche dans une liste (complexité O(n))
 *   Mission 3 : Égalité logique avec equals() et hashCode()
 *   Mission 4 : Map — accès direct par clé en O(1)
 */
public class ExplorationsCollections {

    public static void main(String[] args) {
        mission1ListeEtGenericite();
        mission2RechercheDansUneListe();
        mission3EgaliteLogique();
        mission4Map();
    }

    // =========================================================================
    // Mission 1 — List et généricité
    // =========================================================================

    /**
     * Démontre l'utilisation de ArrayList<Collaborateur> :
     *   - La généricité garantit la cohérence du type à la COMPILATION.
     *   - Pas de cast explicite lors de la récupération d'un élément.
     *   - Parcours avec for-each (Iterable).
     */
    public static void mission1ListeEtGenericite() {
        System.out.println("=== Mission 1 : List et Généricité ===");

        Adresse adresse = new Adresse("12 rue des Lilas", "75000", "Paris", "France");

        // List<Collaborateur> : seuls des Collaborateur peuvent être ajoutés
        // Vérification à la COMPILATION, pas uniquement à l'exécution (contrairement aux tableaux bruts)
        List<Collaborateur> equipe = new ArrayList<>();
        equipe.add(new Programmeur("T001", "Alice", "Martin", 42000, adresse, "Java"));
        equipe.add(new Testeur(   "T002", "Bob",   "Dupont", 38000, adresse));
        equipe.add(new Programmeur("T003", "Clara", "Bernard", 45000, adresse, "Python"));

        // La ligne suivante NE COMPILE PAS — la généricité protège dès la compilation :
        // equipe.add("Erreur");

        System.out.println("Taille de la liste : " + equipe.size());

        // Parcours for-each : le type de chaque élément est garanti Collaborateur
        System.out.println("Membres de l'équipe :");
        for (Collaborateur c : equipe) {
            System.out.println("  - " + c);
        }

        // Accès par index — pas de cast nécessaire grâce à la généricité
        Collaborateur premier = equipe.get(0);
        System.out.println("Premier élément : " + premier);

        // Suppression par index
        equipe.remove(1); // supprime Bob (index 1)
        System.out.println("Après suppression de l'index 1 : " + equipe.size() + " élément(s)");

        // contains() utilise equals() — ici basé sur l'identifiant (voir Mission 3)
        Collaborateur alice2 = new Programmeur("T001", "Alice", "Martin", 42000, adresse, "Java");
        System.out.println("contains(alice avec même identifiant) : " + equipe.contains(alice2));

        System.out.println();
    }

    // =========================================================================
    // Mission 2 — Recherche dans une liste
    // =========================================================================

    /**
     * Démontre les limites d'une recherche linéaire dans une List :
     *   - Complexité O(n) : on parcourt potentiellement toute la liste.
     *   - Transition naturelle vers la Map (Mission 4) pour un accès en O(1).
     */
    public static void mission2RechercheDansUneListe() {
        System.out.println("=== Mission 2 : Recherche dans une liste ===");

        List<Collaborateur> collaborateurs = DonneesDemo.creerCollaborateurs();
        String cible = "C015";
        int comparaisons = 0;
        Collaborateur trouve = null;

        // Recherche linéaire — O(n) dans le pire cas
        for (Collaborateur c : collaborateurs) {
            comparaisons++;
            if (c.getIdentifiant().equals(cible)) {
                trouve = c;
                break;
            }
        }

        System.out.println("Liste de " + collaborateurs.size() + " collaborateurs.");
        System.out.println("Recherche de l'identifiant : " + cible);
        System.out.println("Comparaisons effectuées    : " + comparaisons);
        System.out.println("Résultat : " + (trouve != null ? trouve : "introuvable"));
        System.out.println();
        System.out.println("=> Avec 20 éléments, c'est supportable.");
        System.out.println("   Avec 100 000 collaborateurs, ce serait jusqu'à 100 000 comparaisons !");
        System.out.println("   La Mission 4 montre comment une Map résout ce problème en O(1).");
        System.out.println();
    }

    // =========================================================================
    // Mission 3 — Égalité logique : equals() et hashCode()
    // =========================================================================

    /**
     * Démontre pourquoi implémenter equals() et hashCode() est indispensable
     * pour que les collections (Set, Map, contains...) fonctionnent correctement.
     *
     * Règle d'or du contrat Java :
     *   Si a.equals(b) == true, alors a.hashCode() == b.hashCode() OBLIGATOIREMENT.
     *   (L'inverse n'est pas vrai : deux objets peuvent avoir le même hashCode sans être equals.)
     */
    public static void mission3EgaliteLogique() {
        System.out.println("=== Mission 3 : Égalité logique — equals() et hashCode() ===");

        Adresse adresse = new Adresse("12 rue des Lilas", "75000", "Paris", "France");

        // Deux instances distinctes représentant le même collaborateur (même identifiant)
        Programmeur p1 = new Programmeur("C001", "Alice", "Martin", 42000, adresse, "Java");
        Programmeur p2 = new Programmeur("C001", "Alice", "Martin", 42000, adresse, "Java");

        System.out.println("--- Comparaison de deux objets avec le même identifiant C001 ---");
        System.out.println("p1 == p2 (identité référentielle) : " + (p1 == p2));
        // false : p1 et p2 sont des objets différents en mémoire

        System.out.println("p1.equals(p2)  (égalité logique)  : " + p1.equals(p2));
        // true : notre equals() compare les identifiants

        System.out.println("p1.hashCode() : " + p1.hashCode());
        System.out.println("p2.hashCode() : " + p2.hashCode());
        System.out.println("=> Même hashCode grâce au contrat equals/hashCode (basé sur l'identifiant).");
        System.out.println();

        // --- Comportement dans un HashSet ---
        System.out.println("--- Ajout de p1 et p2 dans un HashSet ---");
        Set<Collaborateur> ensemble = new HashSet<>();
        boolean ajout1 = ensemble.add(p1);
        boolean ajout2 = ensemble.add(p2); // même identifiant -> doublon logique -> rejeté

        System.out.println("Ajout de p1 : " + ajout1);  // true
        System.out.println("Ajout de p2 : " + ajout2);  // false — refusé car equals() retourne true
        System.out.println("Taille du HashSet : " + ensemble.size() + " (attendu : 1)");
        System.out.println();

        // --- contains() ---
        System.out.println("--- contains() avec un troisième objet (même identifiant) ---");
        Programmeur p3 = new Programmeur("C001", "Alice", "Martin", 42000, adresse, "Java");
        System.out.println("ensemble.contains(p3) : " + ensemble.contains(p3));
        // true : hashCode identique -> même bucket, equals() retourne true
        System.out.println();

        // --- Rappel du contrat ---
        System.out.println("Rappel du contrat Java :");
        System.out.println("  Si a.equals(b) == true  =>  a.hashCode() == b.hashCode()  [OBLIGATOIRE]");
        System.out.println("  Violer ce contrat brise HashSet, HashMap et toute collection basée sur le hachage.");
        System.out.println();
    }

    // =========================================================================
    // Mission 4 — Map : accès direct par clé
    // =========================================================================

    /**
     * Démontre les bases de HashMap et LinkedHashMap :
     *   - put() écrase silencieusement si la clé existe déjà.
     *   - containsKey() pour protéger avant un put().
     *   - putIfAbsent() : insère seulement si la clé est absente.
     *   - get() en O(1) — pas de boucle.
     *   - Différence HashMap (ordre aléatoire) vs LinkedHashMap (ordre d'insertion).
     */
    public static void mission4Map() {
        System.out.println("=== Mission 4 : Map — accès direct par clé ===");

        Adresse paris = new Adresse("12 rue des Lilas", "75000", "Paris", "France");

        // LinkedHashMap : accès O(1) ET ordre d'insertion préservé
        Map<String, Collaborateur> annuaireSimple = new LinkedHashMap<>();

        Collaborateur alice  = new Programmeur("C001", "Alice", "Martin",  42000, paris, "Java");
        Collaborateur bob    = new Testeur(    "C002", "Bob",   "Dupont",  38000, paris);
        Collaborateur alice2 = new Programmeur("C001", "Alice", "Martin",  99000, paris, "Kotlin"); // même clé !

        annuaireSimple.put("C001", alice);
        annuaireSimple.put("C002", bob);

        System.out.println("--- put() écrase silencieusement si la clé existe déjà ---");
        System.out.println("Avant : " + annuaireSimple.get("C001"));
        annuaireSimple.put("C001", alice2); // alice est remplacée par alice2 sans avertissement !
        System.out.println("Après put(C001, alice2) : " + annuaireSimple.get("C001"));
        System.out.println();

        // Remettre alice pour la suite
        annuaireSimple.put("C001", alice);

        System.out.println("--- containsKey() pour protéger avant un put() ---");
        if (!annuaireSimple.containsKey("C001")) {
            annuaireSimple.put("C001", alice);
        } else {
            System.out.println("C001 existe déjà -> put() ignoré.");
        }
        System.out.println();

        System.out.println("--- putIfAbsent() : insère seulement si la clé est absente ---");
        annuaireSimple.putIfAbsent("C001", alice2);  // ignoré : C001 déjà présent
        annuaireSimple.putIfAbsent("C003", new Testeur("C003", "Clara", "Bernard", 45000, paris));
        System.out.println("C001 (inchangé) : " + annuaireSimple.get("C001"));
        System.out.println("C003 (ajouté)   : " + annuaireSimple.get("C003"));
        System.out.println();

        System.out.println("--- get() en O(1) : recherche sans boucle ---");
        Collaborateur c999 = annuaireSimple.get("C999"); // inexistant -> null
        System.out.println("Recherche de C999 : " + (c999 != null ? c999 : "introuvable (O(1) !)"));
        System.out.println();

        System.out.println("--- Parcours des entrées (entrySet) ---");
        for (Map.Entry<String, Collaborateur> entree : annuaireSimple.entrySet()) {
            System.out.println("  " + entree.getKey() + " -> " + entree.getValue());
        }
        System.out.println();

        System.out.println("--- HashMap vs LinkedHashMap ---");
        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> linkedMap = new LinkedHashMap<>();
        String[] cles = { "B", "A", "C", "Z", "M" };
        for (String cle : cles) {
            hashMap.put(cle, cle);
            linkedMap.put(cle, cle);
        }
        System.out.println("Insertion dans l'ordre : B, A, C, Z, M");
        System.out.println("HashMap       (ordre indéterminé)   : " + hashMap.keySet());
        System.out.println("LinkedHashMap (ordre d'insertion)   : " + linkedMap.keySet());
        System.out.println("=> L'Annuaire utilise LinkedHashMap pour préserver l'ordre de saisie.");
        System.out.println();
    }
}
