import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

//Adam Tomaszewski 18598
//Laboratorium nr 4 - zadanie 2 (SPECJALNE)

// Klasa generowanie pozwalająca nam w oknie odbiorcy na wygenerowanie par kluczy
class generowanie
{
    BigInteger p_tym, q_tym, fi_tym, e_tym, d_tym, N_tym;
    generowanie()
    {
        Random random = new SecureRandom();

        p_tym = BigInteger.probablePrime(1024,random);
        q_tym = BigInteger.probablePrime(1024,random);

        N_tym = p_tym.multiply(q_tym);

        fi_tym = p_tym.subtract(BigInteger.valueOf(1)).multiply(q_tym.subtract(BigInteger.valueOf(1)));

        e_tym = fi_tym.subtract(BigInteger.valueOf(1));
        while(nwd(e_tym,fi_tym).compareTo(BigInteger.valueOf(1)) != 0)
        {
            e_tym = e_tym.subtract(BigInteger.valueOf(1));
        }

        d_tym = e_tym.modInverse(fi_tym);
    }

    static BigInteger nwd(BigInteger test, BigInteger fi)
    {
        BigInteger temp;

        while(fi.compareTo(BigInteger.valueOf(0)) != 0)
        {
            temp = fi;
            fi = test.mod(fi);
            test = temp;
        }
        return test;
    }
}


// klasa szyfrowanie pozwalająca w oknie szyfrowania na szyfrowanie wiadomości
class Szyfrowanie
{
    BigInteger N, e, mnoznik, wynik_sz, wartosc;

    public String[] getNapis()
    {
        String[] text = {kodowanie.area.getText(), kodowanie.oN.getText(), kodowanie.oe.getText()};
        return text;
    }

    Szyfrowanie()
    {
        String napis[] = getNapis();

        e = new BigInteger(napis[2]);
        N = new BigInteger(napis[1]);

        mnoznik = BigInteger.valueOf(256);
        wartosc = BigInteger.valueOf(0);

        for(int i=0; i<napis[0].length(); i++)
        {
            wartosc = wartosc.multiply(mnoznik).add(BigInteger.valueOf(napis[0].charAt(i)));
        }

        wynik_sz = wartosc;

        wynik_sz = wynik_sz.modPow(e,N);
    }
}

//No i klasa Odszyfrowanie pozwalająca na odszyfrowanie naszej wiadomości
class Odszyfrowanie
{
    BigInteger wynik_de, d, N, mnoznik, wartosc, znak, wynik_sz;
    String napis ="";

    public String[] getNapis()
    {
        String[] text = {kodowanie.zaszyfr.getText(), kodowanie.ld.getText(), kodowanie.lN.getText()};
        return text;
    }

    Odszyfrowanie()
    {
        String[] napisy = getNapis();
        wynik_sz = new BigInteger(napisy[0]);
        d = new BigInteger(napisy[1]);
        N = new BigInteger(napisy[2]);

        wynik_de = wynik_sz.modPow(d,N);

        mnoznik = BigInteger.valueOf(256);
        wartosc = BigInteger.valueOf(0);
        wartosc = wynik_de;

        while(wartosc.compareTo(BigInteger.valueOf(0)) != 0)
        {
            znak = wartosc.mod(mnoznik);
            napis = (char) znak.intValue() + napis;
            wartosc = wartosc.subtract(znak).divide(mnoznik);
        }

    }
}

public class kodowanie extends JFrame implements ActionListener
{
    //utworzenie potrzebnych nam komponentów oraz ustawienie ich w oknie
    // (wiem że trochę chaotycznie, ale sam się nie spodziewałem że tyle tego będzie).

    JFrame okno;
    JPanel szyfr, odszyfr, toolbar, home;
    JButton sz, odsz, szyfruj, odszyfruj, generuj;
    CardLayout c;
    static JTextArea area;
    JTextArea area2;
    static JTextField le, oN, oe,  zaszyfr, lN, ld;
    JTextField oSz;
    JScrollPane scrollable, scrollable2;
    JLabel infoszyfr, podajlicz, podajN, podajd, oddajN, oddaje, oddajSzyfr, odszyfrowane, genE, informacjosz, informacjasz;


