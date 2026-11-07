# Perbaikan Integrasi Database GUI Auto SHIFT

## Perubahan GUI Admin
- `AdminDataDAO` menjadi lapisan akses data untuk halaman administrasi.
- `AdminDataController` menjadi penghubung DAO dengan View.
- Halaman Pegawai, Kendaraan, Stok Unit, Tarif Jasa, Sparepart, Servis, dan Transaksi memuat data dari MySQL saat halaman dibuka.
- Halaman Pelanggan tetap menggunakan `PelangganController` yang sudah tersedia.

## Perubahan GUI Sales dan Mekanik
- Menambahkan `RoleDataDAO` untuk query baca data halaman Sales dan Mekanik.
- Menambahkan `RoleDataController` sebagai penghubung View dengan DAO.
- Sales: halaman Transaksi, Kendaraan, Stok Unit, Tarif Jasa, Sparepart, dan Servis sekarang memuat data saat dibuka. Halaman Pelanggan tetap menggunakan controller yang sudah ada.
- Mekanik: halaman Transaksi, Tarif Jasa, Sparepart, dan Servis sekarang memuat data saat dibuka.
- Query transaksi menampilkan nama pelanggan dan nama sales; query servis menampilkan nomor polisi, nama mekanik, jadwal servis berikutnya, diagnosis, tipe kendaraan, dan nomor transaksi.
- Model tabel dibuat read-only dan menggunakan tipe kolom `Object` agar tanggal dan nilai numerik dari JDBC dapat ditampilkan dengan aman.
- Header tabel Stok Unit diperjelas agar sesuai dengan kolom yang ditampilkan (No. Rangka, No. Mesin, ID Transaksi).

## Struktur MVC
- Model tetap menggunakan kelas yang sudah tersedia di package `com.bengkelshift.model`.
- DAO menangani query database.
- Controller menjadi perantara View dan DAO.
- View memanggil controller saat halaman dibuka dan mengisi `JTable`.

## Cara menjalankan
1. Jalankan MySQL melalui XAMPP/Laragon.
2. Import `db_shift2.sql` melalui phpMyAdmin atau MySQL.
3. Pastikan nama database adalah `db_shift2`.
4. Periksa `src/main/java/com/bengkelshift/database/Koneksi.java`:
   - URL: `jdbc:mysql://localhost:3306/db_shift2`
   - USER: `root`
   - PASSWORD: kosong (sesuaikan jika MySQL lokal menggunakan password).
5. Buka folder project melalui Apache NetBeans sebagai project Maven.
6. Gunakan JDK 21, lalu Clean and Build dan jalankan project.

## Catatan pengujian dan ruang lingkup
- Seluruh source Java berhasil dikompilasi menggunakan JDK 21 dengan dependensi AbsoluteLayout yang disertakan.
- Koneksi langsung ke server MySQL tidak dapat diuji di lingkungan pengerjaan ini. Jalankan project dengan database lokal untuk memastikan koneksi dan hasil query.
- Perbaikan Sales dan Mekanik pada paket ini berfokus pada pembacaan dan penampilan data database ke JTable. Tombol tambah/ubah/hapus pada halaman yang form-nya belum lengkap tidak dianggap sudah memiliki implementasi CRUD.
- Desain GUI dan file NetBeans `.form` dipertahankan.
