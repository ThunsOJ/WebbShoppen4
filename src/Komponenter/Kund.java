package Komponenter;

public class Kund {
    private int id;
    private String namn;
    private String adress;
    private String ort;
    private String lösenord;

    public Kund(int id, String namn, String adress, String ort, String lösenord) {
        this.id = id;
        this.namn = namn;
        this.adress = adress;
        this.ort = ort;
        this.lösenord = lösenord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getLösenord() {
        return lösenord;
    }

    public void setLösenord(String lösenord) {
        this.lösenord = lösenord;
    }
}
