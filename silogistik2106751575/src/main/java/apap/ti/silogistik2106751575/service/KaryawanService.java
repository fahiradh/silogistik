package apap.ti.silogistik2106751575.service;

import java.util.List;

import apap.ti.silogistik2106751575.model.Karyawan;

public interface KaryawanService {
    void addKaryawan(Karyawan karyawan);
    List<Karyawan> getAllKaryawan();   
}
