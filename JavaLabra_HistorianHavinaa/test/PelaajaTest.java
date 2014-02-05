import Logiikka.Pelaaja;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *Testiluokka, joka testaa Pelaaja-luokan toimivuutta
 * 
 * @author Helinä Hakala
 */
public class PelaajaTest {
    
    double tarkkuus = 0.01;
    Pelaaja pelaaja;
    
    public PelaajaTest() {
    }
    
    @Before
    public void setUp() {
        pelaaja = new Pelaaja("Testi");
    }
    
    @Test
    public void kunUusiPelaajaLuodaanParametrinNimiTallentuuOlioon() {
        assertEquals("Testi", pelaaja.getNimi());
    }

    @Test
    public void kunUusiPelaajaLuodaanKysytytKysymyksetOnNolla() {
        assertEquals(0, pelaaja.getKysytyt(), tarkkuus);
    }
    
    @Test
    public void kunUusiPelaajaLuodaanOikeinVastatutOnNolla() {
        assertEquals(0, pelaaja.getOikeat(), tarkkuus);
    }
    
    @Test
    public void kunPelaajaltaKysytaanVuosilukujaYhteensaKysytytKasvavat() {
        pelaaja.kasvataKysyttyjaVuosilukuja(5);
        assertEquals(5, pelaaja.getKysytyt(), tarkkuus);
    }
    
    @Test
    public void kunPelaajaVastaaOikeinOikeinVastatutKasvavat() {
        
        pelaaja.kasvataOikeinVastattuja(3);
        assertEquals(3, pelaaja.getOikeat(), tarkkuus);
    }
    
    @Test
    public void kunPelaajaOlionTallennusOnnistuuNiinTrue() {
        boolean onnistuiko = false;
        try {
            onnistuiko = pelaaja.tallennaPelaaja(pelaaja, pelaaja.getNimi());
        } catch (IOException ex) {
            Logger.getLogger(PelaajaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(true, onnistuiko);
    }
    
    @Test
    public void pelaajaOlioVoidaanNoutaaTiedostosta() {
        pelaaja = null;
        try {
            pelaaja = Pelaaja.noudaPelaaja("Testi");
        } catch (IOException ex) {
            Logger.getLogger(PelaajaTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PelaajaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(pelaaja);
    }
    
    @Test
    public void getNimiPalauttaaPelaajanNimen() {
        assertEquals("Testi", pelaaja.getNimi());
    }
    
    @Test
    public void getOikeatPalauttaaOikeanMaaranOikeinVastattujaKunOikeitaOnNolla() {
        pelaaja.kasvataOikeinVastattuja(0);
        assertEquals(0, pelaaja.getOikeat(), tarkkuus);
    }
    
    @Test
    public void getOikeatPalauttaaOikeanMaaranOikeinVastattujaKunOikeitaOnViisi() {
        pelaaja.kasvataOikeinVastattuja(5);
        assertEquals(5, pelaaja.getOikeat(), tarkkuus);
    }
    
    @Test
    public void getKysytytPalauttaaOikeanMaaranYhteensaKysyttyjaKunNiitaOnNolla() {
        pelaaja.kasvataKysyttyjaVuosilukuja(0);
        assertEquals(0, pelaaja.getKysytyt(), tarkkuus);
    }
    
    @Test
    public void getKysytytPalauttaaOikeanMaaranYhteensaKysyttyjaKunNiitaOnNelja() {
        pelaaja.kasvataKysyttyjaVuosilukuja(4);
        assertEquals(4, pelaaja.getKysytyt(), tarkkuus);
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
