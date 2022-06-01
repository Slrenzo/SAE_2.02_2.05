/*
 * LanceurApplication.java                                  1 juin 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package sae.gui;

import static sae.pert.Projet.UNITE_TEMPS;

import java.util.Scanner;

import sae.pert.Projet;

/** 
 * Lance une application
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class LanceurApplication {
    
    /**
     * Lanceur du projet
     * @param args
     */
    public static void main(String[] args) {
        Scanner entree = new Scanner(System.in);
        Menu.menuPrincipal(entree);
    }

}
