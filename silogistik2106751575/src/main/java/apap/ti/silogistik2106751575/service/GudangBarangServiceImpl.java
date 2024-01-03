package apap.ti.silogistik2106751575.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751575.model.Barang;
import apap.ti.silogistik2106751575.model.Gudang;
import apap.ti.silogistik2106751575.model.GudangBarang;
import apap.ti.silogistik2106751575.repository.BarangDb;
import apap.ti.silogistik2106751575.repository.GudangBarangDb;
import apap.ti.silogistik2106751575.repository.GudangDb;

@Service
public class GudangBarangServiceImpl implements GudangBarangService{
    @Autowired
    GudangBarangDb gudangBarangDb;

    @Autowired
    GudangDb gudangDb;

    @Autowired
    BarangDb barangDb;
    
    @Override
    public void restockGudang(GudangBarang gudangBarang) {
        gudangBarangDb.save(gudangBarang);
    }

    @Override
    public List<GudangBarang> findAllByBarang(Barang barang){
        return gudangBarangDb.findAllByBarang(barang);
    }

    @Override
    public List<Gudang> findGudangByBarangSku(String merk){
        List<Gudang> gudangBarangFiltered = gudangBarangDb.findGudangByBarangSku(merk);
        return gudangBarangFiltered;
    }

    @Override
    public List<GudangBarang> findAllByGudang(Gudang gudang) {
        return gudangBarangDb.findAllByGudang(gudang);
    }

    @Override
    public GudangBarang getGudangBarangByBarang(Barang barang){
        return gudangBarangDb.findFirstByBarang(barang);
    }
}
