package Logiikka;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Toistoharjoittelupelin toiminnallisuus
 * 
 * @author Helinä Hakala
 */
public class Pelilogiikka {

    private ArrayList<String[]> vuosilukuLista;
    private ArrayList<String[]> kyseltavat;
    private String kirjanLuku;
    private int kysymystenMaara;
    private int kysytyt;
    private int oikeat;
    private int oikeaVaihtoehto;
    private int tapahtumanJarjNro;

    /**
     * Konstruktori, joka luo Pelilogiikka-olion
     * 
     * @param kirjanLuku käytettävän vuosilukutiedoston nimi
     * @param vuosilukuja määrä, kuinka monta vuosilukua pelaajalta kysytään
     */
    public Pelilogiikka(String kirjanLuku, int vuosilukuja) {
        //konstruktori
        this.kirjanLuku = kirjanLuku;
        kysymystenMaara = vuosilukuja;
        kyseltavat = new ArrayList<String[]>();
        oikeat = 0;
        tapahtumanJarjNro = 0;
    }
    
    /**
     * Konstruktori, joka luo Pelilogiikka-olion
     * 
     * @param vuosilukuja määrä, kuinka monta vuosilukua pelaajalta kysytään
     */
    public Pelilogiikka(int vuosilukuja) {
        this("Testiluku", vuosilukuja);
    }
    
    /**
     * Konstruktori, joka luo Pelilogiikka-olion
     */
    public Pelilogiikka() {
        this("Testiluku", 5);
    }
    
    /**
     * Käynnistää vuosilukujen kyselypelin
     * 
     * @throws FileNotFoundException 
     */
    public void aloitaPeli() {

        Vuosilukukirjasto vuodet = new Vuosilukukirjasto();
        try {
            vuosilukuLista = vuodet.run(kirjanLuku);
        } catch (FileNotFoundException e) {
            System.out.println("Tiedostoa " + kirjanLuku + " ei löydy.");
        }

        arvoVuosiluvut();

    }

    /**
     * Arpoo satunnaisessa järjestyksessä tietystä tiedostosta vuosilukuja
     */
    private void arvoVuosiluvut() {
        for (int i = 0; i < Math.min(kysymystenMaara, vuosilukuLista.size()); i++) {
            int arvottuLuku = (int) (vuosilukuLista.size() * Math.random());
            if (!kyseltavat.contains(vuosilukuLista.get(arvottuLuku))) {
                kyseltavat.add(vuosilukuLista.get(arvottuLuku));
            }
            else {
                i -= 1;
            }
        }
    }
    
    /**
     * Arpoo vuosilukuvaihtoehdot, joista pelaaja valitsee vastauksen
     * 
     * @return Palauttaa String-taulukon, jonne oikea vuosiluku on tallennettu kolmen vaihtoehdon kanssa
     */
    public String[] arvoVaihtoehdot() {
        ArrayList<String> taulukko = new ArrayList<String>();
        taulukko.add(getVuosiluku());
        while (taulukko.size() < 4) {
            int luku = (int) (vuosilukuLista.size() * Math.random());
            if (!taulukko.contains(vuosilukuLista.get(luku)[0])) {
                taulukko.add(vuosilukuLista.get(luku)[0]);
            }
        }
        Collections.shuffle(taulukko);
        return taulukko.toArray(new String[4]);
    }
    
    /**
     * Tarkistaa, onko pelaajan antama vuosiluku oikea vastaus
     * 
     * @param vuosi Parametrina oikea vuosiluku
     * @param vastaus Parametrina pelaajan vastaus
     * @return Palauttaa totuusarvon siitä, vastasiko pelaaja oikein
     */
    public boolean tarkistaVastaus(String vastaus) {
        kysytyt += 1;
        if (vastaus.equals(getVuosiluku())) {
            oikeat += 1;
            return true;
        }
        return false;
    }
    
    /**
     * Siirtyy seuraavaan tapahtumaan, jonka vuosilukua kysellään
     * 
     * @return Palauttaa totuusarvon siitä, onko seuraavaa kysymystä jäljellä
     */
    public boolean seuraavaanKysymykseen() {
        if (tapahtumanJarjNro == kysymystenMaara) {
            return false;
        }
        tapahtumanJarjNro++;
        return true;
    }
    
    /**
     * Hakee kyseisen tapahtuman järjestysnumeron
     * 
     * @return Palauttaa tapahtuman järjestysnumeron
     */
    public int getJarjestysluku() {
        return tapahtumanJarjNro;
    }
    
    /**
     * Hakee seuraavan arvotun tapahtuman
     * 
     * @param i Parametri kertoo, monesko arvottu tapahtuma kysytään
     * @return Palauttaa seuraavan tapahtuman
     */
    public String getTapahtuma() {
        return kyseltavat.get(tapahtumanJarjNro % kyseltavat.size())[1];
    }
    
    /**
     * Hakee seuraavan arvotun vuosiluvun
     * 
     * @param i Parametri kertoo, monesko arvottu vuosiluku haetaan
     * @return Palauttaa seuraavan vuosiluvun
     */
    public String getVuosiluku() {
        return kyseltavat.get(tapahtumanJarjNro % kyseltavat.size())[0];
    }
    
    /**
     * Hakee pelierässä kysyttyjen vuosilukujen määrän
     * 
     * @return Palauttaa kysyttyjen vuosilukujen määrän
     */
    public int getKysytyt() {
        return kysytyt;
    }
    
    /**
     * Hakee pelierässä oikein vastattujen vuosilukujen määrän
     * 
     * @return Palauttaa oikein vastattujen vuosilukujen määrän
     */
    public int getOikeat() {
        return oikeat;
    }
    
    /**
     * Hakee, kuinka monta vuosilukua pelierässä on tarkoitus kysyä
     * 
     * @return Palauttaa kysyttävien vuosilukujen määrän
     */
    public int getKysymystenMaara() {
        return kysymystenMaara;
    }
    
    /**
     * Hakee ArrayListin, jonne arvotut vuosiluvut on tallennettu
     * 
     * @return Palauttaa ArrayListin, jonne arvotut vuosiluvut on tallennettu
     */
    public ArrayList getArvotutVuosiluvut() {
        return kyseltavat;
    }
    
    /**
     * Hakee sen arvon, mikä vastaa oikean vastauksen indeksiä vaihtoehtojen joukossa
     * 
     * @return Palauttaa int-arvon (taulukon indeksin)
     */
    public int getOikeaVaihtoehto() {
        return oikeaVaihtoehto;
    }
    
}
