package com.bengkelshift.controller;

import com.bengkelshift.dao.CrudDAO;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bengkelshift.database.Koneksi;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.TableModel;

/** Menghubungkan tombol GUI Swing ke Controller CRUD tanpa mengubah desain form. */
public final class CrudBinding {
    private CrudBinding() {}

    public static void bind(Object frame, String key, String addButton, String editButton, String deleteButton) {
        try {
            CrudController controller = new CrudController();
            JTable table = (JTable) field(frame, "jTable1");
            JButton add = addButton == null ? null : (JButton) field(frame, addButton);
            JButton edit = editButton == null ? null : (JButton) field(frame, editButton);
            JButton delete = deleteButton == null ? null : (JButton) field(frame, deleteButton);
            if (add != null) add.addActionListener(e -> {
                try {
                    Map<String,Object> values = form(frame, key, controller, null);
                    if (values == null) return;
                    if (controller.tambah(key, values)) { refresh(frame); info(frame,"Data berhasil ditambahkan."); }
                } catch (Exception ex) { error(frame, ex, "menambahkan"); }
            });
            if (edit != null) edit.addActionListener(e -> {
                try {
                    int id = selectedId(table);
                    if (id <= 0) { info(frame,"Pilih baris data yang ingin diubah terlebih dahulu."); return; }
                    Map<String,Object> current = controller.get(key,id);
                    if (current == null) { info(frame,"Data tidak ditemukan. Muat ulang tabel."); return; }
                    Map<String,Object> values = form(frame,key,controller,current);
                    if (values == null) return;
                    if (controller.ubah(key,id,values)) { refresh(frame); info(frame,"Data berhasil diperbarui."); }
                } catch (Exception ex) { error(frame, ex, "memperbarui"); }
            });
            if (delete != null) delete.addActionListener(e -> {
                try {
                    int id = selectedId(table);
                    if (id <= 0) { info(frame,"Pilih baris data yang ingin dihapus terlebih dahulu."); return; }
                    int ok = JOptionPane.showConfirmDialog((Component)frame,"Hapus data dengan ID "+id+"?","Konfirmasi Hapus",JOptionPane.YES_NO_OPTION);
                    if (ok == JOptionPane.YES_OPTION && controller.hapus(key,id)) { refresh(frame); info(frame,"Data berhasil dihapus."); }
                } catch (Exception ex) { error(frame,ex,"menghapus"); }
            });
        } catch (Exception ex) {
            System.err.println("CRUD binding gagal untuk "+key+": "+ex.getMessage());
        }
    }

    /** Mengganti form input lama di samping tabel dengan toolbar CRUD; input hanya muncul di popup. */
    public static void popupTableLayout(Object frame, String addName, String editName, String deleteName) {
        try {
            JPanel content = (JPanel) field(frame, "jPanel3");
            JScrollPane scroll = (JScrollPane) field(frame, "jScrollPane1");
            JLabel heading = (JLabel) field(frame, "jLabel4");
            // Form lama pada halaman Admin berada di jPanel4, di luar area tabel.
            // Sembunyikan seluruh panel tersebut agar tidak ada JTextField tersisa.
            try {
                Object oldForm = field(frame, "jPanel4");
                if (oldForm instanceof JPanel panel) {
                    panel.setVisible(false);
                    java.awt.Container parent = panel.getParent();
                    if (parent != null) {
                        parent.revalidate();
                        parent.repaint();
                    }
                }
            } catch (NoSuchFieldException ignored) {
                // Beberapa halaman memang tidak memiliki panel form terpisah.
            }
            JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
            toolbar.setOpaque(false);
            if (addName != null) toolbar.add((JButton) field(frame, addName));
            if (editName != null) toolbar.add((JButton) field(frame, editName));
            if (deleteName != null) toolbar.add((JButton) field(frame, deleteName));
            content.removeAll();
            content.setLayout(new BorderLayout(8, 8));
            JPanel top = new JPanel(new BorderLayout(4, 4));
            top.setOpaque(false);
            if (heading != null) top.add(heading, BorderLayout.NORTH);
            if (toolbar.getComponentCount() > 0) top.add(toolbar, BorderLayout.SOUTH);
            content.add(top, BorderLayout.NORTH);
            content.add(scroll, BorderLayout.CENTER);
            content.revalidate();
            content.repaint();
            if (frame instanceof Window window) window.pack();
        } catch (Exception ex) {
            System.err.println("Gagal mengubah halaman menjadi popup: " + ex.getMessage());
        }
    }

