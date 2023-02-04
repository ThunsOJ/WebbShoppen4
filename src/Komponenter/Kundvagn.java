package Komponenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kundvagn {
    private int id;
    private List<Sko> sko;
    private Kund kund;

    public Kundvagn(int id, List<Sko> sko, Kund kund) {
        this.id = id;
        this.sko = sko;
        this.kund = kund;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Sko> getSko() {
        return sko;
    }

    public void setSko(List<Sko> sko) {
        this.sko = sko;
    }

    public Kund getKund() {
        return kund;
    }

    public void setKund(Kund kund) {
        this.kund = kund;
    }

    @Override
    public String toString() {
        List<String> skoModell = new ArrayList<>();
        sko.forEach(k -> skoModell.add(k.getModell()));
        return "Kundvagn{" +
                "id=" + id +
                ", sko=" + Arrays.toString(skoModell.toArray())+
                ", kund=" + kund.getNamn() +
                '}';
    }
}
