package br.harlan.sbi.factory.dtos;

import br.harlan.sbi.domain.Telephone;
import br.harlan.sbi.dto.TelephoneDTO;
import org.jetbrains.annotations.NotNull;

public class TelephoneDTOFactory {
    @NotNull
    public static TelephoneDTO create(Telephone telephone) {
        TelephoneDTO telephoneDTO = new TelephoneDTO(telephone.getCodeProvince(), telephone.getTelephone());
        telephoneDTO.setId(telephone.getId());
        return telephoneDTO;
    }
}
