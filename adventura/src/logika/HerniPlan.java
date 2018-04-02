package logika;

import java.util.Map;
import java.util.Observable;
import java.util.HashMap;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří postavy, věci, všechny prostory, 
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    1.8.0_121
 */
public class HerniPlan extends Observable {
    private Prostor aktualniProstor;
    private Map<String, Postava> zoznamPostav; // obsahuje zoznam postáv v hre
    private Map<String, Prostor> zoznamMiestnosti; // obsahuje zoznam miestností v hre
    private Batoh batoh; 
    private boolean ochromeny = false;
    private boolean rozzureny = false;
    private boolean nakrmeny = false;
    private boolean mrtvy = false;    
    private boolean smrt = false;

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví 4.C.
     *  Vytvára jednotlivé postavy, veci a umiestňuje ich do miestností.
     */
    public HerniPlan() {
    	
 	
        // vytvářejí se jednotlivé prostory        
        zoznamMiestnosti = new HashMap<String, Prostor>();
        
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        Prostor poschodie = new Prostor("prvé poschodie", "chodba na prvom poschodí Vašej strednej školy",true);
        Prostor zborovna = new Prostor("zborovňa","zborovňa, hlavné sídlo učiteľského zboru",false);
        Prostor toalety = new Prostor("toalety","toalety, na ktorých môžete stretnúť ducha",false);
        Prostor schody = new Prostor("schody","schodisko spájajúce prízemie a prvé poschodie",true);
        Prostor prizemie = new Prostor("prízemie","chodba na prízemí Vašej strednej školy",true);
        Prostor bufet = new Prostor("bufet","bufet, v ktorom môžete nájsť rôzne občerstvenie",false);
        Prostor skrinky = new Prostor("skrinky","šatňové skrinky, kam si študenti odkladajú topánky a kabáty",true);
        Prostor telocvicna = new Prostor("telocvičňa","školská telocvičňa",true);

        zoznamMiestnosti.put(stvrtaC.getNazov(), stvrtaC);
        zoznamMiestnosti.put(poschodie.getNazov(), poschodie);
        zoznamMiestnosti.put(zborovna.getNazov(), zborovna);
        zoznamMiestnosti.put(toalety.getNazov(), toalety);
        zoznamMiestnosti.put(schody.getNazov(), schody);
        zoznamMiestnosti.put(prizemie.getNazov(), prizemie);
        zoznamMiestnosti.put(bufet.getNazov(), bufet);
        zoznamMiestnosti.put(skrinky.getNazov(), skrinky);
        zoznamMiestnosti.put(telocvicna.getNazov(), telocvicna); 

        // přiřazují se průchody mezi prostory (sousedící prostory)
        stvrtaC.setVychod(poschodie);
        poschodie.setVychod(stvrtaC);
        poschodie.setVychod(schody);
        poschodie.setVychod(toalety);
        poschodie.setVychod(zborovna);
        zborovna.setVychod(poschodie);
        toalety.setVychod(poschodie);
        schody.setVychod(poschodie);
        schody.setVychod(prizemie);
        prizemie.setVychod(schody);
        prizemie.setVychod(bufet);
        bufet.setVychod(prizemie);
        prizemie.setVychod(skrinky);        
        skrinky.setVychod(prizemie);
        skrinky.setVychod(telocvicna);
        telocvicna.setVychod(skrinky);

        aktualniProstor = stvrtaC;  // hra začíná v 4.C  

        // inicializácia postáv a priradenie do zoznamu postáv 
        Postava duch = new Postava("Duch Dušan","Bububu !!!", skrinky);
        Postava upir = new Postava("Gróf Drakula", "Mám hlad !!!", bufet);

        duch.pridajMiestnost(toalety);
        duch.pridajMiestnost(skrinky);        
        upir.pridajMiestnost(stvrtaC);
        upir.pridajMiestnost(prizemie);
        upir.pridajMiestnost(telocvicna);
        upir.pridajMiestnost(poschodie);
        upir.pridajMiestnost(bufet);
        upir.pridajMiestnost(zborovna);
        upir.pridajMiestnost(schody);
        upir.pridajMiestnost(skrinky);
        upir.pridajMiestnost(toalety);

        zoznamPostav = new HashMap<String, Postava>();
        zoznamPostav.put(duch.getMeno(), duch);
        zoznamPostav.put(upir.getMeno(), upir);

        // inicializácia vecí a priradenie na pozície, vytvorenie zoznamu vecí

        Vec medicinbal = new Vec("medicinbal",true);
        Vec chladnicka = new Vec("chladnička",false);
        Vec paradajka = new Vec("paradajka",true);
        Vec cesnak = new Vec("cesnak",true);
        Vec kluc = new Vec("kľúč",true);

        telocvicna.vlozVec("medicinbal",medicinbal);
        bufet.vlozVec("chladnička",chladnicka);
        chladnicka.vlozVec(paradajka);
        chladnicka.vlozVec(cesnak);
        zborovna.vlozVec("kľúč",kluc);

        //vytvorenie batoha
        batoh = new Batoh();

    } 

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve kterém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metóda vracia odkaz na miestnosť, ak je súčasťou hry.
     *
     *@param     miestnost Miestnosť, ktorá má byť nájdená v zozname miestností hry      
     *@return     mapa miestností v hre
     */

