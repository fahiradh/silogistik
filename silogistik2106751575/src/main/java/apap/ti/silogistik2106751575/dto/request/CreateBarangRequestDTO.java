package apap.ti.silogistik2106751575.dto.request;

import java.util.List;

import apap.ti.silogistik2106751575.model.Gudang;
import apap.ti.silogistik2106751575.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBarangRequestDTO {
    private String sku;
    private int tipeBarang;
    private String merk;
    private int hargaBarang;
    private List<Gudang> listGudang;
    private GudangBarang gudangBarang;
}
