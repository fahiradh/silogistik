package apap.ti.silogistik2106751575.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751575.model.PermintaanPengiriman;
import apap.ti.silogistik2106751575.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751575.repository.PermintaanPengirimanBarangDb;

@Service
public class PermintaanPengirimanBarangServiceImpl implements PermintaanPengirimanBarangService{
    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;

    @Override
    public List<PermintaanPengirimanBarang> findAllByPermintaanPengiriman(PermintaanPengiriman pp) {
        return permintaanPengirimanBarangDb.findAllByPermintaanPengiriman(pp);
    }

    @Override
    public List<PermintaanPengirimanBarang> listPermintaanPengirimanDenganBatasan(LocalDate startDate, LocalDate endDate, String sku) {
        return permintaanPengirimanBarangDb.findPengirimanBarangByTanggalAndSku(startDate, endDate, sku);
    }
}
