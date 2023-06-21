package br.com.uniamerica.ibellembalagens.Service;

import br.com.uniamerica.ibellembalagens.Entity.Product;
import br.com.uniamerica.ibellembalagens.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product save(Product product) {
        if(product.getQuantity() == null) {
            product.setQuantity(0.0F);
            product.setUnitValue(BigDecimal.valueOf(0));
        }
        return this.productRepository.save(product);
    }

    public List<Product> listAll() {
        return this.productRepository.findAll();
    }

    public Product findById(Long id) {
        return this.productRepository.findById(id).orElse(new Product());
    }

    @Transactional
    public void update(Long id, Product product) {
        if(id == product.getId()) {
            this.productRepository.save(product);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void disable(Long id){
        var product = this.productRepository.findById(id);
        if (id == product.get().getId()) {
            this.productRepository.disable(id);
        }
        else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void enabled(Long id){
        var product = this.productRepository.findById(id);
        if (id == product.get().getId()) {
            this.productRepository.enabled(id);
        }
        else {
            throw new RuntimeException();
        }
    }


    public List<Product> findByActiveProducts() {
        return this.productRepository.findByActiveProducts();
    }

    public List<Product> findByInactiveProducts() {
        return this.productRepository.findByInactiveProducts();
    }

//    public BigDecimal getUnitValueByIdProduct(Long id) {
//        return this.productRepository.getUnitValueByIdProduct(id);
//    }

}
