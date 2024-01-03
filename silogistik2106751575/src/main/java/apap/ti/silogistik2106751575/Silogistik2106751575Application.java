package apap.ti.silogistik2106751575;

import java.util.Locale;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;

import apap.ti.silogistik2106751575.service.KaryawanService;
import apap.ti.silogistik2106751575.dto.GudangMapper;
import apap.ti.silogistik2106751575.dto.KaryawanMapper;
import apap.ti.silogistik2106751575.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751575.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751575.service.GudangService;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class Silogistik2106751575Application {
	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106751575Application.class, args);
	}
	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, KaryawanService karyawanService, GudangMapper gudangMapper, KaryawanMapper karyawanMapper){
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			// Membuat fake data memanfaatkan Java Faker
			var gudangDTO = new CreateGudangRequestDTO();
			gudangDTO.setNama(faker.company().name());
            gudangDTO.setAlamatGudang(faker.address().fullAddress());

			var karyawanDTO = new CreateKaryawanRequestDTO();
			karyawanDTO.setJenisKelamin(faker.options().option(1, 2));
			karyawanDTO.setTanggalLahir(faker.date().birthday());
			karyawanDTO.setNama(faker.name().fullName());

			// Mapping gudangDTO
			var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
            gudangService.createGudang(gudang);

			// Mapping karyawanDTO
			var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);
			karyawanService.addKaryawan(karyawan);
		};
	}
}
