package com.bengkelshift.controller;

import com.bengkelshift.dao.SalesDAO;
import com.bengkelshift.model.Sales;
import java.util.ArrayList;

public class SalesController {

    private final SalesDAO dao = new SalesDAO();

    public boolean tambah(Sales data) {
        try {
            validasi(data);
            return dao.tambahSales(data);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambah data: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Sales> tampilSemua() {
        try {
            return dao.getAllSales();
        } catch (Exception e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean ubah(Sales data) {
        try {
            if (data == null || data.getIdKaryawan() <= 0) {
                throw new IllegalArgumentException("ID data harus lebih dari 0.");
            }
            validasi(data);
            return dao.updateSales(data);
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
            return dao.hapusSales(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi gagal: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghapus data: " + e.getMessage());
            return false;
        }
    }

    private void validasi(Sales data) {
        if (data == null) throw new IllegalArgumentException("Objek data tidak boleh null.");
        if (data.getNamaKaryawan() == null || data.getNamaKaryawan().isBlank()) throw new IllegalArgumentException("Nama sales wajib diisi.");
        if (data.getJumlahUnitTerjual() < 0) throw new IllegalArgumentException("Jumlah unit terjual tidak boleh negatif.");
    }
}
