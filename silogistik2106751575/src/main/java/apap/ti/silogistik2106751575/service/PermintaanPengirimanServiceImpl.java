package apap.ti.silogistik2106751575.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.glassfish.jaxb.core.marshaller.DumbEscapeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import apap.ti.silogistik2106751575.model.PermintaanPengiriman;
import apap.ti.silogistik2106751575.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751575.repository.BarangDb;
import apap.ti.silogistik2106751575.repository.PermintaanPengirimanDb;
import java.time.Period;
import java.time.LocalDate;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
   @Autowired
   PermintaanPengirimanDb permintaanPengirimanDb;

   @Autowired
   BarangDb barangDb;

   @Override
   public void create(PermintaanPengiriman permintaanPengiriman) {
      permintaanPengirimanDb.save(permintaanPengiriman);
   }

   @Override
   public List<PermintaanPengiriman> getAllPermintaanPengiriman() {
      return permintaanPengirimanDb.findAllByOrderByWaktuPermintaanDesc();
   }

   @Override
   public String generateIdPermintaan(int kodeLayanan, LocalDateTime detailWaktu, int jumlah){
      String jam = String.valueOf(detailWaktu.getHour());
      String menit = String.valueOf(detailWaktu.getMinute());
      String detik = String.valueOf(detailWaktu.getSecond());
      if (jam.length() != 2) jam = "0" + jam;
      if (menit.length() != 2) menit = "0" + menit;
      if (detik.length() != 2) detik = "0" + detik;
      String waktu = jam + ":" + menit + ":" + detik;
      String kuantitas = String.valueOf(jumlah);
      if (jumlah > 99) kuantitas = kuantitas.substring((kuantitas.length()-2));
      else if (jumlah < 10) kuantitas = "0" + kuantitas;
      int jenisLayanan = kodeLayanan;
      String kodeJenisLayanan = "HEM";
      if (jenisLayanan == 1) kodeJenisLayanan = "SAM";
      else if (jenisLayanan == 2) kodeJenisLayanan = "KIL";
      else if (jenisLayanan == 3) kodeJenisLayanan = "REG";
      String nomorPengiriman = "REQ" + kuantitas + kodeJenisLayanan + waktu;
      return nomorPengiriman;
   }

   @Override
   public PermintaanPengiriman getPermintaanPengirimanById(long id){
      return permintaanPengirimanDb.findById(id);
   }

   @Override
   public void deletePermintaan(PermintaanPengiriman permintaanPengiriman){
      permintaanPengiriman.setCancelled(true);
      permintaanPengirimanDb.save(permintaanPengiriman);
      // permintaanPengirimanDb.delete(permintaanPengiriman);
   }

   @Override
   public String parsingKodeJenisLayanan(int kode){
      String layanan = "Same Day";
      if (kode == 2) layanan = "Kilat";
      else if (kode == 3) layanan = "Reguler";
      else if (kode == 4) layanan = "Hemat";
      return layanan;
   }

   @Override
   public void passingListPermintaan(List<PermintaanPengirimanBarang> listPermintaanPengiriman, long id) {
      var permintaanPengiriman = getPermintaanPengirimanById(id);
      if (permintaanPengiriman.getListPermintaan() == null || permintaanPengiriman.getListPermintaan().size() == 0){
         permintaanPengiriman.setListPermintaan(new ArrayList<>());
      }
      for (PermintaanPengirimanBarang ppb : listPermintaanPengiriman){
         ppb.setPermintaanPengiriman(permintaanPengiriman);
         permintaanPengiriman.getListPermintaan().add(0, ppb);
      }
      permintaanPengirimanDb.save(permintaanPengiriman);
   }

   @Override
   public boolean cekTanggalPermintaan(LocalDate tanggalPemintaan){
      LocalDate hariIni = LocalDate.now();
      Period selisih = Period.between(tanggalPemintaan, hariIni);
      if (selisih.getDays() < 0) {
         return true;
      }
      else if (selisih.getDays() == 0){
         return true;
      }
      return false;
   }
}
