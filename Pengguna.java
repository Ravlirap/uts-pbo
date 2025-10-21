import java.util.*;

public class Pengguna {
    protected String id, nama, username, password;
    protected AplikasiMusik app;
    protected ArrayList<Playlist> daftarPlaylist = new ArrayList<>();
    protected ArrayList<RiwayatPutar> daftarRiwayat = new ArrayList<>();

    public Pengguna(String id, String nama, String username, String password, AplikasiMusik app) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.app = app;
    }

    public String getNama() { return nama; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public ArrayList<RiwayatPutar> getDaftarRiwayat() { return daftarRiwayat; }

    public void membuatPlaylist(Scanner sc) {
        System.out.print("Nama Playlist: ");
        String nama = sc.nextLine();
        String id = "P" + (app.getDaftarPlaylist().size() + 1);
        Playlist p = new Playlist(id, nama, this);
        app.getDaftarPlaylist().add(p);
        daftarPlaylist.add(p);
        System.out.println("Playlist \"" + nama + "\" berhasil dibuat!");
    }

    public void menambahLaguKePlaylist(Scanner sc) {
        if (daftarPlaylist.isEmpty()) {
            System.out.println("Belum ada playlist! Buat dulu.");
            return;
        }

        System.out.println("Pilih Playlist:");
        for (int i = 0; i < daftarPlaylist.size(); i++)
            System.out.println((i+1) + ". " + daftarPlaylist.get(i).getNama());
        int pilih = Integer.parseInt(sc.nextLine()) - 1;
        Playlist pl = daftarPlaylist.get(pilih);

        System.out.println("Daftar Lagu Tersedia:");
        for (Lagu l : app.getDaftarLagu())
            System.out.println(l.getId() + " - " + l.getJudul());

        System.out.print("Masukkan ID Lagu: ");
        Lagu lagu = app.cariLagu(sc.nextLine());
        if (lagu != null) {
            pl.tambahLagu(lagu);
            System.out.println("Lagu berhasil ditambahkan ke playlist \"" + pl.getNama() + "\"");
        } else {
            System.out.println("Lagu tidak ditemukan!");
        }
    }

    public void putarLagu(Scanner sc) {
        System.out.println("Masukkan ID Lagu yang ingin diputar:");
        for (Lagu l : app.getDaftarLagu())
            System.out.println(l.getId() + " - " + l.getJudul());
        Lagu lagu = app.cariLagu(sc.nextLine());
        if (lagu != null) {
            System.out.println("Memutar: " + lagu.getJudul() + " - " + lagu.getArtis());
            app.simpanRiwayat(this, lagu);
        } else {
            System.out.println("Lagu tidak ditemukan!");
        }
    }

    public void lihatRiwayatPutar() {
        if (daftarRiwayat.isEmpty()) {
            System.out.println("Belum ada riwayat pemutaran.");
            return;
        }
        System.out.println("=== RIWAYAT PEMUTARAN ===");
        for (RiwayatPutar r : daftarRiwayat)
            System.out.println(r.getTanggalPutar() + " - " + r.getLagu().getJudul());
    }
}
