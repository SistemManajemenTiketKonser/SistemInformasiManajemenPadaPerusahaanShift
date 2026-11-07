/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bengkelshift.dao;

import com.bengkelshift.database.Koneksi;
import com.bengkelshift.model.Transaksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Lenovo GK
 */
public class TransaksiDAO {
     public boolean tambahTransaksi(Transaksi transaksi) {
        String sql = "INSERT INTO transaksi "
                + "(id_transaksi, tanggal_transaksi, jenis_transaksi, "
                + "diskon, total_bayar, metode_pembayaran, "
                + "status_pembayaran, sales_id_karyawan, id_pelanggan) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, transaksi.getIdTransaksi());
            ps.setDate(2, transaksi.getTanggalTransaksi());
            ps.setString(3, transaksi.getJenisTransaksi());
            ps.setBigDecimal(4, transaksi.getDiskon());
            ps.setBigDecimal(5, transaksi.getTotalBayar());
            ps.setString(6, transaksi.getMetodePembayaran());
            ps.setString(7, transaksi.getStatusPembayaran());
            ps.setInt(8, transaksi.getSalesIdKaryawan());
            ps.setInt(9, transaksi.getIdPelanggan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan transaksi: " + e.getMessage());
            return false;
        }
    }
    public ArrayList<Transaksi> getAllTransaksi() {
        ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
        String sql = "SELECT * FROM transaksi";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Transaksi transaksi = new Transaksi();

                transaksi.setIdTransaksi(rs.getInt("id_transaksi"));
                transaksi.setTanggalTransaksi(
                        rs.getDate("tanggal_transaksi"));
                transaksi.setJenisTransaksi(
                        rs.getString("jenis_transaksi"));
                transaksi.setDiskon(rs.getBigDecimal("diskon"));
                transaksi.setTotalBayar(rs.getBigDecimal("total_bayar"));
                transaksi.setMetodePembayaran(
                        rs.getString("metode_pembayaran"));
                transaksi.setStatusPembayaran(
                        rs.getString("status_pembayaran"));
                transaksi.setSalesIdKaryawan(
                        rs.getInt("sales_id_karyawan"));
                transaksi.setIdPelanggan(rs.getInt("id_pelanggan"));

                daftarTransaksi.add(transaksi);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data transaksi: " + e.getMessage());
        }

        return daftarTransaksi;
    }
    public boolean updateTransaksi(Transaksi transaksi) {
        String sql = "UPDATE transaksi SET "
                + "tanggal_transaksi = ?, jenis_transaksi = ?, "
                + "diskon = ?, total_bayar = ?, metode_pembayaran = ?, "
                + "status_pembayaran = ?, sales_id_karyawan = ?, "
                + "id_pelanggan = ? WHERE id_transaksi = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, transaksi.getTanggalTransaksi());
            ps.setString(2, transaksi.getJenisTransaksi());
            ps.setBigDecimal(3, transaksi.getDiskon());
            ps.setBigDecimal(4, transaksi.getTotalBayar());
            ps.setString(5, transaksi.getMetodePembayaran());
            ps.setString(6, transaksi.getStatusPembayaran());
            ps.setInt(7, transaksi.getSalesIdKaryawan());
            ps.setInt(8, transaksi.getIdPelanggan());
            ps.setInt(9, transaksi.getIdTransaksi());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah data transaksi: " + e.getMessage());
            return false;
        }
    }
    public boolean hapusTransaksi(int idTransaksi) {
        String sql = "DELETE FROM transaksi WHERE id_transaksi = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTransaksi);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus transaksi: " + e.getMessage());
            return false;
        }
    }
}
