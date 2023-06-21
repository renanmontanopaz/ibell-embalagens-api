package br.com.uniamerica.ibellembalagens.Controller;

import br.com.uniamerica.ibellembalagens.Entity.Product;
import br.com.uniamerica.ibellembalagens.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody Product product
    ){
        try{
            this.productService.save(product);
            return ResponseEntity.ok().body("Produto cadastrado!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAll(

    ){
        return ResponseEntity.ok().body(this.productService.listAll());
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<Product> findById(
            @PathVariable("idProduct") Long idProduct
    ){
        return ResponseEntity.ok().body(this.productService.findById(idProduct));
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<?> update(
            @PathVariable("idProduct") Long idProduct,
            @RequestBody Product product
    ){
        try{
            this.productService.update(idProduct, product);
            return ResponseEntity.ok().body("Produto atualizado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/disable/{idProduct}")
    public ResponseEntity<?> disable(
            @PathVariable("idProduct") Long idProduct
    ){
        try{
            this.productService.disable(idProduct);
            return ResponseEntity.ok().body("Produto desativado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/enabled/{idProduct}")
    public ResponseEntity<?> enabled(
            @PathVariable("idProduct") Long idProduct
    ){
        try{
            this.productService.enabled(idProduct);
            return ResponseEntity.ok().body("Produto ativado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/actives")
    public ResponseEntity<?> findByActiveProducts() {
        return ResponseEntity.ok().body(this.productService.findByActiveProducts());
    }


    @GetMapping("/inactives")
    public ResponseEntity<?> findByInactiveProducts() {
        return ResponseEntity.ok().body(this.productService.findByInactiveProducts());
    }
}
