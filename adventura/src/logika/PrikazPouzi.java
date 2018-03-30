package logika;

class PrikazPouzi implements IPrikaz {
    private static final String NAZOV = "použi";
    private HerniPlan plan;
     
    public PrikazPouzi(HerniPlan plan, Hra hra) {
        this.plan = plan;
    }

   
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokiaľ chýba druhé slovo (vec), tak ....
            return "Čo mám použiť ? Musíte zadať názov predmetu z batoha.";
        }    
        
        String predmet = parametry[0];
        
        if (plan.getBatoh().jeVecVBatohu(predmet)) {  
            if (plan.getBatoh().jeVecVBatohu("kľúč")) {
                if (predmet.equals("medicinbal") && plan.jeOchromeny()) {
                    plan.zabiPostavu(plan.vratPostava("Gróf Drakula"));
                    plan.getBatoh().vyberZBatoha(predmet); 
                    return "Zlikvidovali ste upíra a úspešne utiekli. Vyhrali ste !";
                } else if (predmet.equals("medicinbal")) {
                    plan.rozzurit(true);
                    plan.getBatoh().vyberZBatoha(predmet); 
                    return "Svojím útokom ste rozčúlili grófa Drakulu.\n"+
                           "Máte poslednú šancu zachrániť sa.\n"+
                           "Starostlivo zvážte ďalší krok ... ";
                } else if (predmet.equals("cesnak") && plan.jeRozzureny()) {
                    plan.zomriet(true);
                    plan.getBatoh().vyberZBatoha(predmet); 
                    return "Gróf Drakula je rozzúrený.\n"+
                           "Toto je Váš koniec.\n"+
                           "Gróf Drakula Vás zje.\n"+
                           "Prehrali ste :(";
                } else if (predmet.equals("cesnak")) {
                    plan.ochromit(true);
                    plan.getBatoh().vyberZBatoha(predmet); 
                    return "Útokom ste oslabili grófa Drakulu.\n"+
                           "Starostlivo zvážte ďalší krok ... ";
                } else if (predmet.equals("paradajka")) {
                    plan.nakrmit(true);
                    plan.getBatoh().vyberZBatoha(predmet); 
                    return "Výborne ! Ako pravý intelektuál ste nakŕmili upíra !\n"
                    + "Víťazoslávne opúšťate školu. Vyhrali ste !";
                } else { 
                    return "Gróf Drakula Vás nechce pustiť von.\n"+
                           "Použite niečo z batoha ...";
                }       
            } return "Ešte nenastala tá správna príležitosť použiť tento predmet.\n"
                      + "Skúste sa porozhliadnuť po škole ...";
        } else {
            return "Táto vec sa nenachádza vo Vašom batohu.";
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
