package apap.ti.silogistik2106751575.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751575.model.Barang;
import apap.ti.silogistik2106751575.model.Gudang;
import apap.ti.silogistik2106751575.model.GudangBarang;

@Repository
public interface GudangBarangDb extends JpaRepository<GudangBarang, String> {
    List<GudangBarang> findAllByBarang(Barang barang);
    @Query("SELECT DISTINCT gb.gudang FROM GudangBarang gb WHERE gb.barang.sku = :sku")
    List<Gudang> findGudangByBarangSku(@Param("sku") String sku);
    List<GudangBarang> findAllByGudang(Gudang gudang);
    GudangBarang findFirstByBarang(Barang barang);
}
