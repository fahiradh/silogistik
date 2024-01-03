package apap.ti.silogistik2106751575.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "barang")
public class Barang {
    @Id
    @Column(name = "sku", nullable = false, unique = true)
    private String sku;

    @NotNull
    @Column(name = "tipe_barang", nullable = false)
    private int tipeBarang;

    @NotBlank
    @Size(max = 255)
    @Column(name = "merk", nullable = false)
    private String merk;

    @NotNull
    @Column(name = "harga_barang", nullable = false)
    private long hargaBarang;

    // Menyimpan gudang tempat barang disimpan
    @OneToMany(mappedBy = "barang", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<GudangBarang> listGudang = new ArrayList<>();

    @OneToMany(mappedBy = "barang", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang = new ArrayList<>();
}
