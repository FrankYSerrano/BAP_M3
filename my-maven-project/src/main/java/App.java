import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        UsuarioService usuarioService = new UsuarioService();

        usuarioService.addUsuario(new Usuario(1, "Juan", "juan@email.com"));
        usuarioService.addUsuario(new Usuario(2, "Ana", "ana@email.com"));
        usuarioService.addUsuario(new Usuario(3, "Luis", "luis@email.com"));
        usuarioService.addUsuario(new Usuario(4, "Maria", "maria@email.com"));
        usuarioService.addUsuario(new Usuario(5, "Pedro", "pedro@email.com"));

        System.out.println(" ***** 5 usuarios creados.");

        // Listar todos los usuarios
        System.out.println(" ***** Lista de usuarios:");
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }

        // Listar un usuario aleatorio
        if (!usuarios.isEmpty()) {
            Random random = new Random();
            Usuario randomUsuario = usuarios.get(random.nextInt(usuarios.size()));
            System.out.println(" ***** Usuario aleatorio:");
            System.out.println(randomUsuario);
        }

        // Actualizar el email de un usuario aleatorio
        if (!usuarios.isEmpty()) {
            Random random = new Random();
            Usuario randomUsuario = usuarios.get(random.nextInt(usuarios.size()));
            boolean updated = usuarioService.updateUsuario(
                randomUsuario.getId(),
                randomUsuario.getName(),
                "Nuevo@email.Com"
            );
            if (updated) {
                System.out.println(" ***** Usuario actualizado: " + usuarioService.getUsuarioById(randomUsuario.getId()));
            } else {
                System.out.println("No se pudo actualizar el usuario.");
            }
        }

        // Eliminar un usuario aleatorio
        usuarios = usuarioService.getAllUsuarios(); // refrescar la lista
        if (!usuarios.isEmpty()) {
            Random random = new Random();
            Usuario randomUsuario = usuarios.get(random.nextInt(usuarios.size()));
            boolean deleted = usuarioService.deleteUsuario(randomUsuario.getId());
            if (deleted) {
                System.out.println(" ***** Usuario eliminado: " + randomUsuario);
            } else {
                System.out.println("No se pudo eliminar el usuario.");
            }
        }

        // Listar todos los usuarios después de eliminar
        System.out.println(" ***** Lista de usuarios después de eliminar:");
        usuarios = usuarioService.getAllUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
}