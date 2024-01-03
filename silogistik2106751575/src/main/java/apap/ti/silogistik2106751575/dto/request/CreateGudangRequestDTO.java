package apap.ti.silogistik2106751575.dto.request;

import apap.ti.silogistik2106751575.model.GudangBarang;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGudangRequestDTO {
    private long id;
    private String nama;
    private String alamatGudang;
    private List<GudangBarang> listBarang;
}
