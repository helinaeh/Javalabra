package Logiikka;

import java.io.*;

/**
 *Toistoharjoittelupelin pelaaja-luokka, jonka olioihin tallentuvat pelaajan tiedot
 * 
 * @author Helinä Hakala
 */
public class Pelaaja implements Serializable {
    
    private String nimi;
    private int oikeat;
    private int yhteensaKysytyt;
    private static File tiedosto;
    
    /**
     * konstruktori, joka luo Pelaaja-olion
     */
    public Pelaaja(String nimi) {
            this.nimi = nimi;
            oikeat = 0;
            yhteensaKysytyt = 0;
    }
    
    /**
     * Tutkii, löytyykö Pelaaja-tiedosto
     * 
     * @param nimi Parametrina annetaan nimi, jolla Pelaaja-tiedostoa etsitään
     * @return palauttaa totuusarvon siitä, löytyikö Pelaaja-tiedosto
     */
    public static boolean etsiPelaaja(String nimi) {
        tiedosto = new File("Pelaajat/" + nimi);
        if (!tiedosto.exists()) {
            System.out.println("Pelaajaa " + nimi + " ei löydy.");
            return false;
        }
        return true;
    }
    
    /**
     * Hakee halutun Pelaaja-olion binääritiedostosta
     * 
     * @param nimi Parametrina noudettavan Pelaajan nimi
     * @return palauttaa noudetun Pelaaja-olion
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static Pelaaja noudaPelaaja(String nimi) throws IOException, ClassNotFoundException {
        
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("Pelaajat/" + nimi);
        } catch (FileNotFoundException e) {
            System.out.println("Tiedostoa " + nimi + " ei löydy.");
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            System.out.println("Lukeminen epäonnistui: " + e.toString());
        }
        
        Pelaaja plj = (Pelaaja) ois.readObject();

	ois.close();
        
        return plj;
    }
    
    /**
     * Tallentaa Pelaaja-olion binääritiedostoon
     * 
     * @param pelaaja Ensimmäisenä parametrina tallennettava Pelaaja-olio
     * @param nimi Toisena parametrina nimi, jolla Pelaaja-olio tallennetaan
     * @return palauttaa totuusarvon siitä, onnistuiko tallennus
     * @throws IOException
     */
    public boolean tallennaPelaaja(Pelaaja pelaaja, String nimi) throws IOException {
        
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("Pelaajat/" + nimi);
        } catch (FileNotFoundException e) {
            System.out.println("Ongelma tallennuksen kanssa.");
            return false;
        }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            System.out.println("Lukeminen epäonnistui: " + e.toString());
            return false;
        }
        
        oos.writeObject(pelaaja);
        
        oos.close();
        
        return true;
    }
    
    /**
     * Kasvattaa Pelaaja-olion oikein vastattujen vuosilukujen määrää
     */
    public void kasvataOikeinVastattuja(int yhteensa) {
        for (int i = 0; i < yhteensa; i++) {
            oikeat++;
        }
    }
    
    /**
     * Kasvattaa Pelaaja-olion kaikkien vastattujen vuosilukujen määrää
     */
    public void kasvataKysyttyjaVuosilukuja(int yhteensa) {
        for (int i = 0; i < yhteensa; i++) {
            yhteensaKysytyt++;
        }
    }
    
    /**
     * Hakee Pelaaja-olion nimen
     * 
     * @return palauttaa nimen
     */
    public String getNimi() {
        return nimi;
    }
    
    /**
     * Hakee Pelaaja-olion oikein vastattujen vuosilukujen määrän
     * 
     * @return palauttaa oikein vastattujen vuosilukujen määrän
     */
    public int getOikeat() {
        return oikeat;
    }
    
    /**
     * Hakee määrän, kuinka monta vuosilukua pelaajalta on yhteensä kysytty
     * 
     * @return palauttaa yhteensä kysyttyjen vuosilukujen määrän
     */
    public int getKysytyt() {
        return yhteensaKysytyt;
    }
    
}
