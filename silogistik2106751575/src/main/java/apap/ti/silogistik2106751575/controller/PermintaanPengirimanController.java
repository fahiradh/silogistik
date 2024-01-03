package apap.ti.silogistik2106751575.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.ti.silogistik2106751575.model.Barang;
import apap.ti.silogistik2106751575.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751575.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106751575.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751575.dto.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106751575.service.BarangService;
import apap.ti.silogistik2106751575.service.GudangBarangService;
import apap.ti.silogistik2106751575.service.KaryawanService;
import apap.ti.silogistik2106751575.service.PermintaanPengirimanBarangService;
import apap.ti.silogistik2106751575.service.PermintaanPengirimanService;

@Controller
public class PermintaanPengirimanController {
    @Autowired
    private PermintaanPengirimanMapper permintaanPengirimanMapper;

    @Autowired
    private PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    private PermintaanPengirimanBarangService permintaanPengirimanBarangService;

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private BarangService barangService;

    @Autowired
    private GudangBarangService gudangBarangService;

    @GetMapping("/permintaan-pengiriman")
    public String daftarPermintaanPengiriman(Model model){
        var listPermintaan = permintaanPengirimanService.getAllPermintaanPengiriman();
        model.addAttribute("listPermintaan", listPermintaan);
        return "daftar-permintaan-pengiriman";
    }

    @GetMapping("/permintaan-pengiriman/tambah")
    public String formTambahPermintaanPengiriman(Model model){
        var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();
        var listBarangExisting = barangService.getAllBarang();
        var listKaryawan = karyawanService.getAllKaryawan();

        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listBarangExisting", listBarangExisting);
        return "form-tambah-permintaan-pengiriman";
    }

    @PostMapping("/permintaan-pengiriman/tambah")
    public String tambahPermintaanPengiriman(@ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO, Model model){
        List<PermintaanPengirimanBarang> temp = permintaanPengirimanDTO.getListPermintaan();
        if (temp == null || temp.size() == 0){
            model.addAttribute("errorMessage", 
                    "Permintaan pengiriman barang tidak boleh kosong.");
            return "error-permintaan-pengiriman";
        }
        else if (permintaanPengirimanService.cekTanggalPermintaan(permintaanPengirimanDTO.getTanggalPengiriman())){
            model.addAttribute("errorMessage", 
                    "Permintaan pengiriman barang harus setelah hari ini.");
            return "error-permintaan-pengiriman";
        }
        int jumlahPesanan = 0;
        for (PermintaanPengirimanBarang ppb: temp){
            var barang = ppb.getBarang();
            var gudangBarang = gudangBarangService.getGudangBarangByBarang(barang);
            if (gudangBarang != null){
                if (gudangBarang.getStok() < ppb.getKuantitas()){
                    var merk = gudangBarang.getBarang().getMerk();
                    model.addAttribute("errorMessage", 
                            "Permintaan pengiriman barang dengan merk " + merk + " melebihi jumlah stok yang tersedia.");
                    return "error-permintaan-pengiriman";
                }
                jumlahPesanan += ppb.getKuantitas();
            }
            else {
                model.addAttribute("errorMessage", 
                            "Stok barang tidak tersedia di gudang manapun.");
                    return "error-permintaan-pengiriman";
            }
        }
        var nomorPengiriman = permintaanPengirimanService.generateIdPermintaan(
                            permintaanPengirimanDTO.getJenisLayanan(),
                            permintaanPengirimanDTO.getWaktuPermintaan(),
                            jumlahPesanan);
        permintaanPengirimanDTO.setListPermintaan(new ArrayList<>());
        var permintaanPengiriman = permintaanPengirimanMapper.createPermintaanPengirimanRequestDTO(permintaanPengirimanDTO);
        permintaanPengiriman.setNomorPengiriman(nomorPengiriman);
        permintaanPengirimanService.create(permintaanPengiriman);
        permintaanPengirimanService.passingListPermintaan(temp, permintaanPengiriman.getId());
        model.addAttribute("nomor", permintaanPengiriman.getNomorPengiriman());
        return "success-tambah-permintaan-pengiriman";
    }

    @PostMapping(value="/permintaan-pengiriman/tambah", params = {"addRowBarang"})
    public String addRowBarang(@ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO, Model model) {
        if (permintaanPengirimanDTO.getListPermintaan() == null || permintaanPengirimanDTO.getListPermintaan().size() == 0) {
            permintaanPengirimanDTO.setListPermintaan(new ArrayList<>());
        }
        permintaanPengirimanDTO.getListPermintaan().add(new PermintaanPengirimanBarang());

        var listBarangExisting = barangService.getAllBarang();
        var listKaryawan = karyawanService.getAllKaryawan();

        model.addAttribute("listBarangExisting", listBarangExisting);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        return "form-tambah-permintaan-pengiriman";
    }

    @GetMapping("/permintaan-pengiriman/{id}/cancel")
    public String batalkanPermintaanPengiriman(@PathVariable("id") long id, Model model){
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(id);
        var waktuPermintaan = permintaanPengiriman.getWaktuPermintaan();
        var waktuSaatIni = LocalDateTime.now();

        Duration selisih = Duration.between(waktuPermintaan, waktuSaatIni);
        var nomor = permintaanPengiriman.getNomorPengiriman();

        if (selisih.getSeconds() < 86400) {
            permintaanPengirimanService.deletePermintaan(permintaanPengiriman);
            model.addAttribute("nomor", nomor);
            return "success-cancel-permintaan-pengiriman";
        } else {
            model.addAttribute("errorMessage", 
                        "Permintaan pengiriman dengan nomor " + nomor + " tidak dapat dibatalkan karena dibuat lebih dari 24 jam terakhir.");
            return "error-permintaan-pengiriman";
        }
    }

    @GetMapping("/permintaan-pengiriman/{id}")
    public String detailPermintaanPengiriman(@PathVariable(value="id") long id, Model model) {
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(id);
        var listPermintaan = permintaanPengiriman.getListPermintaan();
        List<ReadPermintaanPengirimanResponseDTO> listPermintaanDTO = new ArrayList<>();
        for (PermintaanPengirimanBarang ppb : listPermintaan){
            var ppbDTO = permintaanPengirimanMapper.readPermintaanToPermintaanPengirimanResponseDTO(ppb);
            ppbDTO.setMerk(ppb.getBarang().getMerk());
            ppbDTO.setSku(ppb.getBarang().getSku());
            listPermintaanDTO.add(ppbDTO);
        }
        
        String jenisLayanan = permintaanPengirimanService.parsingKodeJenisLayanan(permintaanPengiriman.getJenisLayanan());
        model.addAttribute("listPermintaan", listPermintaanDTO);
        model.addAttribute("jenisLayanan", jenisLayanan);
        model.addAttribute("permintaanPengiriman", permintaanPengiriman);
        return "detail-permintaan-pengiriman";
    }

    @GetMapping("/filter-permintaan-pengiriman")
    public String filterPermintaanPemesanan(@RequestParam(value = "start", required = false) LocalDate start,
                                            @RequestParam(value = "end", required = false) LocalDate end,
                                            @RequestParam(value = "sku", required = false) String sku,
                                            Model model){
        List<PermintaanPengirimanBarang> listPermintaan = permintaanPengirimanBarangService.listPermintaanPengirimanDenganBatasan(start, end, sku);
        List<Barang> listBarangExisting = barangService.getAllBarangSortedByMerk();
        model.addAttribute("listPermintaan", listPermintaan);
        model.addAttribute("listBarangExisting", listBarangExisting);
        return "daftar-permintaan-pengiriman-filtered";                           
    }
}
