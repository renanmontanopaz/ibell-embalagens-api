package br.com.uniamerica.ibellembalagens.Repository;

import br.com.uniamerica.ibellembalagens.Entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    @Modifying
    @Query("UPDATE Provider provider SET provider.active = false WHERE provider.id = :id")
    public void disable(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Provider provider SET provider.active = true WHERE provider.id = :id")
    public void enabled(@Param("id") Long id);


    @Query("SELECT provider FROM Provider provider WHERE provider.active = true")
    public List<Provider> findByActiveProviders();

    @Query("SELECT provider FROM Provider provider WHERE provider.active = false")
    public List<Provider> findByInactiveProviders();

}
