package apap.ti.silogistik2106751575.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "karyawan")
public class Karyawan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_karyawan", nullable = false)
    private long id;

    @NotBlank
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private int jenisKelamin;

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    private LocalDate tanggalLahir;

    @OneToMany(mappedBy = "karyawan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermintaanPengiriman> listPermintaanPengiriman = new ArrayList<>();
}