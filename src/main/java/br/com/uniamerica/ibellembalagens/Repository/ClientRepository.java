package br.com.uniamerica.ibellembalagens.Repository;

import br.com.uniamerica.ibellembalagens.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Modifying
    @Query("UPDATE Client client SET client.active = false WHERE client.id = :id")
    public void disable(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Client client SET client.active = true WHERE client.id = :id")
    public void enabled(@Param("id") Long id);

    @Query("SELECT client FROM Client client WHERE client.active = true")
    public List<Client> findByActiveClients();

    @Query("SELECT client FROM Client client WHERE client.active = false")
    public List<Client> findByInactiveClients();

}
