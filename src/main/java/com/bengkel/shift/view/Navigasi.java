package com.bengkel.shift.view;

import com.bengkelshift.controller.LoginController;
import com.bengkelshift.dao.MekanikDAO;
import com.bengkelshift.dao.SalesDAO;
import com.bengkelshift.model.Karyawan;
import com.bengkelshift.model.Mekanik;
import com.bengkelshift.model.Sales;
import java.util.function.Supplier;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Pusat navigasi antar GUI.
 *
 * Semua frame hanya perlu memanggil satu method menu sesuai perannya
 * (menuAdmin / menuSales / menuMekanik / menuPelanggan) di constructor.
 * Frame tujuan dibuat saat tombol diklik, lalu frame lama ditutup (dispose).
 */
public final class Navigasi {

    public enum Peran { ADMIN, SALES, MEKANIK }

    private static Karyawan penggunaAktif;
    private static Peran peranAktif;

    private Navigasi() {
    }

    // ------------------------------------------------------------------
    // Sesi pengguna
    // ------------------------------------------------------------------

    public static Karyawan getPenggunaAktif() {
        return penggunaAktif;
    }

    public static Peran getPeranAktif() {
        return peranAktif;
    }

    /**
     * Dipanggil setelah login berhasil. Menentukan peran lalu membuka
     * halaman awal sesuai peran.
     *
     * @return true jika berhasil masuk ke halaman utama
     */
    public static boolean masuk(JFrame dari, Karyawan pengguna) {
        Peran peran = tentukanPeran(pengguna);

        if (peran == null) {
            JOptionPane.showMessageDialog(
                    dari,
                    "Akun ini belum terdaftar sebagai Admin, Sales, atau Mekanik.\n"
                    + "Hubungi admin untuk mengatur peran akun Anda.",
                    "Peran Tidak Ditemukan",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        penggunaAktif = pengguna;
        peranAktif = peran;

        switch (peran) {
            case ADMIN -> buka(dari, new com.bengkel.shift.view.admin.transaksi());
            case SALES -> buka(dari, new com.bengkel.shift.view.sales.main());
            case MEKANIK -> buka(dari, new com.bengkel.shift.view.mekanik.servs());
        }
        return true;
    }

    private static Peran tentukanPeran(Karyawan pengguna) {
        if (pengguna == null) {
            return null;
        }

        if (new LoginController().isAdmin(pengguna)) {
            return Peran.ADMIN;
        }

        for (Sales s : new SalesDAO().getAllSales()) {
            if (s.getIdKaryawan() == pengguna.getIdKaryawan()) {
                return Peran.SALES;
            }
        }

        for (Mekanik m : new MekanikDAO().getAllMekanik()) {
            if (m.getIdKaryawan() == pengguna.getIdKaryawan()) {
                return Peran.MEKANIK;
            }
        }

        return null;
    }

    public static void logout(JFrame dari) {
        int pilihan = JOptionPane.showConfirmDialog(
                dari,
                "Apakah Anda yakin ingin logout?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (pilihan == JOptionPane.YES_OPTION) {
            penggunaAktif = null;
            peranAktif = null;
            buka(dari, new login());
        }
    }

    // ------------------------------------------------------------------
    // Perpindahan frame
    // ------------------------------------------------------------------

    public static void buka(JFrame dari, JFrame tujuan) {
        if (dari instanceof login) {
            tujuan.setLocationRelativeTo(null);
        } else {
            tujuan.setExtendedState(dari.getExtendedState());
            tujuan.setLocation(dari.getLocation());
        }

        tujuan.setVisible(true);
        dari.dispose();
    }

    private static void pasang(JFrame dari, JButton tombol,
            Class<? extends JFrame> kelasTujuan,
            Supplier<? extends JFrame> pembuat) {

        if (tombol == null) {
            return;
        }

        tombol.addActionListener(evt -> {
            // Sudah berada di halaman yang sama: tidak perlu berpindah.
            if (dari.getClass() == kelasTujuan) {
                return;
            }
            buka(dari, pembuat.get());
        });
    }

    private static void pasangLogout(JFrame dari, JButton tombol) {
        if (tombol != null) {
            tombol.addActionListener(evt -> logout(dari));
        }
    }

    // ------------------------------------------------------------------
    // Menu per peran
    // ------------------------------------------------------------------

    /** Sidebar Admin. Dashboard dan Transaksi sama-sama menuju halaman transaksi admin. */
    public static void menuAdmin(JFrame f,
            JButton dashboard, JButton pelanggan, JButton transaksi,
            JButton kendaraan, JButton stokUnit, JButton tarif,
            JButton sparePart, JButton servis, JButton pegawai,
            JButton logout) {

        pasang(f, dashboard, com.bengkel.shift.view.admin.transaksi.class,
                com.bengkel.shift.view.admin.transaksi::new);
        pasang(f, transaksi, com.bengkel.shift.view.admin.transaksi.class,
                com.bengkel.shift.view.admin.transaksi::new);
        pasang(f, pelanggan, com.bengkel.shift.view.admin.pelanggan.class,
                com.bengkel.shift.view.admin.pelanggan::new);
        pasang(f, kendaraan, com.bengkel.shift.view.admin.kendaraan.class,
                com.bengkel.shift.view.admin.kendaraan::new);
        pasang(f, stokUnit, com.bengkel.shift.view.admin.stok.class,
                com.bengkel.shift.view.admin.stok::new);
        pasang(f, tarif, com.bengkel.shift.view.admin.taridf.class,
                com.bengkel.shift.view.admin.taridf::new);
        pasang(f, sparePart, com.bengkel.shift.view.admin.spart.class,
                com.bengkel.shift.view.admin.spart::new);
        pasang(f, servis, com.bengkel.shift.view.admin.servs.class,
                com.bengkel.shift.view.admin.servs::new);
        pasang(f, pegawai, com.bengkel.shift.view.admin.pegawai.class,
                com.bengkel.shift.view.admin.pegawai::new);
        pasangLogout(f, logout);
    }

    /** Sidebar Sales. Halaman Transaksi sales adalah kelas sales.main. */
    public static void menuSales(JFrame f,
            JButton pelanggan, JButton transaksi, JButton kendaraan,
            JButton stokUnit, JButton tarif, JButton sparePart,
            JButton servis, JButton logout) {

        pasang(f, transaksi, com.bengkel.shift.view.sales.main.class,
                com.bengkel.shift.view.sales.main::new);
        pasang(f, pelanggan, com.bengkel.shift.view.sales.pelanggan.class,
                com.bengkel.shift.view.sales.pelanggan::new);
        pasang(f, kendaraan, com.bengkel.shift.view.sales.kendaraan.class,
                com.bengkel.shift.view.sales.kendaraan::new);
        pasang(f, stokUnit, com.bengkel.shift.view.sales.stok.class,
                com.bengkel.shift.view.sales.stok::new);
        pasang(f, tarif, com.bengkel.shift.view.sales.tarif.class,
                com.bengkel.shift.view.sales.tarif::new);
        pasang(f, sparePart, com.bengkel.shift.view.sales.sparepart.class,
                com.bengkel.shift.view.sales.sparepart::new);
        pasang(f, servis, com.bengkel.shift.view.sales.servis.class,
                com.bengkel.shift.view.sales.servis::new);
        pasangLogout(f, logout);
    }

    /** Sidebar Mekanik. */
    public static void menuMekanik(JFrame f,
            JButton transaksi, JButton tarif, JButton sparePart,
            JButton servis, JButton logout) {

        pasang(f, servis, com.bengkel.shift.view.mekanik.servs.class,
                com.bengkel.shift.view.mekanik.servs::new);
        pasang(f, sparePart, com.bengkel.shift.view.mekanik.spart.class,
                com.bengkel.shift.view.mekanik.spart::new);
        pasang(f, tarif, com.bengkel.shift.view.mekanik.tarif.class,
                com.bengkel.shift.view.mekanik.tarif::new);
        pasang(f, transaksi, com.bengkel.shift.view.mekanik.transaksi.class,
                com.bengkel.shift.view.mekanik.transaksi::new);
        pasangLogout(f, logout);
    }
}
