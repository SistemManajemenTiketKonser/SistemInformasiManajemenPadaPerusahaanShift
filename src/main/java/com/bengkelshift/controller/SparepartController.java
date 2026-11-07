package com.bengkelshift.controller;

import com.bengkelshift.dao.SparepartDAO;
import com.bengkelshift.model.Sparepart;
import java.util.ArrayList;

public class SparepartController {

    private final SparepartDAO dao = new SparepartDAO();

    public boolean tambah(Sparepart data) {
        try {
            validasi(data);
            return dao.tambahSparepart(data);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambah data: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Sparepart> tampilSemua() {
        try {
            return dao.getAllSparepart();
        } catch (Exception e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean ubah(Sparepart data) {
        try {
            if (data == null || data.getIdSparepart() <= 0) {
                throw new IllegalArgumentException("ID data harus lebih dari 0.");
            }
            validasi(data);
            return dao.updateSparepart(data);
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
            return dao.hapusSparepart(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghapus data: " + e.getMessage());
            return false;
        }
    }

    private void validasi(Sparepart data) {
        if (data == null) throw new IllegalArgumentException("Objek data tidak boleh null.");
        if (data.getNamaSparepart() == null || data.getNamaSparepart().isBlank()) throw new IllegalArgumentException("Nama sparepart wajib diisi.");
        if (data.getHarga() == null || data.getHarga().signum() < 0) throw new IllegalArgumentException("Harga sparepart tidak valid.");
        if (data.getSatuan() == null || data.getSatuan().isBlank()) throw new IllegalArgumentException("Satuan wajib diisi.");
    }
}
