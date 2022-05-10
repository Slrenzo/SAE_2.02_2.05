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
    
    /* Matrice d'adjacence du graphe d'ordonnancement */
    private ArrayList<ArrayList<Double>> ordonnancement;
    
    
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
        this.ordonnancement = new ArrayList<ArrayList<Double>>();
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
        String ordonnancement = "";
        for (int i = 0; i < this.ordonnancement.size(); i++) {
            for (int j = 0; i < this.ordonnancement.get(i).size(); i++) {
                ordonnancement += this.ordonnancement.get(i).get(j) + " ";
            }
            ordonnancement += "\n";
        }
        return "Ce projet est nomm� : " + this.nom + "\n" 
               + "Son unit� de temps est : " + this.uniteTemps + "\n" 
               + "Ses t�ches sont : \n" + taches
               + "La matrice d'adjacence de son graphe d'ordonnancement est :\n"
               + ordonnancement;
    }
    
}
