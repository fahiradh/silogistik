package apap.ti.silogistik2106751575.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gudang")
public class Gudang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotBlank
    @Size(max = 255)
    @Column(name = "alamat_gudang", nullable = false)
    private String alamatGudang;

    // Menyimpan barang yang ada di gudang
    @OneToMany(mappedBy = "gudang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GudangBarang> listBarang = new ArrayList<>();
}