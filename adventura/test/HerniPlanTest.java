

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import logika.HerniPlan;
import logika.Hra;
import logika.Postava;
import logika.Prostor;
import logika.Vec;

import java.util.Map;
import java.util.HashMap;

/*******************************************************************************
 * Testovacia trieda HerniPlanTest slúži ku komplexnému otestovaniu
 * triedy HerniPlan
 *
 * @author    Ivana Stanová
 * @version  ZS 2017/2018
 */
public class HerniPlanTest
{
    /**
     * Inicializácia predchádzajúca spusteniu každého testu a pripravujúca
     * tzv. prípravok (fixture), čo je množina objektov, s ktorými budú
     * testy pracovať.
     */
    @Before
    public void setUp()
    {
        new Hra();
    }

    /**
     * „Upratovanie“ po teste – táto metóda je spustená po vykonaní každého
     * testu.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public  void testGetAktualniProstor() {
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        HerniPlan plan = new HerniPlan();
        plan.setAktualniProstor(stvrtaC);
        assertEquals(stvrtaC,plan.getAktualniProstor());
    }
    
    @Test
    public  void testVratMiestnost() {
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        HerniPlan plan = new HerniPlan();
        Map<String, Prostor> zoznamMiestnosti = new HashMap<String, Prostor>();
        zoznamMiestnosti.put(stvrtaC.getNazov(), stvrtaC);
        
        assertEquals(stvrtaC,plan.vratMiestnost("4.C"));
    }
    
    @Test
    public  void testVratPostava() {
        HerniPlan plan = new HerniPlan();
        Postava duch = new Postava("Duch Dušan","Bububu !!!", null);
        Map<String, Postava> zoznamPostav = new HashMap<String, Postava>();
        zoznamPostav.put(duch.getMeno(), duch);
        
        assertEquals(duch,plan.vratPostava("Duch Dušan"));
    }
    
    @Test
    public  void testSetAktualniProstor() {
        HerniPlan plan = new HerniPlan();
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        plan.setAktualniProstor(stvrtaC);
        assertEquals(stvrtaC,plan.getAktualniProstor());
    }
    
    @Test
    public  void testPopisPostavVMiestonsti() {
        HerniPlan plan = new HerniPlan();
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        Postava duch = new Postava("Duch Dušan","Bububu !!!", stvrtaC);
        
        plan.getPostavy().put("Duch Dušan",duch);
        plan.setAktualniProstor(stvrtaC);
        duch.setAktMiestnost(stvrtaC); 
        
        String popis = "V miestnosti ste uvideli tieto postavy: \n" +
        "Duch Dušan\n";
        
        assertEquals(popis,plan.popisPostavVMiestnosti());
    }
    
    @Test
    public  void testJePostavaVMiestnosti() {
        HerniPlan plan = new HerniPlan();
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        Postava duch = new Postava("Duch Dušan","Bububu !!!", stvrtaC);
        plan.getPostavy().put("Duch Dušan",duch);
        plan.setAktualniProstor(stvrtaC);
        assertTrue(plan.jePostavaVMiestnosti("Duch Dušan"));
        assertFalse(plan.jePostavaVMiestnosti("Vodník"));
    }
    
    @Test
    public  void testNajdiPostavu() {
        HerniPlan plan = new HerniPlan();
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        Postava duch = new Postava("Duch Dušan","Bububu !!!", stvrtaC);
        plan.getPostavy().put("Duch Dušan",duch);
        plan.setAktualniProstor(stvrtaC);
        assertEquals(duch,plan.najdiPostavu("Duch Dušan"));
        assertEquals(null,plan.najdiPostavu("Vodník"));
    }
    
    @Test
    public  void testZabiPostavu() {
        HerniPlan plan = new HerniPlan();
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        Postava duch = new Postava("Duch Dušan","Bububu !!!", stvrtaC);
        plan.getPostavy().put("Duch Dušan",duch);
        
        plan.zabiPostavu(duch);
        
        assertTrue(plan.jeMrtvy());
    }
    
    @Test
    public  void testJeMrtvy() {
        HerniPlan plan = new HerniPlan();
        Prostor stvrtaC = new Prostor("4.C","4.C, je to vaša trieda",true);
        Postava vodnik = new Postava("Vodník","Čľup !", stvrtaC);
        plan.getPostavy().put("Vodník",vodnik);
        
        plan.zabiPostavu(vodnik);
        
        assertTrue(plan.jeMrtvy());
    }
    
    @Test
    public  void testPresunPostavy() {
        HerniPlan plan = new HerniPlan();
        Prostor toalety = new Prostor("toalety","toalety, na ktorých môžete stretnúť ducha",false);
        Postava vodnik = new Postava("Vodník","Čľup !", null);
        plan.getPostavy().put("Vodník",vodnik);
        vodnik.pridajMiestnost(toalety);
        plan.presunPostavy();        
        
        assertEquals(toalety,vodnik.getAktMiestnost());
    }
    
    @Test
    public  void testGetBatoh() {
        HerniPlan plan = new HerniPlan();
        Vec nohavice = new Vec("nohavice",true);
        Vec kosela = new Vec("košeľa",false);
        
        plan.getBatoh().pridajDoBatoha("košeľa",kosela);
        plan.getBatoh().pridajDoBatoha("nohavice",nohavice);        
       
        assertEquals(nohavice,plan.getBatoh().vyberZBatoha("nohavice"));
    }
    
    @Test
    public  void testOchromit() {
        HerniPlan plan = new HerniPlan();
        plan.ochromit(true);
        
        assertTrue(plan.jeOchromeny());
    }
    
    @Test
    public  void testRozzurit() {
        HerniPlan plan = new HerniPlan();
        plan.rozzurit(false);
        
        assertFalse(plan.jeRozzureny());
    }
    
    @Test
    public  void testNakrmit() {
        HerniPlan plan = new HerniPlan();
        plan.nakrmit(true);
        
        assertTrue(plan.jeNakrmeny());
    }
    
    @Test
    public  void testZomriet() {
        HerniPlan plan = new HerniPlan();
        new Postava("Vodník","Čľup !", null);
        
        plan.zomriet(true);
        
        assertTrue(plan.zomreliSme());
    }
    
    @Test
    public  void testZomreliSme() {
        HerniPlan plan = new HerniPlan();
        new Postava("Vodník","Čľup !", null);
        
        plan.zomriet(false);
        
        assertFalse(plan.zomreliSme());
    }
    
    @Test
    public  void testJeOchromeny() {
        HerniPlan plan = new HerniPlan();
        new Postava("Vodník","Čľup !", null);
        
        plan.ochromit(true);
        
        assertTrue(plan.jeOchromeny());
    }
    
    @Test
    public  void testJeRozzureny() {
        HerniPlan plan = new HerniPlan();
        plan.rozzurit(true);
        
        assertTrue(plan.jeRozzureny());
    }
    
    @Test
    public  void testJeNakrmeny() {
        HerniPlan plan = new HerniPlan();
        plan.nakrmit(true);
        
        assertTrue(plan.jeNakrmeny());
    }
    
    @Test
    public  void testGetPostavy() {
        HerniPlan plan = new HerniPlan();
        Postava vodnik = new Postava("Vodník","Čľup !", null);
        plan.getPostavy().put("Vodník",vodnik);

        assertEquals(vodnik,plan.getPostavy().get("Vodník"));
    }
}
