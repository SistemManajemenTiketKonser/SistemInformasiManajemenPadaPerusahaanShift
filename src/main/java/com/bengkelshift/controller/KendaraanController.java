package com.bengkelshift.controller;

import com.bengkelshift.dao.PelangganDAO;
import com.bengkelshift.model.Pelanggan;
import com.bengkelshift.dao.KendaraanDAO;
import com.bengkelshift.model.Kendaraan;
import java.util.ArrayList;

public class KendaraanController {

    private final KendaraanDAO dao = new KendaraanDAO();

    public boolean tambah(Kendaraan data) {
        try {
            validasi(data);
            return dao.tambahKendaraan(data);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambah data: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Pelanggan> daftarPelanggan() {
        PelangganDAO pelangganDAO = new PelangganDAO();
        return pelangganDAO.getAllPelanggan();
    }

    public boolean ubah(Kendaraan data) {
        try {
            if (data == null || data.getIdKendaraan() <= 0) {
                throw new IllegalArgumentException("ID data harus lebih dari 0.");
            }
            validasi(data);
            return dao.updateKendaraan(data);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat mengubah data: " + e.getMessage());
            return false;
        }
    }

    public boolean hapus(int id) {
        try {
            if (id <= 0) throw new IllegalArgumentException("ID harus lebih dari 0.");
            return dao.hapusKendaraan(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghapus data: " + e.getMessage());
            return false;
        }
    }

    private void validasi(Kendaraan data) {
        if (data == null) throw new IllegalArgumentException("Objek data tidak boleh null.");
        if (data.getNoPolisi() == null || data.getNoPolisi().isBlank()) throw new IllegalArgumentException("Nomor polisi wajib diisi.");
        if (data.getTipeKendaraan() == null || data.getTipeKendaraan().isBlank()) throw new IllegalArgumentException("Tipe kendaraan wajib diisi.");
        if (data.getTahunProduksi() <= 0) throw new IllegalArgumentException("Tahun produksi tidak valid.");
        if (data.getIdPelanggan() <= 0) throw new IllegalArgumentException("ID pelanggan harus lebih dari 0.");
    }
}
