package br.com.uniamerica.ibellembalagens.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tb_stock_input")
public class StockInput extends AbstractEntity {

    @Getter @Setter
    @JoinColumn(name = "id_product", nullable = false)
    @ManyToOne
    private Product product;

    @Getter @Setter
    @JoinColumn(name = "id_provider", nullable = false)
    @ManyToOne
    private Provider provider;

    @Getter @Setter
    @Column(name = "cost_value", nullable = false)
    private Float costValue;

    @Getter @Setter
    @Column(name = "input_quantity", length = 25, nullable = false)
    private Float inputQuantity;

    @Getter @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "date_entry", length = 25, nullable = false)
    private Date dateEntry;

    @Getter@Setter
    @Column(name = "observation", length = 255, nullable = true)
    private String observation;

}
