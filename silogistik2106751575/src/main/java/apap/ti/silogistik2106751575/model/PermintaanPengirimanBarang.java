package apap.ti.silogistik2106751575.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman_barang")
public class PermintaanPengirimanBarang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permintaan_pengiriman", referencedColumnName = "id", nullable = false)
    private PermintaanPengiriman permintaanPengiriman;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku", nullable = false)
    private Barang barang;

    @NotNull
    @Column(name = "kuantitas_pengiriman", nullable = false)
    private int kuantitas;
}
