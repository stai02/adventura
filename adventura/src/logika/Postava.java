package logika;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Trieda Postava - popisuje jednotlivé postavy v hre
 *
 * Táto trieda je súčasťou jednoduchej textovej hry.
 * 
 * S postavami je možné komunikovať, môžu sa pohybovať.
 * 
 * @author   Ivana Stanová
 * @version  pre ZS 2017/2018
 */
public class Postava
{
    private String meno;
    private String replika;
    private Prostor aktualnaMiestnost;
    private List <Prostor> miestnosti; // miestnosti, do ktorých môže chodiť

    /**
     * Vytvorenie postavy so zadanou replikou a počiatočnou pozíciou.
     * 
     * @param meno Meno postavy.
     * @param replika Replika, ktorú postava stále opakuje.
     * @param pociatok Počiatočná pozícia postavy (miestnosť).
     */
    public Postava(String meno, String replika, Prostor pociatok)
    {
        this.meno = meno;
        this.replika = replika;
        this.aktualnaMiestnost = pociatok;
        miestnosti = new ArrayList<Prostor>();
    }
    
    /**
     * Metóda vracia meno postavy (bolo zadané pri vytváraní postavy ako parameter
     * konštruktora) 
     *
     * @return     meno postavy
     */
    public String getMeno()
    {
        return meno;
    }
    
    /**
     * Metóda vracia repliku postavy (bola zadaná pri vytváraní postavy ako parameter
     * konštruktora) 
     *
     * @return     replika, ktorú postava opakuje
     */
    public String getReplika()
    {
        return replika;
    }
    
    /**
     * Metóda vracia aktuálnu pozíciu postavy (bola zadaná pri vytváraní postavy ako parameter
     * konštruktora) 
     *
     * @return     aktuálna miestnosť, v ktorej sa postava nachádza
     */
    public Prostor getAktMiestnost()
    {
        return aktualnaMiestnost;
    }
    
    /**
     * Metóda nastaví aktuálnu pozíciu postavy.
     *
     * @param     miestnost miestnosť, do ktorej postavu umiestnime
     */
    public void setAktMiestnost(Prostor miestnost)
    {
        this.aktualnaMiestnost = miestnost;
    }    
    
    /**
     * Metóda pridá všetky prípustné miestnosti do zoznamu. 
     *
     * @param dalsie Nula a viac priestorov, kam postava môže vstúpiť.
     */
    public void pridajMiestnost(Prostor prostor)
    {
        miestnosti.add(prostor);
    }
    
    /**
     * Metóda vráti všetky prípustné miestnosti zo zoznamu. 
     *
     * @return List<Prostor> Zoznam priestorov, kam postava môže vstúpiť.
     */
    public List<Prostor> getMiestnosti()
    {
        return miestnosti;
    }
    
    /**
     * Postava preletí do náhodnej pípustnej miestnosti, 
     * bez ohľadu na existenciu dverí či prechodov.
     */
    public void prejdi()
    {
        if (! miestnosti.isEmpty()) {
            Collections.shuffle(miestnosti); // náhodné triedenie zoznamu povolených miestností
            for (int i = 0; i < miestnosti.size(); i++) {
				aktualnaMiestnost = miestnosti.get(0);
			}
        }
    }
    
     @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Postava)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        } 
        
        
        // přetypujeme parametr na typ Prostor
        Postava druha = (Postava) o;
            
        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.
        return (java.util.Objects.equals(this.meno, druha.meno));    
             
        
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashMena = java.util.Objects.hashCode(this.meno);
        vysledek = 37 * vysledek + hashMena;
        return vysledek;
    }    
}
