package apap.ti.silogistik2106751575.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751575.model.PermintaanPengiriman;

@Repository
public interface PermintaanPengirimanDb extends JpaRepository<PermintaanPengiriman, String> {
    PermintaanPengiriman findById(long id);
    List<PermintaanPengiriman> findAllByOrderByWaktuPermintaanDesc();
}
