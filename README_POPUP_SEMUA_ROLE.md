# Pembaruan Tampilan Popup CRUD untuk Sales dan Mekanik

## Sales
- Halaman Pelanggan tetap menggunakan popup untuk tambah dan ubah.
- Halaman Kendaraan, Stok Unit, Tarif/Jasa Servis, Sparepart, dan Servis sekarang menampilkan tabel serta tombol aksi saja.
- Tombol Tambah dan Ubah membuka dialog popup. Tombol Hapus tetap berada di halaman utama dan meminta konfirmasi.
- Form input lama di samping tabel dihilangkan dari tampilan.
- Halaman Transaksi tetap menggunakan dialog popup yang sudah tersedia.

## Mekanik
- Halaman Servis menampilkan tabel dan tombol Ubah. Form pembaruan kilometer, keluhan, diagnosis, dan jadwal servis berikutnya dibuka melalui popup.
- Halaman Sparepart hanya menampilkan tabel; form input lama dihilangkan karena mekanik hanya memiliki hak lihat.
- Halaman Tarif dan Transaksi tetap bersifat lihat saja sesuai hak akses mekanik.

## Catatan
- Desain navigasi utama dan tabel dipertahankan.
- Perubahan layout dilakukan saat form dibuat, tanpa mengubah file `.form` NetBeans.
- Source Java berhasil dikompilasi menggunakan JDK 21. Pengujian runtime dengan MySQL perlu dilakukan pada komputer yang menjalankan database.
