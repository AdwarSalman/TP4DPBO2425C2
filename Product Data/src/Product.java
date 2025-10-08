public class Product {
    private String id;
    private String nama;
    private double harga;
    private String kategori;
    private int stok; // penambahan atribut baru
    private String merek; // penambahan atribut baru

    public Product(String id, String nama, double harga, String kategori, int stok, String merek) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.stok = stok;
        this.merek = merek;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setMerek(String merek) {this.merek = merek;}


    public String getId() {
        return this.id;
    }

    public String getNama() {
        return this.nama;
    }

    public double getHarga() {
        return this.harga;
    }

    public String getKategori() {
        return this.kategori;
    }

    public int getStok() {
        return this.stok;
    }

    public String getMerek() {return this.merek;
    }
}