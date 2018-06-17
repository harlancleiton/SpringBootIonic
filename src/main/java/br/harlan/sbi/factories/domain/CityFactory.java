package br.harlan.sbi.factories.domain;

import br.harlan.sbi.domain.City;
import br.harlan.sbi.dtos.CityDTO;

public class CityFactory {
    public static City create(CityDTO cityDTO) {
        City city = new City();
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());
        city.setProvince(ProvinceFactory.create(cityDTO.getProvince()));
        return city;
    }
}
