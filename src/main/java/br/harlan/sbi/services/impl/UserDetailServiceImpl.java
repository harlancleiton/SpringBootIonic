package br.harlan.sbi.services.impl;

import br.harlan.sbi.domain.Client;
import br.harlan.sbi.repositories.ClientRepository;
import br.harlan.sbi.security.UserDetailImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private ClientRepository clientRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Client> optional = clientRepository.findByEmail(email);
        if (optional.isPresent()) {
            Client client = optional.get();
            UserDetailImpl userDetail = new UserDetailImpl(client.getId(), client.getEmail(), client.getPassword(),
                    client.getProfiles().stream().map(
                            profile -> new SimpleGrantedAuthority(profile.getValue())).collect(Collectors.toList()));
            LOGGER.info("loadUser: Username: {}", userDetail.toString());
            return userDetail;
        } else
            throw new UsernameNotFoundException(email);
    }
}
