TP4DPBO2025C2
✋ Janji

Saya Muhammad Adwar Salman dengan NIM (isi NIM kamu di sini) mengerjakan Tugas Praktikum 4 dalam mata kuliah Desain Pemrograman Berorientasi Objek (DPBO) untuk keberkahan-Nya, maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan.
Aamiin.

💡 Desain Program

Aplikasi ini merupakan program manajemen data produk berbasis Java Swing yang mengimplementasikan operasi CRUD (Create, Read, Update, Delete).
Setiap produk direpresentasikan oleh kelas Product, dan seluruh logika serta antarmuka dikelola melalui kelas ProductMenu.

🧱 Kelas dan Struktur Program
# Product

Kelas model untuk menyimpan data produk, dengan atribut:

id → ID unik produk

nama → Nama produk

harga → Harga produk

stok → Jumlah stok produk

merek → Atribut baru (brand atau nama dagang produk)

kategori → Jenis produk yang dipilih melalui JComboBox

Metode:

Getter & Setter untuk setiap atribut.

# ProductMenu

Kelas GUI utama dengan komponen:

JTextField → Input nama, harga, stok, dan merek

JComboBox → Input kategori produk (bonus +20 karena bukan JTextField)

JTable → Menampilkan daftar produk

JButton → Aksi CRUD:

Add → Tambah produk baru

Update → Ubah data produk

Delete → Hapus produk dengan konfirmasi

Clear → Kosongkan form input

🔄 Alur Program
# 1. Memulai Aplikasi

Program dijalankan → ProductMenu terbuka.

Tabel produk kosong, siap menerima data baru.

# 2. Menambahkan Produk (Create)

Pengguna mengisi form: Nama, Harga, Stok, Merek, dan Kategori.

Klik tombol Add.

Data disimpan ke dalam list dan langsung tampil di tabel.

# 3. Menampilkan Produk (Read)

Seluruh data produk dalam list otomatis muncul di tabel (JTable).

Tabel selalu diperbarui setelah operasi CRUD.

# 4. Mengubah Produk (Update)

Pilih produk dari tabel.

Form otomatis menampilkan data produk yang dipilih.

Edit data dan klik Update → tabel diperbarui.

# 5. Menghapus Produk (Delete)

Pilih produk dari tabel.

Klik tombol Delete.

Muncul konfirmasi (JOptionPane.showConfirmDialog).

Jika pengguna memilih Yes, data dihapus dari list dan tabel diperbarui.

# 6. Mengosongkan Form (Clear)

Klik Clear untuk mereset semua field input dan mengembalikan ComboBox ke posisi awal.

🖥️ Desain GUI

Tampilan dirancang menggunakan IntelliJ IDEA GUI Builder dengan layout sederhana dan terstruktur.

[Form Input Produk]
-----------------------------------------------
Nama: [__________]
Harga: [__________]
Stok:  [____]
Merek: [__________]
Kategori: [Electronics ▼]

[Tabel Produk]
--------------------------------------------------------------
| ID | Nama | Harga | Stok | Merek | Kategori |
--------------------------------------------------------------

[Tombol Aksi]
[Add] [Update] [Delete] [Clear]

📸 Dokumentasi Program

Berikut dokumentasi yang menunjukkan seluruh operasi CRUD dijalankan dengan sukses:

# 🟢 Create (Tambah Produk)

# 🔵 Read (Tabel Produk)

# 🟡 Update (Edit Produk)

# 🔴 Delete (Konfirmasi & Hapus)

📁 Semua screenshot disimpan di folder screenshot/ atau docs/ dalam repository.

⚙️ Catatan Teknis

Atribut baru: merek

Komponen tambahan: JComboBox untuk kategori (bonus +20)

Konfirmasi delete: menggunakan JOptionPane.showConfirmDialog

Penyimpanan data: menggunakan ArrayList<Product>

Update tabel otomatis: setiap operasi CRUD merefresh tampilan

✅ Kesimpulan

Program ini memenuhi seluruh ketentuan tugas:

Mengimplementasikan CRUD lengkap

Menambahkan atribut baru (merek)

Menambahkan komponen bukan JTextField (JComboBox)

Menampilkan prompt konfirmasi sebelum delete
