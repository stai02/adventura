package logika;


/**
 * Trieda PrikazPomoc implementuje pre hru príkaz pomoc. 
 * Táto trieda je súčasťou jednoduchej textovej hry.
 *  
 *@author     Ivana Stanová
 *@version    pre ZS 2017/2018
 */
public class PrikazPomoc implements IPrikaz
{
    private static final String NAZEV = "pomoc";


    /**
     * Konštruktory triedy PrikazPomoc.
     */
    public PrikazPomoc() {
    }

    /**
     *  Vykonáva príkaz "pomoc". Vypíše, ako daný príkaz funguje.
     *
     *@param parametry - ako parameter obsahuje názov príkazu,
     *                        ktorý má byť hráčovi objasnený.
     *@return správa, ktorú vypíše hra hráčovi
     */ 
    @Override
    public String provedPrikaz(String ... parametry) {
        if (parametry.length == 0) {
            // pokiaľ chýba druhé slovo, tak ...
            return "S akým príkazom potrebujete pomôcť ? Musíte zadať názov tohto príkazu.";
        }  
        
        String prikaz = parametry[0];       
        
            if (prikaz.equals("vojdi")) {
            return "Po zadaní príkazu a miestnosti sa presuniete do uvedenej miestnosti."; 
        } else if (prikaz.equals("konec")){
            return "Týmto príkazom ukončíte hru.";            
        } else if (prikaz.equals("zober")){
            return "Vezme predmet z miestnosti a pridá ho do batoha.";            
        } else if (prikaz.equals("odhod")){
            return "Vezme predmet z batoha a odhodí ho v aktuálnej miestnosti.";            
        }  else if (prikaz.equals("pomoc")){
            return "Vypíše pomôcku k používaniu jednotlivých príkazov.";            
        }  else if (prikaz.equals("komunikuj")){
            return "Po zadaní tohto príkazu vám odpovie uvedená osoba.";            
        }  else if (prikaz.equals("vypíš obsah batoha")){
            return "Vypíšu sa predmety, ktoré hráč nosí v batohu.";            
        }  else if (prikaz.equals("pouzi")){
            return "Použije sa daná vec, ak ju má hráč v batohu.";            
        }   else if (prikaz.equals("preskúmaj")){
            return "Ak nezadáte žiaden parameter, preskúma sa aktuálna miestnosť.\n"+
                   "Vypíšu sa veci a postavy v miestnosti a susedné priestory, kam môžete vôjsť.\n"+
                   "Ak za parameter dosadíte vec z miestnosti, vypíšu sa veci, ktoré obsahuje."; 
        } else if (prikaz.equals("nápoveda")){
            return "Vypíše sa hlavná úloha a zoznam dostupných príkazov."; 
        } else {
            return "Takýto príkaz neexistuje. Pre zoznam príkazov použite nápovedu.";
        }
    
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
