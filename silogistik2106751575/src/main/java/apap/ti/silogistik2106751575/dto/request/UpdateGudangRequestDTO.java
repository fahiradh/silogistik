package apap.ti.silogistik2106751575.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGudangRequestDTO extends CreateGudangRequestDTO{
    private long id;
    private int stok;
}
