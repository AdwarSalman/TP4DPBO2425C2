# TP4DPBO2025C2

# Janji

Saya Muhammad Adwar Salman dengan NIM 2401539 mengerjakan Tugas Praktikum 4 dalam mata kuliah Desain Pemrograman Berorientasi Objek (DPBO) untuk keberkahan-Nya, maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan.
Aamiin.

# Desain Program

Aplikasi ini merupakan program manajemen data produk berbasis Java Swing yang mengimplementasikan operasi CRUD (Create, Read, Update, Delete).
- Create: Menambahkan data produk baru.
- Read: Menampilkan tabel produk.
- Update: Mengedit data produk yang sudah ada.
- Delete: Menghapus data produk.

# Kelas dan Struktur Program
## Product

Kelas model untuk menyimpan data produk, dengan atribut:

- id → ID unik produk
- nama → Nama produk
- harga → Harga produk
- kategori → Jenis produk yang dipilih melalui JComboBox
- stok → Jumlah stok produk
- merek → Atribut baru (brand atau nama dagang produk)

Metode:

Getter & Setter untuk setiap atribut.

# ProductMenu

Kelas GUI utama dengan komponen:

- JTextField → Input nama, harga, dan merek
- JSpinner → Input angka stok produk
- JComboBox → Input kategori produk 
- JTable → Menampilkan daftar produk
- JButton → Aksi CRUD:
- Add → Tambah produk baru
- Update → Ubah data produk
- Delete → Hapus produk dengan konfirmasi
- Clear → Kosongkan form input

# Struktur :
Semuanya ditampung dalam main panel yang terbagi menjadi 3 bagian:
- Bagian input untuk setiap atribut produk
- Bagian tombol untuk interaktif user modifikasi data produk
- Bagian Tabel untuk menampilkan data produk dan interaktif dapat di pilih untuk di modifikasi

# Desain GUI

<img width="876" height="687" alt="image" src="https://github.com/user-attachments/assets/c07d50dc-d526-463b-82e0-6a51ec4d3968" />


# Alur Program
## 1. Memulai Aplikasi

Program dijalankan → ProductMenu terbuka.
Tabel produk berisi data default, siap menerima data baru.

## 2. Menambahkan Produk (Create)

Pengguna mengisi form: Nama, Harga, Stok, Merek, dan Kategori.
Klik tombol Add.
Data disimpan ke dalam list dan langsung tampil di tabel.

## 3. Menampilkan Produk (Read)

Seluruh data produk dalam list otomatis muncul di tabel (JTable).
Tabel selalu diperbarui setelah operasi CRUD.

## 4. Mengubah Produk (Update)

Pilih produk dari tabel.
Form otomatis menampilkan data produk yang dipilih.
Edit data dan klik Update → tabel diperbarui.

## 5. Menghapus Produk (Delete)

Pilih produk dari tabel.
Klik tombol Delete.
Muncul konfirmasi (JOptionPane.showConfirmDialog).
Jika pengguna memilih Yes, data dihapus dari list dan tabel diperbarui.

## 6. Mengosongkan Form (Clear)

Klik Clear untuk mereset semua field input dan mengembalikan ComboBox ke posisi awal.


# Catatan Teknis

- Atribut baru: merek & Stok
- Komponen tambahan: JSpinner untuk input stok dan JTextField untuk input merek
- Konfirmasi delete: menggunakan JOptionPane.showConfirmDialog
- Penyimpanan data: menggunakan ArrayList<Product>
- Update tabel otomatis: setiap operasi CRUD merefresh tampilan
- Menampilkan prompt konfirmasi sebelum delete

# Dokumentasi Program
## Add

https://github.com/user-attachments/assets/69f4dbae-591e-41e2-82f9-842cb09fcc51

## Update

https://github.com/user-attachments/assets/47937396-2ab8-4501-a21d-b155028f37ce

## Delete

https://github.com/user-attachments/assets/b6b97ac1-b525-4afe-924a-2a35fe487d94
