/*
 * Tache.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022, pas de copyright 
 */

package sae.pert;

import java.util.ArrayList;

/**
 * Tache permettant de faire avancer un projet.
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class Tache {
    
    /* Nom attribu� � la tache */
    private String nom;
    
    /* Description synth�tique de la tache */
    private String description;
    
    /* 
     * Dur�e que la tache prendra a �tre r�alis�e
     * (L'unit� de temps sera d�fini dans le projet ou est utilis� la tache)
     */
    private double duree;
    
    /*
     * Date au plus tot de cette tache d�fini par un projet
     */
    private double dateAuPlusTot;
    
    
    /*
     * Date au plus tard de cette tache d�fini par un projet
     */
    private double dateAuPlusTard;
    
    /*
     * Liste des taches � r�aliser avant de r�alis� cette tache
     */
    private ArrayList<Tache> tachesPrealables;
    
    /**
     * D�finition d'une tache ayant une dur�e de r�alisation
     * @param nom nom ni vide ni null de la tache
     * @param description description ni vide ni null de la tache
     * @param duree dur�e de la tache sup�rieur � 0.0
     * @throws IllegalArgumentException nom est vide
     * @throws IllegalArgumentException description est vide
     * @throws IllegalArgumentException duree inf�rieur ou �gal � 0.0
     */
    public Tache(String nom, String description, double duree) {
        if (nom.isBlank()) {
            throw new IllegalArgumentException("Le nom est vide");
        }
        if (description.isBlank()) {
            throw new IllegalArgumentException("Le description est vide");
        }
        if (duree <= 0.0) {
            throw new IllegalArgumentException("La dur�e est nulle "
                                               + "ou n�gative");
        }
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.dateAuPlusTard = 0.0;
        this.dateAuPlusTot = 0.0;
        this.tachesPrealables = new ArrayList<Tache>(0);
    }

    /**
     * Affiche le nom de la tache
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Affiche la description de la tache
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifie la description de la tache
     * @param description description � modifier
     * @throws IllegalArgumentException description est vide
     */
    public void setDescription(String description) {
        if (description.isBlank()) {
            throw new IllegalArgumentException("Le description est vide");
        }
        this.description = description;
    }

    /**
     * Affiche la dur�e de la tache
     * @return duree
     */
    public double getDuree() {
        return duree;
    }

    /**
     * Modifie la dur�e de la tache
     * @param duree dur�e � modifier
     * @throws IllegalArgumentException duree inf�rieur ou �gal � 0.0
     */
    public void setDuree(double duree) {
        if (duree <= 0.0) {
            throw new IllegalArgumentException("La dur�e est nulle "
                                               + "ou n�gative");
        }
        this.duree = duree;
    }
    
    /**
     * @return dateAuPlusTot de cette tache
     */
    public double getDateAuPlusTot() {
        return dateAuPlusTot;
    }

    /**
     * @param dateAuPlusTot modifie la date au plus tot de cette tache
     * @throws IllegalArgumentException si la date au plus tot est n�gative
     */
    public void setDateAuPlusTot(double dateAuPlusTot) {
        if (dateAuPlusTot < 0.0) {
            throw new IllegalArgumentException("La date est n�gative");
        }
        this.dateAuPlusTot = dateAuPlusTot;
    }

    /**
     * @return dateAuPlusTard de cette tache
     */
    public double getDateAuPlusTard() {
        return dateAuPlusTard;
    }

    /**
     * @param dateAuPlusTard modifie la date au plus tard de cette tache
     * @throws IllegalArgumentException si la date au plus t est n�gative
     */
    public void setDateAuPlusTard(double dateAuPlusTard) {
        if (dateAuPlusTard < 0.0) {
            throw new IllegalArgumentException("La date est n�gative");
        }
        this.dateAuPlusTard = dateAuPlusTard;
    }

    /**
     * @return tachesPrealables les taches pr�alables de cette tache
     */
    public ArrayList<Tache> getTachesPrealables() {
        ArrayList<Tache> cloneTachesPrealables = new ArrayList<Tache>(0);
        for (int i = 0; i < this.tachesPrealables.size(); i++) {
            cloneTachesPrealables.add(this.tachesPrealables.get(i));
        }
        return cloneTachesPrealables;
    }

    /**
     * Ajoute une tache � aux taches pr�alables de cette tache
     * @param tache tache que l'on souhaite ajouter aux taches pr�alables
     * @throws IllegalArgumentException tache est d�ja une tache pr�alable 
     *         de cette tache
     * @throws IllegalArgumentException la tache que l'on ajoute est la m�me
     *         que cette tache
     */
    public void ajouterTachePrealable(Tache tache) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.tachesPrealables.size(); i++) {
            estPresent = this.tachesPrealables.get(i).nom == tache.nom;
        }
        if (estPresent) {
            throw new IllegalArgumentException("Cette tache est d�ja une "
                                               + "tache pr�alable");
        }
        if (this.nom == tache.nom) {
            throw new IllegalArgumentException("Cette tache et la tache que"
                                               + " vous souhaitez ajouter"
                                               + " sont les m�me");
        }
        this.tachesPrealables.add(tache);
    }
    
    /**
     * Enl�ve une tache � aux taches pr�alables de cette tache
     * @param tache tache que l'on souhaite ajouter aux taches pr�alables
     * @throws IllegalArgumentException tache ne fait pas partie des taches 
     *         pr�alables de cette tache
     */
    public void enleverTachePrealable(Tache tache) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.tachesPrealables.size(); i++) {
            estPresent = this.tachesPrealables.get(i).nom == tache.nom;
        }
        if (!estPresent) {
            throw new IllegalArgumentException("Cette tache n'est pas une "
                                               + "tache pr�alable");
        }
        this.tachesPrealables.remove(tache);
    }
    
    @Override
    public String toString() {
        String tachesPrealables = this.tachesPrealables.size() != 0 
                                  ? "\n  Taches pr�alables : "
                                  : "\n  Cette tache n'a pas de taches "
                                  + "pr�alables";
        for (int i = 0; i < this.tachesPrealables.size(); i++) {
            tachesPrealables += this.tachesPrealables.get(i).nom + " | ";
        }
        return "Cette tache est d�fini par :\n  Nom : " + this.nom 
               + "\n  Description : " + this.description
               + "\n  Duree : " + this.duree
               + "\n  Date au plus tot : " + this.dateAuPlusTot
               + "\n  Date au plus tard : " + this.dateAuPlusTard
               + tachesPrealables;
    }
}
