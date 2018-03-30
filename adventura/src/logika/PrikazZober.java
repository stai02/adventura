package logika;


/**
 * Trieda PrikazZober implementuje pre hru príkaz zober. 
 * Táto trieda je súčasťou jednoduchej textovej hry.
 *  
 *@author     Ivana Stanová
 *@version    pre ZS 2017/2018
 */
public class PrikazZober implements IPrikaz
{
    private static final String NAZEV = "zober";
    private HerniPlan plan;


    /**
     * Konštruktory triedy PrikazZober.
     */
    public PrikazZober(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
     *  Vykonáva príkaz "zober". Pridá do batoha vec z miestnosti (aj vec,
     *  ktorá je v tzv. "truhlici").
     *
     *@param parametry - ako parameter obsahuje názov veci,
     *                        ktorú chce hráč pridať do batoha.
     *@return správa, ktorú vypíše hra hráčovi
     */ 
    @Override
    public String provedPrikaz(String ... parametry) {
        if (parametry.length == 0) {
            // pokiaľ chýba druhé slovo, tak ...
            return "Čo mám zobrať ? Musíte zadať názov predmetu z miestnosti.";
        }     
        
        String predmet = parametry[0]; 
        
        if (plan.getAktualniProstor().obsahujeVec(predmet) &&
            plan.getBatoh().jePlny() == false) {
             // vezme vec a vloží ju do batoha             
             if (plan.getAktualniProstor().vyberVec(predmet) == null) {
                 return "Túto vec nemôžete zodvihnúť.";
             } else {         
                 plan.getBatoh().pridajDoBatoha(predmet, plan.getAktualniProstor().vyberVec(predmet));
                 return "Do batoha ste vložili " + predmet +".";              
             }
        } else {
            return "Taký predmet sa v miestnosti nenachádza.";            
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
