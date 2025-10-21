public class RiwayatPutar {
    private String id;
    private String tanggalPutar;
    private Lagu lagu;
    private Pengguna pengguna;

    public RiwayatPutar(String id, String tanggalPutar, Lagu lagu, Pengguna pengguna) {
        this.id = id;
        this.tanggalPutar = tanggalPutar;
        this.lagu = lagu;
        this.pengguna = pengguna;
    }

    public String getId() { return id; }
    public String getTanggalPutar() { return tanggalPutar; }
    public Lagu getLagu() { return lagu; }
    public Pengguna getPengguna() { return pengguna; }
}
