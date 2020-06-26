import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.lang.*;
import java.util.List;

//Adam Tomaszewski 18598
//Laboratorium NR 5 - zadanie 3 (SPECJALNE)

//tworzę klasę losowanie dla wylosowania sześciu liczb oraz ich wypisaniu
class losowanie
{
    public List getNapis()
    {
        String text = Gra.liczby.getText();
        List<String> items = Arrays.asList(text.split("\\s*,\\s*"));
        return items;
    }

    losowanie()
    {
        List items = getNapis();
        int ball[] = new int[49];
        for(int i=0; i<ball.length; i++)
        {
            ball[i] = i+1;
        }
        int temp = 0;
        int j = 0;
        for(int i=0; i<100; i++)
        {
            j = (int)(Math.random()*49);
            temp = ball[0];
            ball[0] = ball[j];
            ball[j] = temp;
        }

        Gra.l1.setText(String.valueOf(ball[0]));
        Gra.l2.setText(String.valueOf(ball[1]));
        Gra.l3.setText(String.valueOf(ball[2]));
        Gra.l4.setText(String.valueOf(ball[3]));
        Gra.l5.setText(String.valueOf(ball[4]));
        Gra.l6.setText(String.valueOf(ball[5]));


        porownaj(items,ball);
    }

    //metoda porównująca liczby wpisane przez użytkownika i te wygenerowane przez program
    static void porownaj(List items, int ball[])
    {
        int licznik = 0;
        for(int i=0; i<6; i++)
        {
            for(int j=0; j<6; j++)
            {
                if(ball[j] == Integer.parseInt(String.valueOf(items.get(i))))
                {
                    licznik++;
                }
            }
        }
        switch (licznik) {
            case 0:
                Gra.wynik.setText("<html><center>Niestety przegrałeś/-aś.<br> " + licznik + " trafień.</html>");
                break;

            case 1:
                Gra.wynik.setText("<html><center>Przegrałeś ale coś trafiłeś/-aś.<br> " + licznik + " trafień.</html>");
                break;

            case 2:
                Gra.wynik.setText("<html><center>Przegrałeś ale coś trafiłeś/-aś.<br> " + licznik + " trafień.</html>");
                break;

            case 3:
                Gra.wynik.setText("<html><center>Przegrałeś ale przynajmniej byłeś/-aś w połowie.<br> " + licznik + " trafień.</html>");
                break;

            case 4:
                Gra.wynik.setText("<html><center>Przegrałeś ale było już blisko.<br> " + licznik + " trafień.</html>");
                break;

            case 5:
                Gra.wynik.setText("<html><center>Przegrałeś, o 1 dobre trafienie do wygranej, szkoda!.<br> " + licznik + " trafień.</html>");
                break;

            case 6:
                Gra.wynik.setText("<html><center>Główna wygrana jest twoja! Gratulacje.<br> " + licznik + " trafień.</html>");
                break;
        }
    }

}

public class Gra extends JFrame implements ActionListener {
    //deklaracja odpowiednich zmiennych
    JFrame okno;
    JButton start, restart, close, losuj;
    JPanel glowna, opcje, informacja;
    static JLabel l1,l2,l3,l4,l5,l6, wynik, info, podaj;
    static JTextField liczby;

