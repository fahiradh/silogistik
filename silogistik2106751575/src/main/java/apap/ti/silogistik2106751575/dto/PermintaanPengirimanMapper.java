package apap.ti.silogistik2106751575.dto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106751575.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751575.dto.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106751575.model.PermintaanPengiriman;
import apap.ti.silogistik2106751575.model.PermintaanPengirimanBarang;

@Mapper(componentModel="spring")
public interface PermintaanPengirimanMapper {
    PermintaanPengiriman createPermintaanPengirimanRequestDTO(CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO);

    ReadPermintaanPengirimanResponseDTO readPermintaanToPermintaanPengirimanResponseDTO(PermintaanPengirimanBarang ppb);
    @AfterMapping
    default void kalkulasiTotalHarga(@MappingTarget ReadPermintaanPengirimanResponseDTO dto, PermintaanPengirimanBarang ppb){
        long totalHarga = ppb.getKuantitas() * ppb.getBarang().getHargaBarang();
        dto.setTotalHarga(totalHarga);
    }
}
