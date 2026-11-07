package com.bengkelshift.controller;

import com.bengkelshift.database.Koneksi;
import com.bengkelshift.model.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuController {
    private final Scanner input;

    public MenuController(Scanner input) {
        this.input = input;
    }

    private record Modul(String nama, Object controller, Class<?> model, String idField) {}

    private final List<Modul> modul = List.of(
        new Modul("Pelanggan", new PelangganController(), Pelanggan.class, "idPelanggan"),
        new Modul("Kendaraan Pelanggan", new KendaraanController(), Kendaraan.class, "idKendaraan"),
        new Modul("Karyawan", new KaryawanController(), Karyawan.class, "idKaryawan"),
        new Modul("Admin", new AdminController(), Admin.class, "idKaryawan"),
        new Modul("Mekanik", new MekanikController(), Mekanik.class, "idKaryawan"),
        new Modul("Sales", new SalesController(), Sales.class, "idKaryawan"),
        new Modul("Servis", new ServisController(), Servis.class, "idServis"),
        new Modul("Sparepart", new SparepartController(), Sparepart.class, "idSparepart"),
        new Modul("Jasa Servis", new JasaServisController(), JasaServis.class, "idJasa"),
        new Modul("Detail Jasa", new DetailJasaController(), DetailJasa.class, "idDetail"),
        new Modul("Transaksi/Pembayaran", new TransaksiController(), Transaksi.class, "idTransaksi"),
        new Modul("Unit Kendaraan", new UnitKendaraanController(), UnitKendaraan.class, "idUnit")
    );

    private final DashboardController dashboardController = new DashboardController();
    public void jalankan(Karyawan pengguna, boolean admin) {
        List<Modul> menuPengguna = new ArrayList<>();
        if (admin) {
            menuPengguna.addAll(modul);
        } else {
            // Menu operasional untuk karyawan non-admin.
            for (Modul m : modul) {
                if (List.of("Pelanggan", "Kendaraan Pelanggan", "Servis",
                        "Sparepart", "Jasa Servis", "Detail Jasa",
                        "Transaksi/Pembayaran", "Unit Kendaraan").contains(m.nama())) {
                    menuPengguna.add(m);
                }
            }
        }

        boolean logout = false;
        while (!logout) {
            System.out.println("\n================================================");
            System.out.println(" SISTEM INFORMASI MANAJEMEN BENGKEL SHIFT");
            System.out.println("================================================");
            System.out.println("Pengguna : " + pengguna.getNamaKaryawan());
            System.out.println("Role     : " + (admin ? "ADMIN" : "KARYAWAN"));
            System.out.println("------------------------------------------------");
            int nomor = 1;
            if (admin) {
                System.out.println(nomor++ + ". Dashboard Admin");
            }
            for (Modul m : menuPengguna) {
                System.out.printf("%2d. Data %s%n", nomor++, m.nama());
            }
            System.out.println(nomor++ + ". Periksa koneksi database");
            System.out.println(" 0. Logout");
            int pilihan = bacaInt("Pilih menu: ");

            if (pilihan == 0) {
                logout = true;
                System.out.println("Logout berhasil. Sampai jumpa, " + pengguna.getNamaKaryawan() + ".");
            } else if (admin && pilihan == 1) {
                dashboardController.tampilkan(pengguna.getNamaKaryawan());
            } else {
                int indeksModul = pilihan - (admin ? 2 : 1);
                if (indeksModul >= 0 && indeksModul < menuPengguna.size()) {
                    jalankanSubmenu(menuPengguna.get(indeksModul));
                } else if (pilihan == menuPengguna.size() + (admin ? 2 : 1)) {
                    periksaKoneksi();
                } else {
                    System.out.println("Pilihan tidak tersedia.");
                }
            }
        }
    }

    private void jalankanSubmenu(Modul m) {
        int pilihan;
        do {
            System.out.println("\n--- KELOLA DATA " + m.nama().toUpperCase() + " ---");
            System.out.println("1. Tambah data");
            System.out.println("2. Tampilkan semua data");
            System.out.println("3. Ubah data");
            System.out.println("4. Hapus data");
            System.out.println("0. Kembali");
            pilihan = bacaInt("Pilih operasi: ");
            try {
                switch (pilihan) {
                    case 1 -> tambah(m);
                    case 2 -> tampil(m);
                    case 3 -> ubah(m);
                    case 4 -> hapus(m);
                    case 0 -> { }
                    default -> System.out.println("Pilihan tidak tersedia.");
                }
            } catch (Exception e) {
                System.out.println("Operasi gagal: " + pesanError(e));
            }
        } while (pilihan != 0);
    }

    private void tambah(Modul m) throws Exception {
        Object data = bacaObjek(m, false);
        boolean berhasil = (boolean) panggil(m.controller(), "tambah", m.model(), data);
        hasil(berhasil, "Data berhasil ditambahkan.", "Data gagal ditambahkan. Periksa pesan DAO dan relasi database.");
    }

    private void tampil(Modul m) throws Exception {
        Object hasil = panggil(m.controller(), "tampilSemua");
        List<?> daftar = (List<?>) hasil;
        System.out.println("\nJumlah data: " + daftar.size());
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data atau data tidak dapat dibaca dari database.");
            return;
        }
        for (Object item : daftar) {
            System.out.println("----------------------------------------");
            for (Field f : semuaField(m.model())) {
                f.setAccessible(true);
                System.out.println(label(f.getName()) + ": " + f.get(item));
            }
        }
        System.out.println("----------------------------------------");
    }

    private void ubah(Modul m) throws Exception {
        Object data = bacaObjek(m, true);
        boolean berhasil = (boolean) panggil(m.controller(), "ubah", m.model(), data);
        hasil(berhasil, "Data berhasil diperbarui.", "Data gagal diperbarui. Pastikan ID tersedia dan data valid.");
    }

    private void hapus(Modul m) throws Exception {
        int id = bacaInt("Masukkan " + label(m.idField()) + " yang akan dihapus: ");
        if (id <= 0) {
            System.out.println("ID harus berupa angka lebih dari 0.");
            return;
        }
        System.out.print("Yakin ingin menghapus data dengan ID " + id + "? (y/n): ");
        if (!input.nextLine().trim().equalsIgnoreCase("y")) {
            System.out.println("Penghapusan dibatalkan.");
            return;
        }
        Method method = m.controller().getClass().getMethod("hapus", int.class);
        boolean berhasil = (boolean) method.invoke(m.controller(), id);
        hasil(berhasil, "Data berhasil dihapus.", "Data gagal dihapus. Pastikan ID tersedia dan tidak terikat relasi lain.");
    }

    private Object bacaObjek(Modul m, boolean modeUbah) throws Exception {
        Object data = m.model().getDeclaredConstructor().newInstance();
        System.out.println(modeUbah ? "Masukkan data baru (termasuk ID data yang akan diubah):" : "Masukkan data baru:");
        for (Field f : semuaField(m.model())) {
            f.setAccessible(true);
            String nama = label(f.getName());
            Object nilai = bacaNilai(f.getType(), nama);
            f.set(data, nilai);
        }
        return data;
    }

    private Object bacaNilai(Class<?> tipe, String nama) {
        while (true) {
            System.out.print(nama + ": ");
            String teks = input.nextLine().trim();
            try {
                if (tipe == String.class) {
                    if (teks.isBlank()) throw new IllegalArgumentException("Nilai tidak boleh kosong.");
                    return teks;
                }
                if (tipe == int.class || tipe == Integer.class) return Integer.parseInt(teks);
                if (tipe == BigDecimal.class) return new BigDecimal(teks);
                if (tipe == Date.class) return Date.valueOf(teks);
                throw new IllegalArgumentException("Tipe data belum didukung: " + tipe.getSimpleName());
            } catch (Exception e) {
                System.out.println("Input tidak valid. " + petunjuk(tipe));
            }
        }
    }

    private String petunjuk(Class<?> tipe) {
        if (tipe == int.class || tipe == Integer.class) return "Masukkan bilangan bulat.";
        if (tipe == BigDecimal.class) return "Masukkan angka desimal, contoh 150000 atau 150000.50.";
        if (tipe == Date.class) return "Gunakan format tanggal YYYY-MM-DD.";
        return "Silakan ulangi input.";
    }

    private int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat.");
            }
        }
    }

    private Object panggil(Object target, String nama) throws Exception {
        Method method = target.getClass().getMethod(nama);
        return method.invoke(target);
    }

    private Object panggil(Object target, String nama, Class<?> tipe, Object argumen) throws Exception {
        return target.getClass().getMethod(nama, tipe).invoke(target, argumen);
    }

    private List<Field> semuaField(Class<?> tipe) {
        List<Field> fields = new ArrayList<>();
        for (Class<?> c = tipe; c != null && c != Object.class; c = c.getSuperclass()) {
            for (Field f : c.getDeclaredFields()) {
                if (!java.lang.reflect.Modifier.isStatic(f.getModifiers())) fields.add(f);
            }
        }
        return fields;
    }

    private String label(String nama) {
        return nama.replaceAll("([a-z])([A-Z])", "$1 $2").replace('_', ' ');
    }

    private void periksaKoneksi() {
        try (Connection conn = Koneksi.getConnection()) {
            if (conn != null && conn.isValid(3)) {
                System.out.println("Koneksi ke database berhasil.");
            } else {
                System.out.println("Koneksi database tidak valid.");
            }
        } catch (Exception e) {
            System.out.println("Koneksi database gagal: " + pesanError(e));
        }
    }

    private String pesanError(Throwable e) {
        Throwable penyebab = e;
        while (penyebab.getCause() != null) penyebab = penyebab.getCause();
        return penyebab.getMessage() == null ? penyebab.toString() : penyebab.getMessage();
    }

    private void hasil(boolean berhasil, String sukses, String gagal) {
        System.out.println(berhasil ? sukses : gagal);
    }
}
