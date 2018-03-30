package logika;


/**
 *  Trieda PrikazPreskumaj implementuje pre hru príkaz preskúmaj.
 *  Táto trieda je súčasťou jednoduchej textovej hry.
 *  
 *@author     Ivana Stanová
 *@version    pre ZS 2017/2018
 */
public class PrikazPreskumaj implements IPrikaz
{
    private static final String NAZEV = "preskúmaj";
    private HerniPlan plan;


    /**
     * Konštruktory triedy PrikazPreskumaj.
     */
    public PrikazPreskumaj(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
     *  Vykonáva príkaz "preskúmaj". Zistí aké sú v miestnosti
     *  predmety, postavy a aké sú z nej východy. Ak je zadaný parameter,
     *  preskúma obsah veci.
     *
     *@param parametry - ako parameter obsahuje názov veci,
     *                        ktorú má preskúmať.
     *@return správa, ktorú vypíše hra hráčovi
     */ 
    @Override
    public String provedPrikaz(String ... parametry) {
        if (parametry.length == 0) {
            // pokiaľ chýba druhé slovo, tak sa prehľadá obsah miestnosti
            return plan.getAktualniProstor().dlouhyPopis() + "\n" +
                    plan.getAktualniProstor().popisVeciVMiestnosti() +
                    "\n" + plan.popisPostavVMiestnosti();            
        }  
        
        String hladanaVec = parametry[0];
        
         if (plan.getAktualniProstor().obsahujeVec(hladanaVec)) {
            Vec vec = plan.getAktualniProstor().getVeci().get(hladanaVec);
            vec.preskumat(true);
            return vec.popisPreskumaj();                  
        }
        else {
            return "Zadaný predmet sa nenachádza v tejto miestnosti, nie je možné ho preskúmať.";
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
