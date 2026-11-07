package com.bengkelshift.dao;

import com.bengkelshift.database.Koneksi;
import java.sql.*;
import java.util.*;

/** DAO CRUD terpusat untuk seluruh modul Admin, Sales, dan Mekanik. */
public class CrudDAO {
    public record Field(String name, String label, String type, boolean nullable) {}
    public record Entity(String table, String id, List<Field> fields) {}
    private static Field f(String n,String l,String t){ return new Field(n,l,t,false); }
    private static Field opt(String n,String l,String t){ return new Field(n,l,t,true); }
    private static final Map<String,Entity> ENTITIES = new HashMap<>();
    static {
        put("pelanggan","pelanggan","id_pelanggan",f("nama_pelanggan","Nama pelanggan","text"),f("no_hp","Nomor HP","text"));
        put("kendaraan","kendaraan","id_kendaraan",f("no_polisi","Nomor polisi","text"),f("tipe_kendaraan","Tipe kendaraan","text"),f("no_rangka","Nomor rangka","text"),f("no_mesin","Nomor mesin","text"),f("tahun_produksi","Tahun produksi","int"),f("id_pelanggan","ID pelanggan (lihat menu Pelanggan)","int"));
        put("stok","unit_kendaraan","id_unit",f("tipe_kendaraan","Tipe kendaraan","text"),f("warna","Warna","text"),f("no_rangka","Nomor rangka","text"),f("no_mesin","Nomor mesin","text"),f("tahun_produksi","Tahun produksi","int"),f("status_unit","Status (Tersedia/Dipesan/Terjual)","text"),opt("tanggal_masuk","Tanggal masuk (YYYY-MM-DD)","date"),f("harga","Harga","decimal"),f("id_transaksi","ID transaksi","int"));
        put("tarif","jasa_servis","id_jasa",f("nama_jasa","Nama jasa","text"),f("harga_jasa","Harga jasa","decimal"),opt("deskripsi","Deskripsi","text"),opt("estimasi_menit","Estimasi menit","int"));
        put("sparepart","sparepart","id_sparepart",f("nama_sparepart","Nama sparepart","text"),f("harga","Harga","decimal"),f("satuan","Satuan","text"),f("id_servis","ID servis","int"),f("id_transaksi","ID transaksi","int"));
        put("servis","servis","id_servis",f("tanggal_servis","Tanggal servis (YYYY-MM-DD)","date"),f("kilometer","Kilometer","int"),opt("keluhan","Keluhan","text"),opt("diagnosis","Diagnosis","text"),opt("jadwal_servis_berikutnya","Jadwal berikutnya (YYYY-MM-DD)","date"),f("id_kendaraan","ID kendaraan","int"),f("mekanik_id_karyawan","ID karyawan mekanik","int"),f("id_transaksi","ID transaksi","int"));
        put("transaksi","transaksi","id_transaksi",f("tanggal_transaksi","Tanggal transaksi (YYYY-MM-DD)","date"),f("jenis_transaksi","Jenis transaksi","text"),f("diskon","Diskon","decimal"),f("total_bayar","Total bayar","decimal"),f("metode_pembayaran","Metode pembayaran","text"),f("status_pembayaran","Status pembayaran","text"),f("sales_id_karyawan","ID karyawan sales","int"),f("id_pelanggan","ID pelanggan","int"));
        put("pegawai","karyawan","id_karyawan",f("nama_karyawan","Nama karyawan","text"),f("no_hp","Nomor HP","text"),f("alamat","Alamat","text"),f("username","Username","text"),f("password","Password","text"),f("status_aktif","Status aktif (aktif/tidak aktif)","text"));
        // Hak akses lebih terbatas untuk role Mekanik.
        put("mekanik_servis","servis","id_servis",f("kilometer","Kilometer","int"),opt("keluhan","Keluhan","text"),opt("diagnosis","Diagnosis","text"),opt("jadwal_servis_berikutnya","Jadwal berikutnya (YYYY-MM-DD)","date"));
        put("mekanik_sparepart","sparepart","id_sparepart",f("nama_sparepart","Nama sparepart","text"),f("harga","Harga","decimal"),f("satuan","Satuan","text"));
        put("mekanik_transaksi","transaksi","id_transaksi",f("status_pembayaran","Status pembayaran","text"));
    }
    private static void put(String key,String table,String id,Field... fields){ ENTITIES.put(key,new Entity(table,id,List.of(fields))); }
    public Entity entity(String key){ Entity e=ENTITIES.get(key); if(e==null) throw new IllegalArgumentException("Jenis data tidak dikenal: "+key); return e; }

