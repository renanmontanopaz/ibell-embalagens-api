package br.com.uniamerica.ibellembalagens.Service;

import br.com.uniamerica.ibellembalagens.Entity.Administrator;
import br.com.uniamerica.ibellembalagens.Repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Transactional
    public Administrator save(Administrator administrator) {
        return this.administratorRepository.save(administrator);
    }

    public List<Administrator> listAll() {
        return this.administratorRepository.findAll();
    }

    public Administrator findById(Long id) {
        return this.administratorRepository.findById(id).orElse(new Administrator());
    }

    @Transactional
    public void update(Long id, Administrator administrator) {
        if(id == administrator.getId()) {
            this.administratorRepository.save(administrator);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void disable(Long id){
        var adm = this.administratorRepository.findById(id);
        if (id == adm.get().getId()) {
            this.administratorRepository.disable(id);
        }
        else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void enabled(Long id){
        var adm = this.administratorRepository.findById(id);
        if (id == adm.get().getId()) {
            this.administratorRepository.enabled(id);
        }
        else {
            throw new RuntimeException();
        }
    }

    public List<Administrator> findByActiveAdm() {
        return this.administratorRepository.findByActiveAdm();
    }

    public List<Administrator> findByInactiveAdm() {
        return this.administratorRepository.findByInactiveAdm();
    }

}
