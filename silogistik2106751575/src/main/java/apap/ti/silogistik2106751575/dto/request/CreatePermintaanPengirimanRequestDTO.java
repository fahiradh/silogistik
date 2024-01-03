package apap.ti.silogistik2106751575.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.ZoneId;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import apap.ti.silogistik2106751575.model.Barang;
import apap.ti.silogistik2106751575.model.Karyawan;
import apap.ti.silogistik2106751575.model.PermintaanPengirimanBarang;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePermintaanPengirimanRequestDTO {
    private String nomorPengiriman;
    private String namaPenerima;
    private String alamatPenerima;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate tanggalPengiriman;
    private int biayaPengiriman;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime waktuPermintaan = LocalDateTime.now(ZoneId.systemDefault());
    private int jenisLayanan;
    private Karyawan karyawan;
    private int kuantitas;
    private Barang sku;
    private List<PermintaanPengirimanBarang> listPermintaan;
}

