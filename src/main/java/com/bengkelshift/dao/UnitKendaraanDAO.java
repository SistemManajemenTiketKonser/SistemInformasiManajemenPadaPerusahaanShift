/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.dao;

import com.bengkelshift.database.Koneksi;
import com.bengkelshift.model.UnitKendaraan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Lenovo GK
 */
public class UnitKendaraanDAO {
    public boolean tambahUnitKendaraan(UnitKendaraan unit) {

        String sql = "INSERT INTO unit_kendaraan "
                + "(id_unit, tipe_kendaraan, warna, no_rangka, "
                + "no_mesin, tahun_produksi, status_unit, "
                + "tanggal_masuk, harga, id_transaksi) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, unit.getIdUnit());
            ps.setString(2, unit.getTipeKendaraan());
            ps.setString(3, unit.getWarna());
            ps.setString(4, unit.getNoRangka());
            ps.setString(5, unit.getNoMesin());
            ps.setInt(6, unit.getTahunProduksi());
            ps.setString(7, unit.getStatusUnit());
            ps.setDate(8, unit.getTanggalMasuk());
            ps.setBigDecimal(9, unit.getHarga());
            ps.setInt(10, unit.getIdTransaksi());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan unit kendaraan: "
                    + e.getMessage());
            return false;
        }
    }
    public ArrayList<UnitKendaraan> getAllUnitKendaraan() {
        ArrayList<UnitKendaraan> daftarUnit = new ArrayList<>();

        String sql = "SELECT * FROM unit_kendaraan";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                UnitKendaraan unit = new UnitKendaraan();

                unit.setIdUnit(rs.getInt("id_unit"));
                unit.setTipeKendaraan(rs.getString("tipe_kendaraan"));
                unit.setWarna(rs.getString("warna"));
                unit.setNoRangka(rs.getString("no_rangka"));
                unit.setNoMesin(rs.getString("no_mesin"));
                unit.setTahunProduksi(rs.getInt("tahun_produksi"));
                unit.setStatusUnit(rs.getString("status_unit"));
                unit.setTanggalMasuk(rs.getDate("tanggal_masuk"));
                unit.setHarga(rs.getBigDecimal("harga"));
                unit.setIdTransaksi(rs.getInt("id_transaksi"));

                daftarUnit.add(unit);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data unit kendaraan: "
                    + e.getMessage());
        }

        return daftarUnit;
    }
    public boolean updateUnitKendaraan(UnitKendaraan unit) {

        String sql = "UPDATE unit_kendaraan SET "
                + "tipe_kendaraan = ?, warna = ?, no_rangka = ?, "
                + "no_mesin = ?, tahun_produksi = ?, status_unit = ?, "
                + "tanggal_masuk = ?, harga = ?, id_transaksi = ? "
                + "WHERE id_unit = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, unit.getTipeKendaraan());
            ps.setString(2, unit.getWarna());
            ps.setString(3, unit.getNoRangka());
            ps.setString(4, unit.getNoMesin());
            ps.setInt(5, unit.getTahunProduksi());
            ps.setString(6, unit.getStatusUnit());
            ps.setDate(7, unit.getTanggalMasuk());
            ps.setBigDecimal(8, unit.getHarga());
            ps.setInt(9, unit.getIdTransaksi());
            ps.setInt(10, unit.getIdUnit());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah unit kendaraan: "
                    + e.getMessage());
            return false;
        }
    }
    public boolean hapusUnitKendaraan(int idUnit) {

        String sql = "DELETE FROM unit_kendaraan WHERE id_unit = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUnit);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus unit kendaraan: "
                    + e.getMessage());
            return false;
        }
    }
}
