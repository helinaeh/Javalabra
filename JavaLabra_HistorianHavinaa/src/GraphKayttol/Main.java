package GraphKayttol;

import javax.swing.JFrame;

/**
 * Pääohjelma, joka käynnistää pelin
 * 
 * @author Helinä Hakala
 */
public class Main {
    
    /**
     * Luokan päämetodi, joka käynnistää pelin
     * 
     * @param args 
     */
    public static void main(String args[]) {
        GraafinenKayttol ikkuna = new GraafinenKayttol();
        ikkuna.setTitle("Historian havinaa");
        ikkuna.pack();
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setVisible(true);
    }
    
}
