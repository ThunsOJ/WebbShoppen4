package Komponenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sko {
    private int id;
    private String märke;
    private int storlek;
    private String färg;
    private String modell;
    private String kön;
    private int pris;
    private int saldo;
    private List<Kategori> kategori;

    public Sko(int id, String märke, int storlek, String färg, String modell, String kön, int pris, int saldo, List<Kategori> kategori) {
        this.id = id;
        this.märke = märke;
        this.storlek = storlek;
        this.färg = färg;
        this.modell = modell;
        this.kön = kön;
        this.pris = pris;
        this.saldo = saldo;
        this.kategori = kategori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMärke() {
        return märke;
    }

    public void setMärke(String märke) {
        this.märke = märke;
    }

    public int getStorlek() {
        return storlek;
    }

    public void setStorlek(int storlek) {
        this.storlek = storlek;
    }

    public String getFärg() {
        return färg;
    }

    public void setFärg(String färg) {
        this.färg = färg;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getKön() {
        return kön;
    }

    public void setKön(String kön) {
        this.kön = kön;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public List<Kategori> getKategori() {
        return kategori;
    }

    public void setKategori(List<Kategori> kategori) {
        this.kategori = kategori;
    }

    public void printSko(){
        List<String> kategoriNamn = new ArrayList<>();
        kategori.forEach(k -> kategoriNamn.add(k.getKategori()));
        System.out.println("Sko{" +
                "id=" + id +
                ", märke='" + märke + '\'' +
                ", storlek=" + storlek +
                ", färg='" + färg + '\'' +
                ", modell='" + modell + '\'' +
                ", kön='" + kön + '\'' +
                ", pris=" + pris +
                ", saldo=" + saldo +
                ", kategori=" + Arrays.toString(kategoriNamn.toArray()) +
                '}');
    }
}
