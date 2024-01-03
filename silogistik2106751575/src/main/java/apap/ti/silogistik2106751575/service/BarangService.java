package apap.ti.silogistik2106751575.service;

import java.util.List;

import apap.ti.silogistik2106751575.model.Barang;
import apap.ti.silogistik2106751575.model.GudangBarang;

public interface BarangService {
    void saveBarang(Barang barang);
    List<Barang> getAllBarang();
    List<Barang> getAllBarangSortedByMerk();
    Barang getBarangBySku(String sku);
    Barang ubahBarang(Barang barangFromDTO);
    void setSkuBarang(Barang barang, int kode);
    void restockBarang(List<GudangBarang> listGudangBarang);
    String parsingTipeBarang(int kode);
}
