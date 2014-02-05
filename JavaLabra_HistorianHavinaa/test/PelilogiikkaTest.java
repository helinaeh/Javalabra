import Logiikka.Pelilogiikka;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *Testiluokka, joka testaa Pelilogiikan toimivuutta
 * 
 * @author Helinä Hakala
 */
public class PelilogiikkaTest {
    
    Pelilogiikka peli;
    double tarkkuus = 0.01;
    
    public PelilogiikkaTest() {
    }
    
    @Before
    public void setUp() {
        peli = new Pelilogiikka();
    }
    
    @Test
    public void uudenPelilogiikanParametriMaaraaKyseltavienKysymystenMaaran() {
        assertEquals(5, peli.getKysymystenMaara(), tarkkuus);
    }
    
    @Test
    public void uudenPelilogiikanParametriMaaraaKyseltavienKysymystenMaaran2() {
        peli = new Pelilogiikka(17);
        assertEquals(17, peli.getKysymystenMaara(), tarkkuus);
    }
    
    @Test
    public void uudenPelilogiikanParametriMaaraaKyseltavienKysymystenMaaran3() {
        peli = new Pelilogiikka(2000);
        assertEquals(2000, peli.getKysymystenMaara(), tarkkuus);
    }
    
    @Test
    public void pelieraSujuuOngelmittaPienellaKysymystenMaaralla() {
        peli.aloitaPeli();
        while(peli.seuraavaanKysymykseen()) {
            String tapahtuma = peli.getTapahtuma();
            String vuosi = peli.getVuosiluku();
            String vastaus = "1290";
            peli.tarkistaVastaus(vastaus);
        }
    }
    
    @Test
    public void pelieraSujuuOngelmittaSuurellaKysymystenMaaralla() {
        peli = new Pelilogiikka(2000);
        peli.aloitaPeli();
        while(peli.seuraavaanKysymykseen()) {
            String tapahtuma = peli.getTapahtuma();
            String vuosi = peli.getVuosiluku();
            String vastaus = "1290";
            peli.tarkistaVastaus(vastaus);
        }
    }
    
    @Test
    public void kunArvotaanVuosilukuvaihtoehdotNiitaOnNelja() {
        peli.aloitaPeli();
        String[] taulukko = peli.arvoVaihtoehdot();
        assertEquals(4, taulukko.length, tarkkuus);
    }
    
    @Test
    public void kunArvotaanVuosilukuvaihtoehdotKaikkiOvatEriVuosiluku() {
        peli.aloitaPeli();
        String[] taulukko = peli.arvoVaihtoehdot();
        boolean onkoErit = true;
        ulko: for (int i = 0; i < 3; i++) {
            for (int j = i+1; j < 4; j++) {
                if (taulukko[i].equals(taulukko[j])) {
                    onkoErit = false;
                    break ulko;
                }
            }
        }
        assertEquals(true, onkoErit);
    }
    
    @Test
    public void kunSiirrytaanSeuraavaanKysymykseenJarjestyslukuKasvaa1() {
        peli.seuraavaanKysymykseen();
        assertEquals(1, peli.getJarjestysluku(), tarkkuus);
    }
    
    @Test
    public void kunSiirrytaanSeuraavaanKysymykseenJarjestyslukuKasvaa2() {
        for (int i = 0; i < 5; i++) {
            peli.seuraavaanKysymykseen();
        }
        assertEquals(5, peli.getJarjestysluku(), tarkkuus);
    }
    
    @Test
    public void kunSiirrytaanSeuraavaanKysymykseenEnemmanKuinKyseltavaaOnJarjestyslukuEiKasvaLiikaa() {
        for (int i = 0; i < 7; i++) {
            peli.seuraavaanKysymykseen();
        }
        assertEquals(5, peli.getJarjestysluku(), tarkkuus);
    }
    
}
