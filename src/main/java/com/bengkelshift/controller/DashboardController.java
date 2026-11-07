package com.bengkelshift.controller;

import com.bengkelshift.database.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DashboardController {
    private final Map<String, String> tabel = new LinkedHashMap<>();

    public DashboardController() {
        tabel.put("Pelanggan", "pelanggan");
        tabel.put("Kendaraan pelanggan", "kendaraan");
        tabel.put("Karyawan", "karyawan");
        tabel.put("Servis", "servis");
        tabel.put("Sparepart", "sparepart");
        tabel.put("Jasa servis", "jasa_servis");
        tabel.put("Transaksi", "transaksi");
    }

    public void tampilkan(String namaAdmin) {
        System.out.println("\n====================================================");
        System.out.println("          DASHBOARD ADMIN - BENGKEL SHIFT");
        System.out.println("====================================================");
        System.out.println("Selamat datang, " + namaAdmin + "!");
        System.out.println("\nRINGKASAN DATA");
        System.out.println("----------------------------------------------------");

        try (Connection conn = Koneksi.getConnection()) {
            for (Map.Entry<String, String> item : tabel.entrySet()) {
                String sql = "SELECT COUNT(*) FROM " + item.getValue();
                try (PreparedStatement ps = conn.prepareStatement(sql);
                     ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        System.out.printf("%-25s : %d%n", item.getKey(), rs.getInt(1));
                    }
                }
            }
            System.out.println("----------------------------------------------------");
            System.out.println("Dashboard berhasil mengambil ringkasan dari database.");
        } catch (SQLException e) {
            System.out.println("Dashboard belum dapat mengambil data: " + e.getMessage());
            System.out.println("Periksa koneksi dan pastikan seluruh tabel tersedia.");
        }
        System.out.println("====================================================");
    }
}
