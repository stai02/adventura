package logika;

import java.util.Map;
import java.util.HashMap;

/**
 * Trieda Vec - popisuje jednotlivé veci v hre
 *
 * Táto trieda je súčasťou jednoduchej textovej hry.
 * 
 * Veci je možné zobrať a vložiť do batoha, odhodiť a použiť.
 * 
 * @author   Ivana Stanová
 * @version  pre ZS 2017/2018
 */
public class Vec
{
    private String nazov;
    private boolean prenosne;
    private Map<String,Vec> zoznamVeci;
    private boolean preskumane = false;

    /**
     * Vytvorenie veci s uvedením jej prenositeľnosti.
     * 
     * @param nazov Názov veci
     * @param prenosne Či vec je alebo nie je prenosná
     */
    public Vec(String nazov, boolean prenosne)
    {
        this.nazov = nazov;
        this.prenosne = prenosne;
        zoznamVeci = new HashMap<String,Vec>();
    }

    /**
     * Metóda vracia názov veci (bol zadaný pri vytváraní veci ako parameter
     * konštruktora) 
     *
     * @return     názov veci
     */
    public String getNazov()
    {
        return nazov;
    }

    /**
     * Metóda vracia prenositeľnosť veci (bolo uvedené pri vytváraní veci ako parameter
     * konštruktora) 
     *
     * @return     prenositeľnosť veci
     */
    public boolean jePrenosne()
    {
        return prenosne;
    }

    /**
     * Metóda vracia, či je vec preskúmaná (zisťuje suvedené pri vytváraní veci ako parameter
     * konštruktora) 
     *
     * @return     false, ak vec nie je preskúmaná
     */
    public boolean jePreskumane()
    {
        return preskumane;
    }

    /**
     * Metóda nastaví, či vec je alebo nie je preskúmaná
     *
     * @param     preskumane True, ak má byť vec preskúmaná
     */
    public void preskumat (boolean preskumane) {
        this.preskumane = preskumane;
    }

    /**
     * Metóda pridá vec do zoznamu vecí
     *
     * @param     nieco Vec ktorá má byť vložená do zoznamu vecí
     */
    public void vlozVec (Vec nieco) {
        zoznamVeci.put(nieco.getNazov(), nieco);
    }

    /**
     * Metóda vráti informáciu o tom, či sa našla vec s daným názvom a je preskúmaná  
     *
     * @param nazov Názov veci, ktorú hľadáme v zozname
     * @return     true, ak je vec preskúmaná a nachádza sa v zozname
     */
    public boolean obsahujeVec(String nazov) {
        return preskumane && zoznamVeci.containsKey(nazov);
    }

    /**
     * Metóda vyberie vec s daným názvom zo zoznamu vecí ak je preskúmaná
     *
     * @param nazov Názov veci, ktorá má byť vybraná zo zoznamu vecí
     * @return     vec zo zoznamu vecí
     */
    public Vec vyberVec(String nazov) {
        Vec vec = null;
        if (this.jePreskumane() && zoznamVeci.containsKey(nazov)) {
            vec = zoznamVeci.get(nazov);
            if (vec.jePrenosne()) {
                zoznamVeci.remove(nazov);
            }
        }
        return vec;
    }

    /**
     * Metóda vracia popis po preskúmaní veci. Ak sa zistilo,
     * že vec obsahuje vo vnútri iné veci, vypíše ich zoznam.
     *
     * @return     komentár výsledku preskúmania veci
     */
    public String popisPreskumaj() {
        if (zoznamVeci.isEmpty()) {
            return "Pozorne ste si prezreli "+nazov+", nič nezvyčajné ste si nevšimli.";
        }
        String popis = "Pozorne ste si prezreli "+nazov+" a vo vnútri ste našli:\n";
        for (String nazov : zoznamVeci.keySet()) {
            popis += " " + nazov;
        }
        return popis;
    }

    /**
     * Metóda vracia odkaz na mapu obsahujúcu veci, ktoré sú umiestnené v tejto veci
     *
     * @return mapa vecí v tejto veci
     */
    public Map<String, Vec> getVeci() {
        return zoznamVeci;
    }

    public String toString() {
        return nazov;
    }
}
