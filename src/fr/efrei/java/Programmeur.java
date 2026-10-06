package fr.efrei.java;

/**
 * Programmeur : collaborateur spécialisé dans le développement logiciel.
 * Implémente Formateur car un programmeur peut animer des formations internes.
 *
 * Reprise du TP2 avec ajout de l'identifiant (délégué à Collaborateur).
 */
public class Programmeur extends Collaborateur implements Formateur {

    private String langagePrefere;

    public Programmeur(
            String identifiant,
            String prenom,
            String nom,
            double salaire,
            Adresse adresse,
            String langagePrefere) {
        super(identifiant, prenom, nom, salaire, adresse);
        this.langagePrefere = langagePrefere;
    }

    public String getLangagePrefere() { return langagePrefere; }

    @Override
    public String getMetier() {
        return "Programmeur (" + langagePrefere + ")";
    }

    @Override
    public void travailler() {
        System.out.println(getPrenom() + " développe une fonctionnalité en " + langagePrefere + ".");
    }

    @Override
    public void former() {
        System.out.println(getPrenom() + " anime une formation interne.");
    }
}
