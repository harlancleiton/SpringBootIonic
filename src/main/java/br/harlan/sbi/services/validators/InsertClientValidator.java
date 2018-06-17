package br.harlan.sbi.services.validators;

import br.harlan.sbi.domain.Client;
import br.harlan.sbi.domain.enuns.ClientType;
import br.harlan.sbi.dtos.ClientRegistrationDTO;
import br.harlan.sbi.response.FieldMessage;
import br.harlan.sbi.services.ClientService;
import br.harlan.sbi.services.exceptions.ObjectNotFoundException;
import br.harlan.sbi.utils.CpfCnpjValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InsertClientValidator implements ConstraintValidator<InsertClient, ClientRegistrationDTO> {
    @Autowired
    ClientService clientService;

    @Override
    public boolean isValid(ClientRegistrationDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> fieldMessages = new ArrayList<>();
        if (value.getClientType() == null)
            fieldMessages.add(new FieldMessage("clientType", "ClientType can not be null."));
        else if (value.getClientType().equals(ClientType.PHYSICAL_PERSON.getValue()) && !CpfCnpjValidator.isValidCPF(value.getCpfCnpj()))
            fieldMessages.add(new FieldMessage("cpfCnpj", "Invalid CPF."));
        else if (value.getClientType().equals(ClientType.LEGAL_PERSON.getValue()) && !CpfCnpjValidator.isValidCNPJ(value.getCpfCnpj()))
            fieldMessages.add(new FieldMessage("cpfCnpj", "Invalid CNPJ."));
        else if (!value.getClientType().equals(ClientType.PHYSICAL_PERSON.getValue()) &&
                !value.getClientType().equals(ClientType.LEGAL_PERSON.getValue()))
            fieldMessages.add(new FieldMessage("ClientType", "Invalid ClientType."));
        if (value.getEmail() != null)
            try {
                Optional<Client> client = clientService.findByEmail(value.getEmail());
                fieldMessages.add(new FieldMessage("Email", "Email already registered."));
            } catch (ObjectNotFoundException e) {

            }
        fieldMessages.forEach(fieldMessage -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName())
                    .addConstraintViolation();
        });
        return fieldMessages.isEmpty();
    }
}
