package apap.ti.silogistik2106751575.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO {
    private String sku;
    private String merk;
    private long hargaBarang;
    private int stok;
}