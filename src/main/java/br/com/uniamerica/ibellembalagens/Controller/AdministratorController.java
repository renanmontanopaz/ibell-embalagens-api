package br.com.uniamerica.ibellembalagens.Controller;

import br.com.uniamerica.ibellembalagens.Entity.Administrator;
import br.com.uniamerica.ibellembalagens.Service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody Administrator administrator
    ){
        try{
            this.administratorService.save(administrator);
            return ResponseEntity.ok().body("Administrador cadastrado!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Administrator>> listAll(

    ){
        return ResponseEntity.ok().body(this.administratorService.listAll());
    }

    @GetMapping("/{idAdm}")
    public ResponseEntity<Administrator> findById(
            @PathVariable("idAdm") Long idAdm
    ){
        return ResponseEntity.ok().body(this.administratorService.findById(idAdm));
    }

    @PutMapping("/{idAdm}")
    public ResponseEntity<?> update(
            @PathVariable("idAdm") Long idAdm,
            @RequestBody Administrator administrator
    ){
        try{
            this.administratorService.update(idAdm, administrator);
            return ResponseEntity.ok().body("Administrador atualizado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/disable/{idAdm}")
    public ResponseEntity<?> disable(
            @PathVariable("idAdm") Long idAdm
    ){
        try{
            this.administratorService.disable(idAdm);
            return ResponseEntity.ok().body("Administrador desativado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/enabled/{idAdm}")
    public ResponseEntity<?> enabled(
            @PathVariable("idAdm") Long idAdm
    ){
        try{
            this.administratorService.enabled(idAdm);
            return ResponseEntity.ok().body("Administrador ativado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/actives")
    public ResponseEntity<?> findByActiveAdm() {
        return ResponseEntity.ok().body(this.administratorService.findByActiveAdm());
    }


    @GetMapping("/inactives")
    public ResponseEntity<?> findByInactiveAdm() {
        return ResponseEntity.ok().body(this.administratorService.findByInactiveAdm());
    }

}
