package fiap.tds.resources;

import fiap.tds.Dtos.UsuarioDto;
import fiap.tds.services.UsuarioService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private final UsuarioService usuarioService = new UsuarioService();

    @POST
    @Path("/login")
    public Response validarUsuario(UsuarioDto dto) {
        boolean valido = usuarioService.validarUsuario(dto);

        if (valido) {
            return Response.ok("Usuário autenticado com sucesso.").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Usuário ou senha inválidos.")
                    .build();
        }
    }
}
