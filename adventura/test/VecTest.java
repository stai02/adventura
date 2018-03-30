



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import logika.Hra;
import logika.Vec;

/*******************************************************************************
 * Testovacia trieda PostavaTest slúži ku komplexnému otestovaniu
 * triedy Postava
 *
 * @author    Ivana Stanová
 * @version  ZS 2017/2018
 */
public class VecTest
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
    public  void testGetNazov() {
        Vec nohavice = new Vec("nohavice",true);
        assertEquals("nohavice", nohavice.getNazov());
    }
    
    @Test
    public  void testJePrenosne() {
        Vec nohavice = new Vec("nohavice",true);
        Vec skrina = new Vec("skriňa",false);
        
        assertTrue(nohavice.jePrenosne());
        assertFalse(skrina.jePrenosne());
    }
    
    @Test
    public  void testJePreskumane() {
        Vec nohavice = new Vec("nohavice",true);
        Vec skrina = new Vec("skriňa",false);
        nohavice.preskumat(true); 
        skrina.preskumat(false);     
        assertTrue(nohavice.jePreskumane());
        assertFalse(skrina.jePreskumane());      
    }
    
    @Test
    public  void testPreskumat() {
        Vec nohavice = new Vec("nohavice",true);
        Vec skrina = new Vec("skriňa",false);
        
        nohavice.preskumat(true);     
        skrina.preskumat(false);
        assertTrue(nohavice.jePreskumane());        
        assertFalse(skrina.jePreskumane());
    }
    
    @Test
    public  void testVlozVec() {
        Vec nohavice = new Vec("nohavice",true);
        Vec skrina = new Vec("skriňa",false);
        
        skrina.vlozVec(nohavice);
        skrina.preskumat(true);
        assertEquals(nohavice,skrina.vyberVec("nohavice"));   
        assertEquals(null,skrina.vyberVec("košeľa"));        
    }
    
    @Test
    public  void testObsahujeVec() {
        Vec nohavice = new Vec("nohavice",true);
        Vec skrina = new Vec("skriňa",false);
        
        skrina.vlozVec(nohavice);
        skrina.preskumat(true);
        assertTrue(skrina.obsahujeVec("nohavice"));
        assertFalse(skrina.obsahujeVec("košeľa"));        
    }
    
    @Test
    public  void testVyberVec() {
        Vec nohavice = new Vec("nohavice",true);
        Vec skrina = new Vec("skriňa",false);
        
        skrina.vlozVec(nohavice);
        skrina.preskumat(true);
        assertEquals(nohavice,skrina.vyberVec("nohavice"));        
    }
    
    @Test
    public  void testPopisPreskumaj() {
        Vec nohavice = new Vec("nohavice",true);
        Vec skrina = new Vec("skriňa",false);
        
        skrina.vlozVec(nohavice);
        skrina.preskumat(true);
        
        String popis = "Pozorne ste si prezreli skriňa a vo vnútri ste našli:\n"
        +" nohavice";        
        assertEquals(popis,skrina.popisPreskumaj());        
    }
    
    @Test
    public  void testGetVeci() {
        Vec nohavice = new Vec("nohavice",true);
        Vec skrina = new Vec("skriňa",false);
        
        skrina.vlozVec(nohavice);
        skrina.preskumat(true);      
        
        assertEquals(nohavice,skrina.getVeci().get("nohavice"));
        assertEquals(null,skrina.getVeci().get("skriňa"));        
    }
}
