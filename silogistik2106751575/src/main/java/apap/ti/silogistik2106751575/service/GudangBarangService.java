package apap.ti.silogistik2106751575.service;

import java.util.List;

import apap.ti.silogistik2106751575.model.Barang;
import apap.ti.silogistik2106751575.model.Gudang;
import apap.ti.silogistik2106751575.model.GudangBarang;

public interface GudangBarangService {
    void restockGudang(GudangBarang gudangBarang);
    List<GudangBarang> findAllByBarang(Barang barang);
    List<Gudang> findGudangByBarangSku(String merk);
    List<GudangBarang> findAllByGudang(Gudang gudang);
    GudangBarang getGudangBarangByBarang(Barang barang);
}