import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AplikasiMusik {
    private ArrayList<Admin> daftarAdmin = new ArrayList<>();
    private ArrayList<Pengguna> daftarPengguna = new ArrayList<>();
    private ArrayList<Lagu> daftarLagu = new ArrayList<>();
    private ArrayList<Genre> daftarGenre = new ArrayList<>();
    private ArrayList<Playlist> daftarPlaylist = new ArrayList<>();
    private ArrayList<RiwayatPutar> daftarRiwayat = new ArrayList<>();
    private Pengguna penggunaAktif;
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Inisialisasi Data Awal
    public void inisialisasiData() {
        Genre g1 = new Genre("G1", "Rock");
        Genre g2 = new Genre("G2", "Pop");
        daftarGenre.addAll(Arrays.asList(g1, g2));

        Lagu l1 = new Lagu("L1", "Let It Be", "The Beatles", g1);
        Lagu l2 = new Lagu("L2", "Shape of You", "Ed Sheeran", g2);
        daftarLagu.addAll(Arrays.asList(l1, l2));

        daftarAdmin.add(new Admin("A1", "Admin Utama", "admin", "admin123", this));
        daftarPengguna.add(new Pengguna("U1", "Rafli", "user", "123", this));

        System.out.println("\n=============================================");
        System.out.println(" SISTEM MANAJEMEN PLAYLIST MUSIK (CONSOLE)");
        System.out.println("=============================================");
        System.out.println("Data awal berhasil diinisialisasi.\n");
    }

    // Jalankan Program
    public void run() {
        while (true) {
            tampilkanHeader("HALAMAN LOGIN");
            System.out.print("Masukkan Username : ");
            String u = scanner.nextLine();
            System.out.print("Masukkan Password : ");
            String p = scanner.nextLine();

            Pengguna login = login(u, p);
            if (login != null) {
                penggunaAktif = login;
                tampilkanHeader("LOGIN BERHASIL");
                System.out.println("Selamat datang, " + login.getNama() + "!");
                jeda();
                if (login instanceof Admin) menuAdmin((Admin) login);
                else menuPengguna(login);
            } else {
                System.out.println("\n---------------------------------------------");
                System.out.println("Login gagal! Username atau password salah!");
                System.out.println("---------------------------------------------\n");
            }
        }
    }

    // Login
    public Pengguna login(String username, String password) {
        for (Admin a : daftarAdmin)
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) return a;

        for (Pengguna p : daftarPengguna)
            if (p.getUsername().equals(username) && p.getPassword().equals(password)) return p;

        return null;
    }

    // Menu Admin
    private void menuAdmin(Admin admin) {
        while (true) {
            tampilkanHeader("MENU ADMIN");
            System.out.println("[1] Kelola Lagu");
            System.out.println("[2] Kelola Genre");
            System.out.println("[3] Logout");
            System.out.println("---------------------------------------------");
            System.out.print("Pilih menu (1-3): ");
            String pilih = scanner.nextLine();

            switch (pilih) {
                case "1": admin.kelolaDataLagu(scanner); break;
                case "2": admin.kelolaDataGenre(scanner); break;
                case "3": logout(); return;
                default: System.out.println("Pilihan tidak valid!");
            }
        }
    }

    // Menu Pengguna
    private void menuPengguna(Pengguna pengguna) {
        while (true) {
            tampilkanHeader("MENU PENGGUNA");
            System.out.println("[1] Membuat Playlist");
            System.out.println("[2] Menambah Lagu ke Playlist");
            System.out.println("[3] Putar Lagu");
            System.out.println("[4] Lihat Riwayat");
            System.out.println("[5] Logout");
            System.out.println("---------------------------------------------");
            System.out.print("Pilih menu (1-5): ");
            String pilih = scanner.nextLine();

            switch (pilih) {
                case "1": pengguna.membuatPlaylist(scanner); break;
                case "2": pengguna.menambahLaguKePlaylist(scanner); break;
                case "3": pengguna.putarLagu(scanner); break;
                case "4": pengguna.lihatRiwayatPutar(); break;
                case "5": logout(); return;
                default: System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public void logout() {
        tampilkanHeader("LOGOUT");
        System.out.println("Logout berhasil. Sampai jumpa, " + penggunaAktif.getNama() + "!");
        penggunaAktif = null;
        jeda();
    }

    // Utility Tampilan
    public void tampilkanHeader(String judul) {
        System.out.println("\n=============================================");
        System.out.println(" " + judul.toUpperCase());
        System.out.println("=============================================");
    }

    public void jeda() {
        System.out.print("\nTekan [Enter] untuk melanjutkan...");
        scanner.nextLine();
    }

    // Fungsi Data
    public ArrayList<Lagu> getDaftarLagu() { return daftarLagu; }
    public ArrayList<Genre> getDaftarGenre() { return daftarGenre; }
    public ArrayList<Playlist> getDaftarPlaylist() { return daftarPlaylist; }
    public void tambahLagu(Lagu l) { daftarLagu.add(l); }
    public void tambahGenre(Genre g) { daftarGenre.add(g); }

    public Lagu cariLagu(String id) {
        for (Lagu l : daftarLagu) if (l.getId().equals(id)) return l;
        return null;
    }
    public Genre cariGenre(String id) {
        for (Genre g : daftarGenre) if (g.getId().equals(id)) return g;
        return null;
    }

    public void simpanRiwayat(Pengguna user, Lagu lagu) {
        String id = "R" + (daftarRiwayat.size() + 1);
        String waktu = LocalDateTime.now().format(dtf);
        RiwayatPutar r = new RiwayatPutar(id, waktu, lagu, user);
        daftarRiwayat.add(r);
        user.getDaftarRiwayat().add(r);
    }
}
