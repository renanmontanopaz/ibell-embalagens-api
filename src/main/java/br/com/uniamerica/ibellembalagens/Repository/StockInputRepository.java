package br.com.uniamerica.ibellembalagens.Repository;

import br.com.uniamerica.ibellembalagens.Entity.StockInput;
import br.com.uniamerica.ibellembalagens.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface StockInputRepository extends JpaRepository<StockInput, Long> {

    @Modifying
    @Query("UPDATE StockInput stockInput SET stockInput.active = false WHERE stockInput.id = :id")
    public void disable(@Param("id") Long id);

    @Modifying
    @Query("UPDATE StockInput stockInput SET stockInput.active = true WHERE stockInput.id = :id")
    public void enabled(@Param("id") Long id);

    @Query("FROM StockInput stockInput WHERE stockInput.product.id = :idProduct")
    public List<StockInput> findByProductInStockInput(@Param("idProduct") Long idProduct);

    @Query("FROM StockInput stockInput WHERE stockInput.provider.id = :idProvider")
    public List<StockInput> findByProviderInStockInput(@Param("idProvider") Long idProvider);


    @Query("SELECT stockInput FROM StockInput stockInput WHERE stockInput.active = true")
    public ArrayList<StockInput> findByActiveStockInputs();

    @Query("SELECT stockInput FROM StockInput stockInput WHERE stockInput.active = false")
    public List<StockInput> findByInactiveStockInputs();

    @Query("SELECT SUM(stockInput.inputQuantity) FROM StockInput stockInput WHERE stockInput.product.id = :idProduct")
    public Float getSumInputQuantity(@Param("idProduct") Long idProduct);


//    select (sum(input_quantity * cost_value) / sum(input_quantity)) as novo_valor
//
//    from ibell.tb_stock_input where stock_input.product_id = :idProduct and active = true

//     / SUM(Product.quantity)
//
    @Query("SELECT (SUM(stockInput.inputQuantity * stockInput.costValue)) " +
            "FROM StockInput stockInput WHERE stockInput.product.id = :idProduct AND stockInput.active = TRUE")
    public Float updateNewCostValue(@Param("idProduct") Long idProduct);

}
