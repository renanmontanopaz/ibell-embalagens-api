package br.com.uniamerica.ibellembalagens.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tb_stock_output")
public class StockOutput extends AbstractEntity {

    @JoinColumn(name = "id_client", nullable = false)
    @ManyToOne
    private Client client;

    @JoinColumn(name = "id_product", nullable = false)
    @ManyToOne
    private Product product;

    @Column(name = "quantity_output", length = 25, nullable = false)
    private Float quantityOutput;

    @Column(name = "sale_value", length = 25, nullable = false)
    private BigDecimal saleValue;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "departure_date", length = 25, nullable = false)
    private LocalDateTime departureDate;

    @Column(name = "observation", length = 255, nullable = true)
    private String observation;

}
