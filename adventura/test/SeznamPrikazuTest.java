

import org.junit.Before;
import org.junit.Test;

import logika.Hra;
import logika.IPrikaz;
import logika.SeznamPrikazu;


import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author    Luboš Pavlíček
 * @version   pro školní rok 2016/2017
 */
public class SeznamPrikazuTest
{
    @Before
    public void setUp() {
        new Hra();
    }

    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
		IPrikaz PrikazKoniec = null;
		seznPrikazu.vlozPrikaz(PrikazKoniec);
        IPrikaz PrikazVojdi = null;
		seznPrikazu.vlozPrikaz(PrikazVojdi);
        assertEquals(PrikazKoniec, seznPrikazu.vratPrikaz("koniec"));
        assertEquals(PrikazVojdi, seznPrikazu.vratPrikaz("vojdi"));
        assertEquals(null, seznPrikazu.vratPrikaz("nápoveda"));
    }
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        IPrikaz PrikazKoniec = null;
		seznPrikazu.vlozPrikaz(PrikazKoniec);
        IPrikaz PrikazVojdi = null;
		seznPrikazu.vlozPrikaz(PrikazVojdi);
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("koniec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("vojdi"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("nápoveda"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("Konec"));
    }
    
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        IPrikaz PrikazKoniec = null;
		seznPrikazu.vlozPrikaz(PrikazKoniec);
        IPrikaz PrikazVojdi = null;
		seznPrikazu.vlozPrikaz(PrikazVojdi);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("koniec"));
        assertEquals(true, nazvy.contains("vojdi"));
        assertEquals(false, nazvy.contains("nápoveda"));
        assertEquals(false, nazvy.contains("Konec"));
    }
    
}
