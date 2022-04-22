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
    
    /* Nom attribu� � la tache */
    private String nom;
    
    /* Description synth�tique de la tache */
    private String description;
    
    /* 
     * Dur�e que la tache prendra a �tre r�alis�e
     * (L'unit� de temps sera d�fini dans le projet ou est utilis� la tache)
     */
    private double duree;
    
    public Tache(String nom, String description, double duree) {
        if (nom.isBlank() || nom == null) {
            throw new IllegalArgumentException("Le nom est vide ou null");
        }
    }
}
