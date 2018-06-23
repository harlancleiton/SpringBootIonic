package br.harlan.sbi.factory.domain;

import br.harlan.sbi.domain.Address;
import br.harlan.sbi.dto.AddressDTO;

public class AddressFactory {
    public static Address create(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setCep(addressDTO.getCep());
        address.setDistrict(addressDTO.getDistrict());
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        if (addressDTO.getComplement().isPresent())
            address.setComplement(addressDTO.getComplement().get());
        address.setCity(CityFactory.create(addressDTO.getCity()));
        return address;
    }
}
