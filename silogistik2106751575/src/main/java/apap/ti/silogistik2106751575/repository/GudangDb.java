package apap.ti.silogistik2106751575.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751575.model.Gudang;

@Repository
public interface GudangDb extends JpaRepository<Gudang, String> {
    Gudang getGudangById(long id);
}
