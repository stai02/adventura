

/*******************************************************************************
 * Testovacia trieda BatohTest slúži ku komplexnému otestovaniu
 * triedy Batoh
 *
 * @author    Ivana Stanová
 * @version  ZS 2017/2018
 */

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import logika.Batoh;
import logika.Hra;
import logika.Vec;

public class BatohTest
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
    public  void testPridajDoBatoha() {
        Vec nohavice = new Vec("nohavice",true);
        Batoh batoh = new Batoh();
        
        batoh.pridajDoBatoha("nohavice", nohavice);
        assertEquals(nohavice, batoh.vyberZBatoha("nohavice"));
        assertEquals(null, batoh.vyberZBatoha("košeľa"));
    }
    
    @Test
    public  void testJePlny() {
        Vec nohavice = new Vec("nohavice",true);
        Vec kosela = new Vec("košeľa",true);
        Vec hodinky = new Vec("hodinky",true);
        Batoh batoh = new Batoh();
        
        batoh.pridajDoBatoha("nohavice", nohavice);
        batoh.pridajDoBatoha("košeľa", kosela);
        batoh.pridajDoBatoha("hodinky", hodinky);
        assertTrue(batoh.jePlny());
    }
    
    @Test
    public  void testVyberZBatoha() {
        Vec kosela = new Vec("košeľa",true);
        Batoh batoh = new Batoh();
        batoh.pridajDoBatoha("košeľa", kosela);
        assertEquals(kosela, batoh.vyberZBatoha("košeľa"));
        assertEquals(null, batoh.vyberZBatoha("nohavice"));
    }
    
    @Test
    public  void testVypisObsahBatoha() {
        Vec kosela = new Vec("košeľa",true);
        Vec nohavice = new Vec("nohavice",true);
        Batoh batoh = new Batoh();
        String popis = "V batohu sú tieto veci: \n"+
        "nohavice\nkošeľa\n";
        
        batoh.pridajDoBatoha("nohavice", nohavice);
        batoh.pridajDoBatoha("košeľa", kosela);        
        assertEquals(popis, batoh.vypisObsahBatoha());
    }
    
    @Test
    public  void testJeVecVBatohu() {
        Vec kosela = new Vec("košeľa",true);
        new Vec("nohavice",true);
        Batoh batoh = new Batoh();
        batoh.pridajDoBatoha("košeľa", kosela);
        assertTrue(batoh.jeVecVBatohu("košeľa"));
        assertFalse(batoh.jeVecVBatohu("nohavice"));  
    }
}
