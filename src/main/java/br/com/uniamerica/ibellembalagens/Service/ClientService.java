package br.com.uniamerica.ibellembalagens.Service;

import br.com.uniamerica.ibellembalagens.Entity.Client;
import br.com.uniamerica.ibellembalagens.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        return this.clientRepository.save(client);
    }

    public List<Client> listAll() {
        return this.clientRepository.findAll();
    }

    public Client findById(Long id) {
        return this.clientRepository.findById(id).orElse(new Client());
    }

    @Transactional
    public void update(Long id, Client client) {
        if(id == client.getId()) {
            this.clientRepository.save(client);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void disable(Long id){
        var client = this.clientRepository.findById(id);
        if (id == client.get().getId()) {
            this.clientRepository.disable(id);
        }
        else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void enabled(Long id){
        var client = this.clientRepository.findById(id);
        if (id == client.get().getId()) {
            this.clientRepository.enabled(id);
        }
        else {
            throw new RuntimeException();
        }
    }


    public List<Client> findByActiveClients() {
        return this.clientRepository.findByActiveClients();
    }

    public List<Client> findByInactiveClients() {
        return this.clientRepository.findByInactiveClients();
    }

}
