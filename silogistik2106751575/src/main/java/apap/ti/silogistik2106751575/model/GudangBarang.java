package apap.ti.silogistik2106751575.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gudang_barang")
public class GudangBarang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_gudang", referencedColumnName = "id", nullable = false)
    private Gudang gudang;

    @ManyToOne
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku", nullable = false)
    private Barang barang;

    @Column(name = "stok", nullable = false)
    private int stok;
}