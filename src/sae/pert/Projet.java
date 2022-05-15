/*
 * Projet.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022, pas de copyright 
 */
package sae.pert;

import java.util.ArrayList;
import java.util.Scanner;

/** 
 * Un projet est un ensemble de taches que l'on doit ordonn� pour arriver �
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
    
    /* Listes des taches � effectuer dans pour ce projet */
    private ArrayList<Tache> taches;
    
    /* Unit� de temps utilis� pour parler des dur�es des taches */
    private String uniteTemps;
    
    /* Date au plus t�t du projet */
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
        "Ann�e(s)"
    };
    
    /** TODO commenter le r�le de cette m�thode (SRP)
     * @param args
     */
    public static void main(String[] args) {
        //TODO faire un menu permettant de creer un projet et de l'afficher
    }
    
    /**
     * D�finition d'un projet qui poss�de un nom 
     * et qui poss�de une unit� de temps
     * @param nom le nom du projet
     * @param description description du projet
     * @param uniteTemps l'unite de temps d�fini pour ce projet
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
            throw new IllegalArgumentException("L'unit� de temps est invalide");
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
     * Ajoute une tache � ce projet
     * @param tacheAAjouter la tache � ajouter au projet
     * @throws IllegalArgumentException si la tache est d�ja dans le projet
     */
    public void ajouterTache(Tache tacheAAjouter) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.taches.size(); i++) {
            estPresent = tacheAAjouter.equals(this.taches.get(i));
        }
        if (estPresent) {
            throw new IllegalArgumentException("Cette tache est d�ja pr�sente");
        }
        this.taches.add(tacheAAjouter);
    }
    
    /** 
     * Enl�ve une tache � ce projet
     * @param tacheAEnlever la tache � enlever au projet
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
     * Permet en interrogeant l'utilisateur de cr�er un projet
     * @return projet Projet que l'on cr�er
     */
    public static Projet creer() {
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
            System.out.print("Veuillez d�crire votre projet : ");
            description = entree.nextLine();
            System.out.println("Veuillez saisir un chiffre pour choisir une unit�"
                             + " de temps pour votre projet : ");
            System.out.println("1. Minute(s)");
            System.out.println("2. Heure(s)");
            System.out.println("3. Jours(s)");
            System.out.println("4. Semaine(s)");
            System.out.println("5. Mois");
            System.out.println("6. Ann�e(s)");
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
        //TODO Modifier cette m�thode pour date plus tot et aucune tache
        String taches = this.taches.size() == 0 ? "Ce projet ne contient pas "
                        + "encore de tache" : "Ses t�ches sont : \n";
        for (int i = 0; i < this.taches.size(); i++) {
            taches += this.taches.get(i) + "\n";
        }
        return "Ce projet est nomm� : " + this.nom + "\n" 
               + "Sa description est : " + this.description + "\n"
               + "Son unit� de temps est : " + this.uniteTemps + "\n" 
               + taches
               + "Sa date au plus tot est : " + this.dateAuPlusTotProjet
               + "Sa date au plus tard est : " + this.dateAuPlusTardProjet;
    }
    
}
