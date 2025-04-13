package br.com.uniamerica.ibellembalagens.entity;

import br.com.uniamerica.ibellembalagens.Entity.Administrator;
import org.junit.jupiter.api.Test;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AdministradorTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testValidAdministrator() {
        Administrator admin = new Administrator();
        admin.setUsername("validUser");
        admin.setPassword("validPass123");

        var violations = validator.validate(admin);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidUsernameLength() {
        Administrator admin = new Administrator();
        admin.setUsername("a"); // Muito curto
        admin.setPassword("validPass123");

        var violations = validator.validate(admin);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("O username devera ter no minimo 3 caracteres e no maximo 25 caracteres",
                violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidPasswordLength() {
        Administrator admin = new Administrator();
        admin.setUsername("validUser");
        admin.setPassword("a"); // Muito curto

        var violations = validator.validate(admin);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("A senha devera ter no minimo 3 caracteres e no maximo 25 caracteres",
                violations.iterator().next().getMessage());
    }

    @Test
    public void testGettersAndSetters() {
        Administrator admin = new Administrator();
        String username = "testUser";
        String password = "testPass123";

        admin.setUsername(username);
        admin.setPassword(password);

        assertEquals(username, admin.getUsername());
        assertEquals(password, admin.getPassword());
    }

    @Test
    public void testInheritanceFromAbstractEntity() {
        Administrator admin = new Administrator();
        admin.setActive(true);
        admin.setRegister(LocalDateTime.now());

        assertTrue(admin.getActive());
        assertNotNull(admin.getRegister());
        assertNull(admin.getUpdate());
    }
}