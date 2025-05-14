package fiap.tds.services;

import fiap.tds.Dtos.UsuarioDto;
import fiap.tds.entities.objects.Usuario;
import fiap.tds.repositories.UsuarioRepository;

import java.util.Optional;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    public boolean validarUsuario(UsuarioDto dto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.getById(dto.id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return usuario.getSenha().equals(dto.senha);
        }

        return false; // Usuário não encontrado
    }
}
