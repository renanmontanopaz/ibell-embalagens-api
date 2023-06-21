package br.com.uniamerica.ibellembalagens.Controller;

import br.com.uniamerica.ibellembalagens.Entity.StockOutput;
import br.com.uniamerica.ibellembalagens.Service.StockOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/stock-output")
public class StockOutputController {

    @Autowired
    private StockOutputService stockOutputService;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody StockOutput stockOutput
    ){
        try{
            this.stockOutputService.save(stockOutput);
            return ResponseEntity.ok().body("Saida de estoque cadastrada!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<StockOutput>> listAll(

    ){
        return ResponseEntity.ok().body(this.stockOutputService.listAll());
    }

    @GetMapping("/{idStockOutput}")
    public ResponseEntity<StockOutput> findById(
            @PathVariable("idStockOutput") Long idStockOutput
    ){
        return ResponseEntity.ok().body(this.stockOutputService.findById(idStockOutput));
    }

    @PutMapping("/{idStockOutput}")
    public ResponseEntity<?> update(
            @PathVariable("idStockOutput") Long idStockOutput,
            @RequestBody StockOutput stockOutput
    ){
        try{
            this.stockOutputService.update(idStockOutput, stockOutput);
            return ResponseEntity.ok().body("Saida de estoque atualizada com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/disable/{idStockOutput}")
    public ResponseEntity<?> disable(
            @PathVariable("idStockOutput") Long idStockOutput
    ){
        try{
            this.stockOutputService.disable(idStockOutput);
            return ResponseEntity.ok().body("Saida de estoque desativada com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/enabled/{idStockOutput}")
    public ResponseEntity<?> enabled(
            @PathVariable("idStockOutput") Long idStockOutput
    ){
        try{
            this.stockOutputService.enabled(idStockOutput);
            return ResponseEntity.ok().body("Saida de estoque ativada com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/product/{idProduct}")
    public ResponseEntity<?> findByProductInStockOutput(@PathVariable("idProduct") Long idProduct) {
        return ResponseEntity.ok().body(this.stockOutputService.findByProductInStockOutput(idProduct));
    }

    @GetMapping("/client/{idClient}")
    public ResponseEntity<?> findByClientInStockOutput(@PathVariable("idClient") Long idClient) {
        return ResponseEntity.ok().body(this.stockOutputService.findByClientInStockOutput(idClient));
    }

    @GetMapping("/actives")
    public ResponseEntity<?> findByActiveStockOutputs() {
        return ResponseEntity.ok().body(this.stockOutputService.findByActiveStockOutputs());
    }


    @GetMapping("/inactives")
    public ResponseEntity<?> findByInactiveStockOutputs() {
        return ResponseEntity.ok().body(this.stockOutputService.findByInactiveStockOutputs());
    }

}
