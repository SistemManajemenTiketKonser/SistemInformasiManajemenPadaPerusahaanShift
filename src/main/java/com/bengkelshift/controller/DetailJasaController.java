package com.bengkelshift.controller;

import com.bengkelshift.dao.DetailJasaDAO;
import com.bengkelshift.model.DetailJasa;
import java.util.ArrayList;

public class DetailJasaController {

    private final DetailJasaDAO dao = new DetailJasaDAO();

    public boolean tambah(DetailJasa data) {
        try {
            validasi(data);
            return dao.tambahDetailJasa(data);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambah data: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<DetailJasa> tampilSemua() {
        try {
            return dao.getAllDetailJasa();
        } catch (Exception e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean ubah(DetailJasa data) {
        try {
            if (data == null || data.getIdDetail() <= 0) {
                throw new IllegalArgumentException("ID data harus lebih dari 0.");
            }
            validasi(data);
            return dao.updateDetailJasa(data);
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
            return dao.hapusDetailJasa(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghapus data: " + e.getMessage());
            return false;
        }
    }

    private void validasi(DetailJasa data) {
        if (data == null) throw new IllegalArgumentException("Objek data tidak boleh null.");
        if (data.getHargaJasaSaatServis() == null || data.getHargaJasaSaatServis().signum() < 0) throw new IllegalArgumentException("Harga jasa tidak valid.");
        if (data.getIdServis() <= 0 || data.getIdJasa() <= 0) throw new IllegalArgumentException("ID servis dan jasa harus valid.");
    }
}
