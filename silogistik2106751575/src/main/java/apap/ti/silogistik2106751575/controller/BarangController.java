package apap.ti.silogistik2106751575.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik2106751575.dto.BarangMapper;
import apap.ti.silogistik2106751575.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751575.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751575.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106751575.model.Barang;
import apap.ti.silogistik2106751575.service.BarangService;
import apap.ti.silogistik2106751575.service.GudangBarangService;
import apap.ti.silogistik2106751575.service.GudangService;
import apap.ti.silogistik2106751575.service.KaryawanService;
import apap.ti.silogistik2106751575.service.PermintaanPengirimanService;


@Controller
public class BarangController {
    @Autowired
    private BarangMapper barangMapper;

    @Autowired
    private BarangService barangService;

    @Autowired
    private GudangService gudangService;

    @Autowired
    private PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private GudangBarangService gudangBarangService;

    @GetMapping(value = "/")
    public String home(Model model){
        model.addAttribute("barang", barangService.getAllBarang().size());
        model.addAttribute("gudang", gudangService.getAllGudang().size());
        model.addAttribute("permintaan", permintaanPengirimanService.getAllPermintaanPengiriman().size());
        model.addAttribute("karyawan", karyawanService.getAllKaryawan().size());
        return "home";
    }

    @GetMapping("/barang")
    public String daftarBarang(Model model){
        var listBarang = barangService.getAllBarang();
        List<ReadBarangResponseDTO> listBarangDTO = new ArrayList<>();
        for (Barang barang : listBarang){
            var barangDTO = barangMapper.readBarangToBarangResponseDTO(barang);
            listBarangDTO.add(barangDTO);
        }
        model.addAttribute("listBarangDTO", listBarangDTO);
        return "daftar-barang";
    }

    @GetMapping("/barang/tambah")
    public String formTambahBarang(Model model){
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);
        return "form-tambah-barang";
    }

    @PostMapping("/barang/tambah")
    public String tambahBarang(@ModelAttribute CreateBarangRequestDTO barangDTO, Model model){                                               
        var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
        barangService.setSkuBarang(barang, barangDTO.getTipeBarang());
        barangService.saveBarang(barang);
        model.addAttribute("barang", barang);
        return "success-tambah-barang";
    }

    @GetMapping("/barang/{sku}/ubah")
    public String formUbahBarang(@PathVariable(value="sku") String sku, Model model) {
        var barang = barangService.getBarangBySku(sku);
        var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);
        String tipeBarang = barangService.parsingTipeBarang(barang.getTipeBarang());
        model.addAttribute("tipe", tipeBarang);
        model.addAttribute("barangDTO", barangDTO);
        return "form-ubah-barang";
    }

    @PostMapping("/barang/{sku}/ubah")
    public String ubahBarang(@PathVariable(value="sku") String sku,
                            @ModelAttribute UpdateBarangRequestDTO barangDTO, Model model){
        var barangFromDTO = barangMapper.ubahBarangRequestDTOToBarang(barangDTO);
        var barang = barangService.ubahBarang(barangFromDTO);
        model.addAttribute("sku", barang.getSku());
        return "success-ubah-barang";
    }

    @GetMapping("/barang/{sku}")
    public String detailBarang(@PathVariable(value="sku") String sku, Model model) {
        var barang = barangService.getBarangBySku(sku);
        var barangDTO = barangMapper.readBarangToBarangResponseDTO(barang);
        String tipeBarang = barangService.parsingTipeBarang(barang.getTipeBarang());
        var listGudangBarang = gudangBarangService.findAllByBarang(barang);
        model.addAttribute("listGudangBarang", listGudangBarang);
        model.addAttribute("tipeBarang", tipeBarang);
        model.addAttribute("barang", barangDTO);
        return "detail-barang";
    }
}
