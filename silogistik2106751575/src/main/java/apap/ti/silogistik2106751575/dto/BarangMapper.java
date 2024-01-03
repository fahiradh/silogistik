package apap.ti.silogistik2106751575.dto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106751575.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751575.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751575.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106751575.model.Barang;
import apap.ti.silogistik2106751575.model.GudangBarang;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    // barang dto -> barang
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);
    // barang dto -> barang
    Barang ubahBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);
    // barang -> barang dto
    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang);

    ReadBarangResponseDTO readBarangToBarangResponseDTO(Barang barang);
    @AfterMapping
    default void addStok(@MappingTarget ReadBarangResponseDTO readBarangToBarangResponseDTO, Barang barang){
        int stok = 0;
        var listGudang = barang.getListGudang();
        for (GudangBarang gb : listGudang){
            if (gb.getBarang().getSku().equals(barang.getSku())){
                stok += gb.getStok();
            }
        }
        readBarangToBarangResponseDTO.setStok(stok);
    }
}
