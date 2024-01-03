package apap.ti.silogistik2106751575.service;

import java.time.LocalDate;
import java.util.List;

import apap.ti.silogistik2106751575.model.PermintaanPengiriman;
import apap.ti.silogistik2106751575.model.PermintaanPengirimanBarang;

public interface PermintaanPengirimanBarangService {
    List<PermintaanPengirimanBarang> findAllByPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
    List<PermintaanPengirimanBarang> listPermintaanPengirimanDenganBatasan(LocalDate start, LocalDate end, String sku);
}
