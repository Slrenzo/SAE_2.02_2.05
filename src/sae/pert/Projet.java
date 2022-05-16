/*
 * Projet.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022, pas de copyright 
 */
package sae.pert;

import java.util.ArrayList;
import java.util.Scanner;

/** 
 * Un projet est un ensemble de taches que l'on doit ordonné pour arriver à
 * un objectif.
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class Projet {

    /* Nom du projet */
    private String nom;
    
    /* Description du projet */
    private String description;
    
    /* Listes des taches à effectuer dans pour ce projet */
    private ArrayList<Tache> taches;
    
    /* Unité de temps utilisé pour parler des durées des taches */
    private String uniteTemps;
    
    /* Date au plus tôt du projet */
    private double dateAuPlusTotProjet;
    
    /* Date au plus tard du projet */
    private double dateAuPlusTardProjet;    
    
    
    /**
     * Tableau d'unite de temps possible 
     */
    public final static String[] UNITE_TEMPS = { 
        "Minute(s)",
        "Heure(s)",
        "Jour(s)",
        "Semaine(s)",
        "Mois",
        "Année(s)"
    };
    
    /** 
     * Un menu pour créer et gérer des projets et qui permet de calculer
     * les dates au plus tot et date au plus tard de chaque tache
     * ainsi que la date au plus tot et la date au plus tard de chaque projet
     * @param args non utlisée
     */
    public static void main(String[] args) {
        //TODO faire un menu permettant de creer un projet et de l'afficher
        Scanner entree = new Scanner(System.in);
        int choix = 0;
        Projet projet = null;
        while (choix != -1) {
            if (projet == null) {
                System.out.println("\n---------- Nom du logiciel ----------\n");
                System.out.println("Menu principale : \n");
                System.out.println("1 - Créer un projet\n"
                                   + "2 - Importer un projet\n"
                                   + "3 - Quitter le logiciel\n");
                System.out.print("Entrez le chiffre de votre choix : ");
                if (entree.hasNextInt()) {
                    choix = entree.nextInt();
                } else {
                    choix = 0;
                }
                entree.nextLine();
                switch (choix) {
                case 1: 
                    projet = creerProjet();
                    break;
                case 2:
                    //TODO coder la methode charger()
                    break;
                case 3:
                    System.out.println("\nBye");
                    choix = -1;
                    break;
                default:
                    System.out.println("\nVeuillez choisir un nombre entre "
                                       + "1 et 3");
                    choix = 0;
                    break;
                }
            } else {
                System.out.println("\n---------- " + projet.getNom() 
                                   + " ----------\n");
                System.out.println("1 - Afficher le projet\n"
                                   + "2 - Ajouter une tache\n"
                                   + "3 - Enlever une tache\n"
                                   + "4 - Configurer les taches préalables\n"
                                   + "5 - Sauvegarder et rerourner au menu\n");
                System.out.print("Entrez le chiffre de votre choix : ");
                if (entree.hasNextInt()) {
                    choix = entree.nextInt();
                } else {
                    choix = 0;
                }
                entree.nextLine();
                switch (choix) {
                case 1: 
                    System.out.println();
                    System.out.println(projet.toString());
                    System.out.println();
                    break;
                case 2:
                    projet.ajouterTache(creerTache());
                    break;
                case 3:
                    //TODO enlever une tache
                    break;
                case 4:
                    //TODO Configurer les taches préalables
                    break;
                case 5:
                    //TODO sauvegarder
                    projet = null;
                    break;
                default:
                    System.out.println("\nVeuillez choisir un nombre entre "
                                       + "1 et 5");
                    choix = 0;
                    break;
                }
            }
        }
    }
    
    /**
     * Définition d'un projet qui possède un nom 
     * et qui possède une unité de temps
     * @param nom le nom du projet
     * @param description description du projet
     * @param uniteTemps l'unite de temps défini pour ce projet
     * @throws IllegalArgumentException si le nom est vide
     * @throws IllegalArgumentException si la description est vide
     * @throws IllegalArgumentException si l'unite de temps est invalide
     */
    public Projet(String nom, String description, String uniteTemps) {
        if (nom.isBlank()) {
            throw new IllegalArgumentException("Le nom est vide");
        }
        if (description.isBlank()) {
            throw new IllegalArgumentException("Le description est vide");
        }
        boolean uniteTempsOk = false;
        for (int i = 0; !uniteTempsOk && i < UNITE_TEMPS.length; i++) {
            uniteTempsOk = uniteTemps == UNITE_TEMPS[i];
        }
        if (!uniteTempsOk) {
            throw new IllegalArgumentException("L'unité de temps est invalide");
        }
        this.nom = nom;
        this.description = description;
        this.uniteTemps = uniteTemps;
        this.taches = new ArrayList<Tache>();
        this.dateAuPlusTotProjet = 0.0;
        this.dateAuPlusTardProjet = 0.0;
    }

    /**
     * @return nom du projet
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return description de ce projet
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description modifie la description de ce projet
     * @throws IllegalArgumentException si la description est vide
     */
    public void setDescription(String description) {
        if (description.isBlank()) {
            throw new IllegalArgumentException("Le description est vide");
        }
        this.description = description;
    }

    /**
     * @return uniteTemps du projet 
     */
    public String getUniteTemps() {
        return uniteTemps;
    }

    /**
     * @return dateAuPlusTotProjet de ce projet
     */
    public double getDateAuPlusTotProjet() {
        return dateAuPlusTotProjet;
    }

    /**
     * @return dateAuPlusTardProjet de ce projet
     */
    public double getDateAuPlusTardProjet() {
        return dateAuPlusTardProjet;
    }

    /** 
     * Permet de créer une tache en interrogeant l'utilisateur
     * @return tache que l'on créer
     */
    public static Tache creerTache() {
        Scanner entree = new Scanner(System.in);
        String nom;
        String description;
        double duree;
        Tache tache = null;
        boolean saisieOk= false;
        while (!saisieOk) {
            System.out.print("Veuillez entrer le nom de la tache : ");
            nom = entree.nextLine();
            System.out.print("Veuillez entrer la description de la tache : ");
            description = entree.nextLine();
            System.out.print("Veuillez entrer la durée de la tache : ");
            if (entree.hasNextDouble()) {
                duree = entree.nextDouble();
            } else {
                duree = -1.0;
            }
            entree.nextLine();
            try {
                tache = new Tache(nom, description, duree);
                saisieOk = true;
            } catch (IllegalArgumentException erreurConstructeur) {
                System.out.println(erreurConstructeur.getMessage());
                System.out.println("Veuillez recommencer");
            }
        }
        return tache;
    }
    
    /** 
     * Ajoute une tache à ce projet
     * @param tacheAAjouter la tache à ajouter au projet
     * @throws IllegalArgumentException si la tache est déja dans le projet
     */
    public void ajouterTache(Tache tacheAAjouter) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.taches.size(); i++) {
            estPresent = tacheAAjouter.equals(this.taches.get(i));
        }
        if (estPresent) {
            throw new IllegalArgumentException("Cette tache est déja présente");
        }
        this.taches.add(tacheAAjouter);
    }
    
    /** 
     * Enlève une tache à ce projet
     * @param tacheAEnlever la tache à enlever au projet
     * @throws IllegalArgumentException si la tache n'est pas dans le projet
     */
    public void enleverTache(Tache tacheAEnlever) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.taches.size(); i++) {
            estPresent = tacheAEnlever.equals(this.taches.get(i));
        }
        if (!estPresent) {
            throw new IllegalArgumentException("Cette tache n'est pas dans "
                                               + "le projet");
        }
        this.taches.remove(tacheAEnlever);
    }
    
    /** 
     * Permet en interrogeant l'utilisateur de créer un projet
     * @return projet Projet que l'on créer
     */
    public static Projet creerProjet() {
        Scanner entree = new Scanner(System.in);
        String nom;
        String description;
        String uniteTemps;
        int uniteTempsChoisie;
        boolean uniteTempsChoisieFaux;
        boolean saisieOk = false;
        Projet projet = null;
        while (!saisieOk) {
            System.out.print("Veuillez entrer le nom de votre projet : ");
            nom = entree.nextLine();
            System.out.print("Veuillez décrire votre projet : ");
            description = entree.nextLine();
            System.out.println("Veuillez saisir un chiffre pour choisir une unité"
                             + " de temps pour votre projet : ");
            System.out.println("1. Minute(s)");
            System.out.println("2. Heure(s)");
            System.out.println("3. Jours(s)");
            System.out.println("4. Semaine(s)");
            System.out.println("5. Mois");
            System.out.println("6. Année(s)");
            System.out.print("Votre choix : ");
            uniteTempsChoisie = 0;
            uniteTempsChoisieFaux = true;
            while (uniteTempsChoisieFaux) {
                if (entree.hasNextInt()) {
                    uniteTempsChoisie = entree.nextInt();
                    uniteTempsChoisieFaux = uniteTempsChoisie <= 0 
                                            || uniteTempsChoisie >= 7;
                    if (uniteTempsChoisieFaux) {
                        System.out.println("Veuillez entrer un nombre "
                                           + "entre un 1 et 6 : ");                        
                    }
                } else {
                    System.out.println("Veuillez entrer un nombre "
                                       + "entre un 1 et 6 : ");
                }
                entree.nextLine();
            }
            uniteTemps = UNITE_TEMPS[uniteTempsChoisie - 1];
            try {
                projet = new Projet(nom, description, uniteTemps);
                saisieOk = true;
            } catch (IllegalArgumentException erreurConstructeur) {
                System.out.println(erreurConstructeur.getMessage());
                System.out.println("Veuillez recommencer");
            }
        }
        return projet;
    }
    
    @Override
    public String toString() {
        String taches = this.taches.size() == 0 ? "Ce projet ne contient pas "
                        + "encore de tache" : "Ses tâches sont : \n";
        for (int i = 0; i < this.taches.size(); i++) {
            taches += this.taches.get(i) + "\n";
        }
        return "Ce projet est nommé : " + this.nom + "\n" 
               + "Sa description est : " + this.description + "\n"
               + "Son unité de temps est : " + this.uniteTemps + "\n" 
               + taches + "\n"
               + "Sa date au plus tot est : " + this.dateAuPlusTotProjet + "\n" 
               + "Sa date au plus tard est : " + this.dateAuPlusTardProjet;
    }
    
}
