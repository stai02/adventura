


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import logika.Prostor;
import logika.Vec;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovacia trieda ProstorTest slúži ku komplexnému otestovaniu
 * triedy Prostor
 *
 * @author    Ivana Stanová
 * @version  ZS 2017/2018
 */
public class ProstorTest
{
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
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {      
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě",true);
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku",false);
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }
    
    @Test
    public  void testViditelnost() {        
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě",true);
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku",false);
        prostor1.zmenViditelnost(true);
        prostor2.zmenViditelnost(false);
        assertTrue(prostor1.jeViditelna());
        assertFalse(prostor2.jeViditelna());
    }
    
    @Test
    public  void testSetVychod() {      
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě",true);
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku",false);
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertNull(prostor1.vratSousedniProstor("šatňa"));
    }

    @Test
    public  void testGetNazov() {       
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě",true);
        assertEquals("hala", prostor1.getNazov());
    }
    
    @Test
    public  void testDlouhyPopis() {        
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        Prostor poschodie = new Prostor("prvé poschodie", "chodba na prvom poschodí Vašej strednej školy",true);
        Prostor zborovna = new Prostor("zborovňa","zborovňa, hlavné sídlo učiteľského zboru",false);
        
        stvrtaC.setVychod(poschodie);
        stvrtaC.setVychod(zborovna);
        
        String popis = "\nNachádzate sa v priestore s názvom 4.C, je to vaša trieda.\n"
        + "Východy:\nprvé poschodie\n";
        
        assertEquals(popis, stvrtaC.dlouhyPopis());
    }
    
    @Test
    public  void testVratSousedniProstor() {        
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        Prostor poschodie = new Prostor("prvé poschodie", "chodba na prvom poschodí Vašej strednej školy",true);
        Prostor zborovna = new Prostor("zborovňa","zborovňa, hlavné sídlo učiteľského zboru",false);
        
        stvrtaC.setVychod(poschodie);
        stvrtaC.setVychod(zborovna);           

        assertEquals(poschodie, stvrtaC.vratSousedniProstor("prvé poschodie"));
        assertEquals(zborovna, stvrtaC.vratSousedniProstor("zborovňa"));    
    }
    
    @Test
    public  void testGetVychody() {        
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        Prostor poschodie = new Prostor("prvé poschodie", "chodba na prvom poschodí Vašej strednej školy",true);
        Prostor zborovna = new Prostor("zborovňa","zborovňa, hlavné sídlo učiteľského zboru",false);
        
        stvrtaC.setVychod(poschodie);
        stvrtaC.setVychod(zborovna);           

        assertTrue(stvrtaC.getVychody().contains(poschodie));
        assertTrue(stvrtaC.getVychody().contains(zborovna));
    }
    
    @Test
    public  void testObsahujeVec() {        
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        Vec desiata = new Vec ("desiata",true);
        stvrtaC.vlozVec("desiata",desiata);

        assertTrue(stvrtaC.obsahujeVec("desiata"));
    }
    
    @Test
    public  void testVyberVec() {        
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        Vec desiata = new Vec ("desiata",true);
        stvrtaC.vlozVec("desiata",desiata);

        assertEquals(desiata,stvrtaC.vyberVec("desiata"));
    }
    
    @Test
    public  void testVlozVec() {        
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        Vec olovrant = new Vec ("olovrant",true);
        stvrtaC.vlozVec("olovrant",olovrant);

        assertEquals(olovrant,stvrtaC.vyberVec("olovrant"));
        assertEquals(null,stvrtaC.vyberVec("desiata"));
    }
    
    @Test
    public  void testPopisVeciVMiestnosti() {        
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        Vec desiata = new Vec ("desiata",true);        
        Vec olovrant = new Vec ("olovrant",true);
        String popis = "V miestnosti ste uvideli tieto predmety:\n"+
        " desiata olovrant ";
        stvrtaC.vlozVec("desiata",olovrant);
        stvrtaC.vlozVec("olovrant",desiata);
        
        assertEquals(popis,stvrtaC.popisVeciVMiestnosti());
    }
    
    @Test
    public  void testGetVeci() {        
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        Vec desiata = new Vec ("desiata",true);        
        stvrtaC.vlozVec("desiata",desiata);
        
        assertEquals(desiata,stvrtaC.getVeci().get("desiata"));
        assertEquals(null,stvrtaC.getVeci().get("olovrant")); 
    }
}