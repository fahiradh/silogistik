package apap.ti.silogistik2106751575.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateKaryawanRequestDTO {
    private BigInteger id;
    private String nama;
    private int jenisKelamin;
    private Date tanggalLahir;
}
