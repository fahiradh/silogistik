package apap.ti.silogistik2106751575.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751575.model.Gudang;
import apap.ti.silogistik2106751575.model.GudangBarang;
import apap.ti.silogistik2106751575.repository.GudangDb;

@Service
public class GudangServiceImpl implements GudangService {
    @Autowired
    GudangDb gudangDb;

    @Override
    public void createGudang(Gudang gudang) {
        gudangDb.save(gudang);
    }

    @Override
    public List<Gudang> getAllGudang() {
        return gudangDb.findAll();
    }

    @Override
    public Gudang getGudangById(long id) {
       return gudangDb.getGudangById(id);
    }

    @Override
    public void restockBarang(Gudang gudangFromDTO){
        var gudang = getGudangById(gudangFromDTO.getId());
        // List Gudang Barang yang saat ini ada di gudang
        var listBarang = gudang.getListBarang();
        // List Gudang Barang yang saat ini ada di gudangDTO
        var listBarangDTO = gudangFromDTO.getListBarang();
        
        for(GudangBarang gb : listBarangDTO){
            boolean flag = false;
            for (GudangBarang gbDTO : listBarang){
                if (gb.getBarang().getSku().equals(gbDTO.getBarang().getSku())){
                    gbDTO.setStok(gb.getStok());
                    flag = true;
                }
            }
            if (!flag){
                gb.setGudang(gudang);
                gudang.getListBarang().add(gb);
            }
        }
        gudangDb.save(gudang);
    }
}
