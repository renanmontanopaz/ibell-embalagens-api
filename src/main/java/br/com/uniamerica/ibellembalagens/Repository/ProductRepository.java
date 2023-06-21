package br.com.uniamerica.ibellembalagens.Repository;

import br.com.uniamerica.ibellembalagens.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("UPDATE Product product SET product.active = false WHERE product.id = :id")
    public void disable(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Product product SET product.active = true WHERE product.id = :id")
    public void enabled(@Param("id") Long id);

    @Query("SELECT product FROM Product product WHERE product.active = true")
    public List<Product> findByActiveProducts();

    @Query("SELECT product FROM Product product WHERE product.active = false")
    public List<Product> findByInactiveProducts();

    @Query("SELECT product.quantity FROM Product product WHERE product.id = :id")
    public Float getQuantityByIdProduct(Long id);

//    @Query("SELECT product.unitValue FROM Product product WHERE product.id = :id")
//    public BigDecimal getUnitValueByIdProduct(@Param("id") Long id);

}
