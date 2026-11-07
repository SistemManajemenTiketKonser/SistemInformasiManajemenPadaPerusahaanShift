package com.bengkelshift.controller;

import com.bengkelshift.dao.RoleDataDAO;
import java.util.Collections;
import java.util.List;

/** Controller pembacaan data untuk halaman Sales dan Mekanik. */
public class RoleDataController {
    private final RoleDataDAO dao = new RoleDataDAO();

    public List<Object[]> tampil(String jenis) {
        try {
            return dao.tampil(jenis);
        } catch (Exception e) {
            System.err.println("Gagal memuat " + jenis + ": " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
