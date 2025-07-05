import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioServiceTest {

    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        usuarioService = new UsuarioService();
    }

    @Test
    public void testAddUsuario() {
        Usuario usuario = new Usuario(1, "Juan", "juan@email.com");
        usuarioService.addUsuario(usuario);
        assertEquals(1, usuarioService.getAllUsuarios().size());
        assertEquals(usuario, usuarioService.getUsuarioById(1));
    }

    @Test
    public void testGetAllUsuarios() {
        usuarioService.addUsuario(new Usuario(1, "Juan", "juan@email.com"));
        usuarioService.addUsuario(new Usuario(2, "Ana", "ana@email.com"));
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        assertEquals(2, usuarios.size());
    }

    @Test
    public void testGetUsuarioById() {
        Usuario usuario = new Usuario(1, "Juan", "juan@email.com");
        usuarioService.addUsuario(usuario);
        Usuario found = usuarioService.getUsuarioById(1);
        assertNotNull(found);
        assertEquals("Juan", found.getName());
    }

    @Test
    public void testUpdateUsuario() {
        usuarioService.addUsuario(new Usuario(1, "Juan", "juan@email.com"));
        boolean updated = usuarioService.updateUsuario(1, "Juan Updated", "juan.updated@email.com");
        assertTrue(updated);
        Usuario usuario = usuarioService.getUsuarioById(1);
        assertEquals("Juan Updated", usuario.getName());
        assertEquals("juan.updated@email.com", usuario.getEmail());
    }

    @Test
    public void testUpdateUsuarioNotFound() {
        boolean updated = usuarioService.updateUsuario(99, "No One", "noone@email.com");
        assertFalse(updated);
    }

    @Test
    public void testDeleteUsuario() {
        usuarioService.addUsuario(new Usuario(1, "Juan", "juan@email.com"));
        boolean deleted = usuarioService.deleteUsuario(1);
        assertTrue(deleted);
        assertNull(usuarioService.getUsuarioById(1));
    }

    @Test
    public void testDeleteUsuarioNotFound() {
        boolean deleted = usuarioService.deleteUsuario(99);
        assertFalse(deleted);
    }
}