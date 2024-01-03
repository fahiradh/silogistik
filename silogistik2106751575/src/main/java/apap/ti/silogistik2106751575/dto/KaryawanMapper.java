package apap.ti.silogistik2106751575.dto;

import apap.ti.silogistik2106751575.model.Karyawan;
import apap.ti.silogistik2106751575.dto.request.CreateKaryawanRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO createKaryawanRequestDTO);
}