    /** Menghilangkan form input pada halaman yang hanya memiliki hak lihat. */
    public static void tableOnlyLayout(Object frame) {
        try {
            JPanel content = (JPanel) field(frame, "jPanel3");
            JScrollPane scroll = (JScrollPane) field(frame, "jScrollPane1");
            JLabel heading = (JLabel) field(frame, "jLabel4");
            content.removeAll();
            content.setLayout(new BorderLayout(8, 8));
            if (heading != null) content.add(heading, BorderLayout.NORTH);
            content.add(scroll, BorderLayout.CENTER);
            content.revalidate();
            content.repaint();
            if (frame instanceof Window window) window.pack();
        } catch (Exception ex) {
            System.err.println("Gagal merapikan halaman tabel: " + ex.getMessage());
        }
    }

    public static void disable(Object frame, String buttonName, String reason) {
        try {
            JButton b = (JButton) field(frame, buttonName);
            if (b != null) { b.setEnabled(false); b.setToolTipText(reason); }
        } catch (Exception e) { System.err.println("Gagal mengatur hak akses tombol: " + e.getMessage()); }
    }

    private record RefOption(int id, String label) {
        @Override public String toString() { return label; }
    }

    // Kolom foreign key ditampilkan sebagai pilihan dari tabel induk agar pengguna
    // tidak perlu menebak atau mengetik ID yang belum tentu tersedia.
    private static final Map<String,String> FK_SQL = Map.of(
        "id_pelanggan", "SELECT id_pelanggan, CONCAT('Pelanggan #',id_pelanggan,' - ',nama_pelanggan) FROM pelanggan ORDER BY id_pelanggan",
        "id_transaksi", "SELECT id_transaksi, CONCAT('Transaksi #',id_transaksi,' - ',tanggal_transaksi,' - ',jenis_transaksi) FROM transaksi ORDER BY id_transaksi",
        "id_kendaraan", "SELECT id_kendaraan, CONCAT('Kendaraan #',id_kendaraan,' - ',no_polisi,' - ',tipe_kendaraan) FROM kendaraan ORDER BY id_kendaraan",
        "mekanik_id_karyawan", "SELECT m.mekanik_id_karyawan, CONCAT('Mekanik #',m.mekanik_id_karyawan,' - ',k.nama_karyawan) FROM mekanik m JOIN karyawan k ON k.id_karyawan=m.mekanik_id_karyawan ORDER BY m.mekanik_id_karyawan",
        "sales_id_karyawan", "SELECT s.sales_id_karyawan, CONCAT('Sales #',s.sales_id_karyawan,' - ',k.nama_karyawan) FROM sales s JOIN karyawan k ON k.id_karyawan=s.sales_id_karyawan ORDER BY s.sales_id_karyawan",
        "id_servis", "SELECT id_servis, CONCAT('Servis #',id_servis,' - ',tanggal_servis) FROM servis ORDER BY id_servis"
    );

