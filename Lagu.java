public class Lagu {
    private String id;
    private String judul;
    private String artis;
    private Genre genre;

    public Lagu(String id, String judul, String artis, Genre genre) {
        this.id = id;
        this.judul = judul;
        this.artis = artis;
        this.genre = genre;
    }

    public String getId() { return id; }
    public String getJudul() { return judul; }
    public String getArtis() { return artis; }
    public Genre getGenre() { return genre; }

    public void setJudul(String judul) { this.judul = judul; }
}
