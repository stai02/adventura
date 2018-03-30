

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import logika.Hra;
import logika.Postava;
import logika.Prostor;
import logika.Vec;

import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        Prostor prizemie = new Prostor("prízemie","prízemie, na ktorom môžete stretnúť ducha",true);
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        Prostor schody = new Prostor("schody","schodisko spájajúce prízemie a prvé poschodie",true);
        Map<String, Prostor> zoznamMiestnosti = new HashMap<String, Prostor>();
        Postava upir = new Postava("Gróf Drakula", "Mám hlad !!!", schody);
        Vec kosela = new Vec("košeľa",true);
        Vec nohavice = new Vec("nohavice",true);
        hra1.getHerniPlan().getPostavy().put(upir.getMeno(), upir);
        hra1.getHerniPlan().getBatoh().pridajDoBatoha("nohavice", nohavice);
        hra1.getHerniPlan().getBatoh().pridajDoBatoha("košeľa", kosela);
        
        hra1.getHerniPlan().nakrmit(false);
        hra1.getHerniPlan().rozzurit(false);
        hra1.getHerniPlan().zomriet(false);
        
        zoznamMiestnosti.put(stvrtaC.getNazov(), stvrtaC);
        zoznamMiestnosti.put(schody.getNazov(), schody);
        zoznamMiestnosti.put(prizemie.getNazov(), prizemie);
        hra1.getHerniPlan().setAktualniProstor(stvrtaC);
        stvrtaC.setVychod(prizemie);
        prizemie.setVychod(stvrtaC);
        prizemie.setVychod(schody);
        schody.setVychod(prizemie);
              
        // test PrikazPreskumaj  
        assertEquals("\nNachádzate sa v priestore s názvom 4.C, je to vaša trieda.\nVýchody:\nprízemie\n\nV miestnosti nič nie je.\nV miestnosti nikto nie je.", hra1.zpracujPrikaz("preskúmaj"));
        assertEquals("Zadaný predmet sa nenachádza v tejto miestnosti, nie je možné ho preskúmať.",hra1.zpracujPrikaz("preskúmaj bufet"));
       
        // test PrikazPouži 
        assertEquals("Čo mám použiť ? Musíte zadať názov predmetu z batoha.", hra1.zpracujPrikaz("použi"));
        assertEquals("Táto vec sa nenachádza vo Vašom batohu.",hra1.zpracujPrikaz("použi nôž"));
        
        // test PrikazVojdi
        assertEquals("4.C", hra1.getHerniPlan().getAktualniProstor().getNazov());
        hra1.zpracujPrikaz("vojdi prízemie");
        assertEquals("prízemie", hra1.getHerniPlan().getAktualniProstor().getNazov());
        assertEquals("Kam mám ísť? Musíte zadať meno východu.", hra1.zpracujPrikaz("vojdi"));
        assertEquals("Tento priestor nesusedí s Vašou aktuálnou pozíciou.",hra1.zpracujPrikaz("vojdi bufet"));
        
        // test PrikazNapoveda
        assertEquals("Zobudili ste sa uprostred noci v škole.\n"
        + "Vašou úlohou je nájsť kľúč od školy,\n"
        + "poraziť tajomnú bytosť a ujsť.\n"
        + "\n"
        + "Môžete zadať tieto príkazy:\n"
        + "zober\nnápoveda\nkomunikuj\nodhod\nvojdi\nvypíš\npreskúmaj\nkoniec\npomoc\npouži\n",hra1.zpracujPrikaz("nápoveda"));       
        
        assertEquals(false, hra1.konecHry());        
        hra1.zpracujPrikaz("vojdi schody");
        assertEquals(false, hra1.konecHry());
        
        // test PrikazVypisObsahBatoha
        assertEquals("Čo mám vypísať ?",hra1.zpracujPrikaz("vypíš"));
        assertEquals("Nedá sa vypísať.",hra1.zpracujPrikaz("vypíš obsah chladničky"));
        assertEquals("V batohu sú tieto veci: \nnohavice\nkošeľa\n",hra1.zpracujPrikaz("vypíš batoh"));
        
        // test PrikazPomoc
        assertEquals("Po zadaní príkazu a miestnosti sa presuniete do uvedenej miestnosti.",hra1.zpracujPrikaz("pomoc vojdi"));
        assertEquals("S akým príkazom potrebujete pomôcť ? Musíte zadať názov tohto príkazu.",hra1.zpracujPrikaz("pomoc"));        
        
        // test PrikazOdhod
        assertEquals("Čo mám odhodiť ? Musíte zadať názov predmetu z batoha",hra1.zpracujPrikaz("odhod"));
        assertEquals("Vec sa nenachádza v batohu.",hra1.zpracujPrikaz("odhod šampón"));
        hra1.zpracujPrikaz("odhod nohavice");
        assertTrue(hra1.getHerniPlan().getAktualniProstor().obsahujeVec("nohavice"));
        assertFalse(hra1.getHerniPlan().getBatoh().jeVecVBatohu("nohavice"));        
           
        // test PrikazZober
        assertEquals("Čo mám zobrať ? Musíte zadať názov predmetu z miestnosti.",hra1.zpracujPrikaz("zober"));
        assertEquals("Taký predmet sa v miestnosti nenachádza.",hra1.zpracujPrikaz("zober pohár"));
        assertEquals("Do batoha ste vložili nohavice.",hra1.zpracujPrikaz("zober nohavice"));
        
        // test PrikazKomunikuj
        assertEquals("S kým sa mám rozprávať? Musíte zadať meno postavy.",hra1.zpracujPrikaz("komunikuj"));
        assertEquals("Taká postava tu nie je.",hra1.zpracujPrikaz("komunikuj Pavúk Peter"));
        assertEquals("Mám hlad !!!",hra1.zpracujPrikaz("komunikuj Gróf Drakula"));
        
        
        assertEquals("schody", hra1.getHerniPlan().getAktualniProstor().getNazov());
        hra1.zpracujPrikaz("koniec");
        assertEquals(true, hra1.konecHry());
    }
}
