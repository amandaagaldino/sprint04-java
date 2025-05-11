package fiap.tds.resources;

import fiap.tds.Dtos.LoginDto;
import fiap.tds.entities.objects.Usuario;
import fiap.tds.repositories.UsuarioRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    UsuarioRepository usuarioRepository;

    @POST
    @Path("/login")
    public Response login(LoginDto login) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(login.id);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getSenha().equals(login.senha)) {
                return Response.ok("Login bem-sucedido!").build();
            }
        }

        return Response.status(Response.Status.UNAUTHORIZED)
                .entity("ID ou senha inválidos.").build();
    }
}
