package logika;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Trieda Batoh - popisuje jednotlivé veci v batohu
 *
 * Tato trieda je súčasťou jednoduchej textovej hry.
 * 
 * Trieda Batoh slúži na ukladanie vecí do batoha.
 * Veci z batoha môžete použiť alebo vyhodiť. 
 *
 * @author   Ivana Stanová
 * @version  pre ZS 2017/2018
 */
public class Batoh
{
    private Map<String, Vec> batoh; //obsahuje veci v batohu  
    private static final int MAX_POCET = 3; // obmedzenie veľkosti batoha

    /**
     * Konštruktory objektov triedy Batoh.
     */
    public Batoh()
    {
        batoh = new HashMap<String, Vec>();
    }

    /**
     * Metóda pridá danú vec do batoha.
     *
     * @param   vec  Vec, ktorá sa má pridať do batoha
     */
    public void pridajDoBatoha(String nazov, Vec vec)
    {
        if (batoh.size() < MAX_POCET) {
            batoh.put(nazov,vec);
        }
        else {
            System.out.println("Batoh je plný.");
        }
    }             

    /**
     * Metóda vyberie danú vec do batoha.
     *
     * @param   vec  Vec, ktorá sa má vybrať do batoha
     */
    public Vec vyberZBatoha(String nazov)
    {
        if (batoh.size() > 0) {
            Vec vec = batoh.get(nazov);
            batoh.remove(nazov);
            return vec;
        } 
        return null;        
    }             

    /**
     * Metóda vyberie danú vec do batoha.
     *
     * @param   vec  Vec, ktorá sa má vybrať do batoha
     */
    public boolean jePlny()
    {
        return batoh.size()==MAX_POCET;
    }

    /**
     *  Metóda vracia text s obsahom batoha
     *
     *@return   text s vecami v batohu
     */
    public String vypisObsahBatoha() {
        String vysledok = "";
        Set<String> mnozinaKlucov = batoh.keySet();
        for (String kluc : mnozinaKlucov) {
            vysledok += kluc + "\n";                           

        }

        if (vysledok.length() == 0) {
            vysledok = "V batohu nič nie je.";           
        }
        else {
            vysledok = "V batohu sú tieto veci: \n" + vysledok;           
        }
        return vysledok;
    }

    /**
     *  Metóda zistí, či je vec v batohu
     *
     *@return  false, ak vec nie je v batohu
     */
    public boolean jeVecVBatohu(String nazov) {
        return batoh.containsKey(nazov);
    }
} 

