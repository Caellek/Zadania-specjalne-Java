import java.util.ArrayList;


//Adam Tomaszewski
//Laboratorium NR 6 - zadanie 3 (SPECJALNE)

//Towrzymy klasę Node (Węzeł)
class Node
{
    private String wladca;
    public ArrayList<Node> synowie = new ArrayList<Node>();

    //konstruktor
    Node(String wladca)
    {
        this.wladca=wladca;
    }

    //metoda drukująca łączenia między władcami oraz ich imiona
    public void print(String prefix, boolean isTail)
    {
        System.out.println(prefix + (isTail ? "╚══ " : "╠══ ") + wladca);
        for( int i=0; i<synowie.size() - 1; i++)
        {
            synowie.get(i).print(prefix + (isTail ? "    " : "║   "), false);
        }
        if (synowie.size() > 0)
        {
            synowie.get(synowie.size() - 1).print(prefix + (isTail ? "    " : "║   "), true);
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
        Siemoslyw.synowie.add(Mieszko);
        Siemoslyw.synowie.add(Czcibor);

        //2nd generetion
        Node BoleslawCh = new Node("Bolesław I Chrobry");
        Node MieszkoMi = new Node("Mieszko Mieszkowic");
        Node Swietopelk = new Node("Świętopełk Mieszkowic");
        Node LambertMi = new Node("Lambert Mieszkowiec");
        Mieszko.synowie.add(BoleslawCh);
        Mieszko.synowie.add(MieszkoMi);
        Mieszko.synowie.add(Swietopelk);
        Mieszko.synowie.add(LambertMi);

        //3rd generation
        Node Bezprym = new Node("Bezprym");
        Node MieszkoLa = new Node("Mieszko II Lambert");
        Node Otto = new Node("Otto");
        Node Dytryk = new Node("Dytryk");
        BoleslawCh.synowie.add(Bezprym);
        BoleslawCh.synowie.add(MieszkoLa);
        BoleslawCh.synowie.add(Otto);
        Swietopelk.synowie.add(Dytryk);

        //4th generation
        Node BoleslawZa = new Node("Boleslaw Zapomniany");
        Node KazimierzOd = new Node("Kazimierz I Odnowiciel");
        MieszkoLa.synowie.add(BoleslawZa);
        MieszkoLa.synowie.add(KazimierzOd);

        //5th generation
        Node BoleslawSm = new Node("Bolesław II Śmiały");
        Node WladyslawHe = new Node("Władysław I Herman");
        Node MieszkoKa = new Node("Mieszko Kazimierzowic");
        KazimierzOd.synowie.add(BoleslawSm);
        KazimierzOd.synowie.add(WladyslawHe);
        KazimierzOd.synowie.add(MieszkoKa);

        //6th generation
        Node Zbigniew = new Node("Zbigniew");
        Node BoleslawKrz = new Node("Bolesław III Krzywousty");
        WladyslawHe.synowie.add(Zbigniew);
        WladyslawHe.synowie.add(BoleslawKrz);

        //7th generation
        Node Lechu = new Node("Leszek");
        Node KazimierzSta = new Node("Kazimierz Starszy");
        Node BoleslawKed = new Node("Bolesław IV Kędzierzawy");
        Node MieszkoSta = new Node("Mieszko III Stary");
        Node HenrykSa = new Node("Kazimierz Sandomierski");
        Node KazimierzSpra = new Node("Kazimierz II Sprawiedliwy");
        BoleslawKrz.synowie.add(Lechu);
        BoleslawKrz.synowie.add(KazimierzSta);
        BoleslawKrz.synowie.add(BoleslawKed);
        BoleslawKrz.synowie.add(MieszkoSta);
        BoleslawKrz.synowie.add(HenrykSa);
        BoleslawKrz.synowie.add(KazimierzSpra);

        //Wypisanie całego drzewa
        Siemoslyw.print(" ", true);
    }

    //Main i wywołanie konstruktora
    public static void main(String[] args)
    {
        new Tree();
    }
}