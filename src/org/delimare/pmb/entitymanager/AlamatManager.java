package org.delimare.pmb.entitymanager;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.delimare.pmb.entity.Alamat;
import org.delimare.pmb.function.Fungsi;
import org.delimare.pmb.function.Logger;

/**
 *
 * @author MyBook Z Series
 */
public class AlamatManager {

    public int insert(Alamat alamat) {
        int result = 0;

        try {
            String sql = "INSERT INTO alamat (rt, rw, kecamatan, kabupaten, provinsi, kode_pos, alamat) "
                       + "VALUES ('" + alamat.getRt() + "', '" + alamat.getRw() + "', '" + alamat.getKecamatan() + "', "
                       + "'" + alamat.getKabupaten() + "', '" + alamat.getProvinsi() + "', " + alamat.getKodePos() + ", "
                       + "'" + alamat.getAlamat() + "');";
            result = Fungsi.EQueryReturnsID(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }

        return result;
    }

    public int update(Alamat alamat) {
        int result = 0;

        try {
            String sql = "UPDATE alamat SET rt = '" + alamat.getRt() + "', rw = '" + alamat.getRw() + "', "
                       + "kecamatan = '" + alamat.getKecamatan() + "', kabupaten = '" + alamat.getKabupaten() + "', "
                       + "provinsi = '" + alamat.getProvinsi() + "', kode_pos = " + alamat.getKodePos() + ", "
                       + "alamat = '" + alamat.getAlamat() + "' WHERE id = " + alamat.getId() + ";";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }

        return result;
    }

    public int delete(int id) {
        int result = 0;

        try {
            String sql = "DELETE FROM alamat WHERE id = " + id + ";";
            result = Fungsi.EQuery(sql);
        } catch (Exception e) {
            result = -1;
            Logger.error(this, e.getMessage());
        }

        return result;
    }

    public ArrayList<Alamat> getSemua(String pencarian) {
        ArrayList<Alamat> hasil = new ArrayList<>();

        try {
            String sql = "SELECT id, rt, rw, kecamatan, kabupaten, provinsi, kode_pos, alamat FROM alamat "
                       + "WHERE kecamatan LIKE '%" + pencarian + "%' OR kabupaten LIKE '%" + pencarian + "%' "
                       + "OR provinsi LIKE '%" + pencarian + "%'";
            ResultSet res = Fungsi.getResult(sql);

            while (res.next()) {
                Alamat alamat = new Alamat();
                alamat.setId(res.getInt("id"));
                alamat.setRt(res.getString("rt"));
                alamat.setRw(res.getString("rw"));
                alamat.setKecamatan(res.getString("kecamatan"));
                alamat.setKabupaten(res.getString("kabupaten"));
                alamat.setProvinsi(res.getString("provinsi"));
                alamat.setKodePos(res.getInt("kode_pos"));
                alamat.setAlamat(res.getString("alamat"));
                hasil.add(alamat);
            }
        } catch (Exception e) {
            Logger.error(this, e.getMessage());
        }

        return hasil;
    }
}