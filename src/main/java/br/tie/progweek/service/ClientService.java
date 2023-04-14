package br.tie.progweek.service;

import br.tie.progweek.model.Client;
import br.tie.progweek.repository.ClientRepository;
import br.tie.progweek.util.LocalTimeZone;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> list() {
        return clientRepository.findAll();
    }

    public Client saveAndUpdate(Client client) {
        if (!client.getId().isEmpty()) {
            return update(client);
        } else {
            return save(client);
        }
    }
    public Client save(Client client) {
        try {
            client.setId(ObjectId.get().toString());
            client.setCreated(LocalTimeZone.now());
            return clientRepository.save(client);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public Optional<Client> getClient(String id) {
        try {
            return clientRepository.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public Client update(Client client) {
        try {
            Optional<Client> optClient = getClient(client.getId());
            if (optClient.isPresent()) {
                Client clientDB = optClient.get();
                clientDB.setName(client.getName());
                clientDB.setCel(client.getCel());
                clientDB.setCpf(client.getCpf());
                clientDB.setEmail(client.getEmail());
                clientDB.setCompany(client.getCompany());
                clientDB.setUpdated(LocalTimeZone.now());
                return clientRepository.save(clientDB);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public Boolean delete(String id) {
        try {
            clientRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
