package logika;
/**
 * Trieda PrikazKomunikuj implementuje pre hru príkaz komunikuj. 
 * Táto trieda je súčasťou jednoduchej textovej hry.
 *  
 *@author     Ivana Stanová
 *@version    pre ZS 2017/2018
 *  
 */
class PrikazKomunikuj implements IPrikaz {
    
    private static final String NAZEV = "komunikuj";
    private HerniPlan plan;
     
    
     /**
    *  Konstruktor třídy
    *  
    *  @param plan Herný plán, kde budete komunikovať s postavami
    */    
    public PrikazKomunikuj(HerniPlan plan) {
        this.plan = plan;
    }
    
    /**
     *  Vracia prehovor postavy, s ktorou chcel hráč komunikovať.
     *  
     *  @param  parametry Meno postavy, s ktorou chce hráč komunikovať
     *  
     *  @return prehovor postavy
     */
    @Override
    public String provedPrikaz(String... parametry) {  
        if (parametry.length == 0) {
            // pokiaľ chýba druhé slovo (postava), tak ....
            return "S kým sa mám rozprávať? Musíte zadať meno postavy.";
        }
        
        String komunikant = parametry[0] + " " + parametry[1];
        Postava postava = plan.najdiPostavu(komunikant);
        
        if (plan.getAktualniProstor().getNazov().equals("skrinky") && 
            postava.getMeno().equals("Duch Dušan") &&
            plan.getBatoh().jeVecVBatohu("medicinbal")) {
            return "Vezmi si medicinbal, možno sa zíde v nebezpečenstve ...";          
        } else if (plan.getAktualniProstor().getNazov().equals("toalety") && 
                   postava.getMeno().equals("Duch Dušan") && (
                   plan.getBatoh().jeVecVBatohu("cesnak") || 
                   plan.getBatoh().jeVecVBatohu("paradajka"))) {
            return "Láska ide cez žalúdok.\n"+
            "Upír nemusí uhasiť hlad práve tebou.\n"+
            "V bufete nájdeš to, čo nasýti upírov hlad.\n"+
            "Inak ho budeš musieť poraziť v súboji.";            
        } else if (plan.getAktualniProstor().getNazov().equals("prízemie") && 
                    postava.getMeno().equals("Gróf Drakula") &&
                    plan.getBatoh().jeVecVBatohu("kľúč")) {
            return "Zjem ťa ! Z tejto školy neutečieš.";            
        } else if(postava == null) {
            return "Taká postava tu nie je.";
        } else {
            return postava.getReplika();
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