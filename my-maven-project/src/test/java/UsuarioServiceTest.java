import org.junit.jupiter.api.*;
import java.io.File;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioServiceTest {

    private UsuarioService usuarioService;
    private static final String DB_FILE = "usuarios.db";

    @BeforeAll
    void cleanDatabaseFile() {
        File db = new File(DB_FILE);
        if (db.exists()) {
            db.delete();
        }
    }

    @BeforeEach
    public void setUp() {
        usuarioService = new UsuarioService();
    }

    @AfterEach
    void clearTable() {
        try (Connection conn = java.sql.DriverManager.getConnection("jdbc:sqlite:" + DB_FILE);
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM usuarios");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddUsuario() {
        Usuario usuario = new Usuario(1, "Juan", "juan@email.com");
        usuarioService.addUsuario(usuario);
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        assertEquals(1, usuarios.size());
        assertEquals("Juan", usuarios.get(0).getName());
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