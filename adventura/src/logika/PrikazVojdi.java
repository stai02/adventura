package logika;

/**
 * Trieda PrikazVojdi implementuje pre hru príkaz vojdi. 
 * Táto trieda je súčasťou jednoduchej textovej hry.
 *  
 *@author     Ivana Stanová
 *@version    pre ZS 2017/2018
 */
class PrikazVojdi implements IPrikaz {
    private static final String NAZEV = "vojdi";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazVojdi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "vojdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám ísť? Musíte zadať meno východu.";
        } 
        
        String smer;
        
        if (parametry.length == 1) {
            smer = parametry[0];
        } else {
            // pokiaľ má názov miestnosti 2 slová
            smer = parametry[0] + " " + parametry[1];
        }
        

        // zkoušíme přejít do sousedního prostoru
        
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
        
        if (sousedniProstor instanceof Prostor) { 
            if (sousedniProstor.jeViditelna()) {
                String doplnujuciText =""; 
                plan.setAktualniProstor(sousedniProstor);
                plan.presunPostavy();
                
                // zviditeľnia sa neviditeľné priestory
                if (sousedniProstor.getNazov().equals("telocvičňa")) {
                    // zviditeľníme toalety, ak ešte nie sú
                    if (!plan.vratMiestnost("toalety").jeViditelna()) {
                    plan.vratMiestnost("toalety").zmenViditelnost(true);
                    doplnujuciText = "Odomkli sa toalety ..."; 
                    }
                
                } else if (sousedniProstor.getNazov().equals("toalety")) {
                    // zviditeľníme bufet, ak ešte nie je
                    if (!plan.vratMiestnost("bufet").jeViditelna()) {
                    plan.vratMiestnost("bufet").zmenViditelnost(true);
                    doplnujuciText = "Odomkol sa bufet ..."; 
                    }
                
                } else if (sousedniProstor.getNazov().equals("bufet")) {
                    // zviditeľníme zborovňu, ak ešte nie je
                    if (!plan.vratMiestnost("zborovňa").jeViditelna()) {
                    plan.vratMiestnost("zborovňa").zmenViditelnost(true);
                    doplnujuciText = "Odomkla sa zborovňa ..."; 
                    }
                }
                
                
                // pripraví sa rozhovor s duchom na toaletách
                if (sousedniProstor.getNazov().equals("toalety")) { 
                plan.vratPostava("Duch Dušan").setAktMiestnost(sousedniProstor);
                }
            
                // pripraví sa rozhovor s duchom v skrinkách
                if (sousedniProstor.getNazov().equals("skrinky")) { 
                plan.vratPostava("Duch Dušan").setAktMiestnost(sousedniProstor);
                }
                
                // pripraví sa finálna konfrontácia s upírom        
                if (plan.getBatoh().jeVecVBatohu("kľúč") &&
                    sousedniProstor.getNazov().equals("prízemie")) {
                    plan.vratPostava("Gróf Drakula").setAktMiestnost(plan.vratMiestnost("prízemie"));
                    doplnujuciText = "Keď ste sa chystali odomknúť východ zo školy,vyskočil na Vás upír.\n"
                    + "Bráňte sa !";      
                } 
                        
                return sousedniProstor.dlouhyPopis() +"\n" + doplnujuciText;                
            
            } else {
                return "Tam sa odtiaľto ísť nedá !";   
            } 
        } else {
            return "Tento priestor nesusedí s Vašou aktuálnou pozíciou.";
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
