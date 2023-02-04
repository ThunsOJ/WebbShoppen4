package Main;

import Komponenter.Beställning;
import Komponenter.Kund;
import Komponenter.Kundvagn;
import Komponenter.Sko;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Rapporter {

    private final List<Kund> kundList;
    private final List<Kundvagn> kundvagnsList;
    private final List<Sko> skoList;
    private final List<Beställning> beställningList;

    public Rapporter(List<Kund> kundList, List<Kundvagn> kundvagnsList, List<Sko> skoList, List<Beställning> beställningList) {
        this.kundList = kundList;
        this.kundvagnsList = kundvagnsList;
        this.skoList = skoList;
        this.beställningList = beställningList;
    }

    public void totalbelopp(){
        Map<String, Integer> prisMap = beställningList.stream().collect(Collectors.toMap(n -> n.getKundvagn().getKund().getNamn(),
                        b -> b.getKundvagn().getSko().stream().mapToInt(Sko::getPris).reduce(0, Integer::sum)));

        prisMap.forEach((k,v) -> System.out.println(k +", Totalbelopp: "+ v));
    }

    public void antalVarorTillagda(){
        Map<String, Long> prisMap2 = beställningList.stream().collect(Collectors.toMap(n -> n.getKundvagn().getKund().getNamn(),
                b -> b.getKundvagn().getSko().stream().mapToInt(Sko::getId).count(), Long::sum));

        prisMap2.forEach((k,v) -> System.out.println(k +", Tillagda produkter: "+ v));
    }

    public void beloppPerOrt(){
        Map<String, Integer> prisMap = beställningList.stream().collect(Collectors.toMap(n -> n.getKundvagn().getKund().getOrt(),
                b -> b.getKundvagn().getSko().stream().mapToInt(Sko::getPris).reduce(0, Integer::sum), Integer::sum));

        prisMap.forEach((k,v) -> System.out.println(k +", Totalbelopp: "+ v));
    }

    public void antalOrdrar(){
        Map<String, Long> prisMap2 = beställningList.stream().collect(Collectors.groupingBy(n -> n.getKundvagn().getKund().getNamn(), Collectors.counting()));

        prisMap2.forEach((k,v) -> System.out.println(k +", ordrar: "+ v));
    }


}
