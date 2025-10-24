import java.util.*;

public class Playlist {
    private String id;
    private String nama;
    private Pengguna pemilik;
    private ArrayList<Lagu> daftarLagu = new ArrayList<>();

    public Playlist(String id, String nama, Pengguna pemilik) {
        this.id = id;
        this.nama = nama;
        this.pemilik = pemilik;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public Pengguna getPemilik() { return pemilik; }
    public ArrayList<Lagu> getDaftarLagu() { return daftarLagu; }

    public void tambahLagu(Lagu l) { daftarLagu.add(l); }
}