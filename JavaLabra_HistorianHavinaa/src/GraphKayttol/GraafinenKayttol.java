package GraphKayttol;

import Logiikka.Pelaaja;
import Logiikka.Pelilogiikka;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Helinä Hakala
 */
public class GraafinenKayttol extends JFrame {

    // käyttöliittymäkentät:
    private JTextField otsikko1, otsikko2, alaviite, valikonOtsikko1, valikonOtsikko2,
            valikkokentta1, valikkokentta2, syottorivi;
    private JTextArea tapahtuma;
    private JButton aloitaNappi, lopetaNappi, ok, tietoaAiheista;
    private JLayeredPane paneeli;
    private BufferedImage kuva;
    private Font tekstiFontti, isompiFontti, otsikkoFontti, alkuFontti;
    private JRadioButton kohta1, kohta2, kohta3, kohta4;
    private ButtonGroup vaihtoehdot;
    private Pelilogiikka logiikka;
    private String[] vaihtoehtoLista;
    private int painalluksia, okPainettu;
    private Pelaaja pelaaja;
    private JComboBox pudotusvalikko;
    private JPanel valikkoPaneeli;

    /**
     * Graafisen käyttöliittymän konstruktori
     */
    public GraafinenKayttol() {

        luoPaneeli();
        lisaaKuvaTaustaksi("Tiedostot/avausikkuna.png");
        maaritteleFontit();
        
        maaritteleKaikkiMuuttujat();
        
        
        otsikko1 = new JTextField();
        alaviite = new JTextField();
        luoTekstikentta(otsikko1, "Historian Havinaa", otsikkoFontti);
        luoTekstikentta(alaviite, "Helinä Hakala", alkuFontti);
        otsikko1.setForeground(Color.WHITE);

        aloitaNappi = new JButton("ALOITA"); //aloitaNappi.setFont(nappulaFontti);
        lopetaNappi = new JButton("LOPETA");
        painalluksia = 0;

        aloitaNappi.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent action) {
                        if (painalluksia == 0) {
                            avaaEkaValikko();
                            painalluksia++;
                        }
                        else if (painalluksia == 1) {
                            String nimi = syottorivi.getText();
                            boolean loytyiko = Pelaaja.etsiPelaaja(nimi);
                            if (!loytyiko) {
                                int luodaankoUusi = JOptionPane.showConfirmDialog(null, "Lisätäänkö uusi pelaaja " + nimi + "?", "Uusi pelaaja", JOptionPane.YES_NO_OPTION);
                                if (luodaankoUusi == JOptionPane.YES_OPTION) {
                                    pelaaja = new Pelaaja(nimi);
                                    painalluksia++;
                                    avaaTokaValikko();
                                }
                                else {
                                    avaaEkaValikko();
                                }
                            }
                            else {
                                int luodaankoUusi = JOptionPane.showConfirmDialog(null, "Jatketaanko pelaajan " + nimi + " tallennusta?", "Vanha pelaaja", JOptionPane.YES_NO_OPTION);
                                if (luodaankoUusi == JOptionPane.YES_OPTION) {
                                    try {
                                        pelaaja = Pelaaja.noudaPelaaja(nimi);
                                    } catch (IOException e) {
                                        System.out.println("Lukeminen epäonnistui: " + e.toString());
                                    } catch (ClassNotFoundException e) {
                                        System.out.println("Luokkaa ei löydy.");
                                    }
                                    painalluksia++;
                                    avaaTokaValikko();
                                }
                                else {
                                    avaaEkaValikko();
                                }
                            }
                        }
                        else if (painalluksia == 2) {
                            int montakoVuosilukua = Integer.parseInt(JOptionPane.showInputDialog(null, "Montako vuosilukua kysellään?", ""));
                            logiikka = new Pelilogiikka((String) pudotusvalikko.getSelectedItem(), montakoVuosilukua);
                            peliEra();
                        }
                    }
                });

        lopetaNappi.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent action) {
                        if (painalluksia == 0) {
                            System.exit(0);
                        }
                        else if (painalluksia == 1) {
                            painalluksia--;
                            aloitusNaytto();
                        }
                        else if (painalluksia == 2) {
                            painalluksia--;
                            int tallennetaanko = JOptionPane.showConfirmDialog(null,
                                            "Haluatko tallentaa pelaajan " + pelaaja.getNimi() + " pelin?",
                                            "Tallennetaanko", JOptionPane.YES_NO_OPTION);
                                    if (tallennetaanko == JOptionPane.YES_OPTION) {
                                        try {
                                            pelaaja.tallennaPelaaja(pelaaja, pelaaja.getNimi());
                                        } catch (IOException e) {
                                            System.out.println("Tallentaminen epäonnistui: " + e.toString());
                                        }
                                    }
                            avaaEkaValikko();
                        }
                        
                    }
                });

        lisaaTekstikentta(otsikko1, 250, 20, 750, 100);
        //lisaaTekstikentta(alaviite, 450, 640, 100, 30);

        lisaaNappula(aloitaNappi, 450, 590, 87, 30);
        lisaaNappula(lopetaNappi, 450, 630, 87, 30);
        
    }
    
    public void aloitusNaytto() {
        valikonOtsikko1.setVisible(false);
        valikkokentta1.setVisible(false);
        valikonOtsikko2.setVisible(false);
        valikkokentta2.setVisible(false);
        syottorivi.setVisible(false);
        pudotusvalikko.setVisible(false);
        otsikko2.setVisible(false);
        tapahtuma.setVisible(false);
        ok.setVisible(false);
        pudotusvalikko.setVisible(false);
        valikkoPaneeli.setVisible(false);
        tietoaAiheista.setVisible(false);
        
        lisaaKuvaTaustaksi("Tiedostot/avausikkuna.png");
        aloitaNappi.setText("ALOITA");
        lopetaNappi.setText("LOPETA");
        
        otsikko1.setVisible(true);
        alaviite.setVisible(true);
        aloitaNappi.setVisible(true);
        lopetaNappi.setVisible(true);
        
        this.repaint();
    }

    public void avaaEkaValikko() {
        otsikko2.setVisible(false);
        valikonOtsikko2.setVisible(false);
        valikkokentta2.setVisible(false);
        tapahtuma.setVisible(false);
        ok.setVisible(false);
        valikkoPaneeli.setVisible(false);
        pudotusvalikko.setVisible(false);
        tietoaAiheista.setVisible(false);
        
        lisaaKuvaTaustaksi("Tiedostot/valikkoikkuna1.png");
        luoTekstikentta(valikonOtsikko1, "KÄYTTÄJÄASETUKSET", isompiFontti);
        luoTekstikentta(valikkokentta1, "Syötä käyttäjän nimi!", tekstiFontti);
        luoMuokattavaRivi(syottorivi, "", tekstiFontti);
        valikonOtsikko1.setForeground(Color.darkGray);
        valikkokentta1.setForeground(Color.darkGray);
        syottorivi.setForeground(Color.darkGray);
        lisaaTekstikentta(valikonOtsikko1, 330, 200, 400, 50);
        lisaaTekstikentta(valikkokentta1, 350, 300, 200, 50);
        lisaaTekstikentta(syottorivi, 350, 400, 250, 30);

        aloitaNappi.setText("OK");
        lopetaNappi.setText("TAKAISIN");
        
        valikonOtsikko1.setVisible(true);
        valikkokentta1.setVisible(true);
        syottorivi.setVisible(true);
        aloitaNappi.setVisible(true);
        lopetaNappi.setVisible(true);
        otsikko1.setVisible(true);
        alaviite.setVisible(true);
        
        this.repaint();
        
        remove(otsikko2);
        remove(valikonOtsikko1);
        remove(valikkokentta1);
        remove(tapahtuma);
        remove(ok);
        remove(valikkoPaneeli);
        remove(syottorivi);
        remove(valikonOtsikko2);
        remove(valikkokentta2);
        remove(aloitaNappi);
        remove(lopetaNappi);
        remove(otsikko1);
        remove(alaviite);
        remove(pudotusvalikko);
        remove(tietoaAiheista);
        
        add(valikonOtsikko1);
        add(valikkokentta1);
        add(aloitaNappi);
        add(lopetaNappi);
        add(otsikko1);
        add(alaviite);
        add(syottorivi);
    }

    public void avaaTokaValikko() {
        otsikko2.setVisible(false);
        valikonOtsikko1.setVisible(false);
        valikkokentta1.setVisible(false);
        tapahtuma.setVisible(false);
        ok.setVisible(false);
        valikkoPaneeli.setVisible(false);
        syottorivi.setVisible(false);
        
        lisaaKuvaTaustaksi("Tiedostot/valikkoikkuna2.png");
        
        luoTekstikentta(valikonOtsikko2, "Valikko", isompiFontti);
        luoTekstikentta(valikkokentta2, "Kyselyalue: ", tekstiFontti);
        
        pudotusvalikko = new JComboBox();
        pudotusvalikko.addItem("Testiluku");
        pudotusvalikko.addItem("Luku1");
        pudotusvalikko.addItem("Luku2");
        pudotusvalikko.addItem("Luku3");
        pudotusvalikko.addItem("Luku4");
        pudotusvalikko.addItem("Luku5");
        pudotusvalikko.setBounds(500, 310, 200, 30);
        add(pudotusvalikko, JLayeredPane.PALETTE_LAYER);
        
        valikonOtsikko2.setForeground(Color.darkGray);
        valikkokentta2.setForeground(Color.darkGray);
        lisaaTekstikentta(valikonOtsikko2, 330, 200, 400, 50);
        lisaaTekstikentta(valikkokentta2, 350, 300, 200, 50);
        
        tietoaAiheista = new JButton("Aluetietoa");
        lisaaNappula(tietoaAiheista, 370, 420, 100, 30);
        
        valikonOtsikko2.setVisible(true);
        valikkokentta2.setVisible(true);
        aloitaNappi.setVisible(true);
        lopetaNappi.setVisible(true);
        otsikko1.setVisible(true);
        alaviite.setVisible(true);
        pudotusvalikko.setVisible(true);
        tietoaAiheista.setVisible(true);
        
        JOptionPane.showMessageDialog(null, "Pelaajan " + pelaaja.getNimi()
                        + " oikein vastatut vuosiluvut: "
                        + pelaaja.getOikeat() + "/" + pelaaja.getKysytyt() + " .",
                pelaaja.getNimi(), JOptionPane.INFORMATION_MESSAGE);
        
        final String tietoa = lisaaTiedotLuvuista();
        
        tietoaAiheista.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent action) {
                        JOptionPane.showMessageDialog(null, tietoa, "Tietoa luvuista",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
        );
        
        this.repaint();
        
        remove(otsikko2);
        remove(valikonOtsikko1);
        remove(valikkokentta1);
        remove(tapahtuma);
        remove(ok);
        remove(valikkoPaneeli);
        remove(syottorivi);
        remove(valikonOtsikko2);
        remove(valikkokentta2);
        remove(aloitaNappi);
        remove(lopetaNappi);
        remove(otsikko1);
        remove(alaviite);
        remove(pudotusvalikko);
        remove(tietoaAiheista);
        
        add(valikonOtsikko2);
        add(valikkokentta2);
        add(aloitaNappi);
        add(lopetaNappi);
        add(otsikko1);
        add(alaviite);
        add(pudotusvalikko);
        add(tietoaAiheista);
    }
    
    public void peliEra() {
        
        paneeli = new JLayeredPane();
        paneeli.setPreferredSize(new Dimension(1000, 680));
        setContentPane(paneeli);
        
        logiikka.aloitaPeli();
        
        //asetetaan tarpeettomat kentät läpinäkyviksi:
        otsikko1.setVisible(false);
        aloitaNappi.setVisible(false);
        lopetaNappi.setVisible(false);
        valikonOtsikko1.setVisible(false);
        valikkokentta1.setVisible(false);
        valikonOtsikko2.setVisible(false);
        valikkokentta2.setVisible(false);
        syottorivi.setVisible(false);
        pudotusvalikko.setVisible(false);
        
        //paneeli = new JLayeredPane();
        //paneeli.setPreferredSize(new Dimension(1000, 680));
        //setContentPane(paneeli);

        tekstiFontti = new Font("Times New Roman", Font.PLAIN, 20);
        //otsikkoFontti = new Font("cmmi10", Font.PLAIN, 40); //"Linux
        otsikkoFontti = new Font("Monotype Corsiva", Font.PLAIN, 40); //Windows
        
        otsikko2 = new JTextField();
        otsikko2.setFont(otsikkoFontti);
        otsikko2.setForeground(Color.WHITE);
        otsikko2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        otsikko2.setEditable(false);
        otsikko2.setText("Historian havinaa");

        tapahtuma = new JTextArea();
        tapahtuma.setFont(tekstiFontti);
        tapahtuma.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        tapahtuma.setEditable(false);  // kirjoituskielto käyttäjälle
        tapahtuma.setText("Peli alkaa!"); //aloitusteksti pelille

        //Stringeiksi pitäisi hakea ohjelmasta vuosilukuvaihtoehdot!
        kohta1 = new JRadioButton();
        kohta2 = new JRadioButton();
        kohta3 = new JRadioButton();
        kohta4 = new JRadioButton();

        vaihtoehdot = new ButtonGroup();
        vaihtoehdot.add(kohta1);
        vaihtoehdot.add(kohta2);
        vaihtoehdot.add(kohta3);
        vaihtoehdot.add(kohta4);
        kohta1.setOpaque(false);
        kohta2.setOpaque(false);
        kohta3.setOpaque(false);
        kohta4.setOpaque(false);

        ok = new JButton("OK");
        ok.setSelected(true);
        okPainettu = 0;
        
        //tapahtumakuuntelija:
        ok.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent action) {
                        //kun painetaan ok, välitetään vastaus pelilogiikalle, TOTEUTA! 
                        if (!ok.isSelected()) {
                            onkoVastausOikein();
                        }
                        else {
                            esitaUusiKysymys();     //varmista, että pelaajan on pakko vastata jotakin!
                        }
                        
                        if (okPainettu == logiikka.getKysymystenMaara()) {
                            //kerrotaan pelaajan yhteispisteet ja poistutaan peli-ikkunasta
                            JOptionPane.showMessageDialog(null, "Peli päättyy! Oikeita vuosilukuja kertyi "
                                    + logiikka.getOikeat() + "/" + logiikka.getKysytyt() + ".", "Peli päättyy", JOptionPane.INFORMATION_MESSAGE);
                            pelaaja.kasvataKysyttyjaVuosilukuja(logiikka.getKysytyt());
                            pelaaja.kasvataOikeinVastattuja(logiikka.getOikeat());
                            avaaTokaValikko();
                        }
                    }
                });

        //lisätään taustakuva
        lisaaKuvaTaustaksi("Tiedostot/peliteema.png");
        

        otsikko2.setOpaque(false);
        otsikko2.setBounds(450, 50, 550, 100);
        add(otsikko2, JLayeredPane.PALETTE_LAYER);

        ok.setBounds(465, 450, 70, 40);
        add(ok, JLayeredPane.PALETTE_LAYER, 0);
        
        tapahtuma.setOpaque(false);
        tapahtuma.setLineWrap(true);
        tapahtuma.setWrapStyleWord(true);
        tapahtuma.setBounds(350, 200, 400, 100);
        add(tapahtuma, JLayeredPane.PALETTE_LAYER);
        
        //asetetaan tarpeelliset kentät näkyviksi:
        otsikko2.setVisible(true);
        tapahtuma.setVisible(true);
        ok.setVisible(true);
        valikkoPaneeli.setVisible(true);
        
        this.repaint();
        
    }
    
    public void onkoVastausOikein() {
        if (kohta1.isSelected()) {
            if (logiikka.tarkistaVastaus(kohta1.getText())) {
                tapahtuma.setText("Oikein! Oikea vastaus oli " + logiikka.getVuosiluku() + ".");
            }
            else {
                tapahtuma.setText("Väärin! Oikea vastaus oli " + logiikka.getVuosiluku() + ".");
            }
        }
        else if (kohta2.isSelected()) {
            if (logiikka.tarkistaVastaus(kohta2.getText())) {
                tapahtuma.setText("Oikein! Oikea vastaus oli " + logiikka.getVuosiluku() + ".");
            }
            else {
                tapahtuma.setText("Väärin! Oikea vastaus oli " + logiikka.getVuosiluku() + ".");
            }
        }
        else if (kohta3.isSelected()) {
            if (logiikka.tarkistaVastaus(kohta3.getText())) {
                tapahtuma.setText("Oikein! Oikea vastaus oli " + logiikka.getVuosiluku() + ".");
            }
            else {
                tapahtuma.setText("Väärin! Oikea vastaus oli " + logiikka.getVuosiluku() + ".");
            }
        }
        else if (kohta4.isSelected()) {
            if (logiikka.tarkistaVastaus(kohta4.getText())) {
                tapahtuma.setText("Oikein! Oikea vastaus oli " + logiikka.getVuosiluku() + ".");
            }
            else {
                tapahtuma.setText("Väärin! Oikea vastaus oli " + logiikka.getVuosiluku() + ".");
            }
        }
        ok.setSelected(true);
        okPainettu++;
    }

    public void esitaUusiKysymys() {
        logiikka.seuraavaanKysymykseen();
        vaihtoehtoLista = logiikka.arvoVaihtoehdot();
        kohta1.setText(vaihtoehtoLista[0]);
        kohta2.setText(vaihtoehtoLista[1]);
        kohta3.setText(vaihtoehtoLista[2]);
        kohta4.setText(vaihtoehtoLista[3]);

        valikkoPaneeli = new JPanel(new GridLayout(0, 1));
        valikkoPaneeli.add(kohta1);
        valikkoPaneeli.add(kohta2);
        valikkoPaneeli.add(kohta3);
        valikkoPaneeli.add(kohta4);
        valikkoPaneeli.setOpaque(false);
        valikkoPaneeli.setBounds(470, 325, 150, 100);
        add(valikkoPaneeli, JLayeredPane.PALETTE_LAYER);

        tapahtuma.setText((logiikka.getJarjestysluku()) + ". " + logiikka.getTapahtuma());  
        vaihtoehdot.clearSelection();
        ok.setSelected(false);
    }
    
    private String lisaaTiedotLuvuista() {
        return "Luku1: Ihmisen oppiaika\nLuku2: Siirtyminen ruoantuotantoon"
                + " 11 000-3 000 vuotta sitten\nLuku3: Verkot ja korkeakulttuurit"
                + " vanhassa maailmassa, 3500 eaa.-200 jaa.\nLuku4: Verkkojen"
                + " kasvu vanhassa maailmassa ja Amerikassa, 200-100 eaa.\nLuku5:"
                + " Tihentyvät verkot, 1000-1500\nLuku6: Maailmanlaajuisen verkon"
                + " kutoutuminen, 1450-1800 TULOSSA!\nLuku7: Vanhat kahleet"
                + " murtuvat, uusi verkko tihentyy, 1750-1914 TULOSSA!\nLuku8:"
                + " Verkon jännitteet: maailma vuodesta 1890 TULOSSA!";
    }

    private void luoPaneeli() {
        paneeli = new JLayeredPane();
        paneeli.setPreferredSize(new Dimension(1000, 680));
        setContentPane(paneeli);
    }

    private void maaritteleFontit() {
        tekstiFontti = new Font("Times New Roman", Font.PLAIN, 20);
        isompiFontti = new Font("Times New Roman", Font.PLAIN, 30);
        //otsikkoFontti = new Font("cmmi10", Font.PLAIN, 70); //linux
        otsikkoFontti = new Font("Monotype Corsiva", Font.PLAIN, 70); //windows
        alkuFontti = new Font("Monotype Corsiva", Font.PLAIN, 16);
    }
    
    private void maaritteleKaikkiMuuttujat() {
        otsikko1 = new JTextField(); otsikko2 = new JTextField(); alaviite = new JTextField();
        valikonOtsikko1 = new JTextField(); valikkokentta1 = new JTextField();
        valikonOtsikko2 = new JTextField(); valikkokentta2 = new JTextField();
        syottorivi = new JTextField();
        tapahtuma = new JTextArea();
        aloitaNappi = new JButton(); lopetaNappi = new JButton(); ok = new JButton();
        tietoaAiheista = new JButton();
        kohta1 = new JRadioButton(); kohta2 = new JRadioButton();
        kohta3 = new JRadioButton(); kohta4 = new JRadioButton();
        vaihtoehdot = new ButtonGroup();
        pudotusvalikko = new JComboBox();
        valikkoPaneeli = new JPanel();
    }

    private void luoTekstikentta(JTextField kentta, String teksti, Font fontti) {
        kentta.setFont(fontti);
        kentta.setForeground(Color.BLACK);
        kentta.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        kentta.setEditable(false);
        kentta.setText(teksti);
    }

    private void lisaaTekstikentta(JTextField kentta,
            int sijLev, int sijKork, int kuvLev, int kuvKork) {
        kentta.setOpaque(false);
        kentta.setBounds(sijLev, sijKork, kuvLev, kuvKork);
        add(kentta, JLayeredPane.PALETTE_LAYER);
        kentta.repaint();
    }

    private void lisaaNappula(JButton nappula, int sijLev, int sijKork, int kuvLev, int kuvKork) {
        nappula.setBounds(sijLev, sijKork, kuvLev, kuvKork);
        add(nappula, JLayeredPane.PALETTE_LAYER, 0);
    }

    private void luoMuokattavaRivi(JTextField kentta, String teksti, Font fontti) {
        kentta.setFont(fontti);
        kentta.setForeground(Color.BLACK);
        kentta.setEditable(true);
        kentta.setText(teksti);
    }

    private void lisaaKuvaTaustaksi(String kuvanReitti) {
        try {
            kuva = ImageIO.read(new File(kuvanReitti));
        } catch (IOException e) {
            System.out.println("Kuvatiedostoa ei löydy.");
        }
        ImageIcon kuvaIcon = new ImageIcon(kuva);
        JLabel picLabel = new JLabel(kuvaIcon);

        picLabel.setPreferredSize(new Dimension(1000, 680));
        picLabel.setBounds(0, 0,
                kuvaIcon.getIconWidth(),
                kuvaIcon.getIconHeight());
        add(picLabel, JLayeredPane.DEFAULT_LAYER, 0); //elementin lisääminen layoutiin
    }

}
