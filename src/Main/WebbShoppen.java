package Main;

import Komponenter.Beställning;
import Komponenter.Kund;
import Komponenter.Kundvagn;
import Komponenter.Sko;
import Repositories.BeställningRepository;
import Repositories.KundRepository;
import Repositories.KundvagnRepository;
import Repositories.SkoRepository;

import java.util.*;

public class WebbShoppen {
    private final SkoRepository skoRepo = new SkoRepository();
    private final KundRepository kundRepo = new KundRepository();
    private final KundvagnRepository kundvagnRepo = new KundvagnRepository();
    private final BeställningRepository beställningRepo = new BeställningRepository();
    private final List<Kund> kundList;
    private final List<Kundvagn> kundvagnsList;
    private final List<Sko> skoList;
    private final List<Beställning> beställningList;
    private final Kund kund;
    private final Kundvagn kundvagn;
    private final Rapporter rapport;
    private final Scanner sc = new Scanner(System.in);


    ShoeSearcher märkeSök = (c, s) -> c.getMärke().equalsIgnoreCase(s);
    ShoeSearcher modellSök = (c, s) -> c.getModell().equalsIgnoreCase(s);
    ShoeSearcher färgSök = (c, s) -> c.getFärg().equalsIgnoreCase(s);
    ShoeSearcher könSök = (c, s) -> c.getKön().equalsIgnoreCase(s);
    ShoeSearcher storlekSök = (c, s) ->  c.getStorlek() == Integer.parseInt(s);



    public WebbShoppen() {
        List<Kundvagn> temp;
        kundList = laddaKunder();
        skoList = laddaSkor();
        kundvagnsList = laddaKundvagnar();
        beställningList = laddaBeställningar();
        rapport = new Rapporter(kundList,kundvagnsList,skoList,beställningList);
        kund = login();

        temp = kundvagnsList.stream().filter(k -> k.getKund().getNamn().equalsIgnoreCase(kund.getNamn())).toList();


        boolean yeah = beställningList.stream().map(Beställning::getId).toList().contains(temp.size()-1);       //Kollar ifall användar
        System.out.println(yeah);

        if(!yeah){
            kundvagn = new Kundvagn(kundvagnsList.size()+1,null,kund);
        } else {
            kundvagn = temp.get(temp.size()-1);
        }

        System.out.println(kundvagn.getId());


        System.out.println("Välkommen " + kund.getNamn());
        meny();

    }

    public List<Kund> laddaKunder(){
        try {
            return kundRepo.kundSkapare();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Sko> laddaSkor(){
        try {
            return skoRepo.skoSkapare();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Kundvagn> laddaKundvagnar(){
        try {
            return kundvagnRepo.kundvagnSkapare(skoList, kundList);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Beställning> laddaBeställningar() {
        try {
            return beställningRepo.beställningSkapare(kundvagnsList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Kund login(){
        while (true) {
            System.out.println("Välkommen till WebbShoppen!");
            System.out.println("-------------------------------------------------------");
            System.out.print("Användarnamn: ");
            String namn = sc.nextLine();
            System.out.print("Lösenord: ");
            String lösenord = sc.nextLine();
            System.out.println("-------------------------------------------------------");

            try {
                return kundList.stream().filter(k -> k.getNamn().equalsIgnoreCase(namn))
                        .filter(k -> k.getLösenord().equalsIgnoreCase(lösenord)).toList().get(0);
            }catch (Exception e){
                System.out.println("Skrev du fel? Användaren kunde inte hittas i vår databas.");
            }
        }
    }

    public void meny(){
        while (true) {
            System.out.println("-------------------------------------------------------");
            System.out.println("""
                    Välj ett alternativ:
                    1. Köpa skor.
                    2. Se sälj-rapporter.
                    3. Avsluta programmet.""");
            String svar = sc.nextLine();
            switch (svar) {
                case ("1") -> läggTillSko();
                case ("2") -> läsRapport();
                case ("3") -> System.exit(0);
                default -> System.out.println("Skriv en siffra 1-3");
            }
        }
    }

    public void läggTillSko() {
        List<Sko> temp = skoList;
        while (true) {
            System.out.println("-------------------------------------------------------");
            temp.forEach(Sko::printSko);
            System.out.println("Vilka attribut vill du söka efter? (tryck q för att gå till menyn)");
            final String attributeToSearchFor = sc.nextLine();
            System.out.println("Skriv det du letar efter.");
            final String wordToSearchFor = sc.nextLine();

            if (attributeToSearchFor.equalsIgnoreCase("märke")) {
                temp = sökSkor(wordToSearchFor, märkeSök, temp);
                läggTillSko(temp);
            } else if (attributeToSearchFor.equalsIgnoreCase("modell")) {
                temp = sökSkor(wordToSearchFor, modellSök, temp);
                läggTillSko(temp);
            } else if (attributeToSearchFor.equalsIgnoreCase("färg")) {
                temp = sökSkor(wordToSearchFor, färgSök, temp);
                läggTillSko(temp);
            } else if (attributeToSearchFor.equalsIgnoreCase("storlek")) {
                temp = sökSkor(wordToSearchFor, storlekSök, temp);
                läggTillSko(temp);
            } else if (attributeToSearchFor.equalsIgnoreCase("kön")) {
                temp = sökSkor(wordToSearchFor, könSök, temp);
                läggTillSko(temp);
            } else if (attributeToSearchFor.equalsIgnoreCase("q") || wordToSearchFor.equalsIgnoreCase("q")) {
                break;
            } else {
                System.out.println("Skriv en kategori som finns");
            }
        }
    }

    public void läggTillSko(List<Sko> skor){
        System.out.println("-------------------------------------------------------");
        if(skor.size()-1 == 0){
            System.out.println("Vill du lägga till sko j/n?");
            String svar = sc.nextLine();
            switch (svar.toLowerCase()) {
                case ("j") -> {
                    kundvagnRepo.addToCart(kund.getId(), kundvagn.getId(), skor.get(0).getId());
                    meny();
                }
                case ("n") -> meny();
            }
        } else {
            System.out.println("Sök tills du har valt en enskild sko");
        }

    }

    public void läsRapport(){
        System.out.println("-------------------------------------------------------");
        System.out.println("""
                   Villken rapport vill du läsa?
                   1. Totalbelopp per kund.
                   2. Antal varor tillagda per kund.
                   3. Totalbelopp per ort
                   4. Antal ordrar per kund.
                   """);
        String svar = sc.nextLine();
        switch (svar) {
            case ("1") -> rapport.totalbelopp();
            case ("2") -> rapport.antalVarorTillagda();
            case ("3") -> rapport.beloppPerOrt();
            case ("4") -> rapport.antalOrdrar();
            default -> System.out.println("Skriv siffra mellan 1-4");
        }
    }

    public List<Sko> sökSkor(String wordToSearchFor, ShoeSearcher sc, List<Sko> temp){
        return temp.stream().filter(c -> sc.search(c, wordToSearchFor)).toList();
    }
}
