package br.com.uniamerica.ibellembalagens.Service;

import br.com.uniamerica.ibellembalagens.Entity.StockInput;
import br.com.uniamerica.ibellembalagens.Repository.ProductRepository;
import br.com.uniamerica.ibellembalagens.Repository.StockInputRepository;
import br.com.uniamerica.ibellembalagens.Repository.StockOutputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class StockInputService {

    @Autowired
    private StockInputRepository stockInputRepository;

    @Autowired
    private StockOutputRepository stockOutputRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public StockInput save(StockInput stockInput) {

        this.stockInputRepository.save(stockInput);

        var product = this.productRepository.findById(stockInput.getProduct().getId()).get();

        var sumOutput = this.stockOutputRepository.getSumOutputQuantity(stockInput.getProduct().getId());
        var sumInput = this.stockInputRepository.getSumInputQuantity(stockInput.getProduct().getId());

        if (sumOutput == null){
            sumOutput = Float.valueOf(0);
        }

        var newQuantity = sumInput - sumOutput;
        product.setQuantity(newQuantity);

//        var product = this.productRepository.findById(stockInput.getProduct().getId()).get();
//        var quantity = this.productRepository.getQuantityByIdProduct(stockInput.getProduct().getId());
//        float quantityInput = stockInput.getInputQuantity();
//        quantity += quantityInput;
//        product.setQuantity(quantity);

        var value = this.stockInputRepository.updateNewCostValue(stockInput.getProduct().getId());
        var quantity = this.productRepository.getQuantityByIdProduct(stockInput.getProduct().getId());
        var newValueUnity = value / quantity;
        product.setUnitValue(BigDecimal.valueOf(newValueUnity));

        this.productRepository.save(product);

        return this.stockInputRepository.save(stockInput);

    }

    public List<StockInput> listAll() {
        return this.stockInputRepository.findAll();
    }

    public StockInput findById(Long id) {
        return this.stockInputRepository.findById(id).orElse(new StockInput());
    }

    @Transactional
    public void update(Long id, StockInput stockInput) {
        if(id == stockInput.getId()) {
            this.stockInputRepository.save(stockInput);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void disable(Long id){
        var stockInput = this.stockInputRepository.findById(id);
        if (id == stockInput.get().getId()) {
            this.stockInputRepository.disable(id);
        }
        else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void enabled(Long id){
        var stockInput = this.stockInputRepository.findById(id);
        if (id == stockInput.get().getId()) {
            this.stockInputRepository.enabled(id);
        }
        else {
            throw new RuntimeException();
        }
    }

    public List<StockInput> findByProductInStockInput(Long id) {
        return this.stockInputRepository.findByProductInStockInput(id);
    }

    public List<StockInput> findByProviderInStockInput(Long id) {
        return this.stockInputRepository.findByProviderInStockInput(id);

    }

    public List<StockInput> findByActiveStockInputs() {
        return this.stockInputRepository.findByActiveStockInputs();
    }

    public List<StockInput> findByInactiveStockInputs() {
        return this.stockInputRepository.findByInactiveStockInputs();
    }

}
