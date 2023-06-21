package br.com.uniamerica.ibellembalagens.Repository;

import br.com.uniamerica.ibellembalagens.Entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    @Modifying
    @Query("UPDATE Administrator adm SET adm.active = false WHERE adm.id = :id")
    public void disable(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Administrator adm SET adm.active = true WHERE adm.id = :id")
    public void enabled(@Param("id") Long id);

    @Query("SELECT adm FROM Administrator adm WHERE adm.active = true")
    public List<Administrator> findByActiveAdm();

    @Query("SELECT adm FROM Administrator adm WHERE adm.active = false")
    public List<Administrator> findByInactiveAdm();

}