    kodowanie()
    {
        okno =  new JFrame("Szyfrowanie i odszyfrowanie RSA");

        szyfr = new JPanel();
        odszyfr = new JPanel();
        toolbar = new JPanel();
        home = new JPanel();

        infoszyfr = new JLabel("Podaj ciąg znaków do zaszyfrowania:");
        podajlicz= new JLabel("Podaj liczbę do odszyfrowania:");
        podajN = new JLabel("N:");
        podajd = new JLabel("d:");
        odszyfrowane = new JLabel("Odszyfrowany ciąg znaków:");
        oddajN = new JLabel("Podaj N od odbiorcy:");
        oddaje = new JLabel("Podaj e od odbiorcy:");
        oddajSzyfr = new JLabel("Szyfr:");
        genE = new JLabel("e:");
        informacjosz = new JLabel();
        informacjasz = new JLabel();

        informacjosz.setForeground(Color.red);
        informacjasz.setForeground(Color.red);

        area = new JTextArea(8,20);
        area2 = new JTextArea(8,20);

        zaszyfr = new JTextField();
        lN = new JTextField();
        ld = new JTextField();
        oN = new JTextField();
        oe = new JTextField();
        oSz = new JTextField();
        le = new JTextField();


        scrollable = new JScrollPane(area);
        scrollable2 = new JScrollPane(area2);

        sz = new JButton("Szyfrowanie");
        odsz = new JButton("Odszyfrowanie");
        szyfruj = new JButton("Szyfruj");
        odszyfruj = new JButton("Odszyfruj");
        generuj = new JButton("Generuj klucze");

        c = new CardLayout();



        zaszyfr.setBounds(300,20,200,30);
        lN.setBounds(300,60,200,30);
        ld.setBounds(300,100,200,30);
        le.setBounds(300,140,200,30);
        oN.setBounds(200,210,200,30);
        oe.setBounds(200,250,200,30);
        oSz.setBounds(200,330,200,30);

        infoszyfr.setBounds(200,20,300,30);
        informacjosz.setBounds(50,80,170,95);
        informacjasz.setBounds(420,315,170,60);
        podajlicz.setBounds(108,20,200,30);
        podajN.setBounds(270,60,20,30);
        podajd.setBounds(270,100,20,30);
        genE.setBounds(270,140,20,30);
        oddajN.setBounds(71,210,120,30);
        oddaje.setBounds(73,250,120,30);
        oddajSzyfr.setBounds(155,330,40,30);
        odszyfrowane.setBounds(220,220,300,30);

        lN.setEditable(false);
        le.setEditable(false);
        ld.setEditable(false);
        oSz.setEditable(false);
        area2.setEditable(false);


        sz.setBounds(175,7,120,25);
        odsz.setBounds(325,7,120,25);
        szyfruj.setBounds(250,290,100,25);
        odszyfruj.setBounds(330,190,100,25);
        generuj.setBounds(180,190,120,25);

        sz.addActionListener(this);
        odsz.addActionListener(this);
        szyfruj.addActionListener(this);
        odszyfruj.addActionListener(this);
        generuj.addActionListener(this);

        scrollable.setVerticalScrollBarPolicy((JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED));
        scrollable2.setVerticalScrollBarPolicy((JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED));

        toolbar.setBounds(0,0,600,40);
        toolbar.setBackground(Color.LIGHT_GRAY);
        toolbar.setLayout(null);

        toolbar.add(sz);
        toolbar.add(odsz);

        odszyfr.setBounds(0,40,600,460);
        odszyfr.setLayout(null);
        szyfr.setBounds(0,40,600,460);
        szyfr.setLayout(null);
        home.setBounds(0,40,600,460);
        home.setLayout(null);

        area.setLineWrap(true);
        area2.setLineWrap(true);

        scrollable.setBounds(100,50,400,150);
        scrollable2.setBounds(100,250,400,150);

        odszyfr.add(podajlicz);
        odszyfr.add(podajN);
        odszyfr.add(podajd);
        odszyfr.add(genE);
        odszyfr.add(zaszyfr);
        odszyfr.add(lN);
        odszyfr.add(ld);
        odszyfr.add(le);
        odszyfr.add(odszyfruj);
        odszyfr.add(generuj);
        odszyfr.add(odszyfrowane);
        odszyfr.add(scrollable2);
        odszyfr.add(informacjosz);



        informacjosz.setVisible(false);
        informacjasz.setVisible(false);


        szyfr.add(infoszyfr);
        szyfr.add(informacjasz);
        szyfr.add(scrollable);
        szyfr.add(szyfruj);
        szyfr.add(oddajN);
        szyfr.add(oddaje);
        szyfr.add(oddajSzyfr);
        szyfr.add(oN);
        szyfr.add(oe);
        szyfr.add(oSz);


        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        home.setLayout(c);
        home.add(szyfr,"1");
        home.add(odszyfr,"2");
        c.show(home, "2");
        okno.add(toolbar);
        okno.add(home);

        okno.setSize(600,500);
        okno.setLayout(null);
        okno.setResizable(false);
        okno.setVisible(true);
    }



    //actionPerformed do actionListenerów
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        if(source == sz)
        {
            c.show(home, "1");
            okno.setTitle("Szyfrowanie");
        }

        if(source == odsz)
        {
            c.show(home, "2");
            okno.setTitle("Odszyfrowanie");
        }

        try
        {
            if (source == szyfruj) {
                Szyfrowanie zaszyfrowane = new Szyfrowanie();

                oSz.setText(String.valueOf(zaszyfrowane.wynik_sz));

                informacjasz.setText("<html>Wygenerowany szyfr przekaż do okna Odszyfrowanie</html>");
                informacjasz.setVisible(true);
            }
        }
        catch (NumberFormatException ev)
        {
            informacjasz.setText("<html> Nie wprowadzono ciągu znaków!</html>");
            informacjasz.setVisible(true);
        }
        try
        {
            if (source == odszyfruj) {
                Odszyfrowanie odszyfrowanie = new Odszyfrowanie();

                area2.setText(String.valueOf(odszyfrowanie.napis));
                generuj.setEnabled(true);
            }
        }
        catch(NumberFormatException ev)
        {
            informacjosz.setText("<html>Nie wprowadzono liczby do odszyfrowania!</html>");
            informacjosz.setVisible(true);
        }


        if(source == generuj)
        {
            generowanie sz = new generowanie();

            lN.setText(String.valueOf(sz.N_tym));
            ld.setText(String.valueOf(sz.d_tym));
            le.setText(String.valueOf(sz.e_tym));

            informacjosz.setText("<html>Wygenerowane klucze e i N przekaż do okna szyfrowania, aby móc zaszyfrować wiadomość." +
                    " Klucz d jest twój, nie ujawniaj go nikomu.</html>");
            informacjosz.setVisible(true);

            //Blokowanie przycisku dodałem z troski o to, żeby nikomu niechcący
            //nie kliknęło się drugi raz "generuj klucze" do czasu odszyfrowania wiadomości :)
            generuj.setEnabled(false);

        }
    }
    //main
    public static void main(String args[])
    {
        new kodowanie();
    }
}