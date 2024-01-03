package apap.ti.silogistik2106751575.service;

import java.util.List;

import apap.ti.silogistik2106751575.model.Gudang;

public interface GudangService {
    void createGudang(Gudang gudang);
    List<Gudang> getAllGudang();
    Gudang getGudangById(long id);
    void restockBarang(Gudang gudang);
}