    Gra()
    {
        //utworzenie oraz rozmieszczenie elementów w oknie
        okno = new JFrame("Lotto");
        glowna = new JPanel();
        opcje = new JPanel();
        informacja = new JPanel();

        liczby = new JTextField();

        l1 = new JLabel();
        l2 = new JLabel();
        l3 = new JLabel();
        l4 = new JLabel();
        l5 = new JLabel();
        l6 = new JLabel();

        l1.setFont(new Font("Arial",Font.BOLD,40));
        l2.setFont(new Font("Arial",Font.BOLD,40));
        l3.setFont(new Font("Arial",Font.BOLD,40));
        l4.setFont(new Font("Arial",Font.BOLD,40));
        l5.setFont(new Font("Arial",Font.BOLD,40));
        l6.setFont(new Font("Arial",Font.BOLD,40));

        l1.setHorizontalAlignment(JLabel.CENTER);
        l2.setHorizontalAlignment(JLabel.CENTER);
        l3.setHorizontalAlignment(JLabel.CENTER);
        l4.setHorizontalAlignment(JLabel.CENTER);
        l5.setHorizontalAlignment(JLabel.CENTER);
        l6.setHorizontalAlignment(JLabel.CENTER);

        wynik = new JLabel();

        podaj = new JLabel("Podaj 6 liczb po przecinku:");

        info = new JLabel("<html><center>Witaj w symulatorze popularnej loterii Lotto, w tej grze " +
                "masz za zadanie wybrać 6 liczb z zakresu 1-49.<br>Następnie zostanie rozpoczęte losowanie" +
                " 6-ciu liczb oraz będą porównane do twoich.<br> Powodzenia i życzę miłej zabawy! :-)</html>", SwingConstants.CENTER);

        start = new JButton("Start");
        restart = new JButton("Graj ponownie");
        close = new JButton("Wyjdź");
        losuj = new JButton("Losowanie");

        start.setBounds(110,80,100,25);
        restart.setBounds(260,80,150,25);
        close.setBounds(460,80,100,25);
        losuj.setBounds(460,44,100,25);

        info.setBounds(0,0,708,60);

        podaj.setBounds(100,41,160,30);
        wynik.setBounds(200,0,300,30);

        liczby.setBounds(260,41,150,30);

        l1.setBounds(0,60,118,50);
        l2.setBounds(118,60,118,50);
        l3.setBounds(236,60,118,50);
        l4.setBounds(354,60,118,50);
        l5.setBounds(472,60,118,50);
        l6.setBounds(590,60,118,50);


        informacja.setBounds(0,0,708,60);
        glowna.setBounds(0,60,708,150);
        opcje.setBounds(0,210,708,122);

        informacja.setLayout(null);
        glowna.setLayout(null);
        opcje.setLayout(null);

        podaj.setVisible(false);
        liczby.setVisible(false);
        losuj.setVisible(false);

        informacja.add(info);
        glowna.add(l1);
        glowna.add(l2);
        glowna.add(l3);
        glowna.add(l4);
        glowna.add(l5);
        glowna.add(l6);
        opcje.add(wynik);
        opcje.add(podaj);
        opcje.add(liczby);
        opcje.add(start);
        opcje.add(restart);
        opcje.add(close);
        opcje.add(losuj);

        okno.add(informacja);
        okno.add(glowna);
        okno.add(opcje);

        start.addActionListener(this);
        restart.addActionListener(this);
        close.addActionListener(this);
        losuj.addActionListener(this);

        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(708,370);
        okno.setLayout(null);
        okno.setResizable(false);
        okno.setVisible(true);

    }
    //metoda sprawdzająca czy liczby podane przez użytkownika nie powtarzają się
    boolean sprawdz(JTextField l, JLabel w)
    {
        boolean spr = true;

        String text = l.getText();
        List<String> items = Arrays.asList(text.split("\\s*,\\s*"));

        for(int i=0; i<6; i++)
        {
            for(int j=1; j<6; j++)
            {
                if (i!=j && items.get(i).equals(items.get(j)))
                {
                    spr = false;
                }
            }
        }
        return spr;
    }
    //metoda sprawdzająca czy podano dokładną ilość liczb
    boolean czySzesc(JTextField l, JLabel w)
    {
        boolean spr = true;
        String text = l.getText();
        List<String> items = Arrays.asList(text.split("\\s*,\\s*"));

        if(items.size() != 6)
        {
            spr = false;
        }

        return spr;
    }
    //metoda sprawdzająca czy nie przekroczono podanego zakresu liczb
    boolean liczba(JTextField l, JLabel w)
    {
        boolean spr = true;
        String text = l.getText();
        List<String> items = Arrays.asList(text.split("\\s*,\\s*"));

        for(int i = 0; i<items.size(); i++)
        {
            int temp = Integer.parseInt(items.get(i));
            if(temp > 49 || temp < 1)
            {
                spr = false;
            }
        }
        return spr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object wybor = e.getSource();

        if(wybor == start)
        {
            podaj.setVisible(true);
            liczby.setVisible(true);
            losuj.setVisible(true);
        }
        //wprowadzenie mniejszej ilości liczb od 6 powoduje wywołanie wyjątku,
        //tak więc tutaj go przechwytujemy.
        try
        {
            if (wybor == losuj)
            {
                if (sprawdz(liczby, wynik) == false) {
                    wynik.setText("Liczba się powtórzyła, wprowadź jeszcze raz!");
                    liczby.setText("");
                } else if (liczba(liczby, wynik) == false) {
                    wynik.setText("Liczba spoza przedziału, wprowadź jeszcze raz!");
                    liczby.setText("");
                } else if (czySzesc(liczby, wynik) == false) {
                    wynik.setText("Za dużo liczb, wprowadź jeszcze raz!");
                    liczby.setText("");
                } else
                    new losowanie();
            }
        }
        catch(ArrayIndexOutOfBoundsException ev)
        {
            if(czySzesc(liczby, wynik) == false)
            {
                wynik.setText("Za mało liczb, wprowadź jeszcze raz!");
                liczby.setText("");
            }
        }

        if(wybor == restart)
        {
            podaj.setVisible(false);
            liczby.setVisible(false);
            losuj.setVisible(false);

            l1.setText("");
            l2.setText("");
            l3.setText("");
            l4.setText("");
            l5.setText("");
            l6.setText("");
            wynik.setText("");
        }

        if(wybor == close)
            System.exit(0);
    }

    public static void main(String[] args)
    {
        new Gra();
    }


}
