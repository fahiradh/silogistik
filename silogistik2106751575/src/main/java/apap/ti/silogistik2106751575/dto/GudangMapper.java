package apap.ti.silogistik2106751575.dto;

import apap.ti.silogistik2106751575.model.Gudang;
import apap.ti.silogistik2106751575.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751575.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106751575.dto.response.ReadBarangResponseDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO gudangRequestDTO);
    ReadBarangResponseDTO gudangToReadGudangDTO(Gudang gudang);
    UpdateGudangRequestDTO gudangToUpdateGudangRequestDTO(Gudang gudang);
    Gudang gudangRequestDTOToGudang(UpdateGudangRequestDTO gudangRequestDTO);
}
