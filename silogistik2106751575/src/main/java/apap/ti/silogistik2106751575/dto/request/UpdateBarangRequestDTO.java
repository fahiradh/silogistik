package apap.ti.silogistik2106751575.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBarangRequestDTO extends CreateBarangRequestDTO {
    private String sku;
}