    private static List<RefOption> loadReferences(String field) throws SQLException {
        String sql = FK_SQL.get(field);
        if (sql == null) return List.of();
        List<RefOption> options = new ArrayList<>();
        try (Connection c = Koneksi.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) options.add(new RefOption(rs.getInt(1), rs.getString(2)));
        }
        return options;
    }

    private static Map<String,Object> form(Object frame,String key,CrudController c,Map<String,Object> current)throws Exception {
        CrudDAO.Entity entity=c.entity(key);
        JPanel panel=new JPanel(new GridBagLayout());
        GridBagConstraints g=new GridBagConstraints();g.insets=new Insets(4,5,4,5);g.fill=GridBagConstraints.HORIZONTAL;
        Map<String,JComponent> inputs=new LinkedHashMap<>();
        int row=0;
        for(CrudDAO.Field f:entity.fields()){
            g.gridx=0;g.gridy=row;g.weightx=0;panel.add(new JLabel(f.label()+":"),g);
            Object existing = current == null ? null : current.get(f.name());
            JComponent input;
            if (FK_SQL.containsKey(f.name())) {
                JComboBox<RefOption> combo = new JComboBox<>();
                List<RefOption> refs = loadReferences(f.name());
                for (RefOption ref : refs) combo.addItem(ref);
                if (existing != null) {
                    for (int i=0;i<combo.getItemCount();i++) if (combo.getItemAt(i).id()==Integer.parseInt(existing.toString())) { combo.setSelectedIndex(i); break; }
                }
                if (refs.isEmpty()) combo.setToolTipText("Belum ada data induk. Tambahkan data terkait terlebih dahulu.");
                input=combo;
            } else {
                JTextField t=new JTextField(existing == null ? "" : String.valueOf(existing),22);
                input=t;
            }
            g.gridx=1;g.weightx=1;panel.add(input,g);inputs.put(f.name(),input);row++;
        }
        JComboBox<String> role=null;JTextField specialization=null;
        if(key.equals("pegawai")){
            g.gridx=0;g.gridy=row;g.weightx=0;panel.add(new JLabel("Peran:"),g);
            role=new JComboBox<>(new String[]{"Pegawai","Admin","Sales","Mekanik"});
            if(current!=null){Object p=current.get("__role");if(p!=null)role.setSelectedItem(p.toString());}
            g.gridx=1;g.weightx=1;panel.add(role,g);row++;
            g.gridx=0;g.gridy=row;g.weightx=0;panel.add(new JLabel("Spesialisasi (khusus Mekanik):"),g);
            specialization=new JTextField(current!=null&&current.get("__spesialisasi")!=null?current.get("__spesialisasi").toString():"Umum",22);g.gridx=1;g.weightx=1;panel.add(specialization,g);
        }
        int result=JOptionPane.showConfirmDialog((Component)frame,new JScrollPane(panel),current==null?"Tambah Data":"Ubah Data",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
        if(result!=JOptionPane.OK_OPTION)return null;
        Map<String,Object> values=new LinkedHashMap<>();
        for(CrudDAO.Field f:entity.fields()){
            JComponent component=inputs.get(f.name());
            if(component instanceof JComboBox<?> combo && FK_SQL.containsKey(f.name())) {
                Object selected=combo.getSelectedItem();
                if(selected==null) throw new IllegalArgumentException("Belum ada data referensi untuk "+f.label()+". Tambahkan data induknya terlebih dahulu.");
                values.put(f.name(),((RefOption)selected).id());
                continue;
            }
            String raw=((JTextField)component).getText().trim();
            if(raw.isEmpty()&&f.nullable()){values.put(f.name(),null);continue;}
            if(raw.isEmpty())throw new IllegalArgumentException(f.label()+" wajib diisi.");
            Object value=switch(f.type()){
                case "int" -> Integer.valueOf(raw);
                case "decimal" -> new BigDecimal(raw.replace(",","."));
                case "date" -> Date.valueOf(raw);
                default -> raw;
            };
            values.put(f.name(),value);
        }
        if(key.equals("pegawai")){
            values.put("__role",role.getSelectedItem().toString());
            values.put("__spesialisasi",specialization.getText().trim().isEmpty()?"Umum":specialization.getText().trim());
        }
        return values;
    }
    private static int selectedId(JTable table){if(table==null||table.getSelectedRow()<0)return -1;int r=table.convertRowIndexToModel(table.getSelectedRow());Object v=table.getModel().getValueAt(r,0);return Integer.parseInt(v.toString());}
    private static Object field(Object o,String name)throws Exception{Class<?> c=o.getClass();while(c!=null){try{Field f=c.getDeclaredField(name);f.setAccessible(true);return f.get(o);}catch(NoSuchFieldException e){c=c.getSuperclass();}}return null;}
    private static void refresh(Object frame){try{Method m=frame.getClass().getDeclaredMethod("muatTabel");m.setAccessible(true);m.invoke(frame);}catch(Exception e){System.err.println("Gagal refresh tabel: "+e.getMessage());}}
    private static void info(Object f,String s){JOptionPane.showMessageDialog((Component)f,s,"Informasi",JOptionPane.INFORMATION_MESSAGE);}
    private static void error(Object f,Exception e,String operation){
        Throwable t=e; while(t.getCause()!=null)t=t.getCause();
        String msg=t.getMessage();
        if(t instanceof SQLException sql && ("23000".equals(sql.getSQLState()) || sql.getErrorCode()==1451 || sql.getErrorCode()==1452)) {
            if(sql.getErrorCode()==1451 || (msg!=null && msg.toLowerCase().contains("delete")))
                msg="Data tidak dapat dihapus karena masih dipakai oleh data lain (foreign key). Hapus atau pindahkan data turunannya terlebih dahulu.";
            else
                msg="Data gagal "+operation+" karena ID relasi tidak ditemukan atau melanggar aturan database. Pilih data dari daftar referensi yang tersedia, lalu coba lagi. Detail: "+(msg==null?"relasi foreign key tidak valid":msg);
        }
        JOptionPane.showMessageDialog((Component)f,"Gagal "+operation+" data: "+(msg==null?e.toString():msg),"Kesalahan",JOptionPane.ERROR_MESSAGE);
    }
}
