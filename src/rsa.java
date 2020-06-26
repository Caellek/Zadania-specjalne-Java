

//MARCIN KURZAWSKI 18724
//PWSZ ELBLAG IIS
//LABORATORIUM NR 6 - ZADANIE 3 (SPECJALNE)

//DRZEWO PISATOW
import java.util.*;

public class rsa{
    public static void main (String[] args)
    {
        Wezel Siemomysl = new Wezel("Siemomysl");
        Wezel Mieszko1 = new Wezel("Mieszko1");
        Wezel Czcibor = new Wezel("Czcibor");
        Siemomysl.synowie.add(Mieszko1);
        Siemomysl.synowie.add(Czcibor);
        Wezel Mieszko = new Wezel("Mieszko Mieszkowic");
        Wezel Swietopelk = new Wezel("Swietopelk");
        Wezel Lambertc = new Wezel("Lambert Mieszkowic");
        Wezel Boleslaw1Chrobry = new Wezel("Boleslaw1 Chrobry");
        Mieszko1.synowie.add(Mieszko);
        Mieszko1.synowie.add(Swietopelk);
        Mieszko1.synowie.add(Lambertc);
        Mieszko1.synowie.add(Boleslaw1Chrobry);
        Wezel Dytryk = new Wezel("Dytryk");
        Swietopelk.synowie.add(Dytryk);
        Wezel Bezprym = new Wezel("Bezprym");
        Wezel MieszkoIILambert = new Wezel("Mieszko II Lambert");
        Wezel Otton = new Wezel("Otton");
        Boleslaw1Chrobry.synowie.add(Bezprym);
        Boleslaw1Chrobry.synowie.add(MieszkoIILambert);
        Boleslaw1Chrobry.synowie.add(Otton);

        Wezel BoleslawZapomniany = new Wezel("Boleslaw Zapomniany");
        Wezel KazimierzIKarolOdnowiciel = new Wezel("Kazimierz I Karol Odnowiciel");
        MieszkoIILambert.synowie.add(BoleslawZapomniany);
        MieszkoIILambert.synowie.add(KazimierzIKarolOdnowiciel);

        Wezel BoleslawIISzczodry = new Wezel("Boleslaw II Szczodry");
        Wezel WladyslawIHerman = new Wezel("Wladyslaw I Herman");
        Wezel Mieszkob = new Wezel("Mieszko");
        Wezel Ottonb = new Wezel("Otton");
        KazimierzIKarolOdnowiciel.synowie.add(BoleslawIISzczodry);
        KazimierzIKarolOdnowiciel.synowie.add(WladyslawIHerman);
        KazimierzIKarolOdnowiciel.synowie.add(Mieszkob);
        KazimierzIKarolOdnowiciel.synowie.add(Ottonb);

        BoleslawIISzczodry.synowie.add(new Wezel("Mieszko"));

        Wezel Zbigniew = new Wezel("Zbigniew");
        Wezel BoleslawIIIKrzywousty = new Wezel("Boleslaw III Krzywousty");
        WladyslawIHerman.synowie.add(Zbigniew);
        WladyslawIHerman.synowie.add(BoleslawIIIKrzywousty);

        BoleslawIIIKrzywousty.synowie.add(new Wezel("Wladyslaw II Wygnaniec"));
        BoleslawIIIKrzywousty.synowie.add(new Wezel("Leszek"));
        BoleslawIIIKrzywousty.synowie.add(new Wezel("KazimierzStarszy"));
        Wezel BoleslawKedzierzawy = new Wezel("Boleslaw Kedzierzawy");
        BoleslawIIIKrzywousty.synowie.add(BoleslawKedzierzawy);
        BoleslawIIIKrzywousty.synowie.add(new Wezel("MieszkoIIIStary"));
        BoleslawIIIKrzywousty.synowie.add(new Wezel("HenrykSandomierski"));
        BoleslawIIIKrzywousty.synowie.add(new Wezel("Mscislaw"));
        BoleslawIIIKrzywousty.synowie.add(new Wezel("KazimierzSprawiedliwy"));

        BoleslawKedzierzawy.synowie.add(new Wezel("Boleslaw"));
        BoleslawKedzierzawy.synowie.add(new Wezel("Leszek"));
        BoleslawKedzierzawy.synowie.add(new Wezel("Konrad"));

        //drukowanie calosci
        System.out.println("Lestek");
        Siemomysl.druczek(" ", true);

    }
}

class Wezel{
    //pola + konstruktor
    private String wladca;
    public ArrayList<Wezel> synowie = new ArrayList<Wezel>();
    Wezel(String wladca){
        this.wladca=wladca;
    }
    //wydruki
    public void druczek(String wybor, boolean koncowy) {
        // '-- dla ostatniego syna i |-- jeszeli sa jeszcze nastepni - dla najbardziej wewnetrznych
        System.out.println(wybor + (koncowy ? "'-- " : "|-- ") + wladca);
        for (int i = 0; i < synowie.size() - 1; i++) {
            //liscie dla korzenia
            synowie.get(i).druczek(wybor + (koncowy ? "      " : "|   "), false);
        }
        //jezeli
        if (synowie.size() > 0) {
            synowie.get(synowie.size() - 1)
                    //dodruk dla obiektow - od lewej strony
                    .druczek(wybor + (koncowy ?"      " : "|   "), true);
        }
    }



}