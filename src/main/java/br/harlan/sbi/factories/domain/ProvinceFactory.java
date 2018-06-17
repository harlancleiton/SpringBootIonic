package br.harlan.sbi.factories.domain;

import br.harlan.sbi.domain.Province;
import br.harlan.sbi.dtos.ProvinceDTO;

public class ProvinceFactory {
    public static Province create(ProvinceDTO provinceDTO) {
        Province province = new Province();
        province.setId(provinceDTO.getId());
        province.setName(provinceDTO.getName());
        province.setUf(provinceDTO.getUf());
        return province;
    }
}
