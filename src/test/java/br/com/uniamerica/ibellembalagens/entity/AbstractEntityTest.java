package br.com.uniamerica.ibellembalagens.entity;

import br.com.uniamerica.ibellembalagens.Entity.AbstractEntity;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractEntityTest {

    @Test
    public void testPrePersist() {
        // Criando uma classe concreta para testar AbstractEntity
        class ConcreteEntity extends AbstractEntity {}

        ConcreteEntity entity = new ConcreteEntity();
        entity.dateRegister();

        assertNotNull(entity.getRegister());
        assertTrue(entity.getActive());
        assertNull(entity.getUpdate());
    }

    @Test
    public void testPreUpdate() {
        // Criando uma classe concreta para testar AbstractEntity
        class ConcreteEntity extends AbstractEntity {}

        ConcreteEntity entity = new ConcreteEntity();
        entity.dateUpdate();

        assertNotNull(entity.getUpdate());
        assertNull(entity.getRegister());
        assertNull(entity.getActive());
    }

    @Test
    public void testGettersAndSetters() {
        // Criando uma classe concreta para testar AbstractEntity
        class ConcreteEntity extends AbstractEntity {}

        ConcreteEntity entity = new ConcreteEntity();

        // Testando setters e getters
        Long id = 1L;
        Boolean active = true;
        LocalDateTime register = LocalDateTime.now();
        LocalDateTime update = LocalDateTime.now().plusHours(1);

        entity.setId(id);
        entity.setActive(active);
        entity.setRegister(register);
        entity.setUpdate(update);

        assertEquals(id, entity.getId());
        assertEquals(active, entity.getActive());
        assertEquals(register, entity.getRegister());
        assertEquals(update, entity.getUpdate());
    }
}