    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.MouseAdapter;
    import java.awt.event.MouseEvent;
    import java.util.ArrayList;

    public class ProductMenu extends JFrame {
        public static void main(String[] args) {
            // buat object window
            ProductMenu menu = new ProductMenu();

            // atur ukuran window
            menu.setSize(700,600);

            // letakkan window di tengah layar
            menu.setLocationRelativeTo(null);

            // isi window
            menu.setContentPane(menu.mainPanel);

            // ubah warna background
            menu.getContentPane().setBackground(Color.lightGray);

            // tampilkan window
            menu.setVisible(true);

            // agar program ikut berhenti saat window diclose
            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        // index baris yang diklik
        private int selectedIndex = -1;

        // list untuk menampung semua produk
        private ArrayList<Product> listProduct;
        private JPanel mainPanel;
        private JTextField idField;
        private JTextField namaField;
        private JTextField hargaField;
        private JTable productTable;
        private JButton addUpdateButton;
        private JButton cancelButton;
        private JButton deleteButton;
        private JComboBox<String> kategoriComboBox;
        private JLabel stokLabel;     // <--- DEKLARASI KOMPONEN BARU 1 (Stok)
        private JLabel merekLabel;   // <--- DEKLARASI KOMPONEN BARU 2 (Merek)
        private JLabel titleLabel;
        private JLabel idLabel;
        private JLabel namaLabel;
        private JLabel hargaLabel;
        private JLabel kategoriLabel;
        private JSpinner stokSpinner;   // untuk input stok menggunakan spinner untuk bonus
        private JTextField merekField;  // untuk input merek


        // constructor
        public ProductMenu() {
            // inisialisasi listProduct
            listProduct = new ArrayList<>();

            // isi listProduct
            populateList();

            // isi tabel produk
            productTable.setModel(setTable());

            // ubah styling title
            titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

            // atur isi combo box
            String[] kategoriData = { "???", "Elektronik", "Makanan", "Minuman", "Pakaian", "Alat Tulis" };
            kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

            // sembunyikan button delete
            deleteButton.setVisible(false);

            // saat tombol add/update ditekan
            addUpdateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (selectedIndex == -1) {
                        insertData();
                    } else {
                        updateData();
                    }
                }
            });
            // saat tombol delete ditekan
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Panggil method yang sudah di isi dengan validasi dan confirmation prompt
                    deleteData();
                }
            });
            // saat tombol cancel ditekan
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearForm();
                }
            });
            // saat salah satu baris tabel ditekan
            productTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    // Cek agar tidak crash jika tabel kosong
                    if (productTable.getSelectedRow() == -1) {
                        return;
                    }

                    // ubah selectedIndex menjadi baris tabel yang diklik
                    selectedIndex = productTable.getSelectedRow();

                    // 1. Ambil value dari tabel (DENGAN INDEX KOLOM YANG BENAR)
                    String curId = productTable.getModel().getValueAt(selectedIndex, 1).toString();
                    String curNama = productTable.getModel().getValueAt(selectedIndex, 2).toString();
                    String curHarga = productTable.getModel().getValueAt(selectedIndex, 3).toString();
                    String curKategori = productTable.getModel().getValueAt(selectedIndex, 4).toString();

                    // Stok: Index 5 (Harus di-parse ke int untuk JSpinner)
                    int curStok = Integer.parseInt(productTable.getModel().getValueAt(selectedIndex, 5).toString());

                    // Merek: Index 6
                    String curMerek = productTable.getModel().getValueAt(selectedIndex, 6).toString();

                    // 2. ubah isi textfield dan komponen
                    idField.setText(curId);
                    namaField.setText(curNama);
                    hargaField.setText(curHarga);
                    kategoriComboBox.setSelectedItem(curKategori);

                    // JSpinner menggunakan setter
                    stokSpinner.setValue(curStok);
                    merekField.setText(curMerek);

                    // 3. Logika Tombol
                    // ubah button "Add" menjadi "Update"
                    addUpdateButton.setText("Update");

                    // tampilkan button delete
                    deleteButton.setVisible(true);
                }
            });
        }

        public final DefaultTableModel setTable() {
            // tentukan kolom tabel
            Object[] cols = { "No", "ID Produk", "Nama", "Harga", "Kategori", "Stok", "Merek" };

            // buat objek tabel dengan kolom yang sudah dibuat
            DefaultTableModel tmp = new DefaultTableModel(null, cols);
            // isi tabel dengan listProduct
            for(int i = 0; i < listProduct.size(); i++){
                Object[] row = {i + 1,
                        listProduct.get(i).getId(),
                        listProduct.get(i).getNama(),
                        String.format("%.2f", listProduct.get(i).getHarga()),
                        listProduct.get(i).getKategori(),
                        listProduct.get(i).getStok(),   // <--- TAMBAH: Ambil data Stok
                        listProduct.get(i).getMerek()  // <--- TAMBAH: Ambil data Merek
                };
                tmp.addRow(row);
            }

            return tmp;
        }

        public void insertData() {
            // 1. Ambil semua value (termasuk Stok dan Merek)
            String id = idField.getText().trim();
            String nama = namaField.getText().trim();
            String hargaStr = hargaField.getText().trim();
            String kategori = kategoriComboBox.getSelectedItem().toString();
            String merek = merekField.getText().trim(); // <-- AMBIL NILAI MEREK
            int stok = (int) stokSpinner.getValue(); // <-- AMBIL NILAI STOK

            // 2. VALIDASI INPUT DASAR (Pencegahan data kosong)
            if (id.isEmpty() || nama.isEmpty() || hargaStr.isEmpty() || merek.isEmpty() || kategori.equals("Pilih Kategori")) {
                JOptionPane.showMessageDialog(mainPanel, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Konversi Harga di dalam try-catch
                double harga = Double.parseDouble(hargaStr);

                // 3. TAMBAH VALIDASI DUPLIKASI ID
                for (Product product : listProduct) {
                    if (product.getId().equalsIgnoreCase(id)) {
                        JOptionPane.showMessageDialog(mainPanel, "ID Produk sudah ada. Gunakan ID lain.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // 4. tambahkan data ke dalam list (GUNAKAN SEMUA 6 ATRIBUT)
                listProduct.add(new Product(id, nama, harga, kategori, stok, merek));

                // update tabel
                productTable.setModel(setTable());

                // bersihkan form
                clearForm();

                // 5. feedback (PERBAIKI PARENT COMPONENT)
                System.out.println("Insert Berhasil");
                JOptionPane.showMessageDialog(mainPanel, "Data berhasil ditambahkan", "Informasi", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(mainPanel, "Harga harus berupa angka!", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        public void updateData() {
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(mainPanel, "Pilih data yang ingin diubah.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                // ambil data dari form
                String id = idField.getText();
                String nama = namaField.getText();
                double harga = Double.parseDouble(hargaField.getText());
                String kategori = kategoriComboBox.getSelectedItem().toString();
                int stok = (int) stokSpinner.getValue();      // <--- AMBIL NILAI STOK
                String merek = merekField.getText().trim();   // <--- AMBIL NILAI MEREK

                if (merek.isEmpty() || kategori.equals("Pilih Kategori")) {
                    JOptionPane.showMessageDialog(mainPanel, "Field Merek dan Kategori harus diisi.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // ubah data produk di list
                listProduct.get(selectedIndex).setId(id);
                listProduct.get(selectedIndex).setNama(nama);
                listProduct.get(selectedIndex).setHarga(harga);
                listProduct.get(selectedIndex).setKategori(kategori);
                listProduct.get(selectedIndex).setStok(stok);    // <--- TAMBAHAN: Setter Stok
                listProduct.get(selectedIndex).setMerek(merek);  // <--- TAMBAHAN: Setter Merek

                // update tabel
                productTable.setModel(setTable());

                // bersihkan form
                clearForm();

                // feedback
                System.out.println("Update berhasil");
                JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Harga harus berupa angka!", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        // tombol Delete
        public void deleteData() {
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(mainPanel, "Pilih baris yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int dialogResult = JOptionPane.showConfirmDialog(
                    mainPanel,
                    "Anda yakin ingin menghapus data dengan ID: " + listProduct.get(selectedIndex).getId() + "?",
                    "Konfirmasi Hapus Data",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (dialogResult == JOptionPane.YES_OPTION) {
                // Hapus data dari list
                listProduct.remove(selectedIndex);

                // update model tabel dan apply ke komponen JTable
                productTable.setModel(setTable());

                // Opsional: hapus seleksi dan refresh
                productTable.clearSelection();
                productTable.revalidate();
                productTable.repaint();

                // Bersihkan form
                clearForm();

                JOptionPane.showMessageDialog(mainPanel, "Data berhasil dihapus.", "Informasi", JOptionPane.INFORMATION_MESSAGE);

                selectedIndex = -1;
            }
        }


        public void clearForm() {
            // kosongkan semua textfield dan combo box
            idField.setText("");
            namaField.setText("");
            hargaField.setText("");
            kategoriComboBox.setSelectedIndex(0);
            stokSpinner.setValue(0); // <--- Reset JSpinner ke nilai awal (0)
            merekField.setText("");  // <--- Kosongkan JTextField Merek

            // ubah button "Update" menjadi "Add"
            addUpdateButton.setText("Add");

            // sembunyikan button delete
            deleteButton.setVisible(false);

            // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
            selectedIndex = -1;
        }

        // panggil prosedur ini untuk mengisi list produk
        private void populateList() {
            // FORMAT BARU: Product("ID", "Nama", Harga, "Kategori", Stok, "Merek")
            listProduct.add(new Product("P001", "Laptop Asus", 8500000.0, "Elektronik", 5, "Asus"));
            listProduct.add(new Product("P002", "Mouse Logitech", 350000.0, "Elektronik", 12, "Logitech"));
            listProduct.add(new Product("P003", "Keyboard Mechanical", 750000.0, "Elektronik", 8, "Razer"));
            listProduct.add(new Product("P004", "Roti Tawar", 15000.0, "Makanan", 50, "Sari Roti"));
            listProduct.add(new Product("P005", "Susu UHT", 12000.0, "Minuman", 30, "Ultra Jaya"));
            listProduct.add(new Product("P006", "Kemeja Putih", 125000.0, "Pakaian", 15, "Cardinal"));
            listProduct.add(new Product("P007", "Celana Jeans", 200000.0, "Pakaian", 10, "Levis"));
            listProduct.add(new Product("P008", "Pensil 2B", 3000.0, "Alat Tulis", 100, "Faber Castell"));
            listProduct.add(new Product("P009", "Buku Tulis", 8000.0, "Alat Tulis", 75, "Sinar Dunia"));
        }
    }