/*
 * Projet.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022, pas de copyright 
 */
package sae.pert;

import java.util.ArrayList;

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
    
    /* Listes des taches � effectuer dans pour ce projet */
    private ArrayList<Tache> taches;
    
    /* Unit� de temps utilis� pour parler des dur�es des taches */
    private String uniteTemps;
    
    /* Dates au plus t�t des diff�rentes taches du projet */
    private ArrayList<Double> datesAuPlusTot;
    
    /* Dates au plus tard des diff�rentes taches du projet */
    private ArrayList<Double> datesAuPlusTard;
    
    /* Date au plus t�t du projet */
    private double dateAuPlusTotFinDeProjet;
    
    /* Date au plus tard du projet */
    private double dateAuPlusTardFinDeProjet;    
    
    
    /** TODO commenter le r�le de ce champ (attribut, r�le associatif) */
    public final static String[] UNITE_TEMPS = { 
        "Minute(s)",
        "Heure(s)",
        "Jour(s)",
        "Semaine(s)",
        "Mois",
        "Ann�e(s)"
    };
    
    
    /**
     * D�finition d'un projet qui poss�de un nom 
     * et qui poss�de une unit� de temps
     * @param nom le nom du projet
     * @param uniteTemps l'unite de temps d�fini pour ce projet
     */
    public Projet(String nom, String uniteTemps) {
        this.nom = nom;
        this.uniteTemps = uniteTemps;
        this.taches = new ArrayList<Tache>();
        this.datesAuPlusTard = new ArrayList<Double>();
        this.datesAuPlusTot = new ArrayList<Double>();
        this.dateAuPlusTardFinDeProjet = 0.0;
        this.dateAuPlusTotFinDeProjet = 0.0;
    }

    /**
     * @return nom du projet
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom modifie le nom du projet
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return uniteTemps du projet 
     */
    public String getUniteTemps() {
        return uniteTemps;
    }

    /**
     * @param uniteTemps modifie uniteTemps du projet
     */
    public void setUniteTemps(String uniteTemps) {
        this.uniteTemps = uniteTemps;
    }
    
    /**
     * @return taches du projet
     */
    public ArrayList<Tache> getTaches() {
        return taches;
    }

    /**
     * @return datesAuPlusTot de chaque tache du projet
     */
    public ArrayList<Double> getDatesAuPlusTot() {
        return datesAuPlusTot;
    }

    /**
     * @return datesAuPlusTard de chaque tache du projet
     */
    public ArrayList<Double> getDatesAuPlusTard() {
        return datesAuPlusTard;
    }

    /**
     * @return dateAuPlusTotFinDeProjet
     */
    public double getDateAuPlusTotFinDeProjet() {
        return dateAuPlusTotFinDeProjet;
    }

    /**
     * @return dateAuPlusTardFinDeProjet
     */
    public double getDateAuPlusTardFinDeProjet() {
        return dateAuPlusTardFinDeProjet;
    }

    /** 
     * Ajoute une tache � ce projet
     * @param tacheAAjouter la tache � ajouter au projet
     */
    public void ajouterTache(Tache tacheAAjouter) {
        this.taches.add(tacheAAjouter);
    }
    
    /** 
     * Enl�ve une tache � ce projet
     * @param tacheAEnlever la tache � enlever au projet
     */
    public void enleverTache(Tache tacheAEnlever) {
        this.taches.remove(tacheAEnlever);
    }
    
    @Override
    public String toString() {
        String taches = "";
        for (int i = 0; i < this.taches.size(); i++) {
            taches += this.taches.get(i) + "\n";
        }
        return "Ce projet est nomm� : " + this.nom + "\n" 
               + "Son unit� de temps est : " + this.uniteTemps + "\n" 
               + "Ses t�ches sont : \n" + taches;
    }
    
}
