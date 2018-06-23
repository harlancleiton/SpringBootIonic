package br.harlan.sbi.factory.domain;

import br.harlan.sbi.domain.Telephone;
import br.harlan.sbi.dto.TelephoneDTO;

public class TelephoneFactory {
    public static Telephone create(TelephoneDTO telephoneDTO) {
        Telephone telephone = new Telephone();
        telephone.setId(telephoneDTO.getId());
        telephone.setCodeProvince(telephoneDTO.getCodeProvince());
        telephone.setTelephone(telephoneDTO.getTelephone());
        return telephone;
    }
}
