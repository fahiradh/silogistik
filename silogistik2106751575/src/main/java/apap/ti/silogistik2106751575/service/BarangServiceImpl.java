package apap.ti.silogistik2106751575.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751575.model.Barang;
import apap.ti.silogistik2106751575.model.GudangBarang;
import apap.ti.silogistik2106751575.repository.BarangDb;

@Service
public class BarangServiceImpl implements BarangService{
    @Autowired
    BarangDb barangDb;

    @Override
    public void saveBarang(Barang barang) {
        barangDb.save(barang);
    }

    @Override
    public void setSkuBarang(Barang barang, int kode){
        String tipeBarang = "ELEC";
        if (kode == 1) tipeBarang = "ELEC";
        else if (kode == 2) tipeBarang = "CLOT";
        else if (kode == 3) tipeBarang = "FOOD";
        else if (kode == 4) tipeBarang = "COSM";
        else if (kode == 5) tipeBarang = "TOOL";
        String count = "001";
        int size = getAllBarang().size();
        if ((size < 10) && (size != 0)) count = "00" + String.valueOf(size+1);
        else if ((size < 100) && (size >= 10)) count = "0" + String.valueOf(size+1);
        else if (size >= 100) count = String.valueOf(size+1);
        barang.setSku(tipeBarang + count);
    }

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAll();
    }

    @Override
    public List<Barang> getAllBarangSortedByMerk(){
        return barangDb.findAllSortedAlphabetically();
    }

    @Override
    public Barang getBarangBySku(String sku) {
        Barang barang = barangDb.getBarangBySku(sku);
        return barang;
    }

    @Override
    public Barang ubahBarang(Barang barangFromDTO) {
        Barang barang = getBarangBySku(barangFromDTO.getSku());
        if (barang != null){
            barang.setHargaBarang(barangFromDTO.getHargaBarang());
            barang.setMerk(barangFromDTO.getMerk());
            barangDb.save(barang);
        }
        return barang;
    }

    @Override
    public String parsingTipeBarang(int kode){
        String res = "Produk Elektronik";
        if (kode == 2) res = "Pakaian & Aksesoris";
        else if (kode == 3) res = "Makanan & Minuman";
        else if (kode == 4) res = "Kosmetik";
        else if (kode == 5) res = "Perlengkapan Rumah";
        return res;
    }

    @Override
    public void restockBarang(List<GudangBarang> listGudangBarang){
        // for (GudangBarang gb : listGudangBarang){
        //     var barang = gb.getBarang();
        //     var listGudang = barang.getListGudang();
             
        //     for (GudangBarang gbBarang : listGudang){
        //         if (gbBarang.getGudang().getId() == gb.getId()){

        //         }
        //     }
        //     barangDb.save(barang);
        // }
        
    }
}
