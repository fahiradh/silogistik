package apap.ti.silogistik2106751575.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import apap.ti.silogistik2106751575.dto.GudangMapper;
import apap.ti.silogistik2106751575.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106751575.model.Barang;
import apap.ti.silogistik2106751575.model.GudangBarang;
import apap.ti.silogistik2106751575.service.BarangService;
import apap.ti.silogistik2106751575.service.GudangBarangService;
import apap.ti.silogistik2106751575.service.GudangService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GudangController {
    @Autowired
    private GudangMapper gudangMapper;

    @Autowired
    private GudangService gudangService;

    @Autowired
    private GudangBarangService gudangBarangService;

    @Autowired
    private BarangService barangService;
    
    @GetMapping("gudang")
    public String daftarGudang(Model model){
        var listGudang = gudangService.getAllGudang();
        model.addAttribute("listGudang", listGudang);
        return "daftar-gudang";
    }

    @GetMapping("/gudang/cari-barang")
    public String daftarGudangCariBarang(Model model){
        var listGudang = gudangService.getAllGudang();
        List<Barang> listBarangExisting = barangService.getAllBarangSortedByMerk();
        model.addAttribute("stok", "-");
        model.addAttribute("listGudang", listGudang);
        model.addAttribute("listBarangExisting", listBarangExisting);
        return "cari-barang";
    }

    @GetMapping("/gudang/{id}")
    public String detailGudang(@PathVariable(value="id") long id, Model model) {
        var gudang = gudangService.getGudangById(id);
        var listGudangBarang = gudangBarangService.findAllByGudang(gudang);
        model.addAttribute("listGudangBarang", listGudangBarang);
        model.addAttribute("gudang", gudang);
        return "detail-gudang";
    }

    @GetMapping("/search")
    public String filterBarang(@RequestParam(value = "query", required = false) String merk, Model model){
        var barang = barangService.getBarangBySku(merk);
        List<GudangBarang> listGudangBarang = gudangBarangService.findAllByBarang(barang);
        List<Barang> listBarangExisting = barangService.getAllBarangSortedByMerk();
        model.addAttribute("listGudangBarang", listGudangBarang);
        model.addAttribute("listBarangExisting", listBarangExisting);
        return "daftar-gudang-filtered";
    }

    @GetMapping("/gudang/{id}/restock-barang")
    public String formRestockBarang(@PathVariable(value = "id") long id, Model model){
        var gudang = gudangService.getGudangById(id);
        var gudangBarangDTO = gudangMapper.gudangToUpdateGudangRequestDTO(gudang);
        var listBarangExisting = barangService.getAllBarang();
        var listBarang = gudang.getListBarang();

        model.addAttribute("gudang", gudang);
        model.addAttribute("gudangBarangDTO", gudangBarangDTO);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listBarangExisting", listBarangExisting);
        return "form-restock-barang";
    }

    @PostMapping(value = "/gudang/{id}/restock-barang", params = {"addRowRestock"})
    private String addRowRestock(@PathVariable(value="id") long id,
                                @ModelAttribute UpdateGudangRequestDTO gudangBarangDTO,
                                Model model) {
        var gudang = gudangService.getGudangById(id);
        var listBarang = gudang.getListBarang();
        var listBarangExisting = barangService.getAllBarang();

        if (gudangBarangDTO.getListBarang() == null){
            gudangBarangDTO.setListBarang(new ArrayList<>());
        }
        gudangBarangDTO.getListBarang().add(new GudangBarang());

        model.addAttribute("gudang", gudang);
        model.addAttribute("gudangBarangDTO", gudangBarangDTO);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listBarangExisting", listBarangExisting);
        return "form-restock-barang";
    }

    @PostMapping("/gudang/{id}/restock-barang")
    public String restockBarangSubmit(@PathVariable(value="id") long id,
                                    @ModelAttribute UpdateGudangRequestDTO gudangBarangDTO, Model model) {
        var gudang = gudangService.getGudangById(id);
        var listBarang = gudangBarangDTO.getListBarang();
        for (GudangBarang gb : listBarang){
            gb.setGudang(gudang);
        }
        var gudangFromDTO = gudangMapper.gudangRequestDTOToGudang(gudangBarangDTO);
        gudangService.restockBarang(gudangFromDTO);
        model.addAttribute("id", id);
        return "success-restock-barang";
    }
}
