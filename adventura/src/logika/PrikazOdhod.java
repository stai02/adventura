package logika;


/**
 *  Trieda PrikazOdhod implementuje pre hru príkaz odhod.
 *  Táto trieda je súčasťou jednoduchej textovej hry.
 *  
 *@author     Ivana Stanová
 *@version    pre ZS 2017/2018
 */
public class PrikazOdhod implements IPrikaz
{
    private static final String NAZEV = "odhod";
    private HerniPlan plan;


    /**
     * Konštruktory triedy PrikazOdhod.
     */
    public PrikazOdhod(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
     *  Vykonáva príkaz "odhod". Vyhodí z batoha vec do priestoru.
     *  Vec sa stane súčasťou miestnosti.
     *
     *@param parametry - ako parameter obsahuje názov veci,
     *                        ktorú má hráč odhodiť.
     *@return správa, ktorú vypíše hra hráčovi
     */ 
    @Override
    public String provedPrikaz(String ... parametry) {
        if (parametry.length == 0) {
            // pokiaľ chýba druhé slovo, tak ...
            return "Čo mám odhodiť ? Musíte zadať názov predmetu z batoha";
        } 
        
        String predmet = parametry[0];     
        
         if (plan.getBatoh().jeVecVBatohu(predmet)) {
            Vec vec = plan.getBatoh().vyberZBatoha(predmet);
            plan.getAktualniProstor().vlozVec(predmet, vec);
            return "Z batoha ste vyhodili " + predmet +"."; 
        }
        else {
            return "Vec sa nenachádza v batohu.";            
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
