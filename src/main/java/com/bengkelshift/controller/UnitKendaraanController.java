package com.bengkelshift.controller;

import com.bengkelshift.dao.UnitKendaraanDAO;
import com.bengkelshift.model.UnitKendaraan;
import java.util.ArrayList;

public class UnitKendaraanController {

    private final UnitKendaraanDAO dao = new UnitKendaraanDAO();

    public boolean tambah(UnitKendaraan data) {
        try {
            validasi(data);
            return dao.tambahUnitKendaraan(data);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambah data: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<UnitKendaraan> tampilSemua() {
        try {
            return dao.getAllUnitKendaraan();
        } catch (Exception e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean ubah(UnitKendaraan data) {
        try {
            if (data == null || data.getIdUnit() <= 0) {
                throw new IllegalArgumentException("ID data harus lebih dari 0.");
            }
            validasi(data);
            return dao.updateUnitKendaraan(data);
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
            return dao.hapusUnitKendaraan(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghapus data: " + e.getMessage());
            return false;
        }
    }

    private void validasi(UnitKendaraan data) {
        if (data == null) throw new IllegalArgumentException("Objek data tidak boleh null.");
        if (data.getTipeKendaraan() == null || data.getTipeKendaraan().isBlank()) throw new IllegalArgumentException("Tipe kendaraan wajib diisi.");
        if (data.getHarga() == null || data.getHarga().signum() < 0) throw new IllegalArgumentException("Harga unit tidak valid.");
        if (data.getTahunProduksi() <= 0) throw new IllegalArgumentException("Tahun produksi tidak valid.");
    }
}
