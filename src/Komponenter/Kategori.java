package Komponenter;

public class Kategori {
    private int id;
    private String kategori;

    public Kategori(int id, String kategori) {
        this.id = id;
        this.kategori = kategori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
