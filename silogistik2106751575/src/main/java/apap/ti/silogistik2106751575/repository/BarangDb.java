package apap.ti.silogistik2106751575.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751575.model.Barang;

@Repository
public interface BarangDb extends JpaRepository<Barang, String> {
    Barang getBarangBySku(String sku);
    @Query(value = "SELECT * FROM barang ORDER BY LOWER(merk) ASC", nativeQuery = true)
    List<Barang> findAllSortedAlphabetically();
}
