/*
 * Tache.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022, pas de copyright 
 */

package sae.pert;

/**
 * Tache permettant de faire avancer un projet.
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class Tache {
    
    /* Nom attribué à la tache */
    private String nom;
    
    /* Description synthétique de la tache */
    private String description;
    
    /* 
     * Durée que la tache prendra a être réalisée
     * (L'unité de temps sera défini dans le projet ou est utilisé la tache)
     */
    private double duree;
    
    public Tache(String nom, String description, double duree) {
        if (nom.isBlank() || nom == null) {
            throw new IllegalArgumentException("Le nom est vide ou null");
        }
    }
}
