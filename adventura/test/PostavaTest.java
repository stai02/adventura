

import org.junit.Before;
import org.junit.Test;

import logika.Hra;
import logika.Postava;
import logika.Prostor;

import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

/*******************************************************************************
 * Testovacia trieda PostavaTest slúži ku komplexnému otestovaniu
 * triedy Postava
 *
 * @author    Ivana Stanová
 * @version  ZS 2017/2018
 */
public class PostavaTest {
    @Before
    public void setUp() {
        new Hra();        
    }
    
    @Test
    public void testGetMeno() {   
        Postava pavuk = new Postava("Pavúk","Ahoj",null);
        assertEquals("Pavúk", pavuk.getMeno());
    }
   
    @Test
    public void testGetReplika() {    
        Postava pavuk = new Postava("Pavúk","Ahoj",null);
        assertEquals("Ahoj", pavuk.getReplika());
    }
    
    @Test
    public void testGetAktMiestnost() {  
        Prostor kuchyna = new Prostor("Kuchyňa","Kuchyňa s pavúkom",true);
        Postava pavuk = new Postava("Pavúk","Ahoj",kuchyna);
        assertEquals(kuchyna, pavuk.getAktMiestnost());
    }
    
    @Test
    public void testSetAktMiestnost() {   
        Prostor kupelna = new Prostor("Kúpeľňa","Obyčajná kúpeľna",true);
        Postava pavuk = new Postava("Pavúk","Ahoj",null);
        pavuk.setAktMiestnost(kupelna);
        assertEquals(kupelna, pavuk.getAktMiestnost());
    }
    
    @Test
    public void testPridajMiestnost() {  
        Prostor kuchyna = new Prostor("Kuchyňa","Kuchyňa s pavúkom",true);
        Prostor kupelna = new Prostor("Kúpeľňa","Obyčajná kúpeľna",true);
        Postava pavuk = new Postava("Pavúk","Ahoj",kuchyna);        
       
        pavuk.pridajMiestnost(kuchyna);  
        pavuk.pridajMiestnost(kupelna);  
        
        assertTrue(pavuk.getMiestnosti().contains(kuchyna));
    }
    
    @Test
    public void testPrejdi() {  
        Prostor kuchyna = new Prostor("Kuchyňa","Kuchyňa s pavúkom",true);
        Prostor kupelna = new Prostor("Kúpeľňa","Obyčajná kúpeľna",true);
        Postava pavuk = new Postava("Pavúk","Ahoj",kuchyna);        
        List<Prostor> miestnosti = new ArrayList<Prostor>();
        
        pavuk.pridajMiestnost(kuchyna);  
        pavuk.pridajMiestnost(kupelna);  
        miestnosti.add(kuchyna);
        miestnosti.add(kupelna);   
        pavuk.prejdi();
        
        assertTrue(miestnosti.contains(pavuk.getAktMiestnost()));
    }
}
