import java.util.ArrayList;


//Adam Tomaszewski
//Laboratorium NR 6 - zadanie 3 (SPECJALNE)

//Towrzymy klasę Node (Węzeł)
class Node
{
    private String king;
    public ArrayList<Node> sons = new ArrayList<Node>();

    //konstruktor
    Node(String king)
    {
        this.king=king;
    }

    //metoda drukująca łączenia między władcami oraz ich imiona
    public void print(String prefix, boolean isTail)
    {
        System.out.println(prefix + (isTail ? "╚══ " : "╠══ ") + king);
        for( int i=0; i<sons.size() - 1; i++)
        {
            sons.get(i).print(prefix + (isTail ? "    " : "║   "), false);
        }
        if (sons.size() > 0)
        {
            sons.get(sons.size() - 1).print(prefix + (isTail ? "    " : "║   "), true);
        }
    }
}

//klasa publiczna drzewa
public class Tree
{
    //konstruktor
    Tree()
    {
        //Tworzenie obiektów klasy Node oraz przypisywanie "synów do ojców"
        System.out.println("Gdzieś tam początek tego szajsu");
        //root
        Node Siemoslyw = new Node("Siemoslyw");

        //1st generation
        Node Mieszko = new Node("Mieszko I");
        Node Czcibor = new Node("Czcibor");
        Siemoslyw.sons.add(Mieszko);
        Siemoslyw.sons.add(Czcibor);

        //2nd generetion
        Node BoleslawCh = new Node("Bolesław I Chrobry");
        Node MieszkoMi = new Node("Mieszko Mieszkowic");
        Node Swietopelk = new Node("Świętopełk Mieszkowic");
        Node LambertMi = new Node("Lambert Mieszkowiec");
        Mieszko.sons.add(BoleslawCh);
        Mieszko.sons.add(MieszkoMi);
        Mieszko.sons.add(Swietopelk);
        Mieszko.sons.add(LambertMi);

        //3rd generation
        Node Bezprym = new Node("Bezprym");
        Node MieszkoLa = new Node("Mieszko II Lambert");
        Node Otto = new Node("Otto");
        Node Dytryk = new Node("Dytryk");
        BoleslawCh.sons.add(Bezprym);
        BoleslawCh.sons.add(MieszkoLa);
        BoleslawCh.sons.add(Otto);
        Swietopelk.sons.add(Dytryk);

        //4th generation
        Node BoleslawZa = new Node("Boleslaw Zapomniany");
        Node KazimierzOd = new Node("Kazimierz I Odnowiciel");
        MieszkoLa.sons.add(BoleslawZa);
        MieszkoLa.sons.add(KazimierzOd);

        //5th generation
        Node BoleslawSm = new Node("Bolesław II Śmiały");
        Node WladyslawHe = new Node("Władysław I Herman");
        Node MieszkoKa = new Node("Mieszko Kazimierzowic");
        KazimierzOd.sons.add(BoleslawSm);
        KazimierzOd.sons.add(WladyslawHe);
        KazimierzOd.sons.add(MieszkoKa);

        //6th generation
        Node Zbigniew = new Node("Zbigniew");
        Node BoleslawKrz = new Node("Bolesław III Krzywousty");
        WladyslawHe.sons.add(Zbigniew);
        WladyslawHe.sons.add(BoleslawKrz);

        //7th generation
        Node Lechu = new Node("Leszek");
        Node KazimierzSta = new Node("Kazimierz Starszy");
        Node BoleslawKed = new Node("Bolesław IV Kędzierzawy");
        Node MieszkoSta = new Node("Mieszko III Stary");
        Node HenrykSa = new Node("Kazimierz Sandomierski");
        Node KazimierzSpra = new Node("Kazimierz II Sprawiedliwy");
        BoleslawKrz.sons.add(Lechu);
        BoleslawKrz.sons.add(KazimierzSta);
        BoleslawKrz.sons.add(BoleslawKed);
        BoleslawKrz.sons.add(MieszkoSta);
        BoleslawKrz.sons.add(HenrykSa);
        BoleslawKrz.sons.add(KazimierzSpra);

        //Wypisanie całego drzewa
        Siemoslyw.print(" ", true);
    }

    //Main i wywołanie konstruktora
    public static void main(String[] args)
    {
        new Tree();
    }
}