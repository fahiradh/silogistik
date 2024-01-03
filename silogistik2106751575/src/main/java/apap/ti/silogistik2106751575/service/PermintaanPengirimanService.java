package apap.ti.silogistik2106751575.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import apap.ti.silogistik2106751575.model.PermintaanPengiriman;
import apap.ti.silogistik2106751575.model.PermintaanPengirimanBarang;

public interface PermintaanPengirimanService {
    void create(PermintaanPengiriman permintaanPengiriman);
    String generateIdPermintaan(int kodeLayanan, LocalDateTime detailWaktu, int jumlah);
    List<PermintaanPengiriman> getAllPermintaanPengiriman();
    PermintaanPengiriman getPermintaanPengirimanById(long id);
    void deletePermintaan(PermintaanPengiriman permintaanPengiriman);
    String parsingKodeJenisLayanan(int kode);
    void passingListPermintaan(List<PermintaanPengirimanBarang> ppb, long id);
    boolean cekTanggalPermintaan(LocalDate tanggalPermintaan);
}
