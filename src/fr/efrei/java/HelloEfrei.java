package fr.efrei.java;

import java.util.List;
import java.util.Scanner;

/**
 * Point d'entrée de l'application — Annuaire des collaborateurs.
 *
 * Reprend la structure du HelloEfrei du TP1 :
 *   - méthode afficherMenu() statique
 *   - méthodes utilitaires statiques (afficherListe, saisirIdentifiant...)
 *   - boucle principale avec validation hasNextInt() + switch fléché
 */
public class HelloEfrei {

    public static void main(String[] args) {

        // --- Initialisation de l'annuaire avec les données de démonstration ---
        Annuaire annuaire = new Annuaire();
        for (Collaborateur c : DonneesDemo.creerCollaborateurs()) {
            annuaire.ajouter(c);
        }

        Scanner scanner = new Scanner(System.in);
        int choix = -1;

        while (choix != 0) {
            afficherMenu();

            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine(); // consommer le saut de ligne résiduel

                switch (choix) {
                    case 0 -> System.out.println("À bientôt !");

                    case 1 -> afficherListe(annuaire.tous());

                    case 2 -> {
                        System.out.print("Fragment de nom : ");
                        String fragment = scanner.nextLine();
                        afficherListe(annuaire.nomContenant(fragment));
                    }

                    case 3 -> {
                        System.out.print("Salaire minimum (€) : ");
                        if (scanner.hasNextDouble()) {
                            double seuil = scanner.nextDouble();
                            scanner.nextLine();
                            afficherListe(annuaire.salaireSuperieurA(seuil));
                        } else {
                            System.out.println("Saisie invalide.");
                            scanner.nextLine();
                        }
                    }

                    case 4 -> afficherListe(annuaire.programmeurs());

                    case 5 -> afficherListe(annuaire.triesParNom());

                    case 6 -> afficherListe(annuaire.triesParSalaire());

                    case 7 -> afficherListe(annuaire.triesParNomPuisPrenom());

                    case 8 -> {
                        System.out.print("Identifiant du collaborateur : ");
                        String id = scanner.nextLine();
                        Collaborateur trouve = annuaire.trouver(id);
                        if (trouve != null) {
                            System.out.println();
                            trouve.afficherFiche();
                        } else {
                            System.out.println("Collaborateur introuvable : " + id);
                        }
                    }

                    case 9 -> {
                        System.out.print("Identifiant du collaborateur : ");
                        String id = scanner.nextLine();
                        Collaborateur trouve = annuaire.trouver(id);
                        if (trouve != null) {
                            System.out.print("Pourcentage d'augmentation : ");
                            if (scanner.hasNextDouble()) {
                                double pourcentage = scanner.nextDouble();
                                scanner.nextLine();
                                double ancienSalaire = trouve.getSalaire();
                                trouve.augmenterSalaire(pourcentage);
                                System.out.printf("Salaire : %.2f € -> %.2f €%n",
                                        ancienSalaire, trouve.getSalaire());
                            } else {
                                System.out.println("Saisie invalide.");
                                scanner.nextLine();
                            }
                        } else {
                            System.out.println("Collaborateur introuvable : " + id);
                        }
                    }

                    default -> System.out.println("Option invalide. Choisissez entre 0 et 9.");
                }

            } else {
                System.out.println("Saisie invalide. Veuillez entrer un nombre.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    // --- Méthodes statiques utilitaires ---

    private static void afficherMenu() {
        System.out.println();
        System.out.println("=== Annuaire des collaborateurs (" + "EFREI" + ") ===");
        System.out.println("1. Afficher tous les collaborateurs");
        System.out.println("2. Rechercher par nom");
        System.out.println("3. Filtrer par salaire minimum");
        System.out.println("4. Afficher les programmeurs");
        System.out.println("5. Trier par nom");
        System.out.println("6. Trier par salaire");
        System.out.println("7. Trier par nom puis prénom");
        System.out.println("8. Afficher la fiche d'un collaborateur");
        System.out.println("9. Augmenter le salaire d'un collaborateur");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    /**
     * Affiche une liste de collaborateurs.
     * Utilise List<? extends Collaborateur> pour accepter aussi bien
     * List<Collaborateur> que List<Programmeur> (covariance via wildcard borné).
     */
    private static void afficherListe(List<? extends Collaborateur> liste) {
        if (liste.isEmpty()) {
            System.out.println("Aucun collaborateur trouvé.");
        } else {
            System.out.println(liste.size() + " collaborateur(s) :");
            for (Collaborateur c : liste) {
                System.out.println("  " + c);
            }
        }
    }
}
