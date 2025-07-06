import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExampleTest {

    @Test
    public void testWithMock() {
        // Mock the UsuarioService class
        UsuarioService mockService = mock(UsuarioService.class);

        // Define behavior for the mock
        when(mockService.getUsuarioById(1))
            .thenReturn(new Usuario(1, "Mock", "mock@email.com"));

        // Use the mock
        Usuario usuario = mockService.getUsuarioById(1);

        // Assert the mock behavior
        assertNotNull(usuario);
        assertEquals("Mock", usuario.getName());
        assertEquals("mock@email.com", usuario.getEmail());

        // Verify interaction
        verify(mockService).getUsuarioById(1);
    }
}