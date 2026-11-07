package com.bengkelshift.controller;

import com.bengkelshift.dao.JasaServisDAO;
import com.bengkelshift.model.JasaServis;
import java.util.ArrayList;

public class JasaServisController {

    private final JasaServisDAO dao = new JasaServisDAO();

    public boolean tambah(JasaServis data) {
        try {
            validasi(data);
            return dao.tambahJasaServis(data);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambah data: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<JasaServis> tampilSemua() {
        try {
            return dao.getAllJasaServis();
        } catch (Exception e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean ubah(JasaServis data) {
        try {
            if (data == null || data.getIdJasa() <= 0) {
                throw new IllegalArgumentException("ID data harus lebih dari 0.");
            }
            validasi(data);
            return dao.updateJasaServis(data);
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
            return dao.hapusJasaServis(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghapus data: " + e.getMessage());
            return false;
        }
    }

    private void validasi(JasaServis data) {
        if (data == null) throw new IllegalArgumentException("Objek data tidak boleh null.");
        if (data.getNamaJasa() == null || data.getNamaJasa().isBlank()) throw new IllegalArgumentException("Nama jasa wajib diisi.");
        if (data.getHargaJasa() == null || data.getHargaJasa().signum() < 0) throw new IllegalArgumentException("Harga jasa tidak valid.");
        if (data.getEstimasiMenit() <= 0) throw new IllegalArgumentException("Estimasi menit harus lebih dari 0.");
    }
}
