package br.harlan.sbi.services.validators;

import br.harlan.sbi.dtos.ClientDTO;
import br.harlan.sbi.response.FieldMessage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UpdateClientValidator implements ConstraintValidator<UpdateClient, ClientDTO> {

    @Override
    public boolean isValid(ClientDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> fieldMessages = new ArrayList<>();

        fieldMessages.forEach(fieldMessage -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName())
                    .addConstraintViolation();
        });
        return fieldMessages.isEmpty();
    }
}
