package apap.ti.silogistik2106751575.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman")
// @SQLDelete(sql = "UPDATE permintaan_pengiriman SET is_cancelled = true WHERE id=?")
public class PermintaanPengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "nomor_pengiriman", nullable = false, unique = true)
    private String nomorPengiriman;

    @NotNull
    @Column(name = "is_cancelled", nullable = false)
    private boolean isCancelled = Boolean.FALSE;

    @NotBlank
    @Column(name = "nama_penerima", nullable = false)
    private String namaPenerima;

    @NotBlank
    @Column(name = "alamat_penerima", nullable = false)
    private String alamatPenerima;

    @NotNull
    @Column(name = "tanggal_pengiriman", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate tanggalPengiriman;

    @NotNull
    @Column(name = "biaya_pengiriman", nullable = false)
    private int biayaPengiriman;

    @NotNull
    @Column(name = "jenis_layanan", nullable = false)
    private int jenisLayanan;

    @NotNull
    @Column(name = "waktu_permintaan", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime waktuPermintaan;

    @ManyToOne
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id_karyawan", nullable = false)
    private Karyawan karyawan;

    @OneToMany(mappedBy = "permintaanPengiriman", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermintaanPengirimanBarang> listPermintaan = new ArrayList<>();
}