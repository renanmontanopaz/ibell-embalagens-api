package br.com.uniamerica.ibellembalagens.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@Table(name = "tb_provider")
public class Provider extends AbstractEntity {

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "cnpj_cpf", length = 18, nullable = false, unique = true)
    private String cnpjCpf;

    @Column(name = "phone_number", length = 14, nullable = false)
    private String phoneNumber;

    @Column(name = "addres", length = 50, nullable = false)
    private String addres;

    @Email
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "observation", length = 255, nullable = true)
    private String observation;

}
