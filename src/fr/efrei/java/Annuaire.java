package fr.efrei.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Annuaire des collaborateurs de l'entreprise.
 *
 * Stockage interne : LinkedHashMap<String, Collaborateur>
 *   - La clé est l'identifiant du collaborateur (ex. "C001").
 *   - LinkedHashMap préserve l'ordre d'insertion (utile pour l'affichage).
 *   - Accès en O(1) par identifiant (vs. O(n) avec une simple liste).
 *
 * Point clé TP3 : equals()/hashCode() de Collaborateur (basé sur l'identifiant)
 * est essentiel pour que containsKey() et remove() fonctionnent correctement.
 */
public class Annuaire {

    private Map<String, Collaborateur> collaborateurs = new LinkedHashMap<>();

    /**
     * Ajoute un collaborateur à l'annuaire.
     *
     * @return true si l'ajout a réussi, false si l'identifiant est déjà utilisé.
     */
    public boolean ajouter(Collaborateur collaborateur) {
        if (collaborateurs.containsKey(collaborateur.getIdentifiant())) {
            return false;
        }
        collaborateurs.put(collaborateur.getIdentifiant(), collaborateur);
        return true;
    }

    /**
     * Recherche un collaborateur par son identifiant.
     *
     * @return le collaborateur trouvé, ou null s'il est absent.
     */
    public Collaborateur trouver(String identifiant) {
        return collaborateurs.get(identifiant);
    }

    /**
     * Supprime un collaborateur par son identifiant.
     *
     * @return true si la suppression a réussi, false si l'identifiant était absent.
     */
    public boolean supprimer(String identifiant) {
        return collaborateurs.remove(identifiant) != null;
    }

    /** Retourne le nombre de collaborateurs dans l'annuaire. */
    public int taille() {
        return collaborateurs.size();
    }

    /** Retourne une copie de la liste de tous les collaborateurs (dans l'ordre d'insertion). */
    public List<Collaborateur> tous() {
        return new ArrayList<>(collaborateurs.values());
    }

    // --- Filtres ---

    /** Retourne la liste des programmeurs uniquement. */
    public List<Programmeur> programmeurs() {
        List<Programmeur> liste = new ArrayList<>();
        for (Collaborateur c : collaborateurs.values()) {
            // instanceof avec pattern variable (Java 16+)
            if (c instanceof Programmeur programmeur) {
                liste.add(programmeur);
            }
        }
        return liste;
    }

    /** Retourne les collaborateurs dont le nom contient le fragment donné (insensible à la casse). */
    public List<Collaborateur> nomContenant(String fragment) {
        List<Collaborateur> liste = new ArrayList<>();
        for (Collaborateur c : collaborateurs.values()) {
            if (c.getNom().toLowerCase().contains(fragment.toLowerCase())) {
                liste.add(c);
            }
        }
        return liste;
    }

    /** Retourne les collaborateurs dont le salaire est strictement supérieur au seuil. */
    public List<Collaborateur> salaireSuperieurA(double seuil) {
        List<Collaborateur> liste = new ArrayList<>();
        for (Collaborateur c : collaborateurs.values()) {
            if (c.getSalaire() > seuil) {
                liste.add(c);
            }
        }
        return liste;
    }

    // --- Tris ---

    /** Retourne une liste triée par nom (ordre alphabétique). */
    public List<Collaborateur> triesParNom() {
        return triesSelon(Comparator.comparing(Collaborateur::getNom));
    }

    /** Retourne une liste triée par salaire croissant. */
    public List<Collaborateur> triesParSalaire() {
        return triesSelon(Comparator.comparingDouble(Collaborateur::getSalaire));
    }

    /** Retourne une liste triée par nom, puis par prénom en cas d'ex-aequo. */
    public List<Collaborateur> triesParNomPuisPrenom() {
        return triesSelon(
            Comparator.comparing(Collaborateur::getNom)
                      .thenComparing(Collaborateur::getPrenom)
        );
    }

    /**
     * Méthode privée mutualisée : crée une copie de la liste, la trie selon
     * le comparateur donné, et la retourne non modifiable.
     */
    private List<Collaborateur> triesSelon(Comparator<Collaborateur> comparateur) {
        List<Collaborateur> liste = tous();
        liste.sort(comparateur);
        return Collections.unmodifiableList(liste);
    }
}
