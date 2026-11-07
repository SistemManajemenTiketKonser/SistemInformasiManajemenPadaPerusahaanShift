# Perbaikan CRUD Sistem Informasi Manajemen Auto SHIFT

## Perubahan
- Menambahkan `CrudDAO`, `CrudController`, dan `CrudBinding` untuk operasi tambah, ubah, dan hapus melalui `PreparedStatement`.
- Menghubungkan tombol CRUD halaman Admin, Sales, dan halaman Servis role Mekanik ke controller.
- Memperbaiki proses tambah pelanggan karena kolom ID pada dump SQL tidak didefinisikan `AUTO_INCREMENT`; ID berikutnya sekarang dibuat dari nilai maksimum + 1.
- Menambahkan transaksi database untuk penyimpanan data pegawai beserta subtype Admin/Sales/Mekanik.
- Menampilkan pesan kesalahan SQL/relasi foreign key kepada pengguna.
- Menjaga file GUI `.form` dan tata letak yang sudah ada.

## Cara kerja tombol
- Tambah: tombol membuka dialog input sesuai kolom tabel, kemudian menyimpan data ke MySQL.
- Ubah: pilih baris pada JTable, tekan Ubah, ubah nilai di dialog, lalu simpan.
- Hapus: pilih baris pada JTable, tekan Hapus, lalu konfirmasi.
- Setelah operasi berhasil, tabel dimuat ulang.
- ID utama dibuat otomatis berdasarkan `MAX(ID) + 1`, karena struktur database `db_shift2.sql` tidak menggunakan `AUTO_INCREMENT`.

## Hak akses
- Admin: CRUD untuk pegawai, kendaraan, stok unit, tarif jasa, sparepart, servis, transaksi, dan pelanggan.
- Sales: CRUD untuk pelanggan, kendaraan, stok unit, tarif jasa, sparepart, servis, dan transaksi.
- Mekanik: dapat mengubah kilometer, keluhan, diagnosis, dan jadwal servis berikutnya. Halaman tarif, transaksi, dan sparepart bersifat baca-saja; tombol perubahan pembayaran/sparepart dinonaktifkan untuk menjaga pemisahan hak akses.

## Menjalankan project
1. Import `db_shift2.sql` ke MySQL/phpMyAdmin.
2. Pastikan nama database adalah `db_shift2`.
3. Periksa `src/main/java/com/bengkelshift/database/Koneksi.java`. Default project menggunakan `root` tanpa password. Sesuaikan `USER` dan `PASSWORD` jika konfigurasi MySQL berbeda.
4. Buka project di NetBeans dan jalankan **Clean and Build**.
5. Uji Tambah, Ubah, dan Hapus pada data uji terlebih dahulu.

## Catatan pengujian
Source Java dikompilasi menggunakan JDK 21 dan library AbsoluteLayout yang disertakan. Koneksi langsung ke server MySQL tidak dapat diuji pada lingkungan perbaikan ini. Penghapusan data yang masih direferensikan oleh tabel lain akan ditolak oleh foreign key; aplikasi akan menampilkan pesan agar data turunannya ditangani terlebih dahulu.

## Perbaikan lanjutan relasi Foreign Key
- Kolom relasi seperti pelanggan, kendaraan, transaksi, servis, sales, dan mekanik kini dipilih melalui daftar pilihan yang diambil langsung dari tabel induk. Pengguna tidak perlu mengetik ID relasi secara manual.
- Pesan kesalahan dibedakan berdasarkan operasi: tambah/perbarui dengan relasi tidak valid tidak lagi menampilkan pesan seolah-olah data sedang dihapus.
- Pesan penghapusan tetap menjelaskan bahwa data induk tidak dapat dihapus selama masih digunakan oleh data turunannya.
- Pemanggilan binding untuk halaman tanpa tombol Tambah (misalnya halaman servis Mekanik) juga diperbaiki agar tombol Edit tetap terhubung.
