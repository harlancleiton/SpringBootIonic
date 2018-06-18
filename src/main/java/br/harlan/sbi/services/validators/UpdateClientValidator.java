package br.harlan.sbi.services.validators;

import br.harlan.sbi.domain.Client;
import br.harlan.sbi.dtos.ClientDTO;
import br.harlan.sbi.response.FieldMessage;
import br.harlan.sbi.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UpdateClientValidator implements ConstraintValidator<UpdateClient, ClientDTO> {
    @Autowired
    private ClientService clientService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public boolean isValid(ClientDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> fieldMessages = new ArrayList<>();
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long id = Long.parseLong(map.get("id"));
        Optional<Client> client = clientService.findByEmail(value.getEmail());
        if (client.isPresent() && !client.get().getId().equals(id))
            fieldMessages.add(new FieldMessage("Email", "Email already registered."));
        fieldMessages.forEach(fieldMessage -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName())
                    .addConstraintViolation();
        });
        return fieldMessages.isEmpty();
    }
}