    public Prostor vratMiestnost(String miestnost) {
        return zoznamMiestnosti.get(miestnost);
    }

    /**
     *  Metóda vracia odkaz na postavu, ak existuje v hre.
     *
     *@param    postava Postava, ktorá má byť nájdená v zozname postáv hry
     *@return     mapa postáv v hre
     */    
    public Postava vratPostava(String postava) {
        return zoznamPostav.get(postava);
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        this.aktualniProstor = prostor;    
        this.setChanged();
        this.notifyObservers();
    } 

    /**
     *  Metóda vypíše postavy v miestnosti
     *
     *@return   zoznam postáv v miestnosti
     */
    public String popisPostavVMiestnosti() {
        String vysledok = "";
        for (Postava postava : zoznamPostav.values()) {
            if (postava.getAktMiestnost().equals(aktualniProstor)) {
                vysledok += postava.getMeno() +"\n";                            
            }
        }

        if (vysledok.length() == 0) {
            vysledok = "V miestnosti nikto nie je.";           
        }
        else {
            vysledok = "V miestnosti ste uvideli tieto postavy: \n" + vysledok;           
        }
        return vysledok;
    }

    /**
     *  Metóda zistí, či je postava v aktuálnej miestnosti
     *
     *@param meno Meno postavy, ktorá sa hľadá v miestnosti
     *@return  false, ak postava nie je v aktuálnej miestnosti
     */
    public boolean jePostavaVMiestnosti(String meno) {
        return zoznamPostav.containsKey(meno) 
        && zoznamPostav.get(meno).getAktMiestnost() == aktualniProstor;
    }

    /**
     * Vracia mapu obsahujúcu postavy, ktoré sa v hre
     * nachádzajú.
     * 
     * @return mapa postáv v hre
     */
    public Map<String, Postava> getPostavy() {
        return zoznamPostav;
    }

    /**
     *  Metóda vráti postavu zadaného mena, ak je v aktuálnej miestnosti
     *
     *@param  meno Meno postavy, ktorá sa hľadá v miestnosti
     *@return  postava zadaného mena, ak je v aktuálnej miestnosti
     */
    public Postava najdiPostavu(String meno) {
        if (jePostavaVMiestnosti(meno)) {
            return zoznamPostav.get(meno);           
        }
        else {
            return null;           
        }
    }

    /**
     *  Metóda odstráni postavu z miestnosti.
     *  
     *  @param postava Postava, ktorú chceme zabiť (odstrániť z miestnosti)
     */
    public void zabiPostavu(Object postava) {
        zoznamPostav.remove(postava);
        this.mrtvy = true;
    }

    /**
     *  Metóda zistí, či postava žije.
     *
     *@return  false, ak nežije
     */
    public boolean jeMrtvy() {
        return mrtvy;
    }

    /**
     *  Metóda pohne všetkými postavami v hre
     */
    public void presunPostavy() {
        for (Postava postava : zoznamPostav.values()) {
            postava.prejdi();          
        }
    }   

    /**
     *  Metóda vracia batoh
     *  
     * @return    batoh
     */
    public Batoh getBatoh() {
        return batoh;
    }   

    /**
     *  Metóda ochromí upíra cesnakom.
     *
     *@param    stav True ak má byť upír ochromený
     */
    public void ochromit(boolean stav) {
        this.ochromeny = stav;
    }

    /**
     *  Metóda zistí, či je  upír ochromený.
     *
     *@return  false, ak nie je ochromený
     */
    public boolean jeOchromeny() {
        return ochromeny;
    }

    /**
     *  Metóda rozzúri upíra.
     *
     *@param    stav True, ak má byť upír rozzúrený
     */
    public void rozzurit(boolean stav) {
        this.rozzureny = stav;
    }

    /**
     *  Metóda zistí, či je upír rozzúrený.
     *
     *@return  false, ak nie je rozzúrený
     */
    public boolean jeRozzureny() {
        return rozzureny;
    }

    /**
     *  Metóda nakŕmi upíra.
     *
     *@param  stav True, ak má byť upír nakŕmený
     */
    public void nakrmit(boolean stav) {
        this.nakrmeny = stav;
    }

    /**
     *  Metóda zistí, či je  upír nakŕmený.
     *
     *@return  false, ak nie je nakŕmený
     */
    public boolean jeNakrmeny() {
        return nakrmeny;
    }

    /**
     *  Metóda predstavuje hráčovu smrť.
     *  
     *@param    stav True, ak má hráč zomrieť
     */
    public void zomriet(boolean stav) {
        this.smrt = stav;
    }

    /**
     *  Metóda zistí, či žijeme.
     *
     *@return  false, ak nežijeme
     */
    public boolean zomreliSme() {
        return smrt;
    }    
}
