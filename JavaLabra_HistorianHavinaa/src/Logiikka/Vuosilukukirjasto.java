package Logiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Toistoharjoittelupelin kirjasto-ohjelma, joka hakee oikean tiedoston vuosilukuineen
 * 
 * @author Helinä Hakala
 */
public class Vuosilukukirjasto {
    
    private static Scanner lukija = new Scanner(System.in);
    
    private File tiedosto;
    private Scanner syottotiedosto;
    private ArrayList<String[]> vuosilukuLista;
    private int rivit;
    
    /**
     * Konstruktori, joka luo Vuosilukukirjasto-olion
     */
    public Vuosilukukirjasto() {
        vuosilukuLista = new ArrayList<String[]>();
    }
    
    /**
     * Käynnistää vuosilukujen haun halutusta tiedostosta
     * 
     * @param kirjanLuku annetaan parametrina vuosiluvut sisältävät tiedoston nimi
     * @return palauttaa ArrayListin, johon kaikki tiedoston vuosiluvut on tallennettu
     * @throws FileNotFoundException 
     */
    public ArrayList run(String kirjanLuku) throws FileNotFoundException {
        
        if (!haeTiedosto(kirjanLuku)) {
            System.exit(0);
        }
        syottotiedosto = new Scanner(tiedosto, "UTF-8");
        rivit = laskeRivit();
        syottotiedosto = new Scanner(tiedosto, "UTF-8");
        
        while (syottotiedosto.hasNextLine()) {
            String vuosilukuja = syottotiedosto.nextLine();
            vuosilukuLista.add(vuosilukuja.split("\t"));
        }
        
        syottotiedosto.close();
        return vuosilukuLista;
        
    }
    
    /**
     * Tutkii, löytyykö tiedosto, josta vuosiluvut tulee hakea
     * 
     * @param kirjanLuku parametrina annetaan tiedoston nimi
     * @return palauttaa totuusarvon siitä, löytyikö tiedosto
     * @throws FileNotFoundException 
     */
    private boolean haeTiedosto(String kirjanLuku) throws FileNotFoundException {
        
        tiedosto = new File("Tiedostot/"+kirjanLuku);
        if (!tiedosto.exists()) {
            System.out.println("Tiedostoa " + kirjanLuku + " ei löydy!");
            return false; // keskeytetään kaikki!
        }
        return true;
    }
    
    /**
     * Laskee tiedoston rivien (ja vuosilukujen) määrän
     * 
     * @return palauttaa rivien määrän
     */
    private int laskeRivit() {
        int riveja = 0;
        while (syottotiedosto.hasNextLine()) {
            syottotiedosto.nextLine();
            riveja += 1;
        }
        return riveja;
    }
    
    /**
     * Hakee vuosilukuLista-ArrayListin
     * 
     * @return palauttaa vuosilukuListan
     */
    public ArrayList getVuosilukulista() {
        return vuosilukuLista;
    }
    
    /**
     * Tutkii, montako riviä vuosilukutiedostossa on
     * 
     * @return palauttaa vuosilukutiedoston rivien määrän
     */
    public int getTiedostonRivit() {
        return rivit;
    }
    
}