import logika.Hra;
import logika.PrikazPomoc;
import logika.PrikazZober;
import logika.SeznamPrikazu;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author    Luboš Pavlíček
 * @version   pro školní rok 2014/2015
 */
public class SeznamPrikazuTest
{
    private Hra hra;
    private PrikazPomoc prPomoc;
    private PrikazZober prZober;
    
    @Before
    public void setUp() {
        hra = new Hra();
        prPomoc = new PrikazPomoc();
        prZober = new PrikazZober(hra.getHerniPlan());
    }

    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prPomoc);
        seznPrikazu.vlozPrikaz(prZober);
        assertEquals(prPomoc, seznPrikazu.vratPrikaz("pomoc"));
        assertEquals(prZober, seznPrikazu.vratPrikaz("zober"));
        assertEquals(null, seznPrikazu.vratPrikaz("napoveda"));
    }
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prPomoc);
        seznPrikazu.vlozPrikaz(prZober);
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("pomoc"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("zober"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("napoveda"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("Konec"));
    }
    
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prPomoc);
        seznPrikazu.vlozPrikaz(prZober);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("pomoc"));
        assertEquals(true, nazvy.contains("zober"));
        assertEquals(false, nazvy.contains("napoveda"));
        assertEquals(false, nazvy.contains("Konec"));
    }
    
}
