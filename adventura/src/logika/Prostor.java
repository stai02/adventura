package logika;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */
public class Prostor {

    private String nazov;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje susedné miestnosti
    private Map<String,Vec> zoznamVeci; // obsahuje zoznam vecí v miestnosti
    private boolean viditelna;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     *@param nazov nazov prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     *@param popis Popis prostoru.
     */
    public Prostor(String nazov, String popis, boolean stav) {
        this.nazov = nazov;
        this.popis = popis;
        this.viditelna = stav;
        vychody = new HashSet<>();
        zoznamVeci = new HashMap<String,Vec>();        
    }

    /**
     * Metoda nastaví viditeľnosť priestoru
     *
     *@param   zmena True, ak sa má priestor stať viditeľným
     */
    public void zmenViditelnost(boolean zmena) {
        this.viditelna = zmena;
    }

    /**
     * Metóda zistí, či je priestoe viditeľný
     *
     *@return   false, ak priestor nie je viditeľný
     */
    public boolean jeViditelna() {
        return viditelna;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     *@param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     *@param o object, který se má porovnávat s aktuálním
     *@return hodnotu true, pokud má zadaný prostor/postava stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }

        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        } 

        // přetypujeme parametr na typ Prostor
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.
        return (java.util.Objects.equals(this.nazov, druhy.nazov));    

    }
    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazov);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     *@return     název prostoru
     */
    public String getNazov() {
        return nazov;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     *@return   dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "\n" + "Nachádzate sa v priestore s názvom " + popis + ".\n"
        + popisVychodu();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     *@return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "Východy:\n";
        for (Prostor sousedni : vychody) {
            if (sousedni.viditelna) {
                vracenyText += sousedni.getNazov() + "\n";
            }
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     *@param nazovSouseda Jméno sousedního prostoru (východu)
     *
     *@return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
            .filter(sousedni -> sousedni.getNazov().equals(nazevSouseda))
            .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     *@return   Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * Metóda zistí, či sa v miestnosti nachádza vec s daným názvom.
     * Prehľadá aj veci v miestnosti (napr. chladničku)
     * 
     *@param    nazov názov veci, ktorú hľadáme
     *
     *@return   false, ak sa hľadaná vec v miestnosti nenachádza
     */
    public boolean obsahujeVec(String nazov) {
        if (najdiVecVMiestnosti(nazov) == null) { // vec nie je v miestnosti
            Set<String> mnozinaKlucov = zoznamVeci.keySet();
            for (String kluc : mnozinaKlucov) {    // prehľadáme veci v miestnosti
                if (zoznamVeci.get(kluc).obsahujeVec(nazov)) { //  ak nejaká vec obsahuje hľadanú vec
                    return true;
                }
            }
            return false;  // vec nie je ani v preskúmaných veciach 
        }
        else {
            return true;
        }

    }

    /**
     * Metóda vyberie vec s uvedeným názvom.
     * 
     * @param   nazov názov veci, ktorá má byť vybraná
     * @return  vec, ktorá bola vybraná
     */
    public Vec vyberVec (String nazov) {
        Vec vybranaVec = najdiVecVMiestnosti(nazov);
        if (vybranaVec != null && vybranaVec.jePrenosne()) {
            zoznamVeci.remove(nazov);
        } else { 
            Set<String> mnozinaKlucov = zoznamVeci.keySet();
            for (String kluc : mnozinaKlucov) {
                vybranaVec= zoznamVeci.get(kluc).vyberVec(nazov);
                if (vybranaVec != null) {
                    break;
                }
            }
        }
        return vybranaVec;
    }

    /**
     * Metóda hľadá vec s daným názvom v miestnosti.
     * Neprehľadáva veci v miestnosti.
     * 
     * @param   nazov Názov veci, ktorá je hľadaná
     * @return      vec, ak bola nájdená
     */
    private Vec najdiVecVMiestnosti(String nazov) {
        Vec vec = null;

        if (zoznamVeci.containsKey(nazov)) {
            vec = zoznamVeci.get(nazov);
        }

        return vec;
    }

    /**
     * Metóda pridá vec do zoznamu vecí v miestnosti
     *
     * @param     vec Vec ktorá má byť vložená do zoznamu vecí
     */
    public void vlozVec(String nazov, Vec vec) {
        zoznamVeci.put(nazov, vec);
    }

    /**
     *  Metóda vracia text s vecami v miestnosti
     *
     *@return   zoznam názvov vecí v miestnosti
     */
    public String popisVeciVMiestnosti() {
        String vysledok = " ";
        Set<String> mnozinaKlucov = zoznamVeci.keySet();
        for (String kluc : mnozinaKlucov) {
            vysledok += zoznamVeci.get(kluc).getNazov() + " ";
        }

        if (vysledok.equals(" ")) {
            return "V miestnosti nič nie je.";           
        }
        else {
            return "V miestnosti ste uvideli tieto predmety:\n" + vysledok;           
        }
    }  

    /**
     * Vracia mapu obsahujúcu veci, ktoré sa v tomto priestore 
     * nachádzajú.
     * 
     * @return mapa vecí v priestore
     */
    public Map<String, Vec> getVeci() {
        return zoznamVeci;
    }
}