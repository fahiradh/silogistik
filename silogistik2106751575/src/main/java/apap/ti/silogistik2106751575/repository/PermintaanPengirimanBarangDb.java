package apap.ti.silogistik2106751575.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751575.model.PermintaanPengiriman;
import apap.ti.silogistik2106751575.model.PermintaanPengirimanBarang;

@Repository
public interface PermintaanPengirimanBarangDb extends JpaRepository<PermintaanPengirimanBarang, String> {
   List<PermintaanPengirimanBarang> findAllByPermintaanPengiriman(PermintaanPengiriman pp);
   @Query("SELECT ppb FROM PermintaanPengirimanBarang ppb WHERE ppb.permintaanPengiriman.tanggalPengiriman BETWEEN :start AND :end AND ppb.barang.sku = :sku")
   List<PermintaanPengirimanBarang> findPengirimanBarangByTanggalAndSku(@Param("start") LocalDate start, @Param("end") LocalDate end, @Param("sku") String sku);
}