    public Map<String,Object> get(String key,int id) throws SQLException {
        Entity e=entity(key); String sql="SELECT * FROM `"+e.table()+"` WHERE `"+e.id()+"`=?";
        try(Connection c=Koneksi.getConnection(); PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,id);try(ResultSet r=p.executeQuery()){if(!r.next())return null;Map<String,Object> m=new LinkedHashMap<>();m.put(e.id(),r.getObject(e.id()));for(Field x:e.fields())m.put(x.name(),r.getObject(x.name()));
            if(key.equals("pegawai")){
                try(PreparedStatement q=c.prepareStatement("SELECT CASE WHEN EXISTS(SELECT 1 FROM admin WHERE admin_id_karyawan=?) THEN 'Admin' WHEN EXISTS(SELECT 1 FROM mekanik WHERE mekanik_id_karyawan=?) THEN 'Mekanik' WHEN EXISTS(SELECT 1 FROM sales WHERE sales_id_karyawan=?) THEN 'Sales' ELSE 'Pegawai' END")){
                    q.setInt(1,id);q.setInt(2,id);q.setInt(3,id);try(ResultSet rr=q.executeQuery()){if(rr.next())m.put("__role",rr.getString(1));}
                }
                try(PreparedStatement q=c.prepareStatement("SELECT spesialisasi FROM mekanik WHERE mekanik_id_karyawan=?")){q.setInt(1,id);try(ResultSet rr=q.executeQuery()){if(rr.next())m.put("__spesialisasi",rr.getString(1));}}
            }
            return m;}}
    }
    public boolean insert(String key,Map<String,Object> values) throws SQLException {
        if(key.equals("pegawai")) return insertEmployee(values);
        Entity e=entity(key); StringBuilder cols=new StringBuilder("`"+e.id()+"`");StringBuilder marks=new StringBuilder("?");
        for(Field f:e.fields()){cols.append(",`").append(f.name()).append('`');marks.append(",?");}
        try(Connection c=Koneksi.getConnection()){
            c.setAutoCommit(false);
            try(Statement s=c.createStatement();ResultSet r=s.executeQuery("SELECT COALESCE(MAX(`"+e.id()+"`),0)+1 FROM `"+e.table()+"`")){
                r.next();int next=r.getInt(1);
                try(PreparedStatement p=c.prepareStatement("INSERT INTO `"+e.table()+"` ("+cols+") VALUES ("+marks+")")){
                    p.setInt(1,next);int i=2;for(Field f:e.fields())p.setObject(i++,values.get(f.name()));
                    boolean ok=p.executeUpdate()>0;c.commit();return ok;
                }
            }catch(Exception ex){c.rollback();throw ex;}
        }
    }
    public boolean update(String key,int id,Map<String,Object> values) throws SQLException {
        if(key.equals("pegawai")) return updateEmployee(id,values);
        Entity e=entity(key);StringJoiner set=new StringJoiner(",");for(Field f:e.fields())set.add("`"+f.name()+"`=?");
        try(Connection c=Koneksi.getConnection();PreparedStatement p=c.prepareStatement("UPDATE `"+e.table()+"` SET "+set+" WHERE `"+e.id()+"`=?")){
            int i=1;for(Field f:e.fields())p.setObject(i++,values.get(f.name()));p.setInt(i,id);return p.executeUpdate()>0;
        }
    }
    public boolean delete(String key,int id) throws SQLException {
        Entity e=entity(key);
        if(key.equals("pegawai")){
            try(Connection c=Koneksi.getConnection()){
                c.setAutoCommit(false);try(PreparedStatement a=c.prepareStatement("DELETE FROM admin WHERE admin_id_karyawan=?");PreparedStatement m=c.prepareStatement("DELETE FROM mekanik WHERE mekanik_id_karyawan=?");PreparedStatement s=c.prepareStatement("DELETE FROM sales WHERE sales_id_karyawan=?")){
                    a.setInt(1,id);a.executeUpdate();m.setInt(1,id);m.executeUpdate();s.setInt(1,id);s.executeUpdate();
                    try(PreparedStatement k=c.prepareStatement("DELETE FROM karyawan WHERE id_karyawan=?")){k.setInt(1,id);int n=k.executeUpdate();c.commit();return n>0;}
                }catch(Exception ex){c.rollback();throw ex;}
            }
        }
        try(Connection c=Koneksi.getConnection();PreparedStatement p=c.prepareStatement("DELETE FROM `"+e.table()+"` WHERE `"+e.id()+"`=?")){p.setInt(1,id);return p.executeUpdate()>0;}
    }
    private boolean insertEmployee(Map<String,Object> v) throws SQLException {
        String role=String.valueOf(v.getOrDefault("__role","Pegawai"));
        try(Connection c=Koneksi.getConnection()){
            c.setAutoCommit(false);try{
                int id;try(Statement s=c.createStatement();ResultSet r=s.executeQuery("SELECT COALESCE(MAX(id_karyawan),0)+1 FROM karyawan")){r.next();id=r.getInt(1);}
                try(PreparedStatement p=c.prepareStatement("INSERT INTO karyawan(id_karyawan,nama_karyawan,no_hp,alamat,username,password,status_aktif) VALUES(?,?,?,?,?,?,?)")){
                    bindEmployee(p,id,v);p.executeUpdate();}
                insertRole(c,id,role,v);c.commit();return true;
            }catch(Exception ex){c.rollback();throw ex;}
        }
    }
    private boolean updateEmployee(int id,Map<String,Object> v) throws SQLException {
        String role=String.valueOf(v.getOrDefault("__role","Pegawai"));
        try(Connection c=Koneksi.getConnection()){
            c.setAutoCommit(false);try(PreparedStatement p=c.prepareStatement("UPDATE karyawan SET nama_karyawan=?,no_hp=?,alamat=?,username=?,password=?,status_aktif=? WHERE id_karyawan=?")){
                p.setObject(1,v.get("nama_karyawan"));p.setObject(2,v.get("no_hp"));p.setObject(3,v.get("alamat"));p.setObject(4,v.get("username"));p.setObject(5,v.get("password"));p.setObject(6,v.get("status_aktif"));p.setInt(7,id);p.executeUpdate();
                try(PreparedStatement d=c.prepareStatement("DELETE FROM admin WHERE admin_id_karyawan=?")){d.setInt(1,id);d.executeUpdate();}
                try(PreparedStatement d=c.prepareStatement("DELETE FROM mekanik WHERE mekanik_id_karyawan=?")){d.setInt(1,id);d.executeUpdate();}
                try(PreparedStatement d=c.prepareStatement("DELETE FROM sales WHERE sales_id_karyawan=?")){d.setInt(1,id);d.executeUpdate();}
                insertRole(c,id,role,v);c.commit();return true;
            }catch(Exception ex){c.rollback();throw ex;}
        }
    }
    private void bindEmployee(PreparedStatement p,int id,Map<String,Object> v)throws SQLException{p.setInt(1,id);p.setObject(2,v.get("nama_karyawan"));p.setObject(3,v.get("no_hp"));p.setObject(4,v.get("alamat"));p.setObject(5,v.get("username"));p.setObject(6,v.get("password"));p.setObject(7,v.get("status_aktif"));}
    private void insertRole(Connection c,int id,String role,Map<String,Object> v)throws SQLException{
        if(role.equalsIgnoreCase("Admin")){try(PreparedStatement p=c.prepareStatement("INSERT INTO admin(admin_id_karyawan,tanggal_mulai_bertugas) VALUES(?,?)")){p.setInt(1,id);p.setDate(2,java.sql.Date.valueOf(java.time.LocalDate.now()));p.executeUpdate();}}
        else if(role.equalsIgnoreCase("Mekanik")){try(PreparedStatement p=c.prepareStatement("INSERT INTO mekanik(mekanik_id_karyawan,spesialisasi) VALUES(?,?)")){p.setInt(1,id);p.setString(2,String.valueOf(v.getOrDefault("__spesialisasi","Umum")));p.executeUpdate();}}
        else if(role.equalsIgnoreCase("Sales")){try(PreparedStatement p=c.prepareStatement("INSERT INTO sales(sales_id_karyawan,jumlah_unit_terjual) VALUES(?,0)")){p.setInt(1,id);p.executeUpdate();}}
    }
}
