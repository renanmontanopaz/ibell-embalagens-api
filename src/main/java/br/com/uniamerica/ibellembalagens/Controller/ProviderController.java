package br.com.uniamerica.ibellembalagens.Controller;

import br.com.uniamerica.ibellembalagens.Entity.Provider;
import br.com.uniamerica.ibellembalagens.Service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody Provider provider
    ){
        try{
            this.providerService.save(provider);
            return ResponseEntity.ok().body("Fornecedor cadastrado!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Provider>> listAll(

    ){
        return ResponseEntity.ok().body(this.providerService.listAll());
    }

    @GetMapping("/{idProvider}")
    public ResponseEntity<Provider> findById(
            @PathVariable("idProvider") Long idProvider
    ){
        return ResponseEntity.ok().body(this.providerService.findById(idProvider));
    }

    @PutMapping("/{idProvider}")
    public ResponseEntity<?> update(
            @PathVariable("idProvider") Long idProvider,
            @RequestBody Provider provider
    ){
        try{
            this.providerService.update(idProvider, provider);
            return ResponseEntity.ok().body("Fornecedor atualizado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/disable/{idProvider}")
    public ResponseEntity<?> disable(
            @PathVariable("idProvider") Long idProvider
    ){
        try{
            this.providerService.disable(idProvider);
            return ResponseEntity.ok().body("Fornecedor desativado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/enabled/{idProvider}")
    public ResponseEntity<?> enabled(
            @PathVariable("idProvider") Long idProvider
    ){
        try{
            this.providerService.enabled(idProvider);
            return ResponseEntity.ok().body("Fornecedor ativado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/actives")
    public ResponseEntity<?> findByActiveProviders() {
        return ResponseEntity.ok().body(this.providerService.findByActiveProviders());
    }


    @GetMapping("/inactives")
    public ResponseEntity<?> findByInactiveProviders() {
        return ResponseEntity.ok().body(this.providerService.findByInactiveProviders());
    }

}
