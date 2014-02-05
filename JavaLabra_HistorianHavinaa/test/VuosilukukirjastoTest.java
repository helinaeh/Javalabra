import Logiikka.Vuosilukukirjasto;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *Testiluokka, joka testaa Vuosilukukirjaston toimivuutta
 * 
 * @author Helinä Hakala
 */
public class VuosilukukirjastoTest {
    
    double tarkkuus = 0.01;
    Vuosilukukirjasto kirjasto;
    
    public VuosilukukirjastoTest() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException {
        kirjasto = new Vuosilukukirjasto();
        kirjasto.run("Testiluku");
    }
    
    @Test
    public void vuosilukulistanKokoOnSamaKuinTiedostonRivimaara() {
        ArrayList<String[]> lista = kirjasto.getVuosilukulista();
        assertEquals(kirjasto.getTiedostonRivit(), lista.size(), tarkkuus);
    }
    
    @Test
    public void ekassaVuosilukutaulukossaOnKaksiLokeroa() {
        ArrayList<String[]> lista = kirjasto.getVuosilukulista();
        String[] taulukko = lista.get(0);
        assertEquals(2, taulukko.length, tarkkuus);
    }
    
    @Test
    public void vikassaVuosilukutaulukossaOnKaksiLokeroa() {
        ArrayList<String[]> lista = kirjasto.getVuosilukulista();
        String[] taulukko = lista.get(lista.size()-1);
        assertEquals(2, taulukko.length, tarkkuus);
    }
    
    
//    @Test
//    public void asd() {
//        assertEquals();
//    }
    
//    @Test
//    public void hello() {
//        assertEquals(haluttuArvo, todellinenArvo, tarkkuus);
//    }
    
}
