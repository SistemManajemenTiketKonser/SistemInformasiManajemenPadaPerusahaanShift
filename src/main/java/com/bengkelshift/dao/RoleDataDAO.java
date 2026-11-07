package com.bengkelshift.dao;

import com.bengkelshift.database.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Query baca khusus untuk tabel-tabel pada dashboard Sales dan Mekanik. */
public class RoleDataDAO {

    private static final java.util.Map<String, String> QUERIES = java.util.Map.ofEntries(
        java.util.Map.entry("sales_transaksi",
            "SELECT t.id_transaksi, t.tanggal_transaksi, p.nama_pelanggan, k.nama_karyawan, t.jenis_transaksi, t.diskon, t.total_bayar, t.metode_pembayaran, t.status_pembayaran " +
            "FROM transaksi t JOIN pelanggan p ON p.id_pelanggan=t.id_pelanggan JOIN karyawan k ON k.id_karyawan=t.sales_id_karyawan ORDER BY t.id_transaksi"),
        java.util.Map.entry("sales_kendaraan",
            "SELECT v.id_kendaraan, v.no_polisi, v.tipe_kendaraan, v.no_mesin, v.no_rangka, p.nama_pelanggan, v.tahun_produksi " +
            "FROM kendaraan v JOIN pelanggan p ON p.id_pelanggan=v.id_pelanggan ORDER BY v.id_kendaraan"),
        java.util.Map.entry("sales_stok",
            "SELECT id_unit, tipe_kendaraan, warna, tanggal_masuk, status_unit, no_rangka, no_mesin, id_transaksi, tahun_produksi FROM unit_kendaraan ORDER BY id_unit"),
        java.util.Map.entry("sales_tarif",
            "SELECT id_jasa, nama_jasa, harga_jasa, deskripsi, estimasi_menit FROM jasa_servis ORDER BY id_jasa"),
        java.util.Map.entry("sales_sparepart",
            "SELECT id_sparepart, nama_sparepart, harga, satuan, id_servis, id_transaksi FROM sparepart ORDER BY id_sparepart"),
        java.util.Map.entry("sales_servis",
            "SELECT s.id_servis, s.tanggal_servis, v.no_polisi, s.kilometer, s.keluhan, k.nama_karyawan, s.jadwal_servis_berikutnya, s.diagnosis, v.tipe_kendaraan, s.id_transaksi " +
            "FROM servis s JOIN kendaraan v ON v.id_kendaraan=s.id_kendaraan JOIN karyawan k ON k.id_karyawan=s.mekanik_id_karyawan ORDER BY s.id_servis"),
        java.util.Map.entry("mekanik_transaksi",
            "SELECT t.id_transaksi, t.tanggal_transaksi, p.nama_pelanggan, k.nama_karyawan, t.jenis_transaksi, t.diskon, t.total_bayar, t.metode_pembayaran, t.status_pembayaran " +
            "FROM transaksi t JOIN pelanggan p ON p.id_pelanggan=t.id_pelanggan JOIN karyawan k ON k.id_karyawan=t.sales_id_karyawan ORDER BY t.id_transaksi"),
        java.util.Map.entry("mekanik_tarif",
            "SELECT id_jasa, nama_jasa, harga_jasa, deskripsi, estimasi_menit FROM jasa_servis ORDER BY id_jasa"),
        java.util.Map.entry("mekanik_sparepart",
            "SELECT id_sparepart, nama_sparepart, harga, satuan, id_servis, id_transaksi FROM sparepart ORDER BY id_sparepart"),
        java.util.Map.entry("mekanik_servis",
            "SELECT s.id_servis, s.tanggal_servis, v.no_polisi, s.kilometer, s.keluhan, k.nama_karyawan, s.jadwal_servis_berikutnya, s.diagnosis, v.tipe_kendaraan, s.id_transaksi " +
            "FROM servis s JOIN kendaraan v ON v.id_kendaraan=s.id_kendaraan JOIN karyawan k ON k.id_karyawan=s.mekanik_id_karyawan ORDER BY s.id_servis")
    );

    public List<Object[]> tampil(String jenis) throws SQLException {
        String sql = QUERIES.get(jenis);
        if (sql == null) throw new IllegalArgumentException("Jenis data tidak dikenal: " + jenis);
        List<Object[]> rows = new ArrayList<>();
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            int jumlahKolom = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Object[] row = new Object[jumlahKolom];
                for (int i = 0; i < jumlahKolom; i++) row[i] = rs.getObject(i + 1);
                rows.add(row);
            }
        }
        return rows;
    }
}
