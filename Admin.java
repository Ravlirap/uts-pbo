import java.util.*;

public class Admin extends Pengguna {
    public Admin(String id, String nama, String username, String password, AplikasiMusik app) {
        super(id, nama, username, password, app);
    }

    public void kelolaDataLagu(Scanner sc) {
        while (true) {
            System.out.println("\n--- KELOLA LAGU ---");
            System.out.println("1. Tambah Lagu");
            System.out.println("2. Edit Lagu");
            System.out.println("3. Hapus Lagu");
            System.out.println("4. Lihat Lagu");
            System.out.println("5. Kembali");
            System.out.print("Pilih: ");
            String pilih = sc.nextLine();

            switch (pilih) {
                case "1":
                    System.out.print("ID Lagu: ");
                    String id = sc.nextLine();
                    System.out.print("Judul: ");
                    String judul = sc.nextLine();
                    System.out.print("Artis: ");
                    String artis = sc.nextLine();
                    System.out.print("ID Genre: ");
                    Genre g = app.cariGenre(sc.nextLine());
                    if (g != null) app.tambahLagu(new Lagu(id, judul, artis, g));
                    else System.out.println("Genre tidak ditemukan!");
                    break;
                case "2":
                    System.out.print("Masukkan ID Lagu yang akan diubah: ");
                    Lagu laguUbah = app.cariLagu(sc.nextLine());
                    if (laguUbah != null) {
                        System.out.print("Judul baru: ");
                        laguUbah.setJudul(sc.nextLine());
                        System.out.println("Data lagu diperbarui!");
                    } else System.out.println("Lagu tidak ditemukan.");
                    break;
                case "3":
                    System.out.print("Masukkan ID Lagu yang akan dihapus: ");
                    Lagu hapus = app.cariLagu(sc.nextLine());
                    if (hapus != null) app.getDaftarLagu().remove(hapus);
                    System.out.println("Lagu dihapus!");
                    break;
                case "4":
                    for (Lagu l : app.getDaftarLagu())
                        System.out.println(l.getId() + " - " + l.getJudul() + " - " + l.getArtis());
                    break;
                case "5": return;
                default: System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public void kelolaDataGenre(Scanner sc) {
        while (true) {
            System.out.println("\n--- KELOLA GENRE ---");
            System.out.println("1. Tambah Genre");
            System.out.println("2. Hapus Genre");
            System.out.println("3. Lihat Genre");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");
            String pilih = sc.nextLine();

            switch (pilih) {
                case "1":
                    System.out.print("ID Genre: ");
                    String id = sc.nextLine();
                    System.out.print("Nama Genre: ");
                    app.tambahGenre(new Genre(id, sc.nextLine()));
                    break;
                case "2":
                    System.out.print("Masukkan ID Genre: ");
                    Genre del = app.cariGenre(sc.nextLine());
                    if (del != null) app.getDaftarGenre().remove(del);
                    break;
                case "3":
                    for (Genre g : app.getDaftarGenre())
                        System.out.println(g.getId() + " - " + g.getNama());
                    break;
                case "4": return;
                default: System.out.println("Pilihan tidak valid!");
            }
        }
    }
}
