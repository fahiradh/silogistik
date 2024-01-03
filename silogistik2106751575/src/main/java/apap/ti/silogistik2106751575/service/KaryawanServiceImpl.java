package apap.ti.silogistik2106751575.service;

import apap.ti.silogistik2106751575.model.Karyawan;
import apap.ti.silogistik2106751575.repository.KaryawanDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KaryawanServiceImpl implements KaryawanService{
    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public void addKaryawan(Karyawan karyawan) {
        karyawanDb.save(karyawan);
    }

    @Override
    public List<Karyawan> getAllKaryawan() {
        return karyawanDb.findAll();
    }

    // @Override
    // public Karyawan getKaryawanById(BigInteger id) {
    //     Karyawan karyawan = karyawanDb.findByIdKaryawan(id);
    //     return karyawan;
    // }

    // @Override
    // public void deleteKaryawan(BigInteger id) {
    //     Karyawan karyawan = getKaryawanById(id);
    //     karyawanDb.delete(karyawan);
    // }
    
}
