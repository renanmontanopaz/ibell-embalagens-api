package br.com.uniamerica.ibellembalagens.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "tb_client")
public class Client extends AbstractEntity {

    @Getter @Setter
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Getter @Setter
    @Column(name = "cnpj_cpf", length = 18, nullable = false, unique = true)
    private String cnpjCpf;

    @Getter @Setter
    @Column(name = "phone_number", length = 14, nullable = false)
    private String phoneNumber;

    @Getter @Setter
    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Getter@Setter
    @Email
    @Column(name = "email", length = 40, nullable = false)
    private String email;

    @Getter @Setter
    @Column(name = "observation", length = 255, nullable = true)
    private String observation;

}
