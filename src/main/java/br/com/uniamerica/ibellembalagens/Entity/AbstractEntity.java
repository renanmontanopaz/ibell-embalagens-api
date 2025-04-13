package br.com.uniamerica.ibellembalagens.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 25, nullable = false, unique = true)
    private Long id;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    @Column(name = "register", nullable = false)
    private LocalDateTime register;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    @Column(name = "update", nullable = true)
    private LocalDateTime update;

    @PrePersist
    public void dateRegister() {
        this.setRegister(LocalDateTime.now());
        this.setActive(true);
    }

    @PreUpdate
    public void dateUpdate() {
        this.setUpdate(LocalDateTime.now());
    }
}
