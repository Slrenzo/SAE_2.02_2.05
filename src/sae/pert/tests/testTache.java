/*
 * testTache.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4, pas de copyright 
 */
package info1;

/** TODO commenter la responsabilit� de cette classe
 * @author emilien.restoueix
 *
 */
public class testTache {
    
    /** TODO commenter le r�le de cette m�thode (SRP)
     * @param args
     */
    public static void main(String[] args) {
        
        boolean ok;
        
        ok = testConstructeurStringStringDouble();
        
        if(ok) {
            System.out.println("Test r�ussis");
        } else {
            System.out.println("Test �chou�");
        }
    }
    
    private static boolean testConstructeurStringStringDouble() {
        
        boolean testOK;
        
        try {
            new Tache(null, "testDescription", 30.5);
            testOK = false;
        } catch (IllegalArgumentException nomNul) {
            testOK = true;
        }
        
        try {
            new Tache("", "testDescription", 30.5);
            testOK = false;
        } catch (IllegalArgumentException nomVide) {
            testOK = true;
        }
        
        try {
            new Tache("testNom", null, 30.5);
            testOK = false;
        } catch (IllegalArgumentException descriptionNul) {
            testOK = true;
        }
        
        try {
            new Tache("testNom", "", 30.5);
            testOK = false;
        } catch (IllegalArgumentException descriptionVide) {
            testOK = true;
        }
        
        try {
            new Tache("testNom", "testDescription", 0);
            testOK = false;
        } catch (IllegalArgumentException dureeNul) {
            testOK = true;
        }
        
        try {
            new Tache("testNom", "testDescription", -5);
            testOK = false;
        } catch (IllegalArgumentException dureeN�gative) {
            testOK = true;
        }
        
        try {
            new Tache("testNom", "testDescription", 35.5);
            testOK = false;
        } catch (IllegalArgumentException dureeNul) {
            testOK = true;
        }
        
        return testOK;
    }

}
