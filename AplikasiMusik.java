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
    private Scanner sc = new Scanner(System.in);
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // =========================
    // Inisialisasi Data
    // =========================
    public void inisialisasiData() {
        Genre g1 = new Genre("G1", "Rock");
        Genre g2 = new Genre("G2", "Pop");
        daftarGenre.addAll(Arrays.asList(g1, g2));

        Lagu l1 = new Lagu("L1", "Let It Be", "The Beatles", g1);
        Lagu l2 = new Lagu("L2", "Shape of You", "Ed Sheeran", g2);
        daftarLagu.addAll(Arrays.asList(l1, l2));

        daftarAdmin.add(new Admin("A1", "Admin Utama", "admin", "admin123", this));
        daftarPengguna.add(new Pengguna("U1", "Rafi", "user1", "12345", this));
    }

    // =========================
    // Jalankan Program
    // =========================
    public void run() {
        while (true) {
            System.out.println("\nLogin Aplikasi Musik");
            System.out.print("Username : ");
            String user = sc.nextLine();
            System.out.print("Password : ");
            String pass = sc.nextLine();

            Pengguna login = login(user, pass);

            if (login != null) {
                penggunaAktif = login;
                System.out.println("Login berhasil!");
                if (login instanceof Admin) menuAdmin((Admin) login);
                else menuPengguna(login);
            } else {
                System.out.println("Username atau password salah. Coba lagi.\n");
            }
        }
    }

    // =========================
    // Login
    // =========================
    public Pengguna login(String username, String password) {
        for (Admin a : daftarAdmin)
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) return a;

        for (Pengguna p : daftarPengguna)
            if (p.getUsername().equals(username) && p.getPassword().equals(password)) return p;

        return null;
    }

    // =========================
    // Menu Admin
    // =========================
    private void menuAdmin(Admin admin) {
        while (true) {
            System.out.println("\nMenu Aplikasi");
            System.out.println("1. Kelola Data Lagu");
            System.out.println("2. Kelola Data Genre");
            System.out.println("3. Logout");
            System.out.print("Pilih menu : ");
            String pilih = sc.nextLine();

            switch (pilih) {
                case "1": admin.kelolaDataLagu(sc); break;
                case "2": admin.kelolaDataGenre(sc); break;
                case "3": logout(); return;
                default: System.out.println("Pilihan tidak valid!");
            }
        }
    }

    // =========================
    // Menu Pengguna
    // =========================
    private void menuPengguna(Pengguna pengguna) {
        while (true) {
            System.out.println("\nMenu Aplikasi");
            System.out.println("1. Membuat Playlist");
            System.out.println("2. Menambahkan Lagu ke Playlist");
            System.out.println("3. Memutar Lagu");
            System.out.println("4. Lihat Riwayat Pemutaran Lagu");
            System.out.println("5. Logout");
            System.out.print("Pilih menu : ");
            String pilih = sc.nextLine();

            switch (pilih) {
                case "1": pengguna.membuatPlaylist(sc); break;
                case "2": pengguna.menambahLaguKePlaylist(sc); break;
                case "3": pengguna.putarLagu(sc); break;
                case "4": pengguna.lihatRiwayatPutar(); break;
                case "5": logout(); return;
                default: System.out.println("Pilihan tidak valid!");
            }
        }
    }

    // =========================
    // Logout
    // =========================
    public void logout() {
        System.out.println("\nLogout berhasil. Sampai jumpa, " + penggunaAktif.getNama() + "!");
        System.out.println("Terima kasih telah menggunakan Aplikasi Musik!");
        System.out.println("Kembali ke halaman login...\n");
        penggunaAktif = null;
    }

    // =========================
    // Fungsi Pendukung
    // =========================
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
        System.out.println("Riwayat pemutaran lagu telah dicatat.");
    }
}
