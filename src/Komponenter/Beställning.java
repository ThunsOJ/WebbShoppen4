package Komponenter;

public class Beställning {
    private int id;
    private String datum;
    private Kundvagn kundvagn;

    public Beställning(int id, String datum, Kundvagn kundvagn) {
        this.id = id;
        this.datum = datum;
        this.kundvagn = kundvagn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Kundvagn getKundvagn() {
        return kundvagn;
    }

    public void setKundvagn(Kundvagn kundvagn) {
        this.kundvagn = kundvagn;
    }
}
