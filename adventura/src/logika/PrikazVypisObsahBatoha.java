package logika;
/**
 *  Trieda PrikazVypisObsahBatoha implementuje pre hru príkaz vypíš.
 *  Táto trieda je súčasťou jednoduchej textovej hry. 
 *  
 *@author     Ivana Stanová 
 *@version    pre ZS 2017/2018
 */
class PrikazVypisObsahBatoha implements IPrikaz {
    private static final String NAZOV = "vypíš";
    private HerniPlan plan;
    
    
    /**
    *  Konštruktor triedy PrikazVypisObsahBatoha
    *  
    *  @param plan herný plán, kde hra prebieha.
    */    
    public PrikazVypisObsahBatoha(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Vykoná príkaz "vypíš obsah batoha".  
     *
     *@param parametry - ak parameter je batoh, vypíše sa jeho obsah 
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Čo mám vypísať ?";
        } else if (parametry[0].equals("batoh")) {            
                return plan.getBatoh().vypisObsahBatoha();   
        } else {
            return "Nedá sa vypísať.";
        }
    }
       
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZOV;
    }

}
