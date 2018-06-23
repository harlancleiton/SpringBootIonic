package br.harlan.sbi.factory.domain;

import br.harlan.sbi.domain.Province;
import br.harlan.sbi.dto.ProvinceDTO;

public class ProvinceFactory {
    public static Province create(ProvinceDTO provinceDTO) {
        Province province = new Province();
        province.setId(provinceDTO.getId());
        province.setName(provinceDTO.getName());
        province.setUf(provinceDTO.getUf());
        return province;
    }
}
