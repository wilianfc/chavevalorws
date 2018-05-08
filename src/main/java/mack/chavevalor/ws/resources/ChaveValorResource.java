package mack.chavevalor.ws.resources;

import java.util.Date;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import mack.chavevalor.dao.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.dropwizard.jersey.params.LongParam;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.PermitAll;

@Path("chavevalor")
@Produces(MediaType.APPLICATION_JSON)
public class ChaveValorResource {

    private Dao<ChaveValor> dao = null;
    
    public ChaveValorResource() {
        try {
            ConexaoDb conexao;
            conexao = new ConexaoDb("jdbc:derby:faculdade;create=true", "app", "app");
            dao = new ChaveValorDao(conexao);            
        } catch(Exception e) {
            e.printStackTrace();
        }        
    }

    @GET
    @PermitAll
    public List<ChaveValor> listar() {
        List<ChaveValor> chavesValores = null;
        try {
            chavesValores = dao.ler();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return chavesValores;

    }
    
    @GET
    @Path("{id}")
    //@RolesAllowed("")
    @PermitAll
    public ChaveValor buscar(@PathParam("id") LongParam id) {
        ChaveValor p = null;
        try {
            p = dao.lerPeloId(id.get());
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (p == null) {
            throw new WebApplicationException("Professor não encontrado", 404);
        }
        return p;
    }
    
    @POST
    //@RolesAllowed("ADMIN")
    @PermitAll
    public ChaveValor criar(ChaveValor chaveValor) {
        try {
            if (chaveValor.getDatahora() == -1) {
                chaveValor.setDatahora(System.currentTimeMillis());
            }
            long id = dao.criar(chaveValor);
            chaveValor.setId(id);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return chaveValor;
    }
    
    @PUT
    @Path("{id}")
    public ChaveValor atualizar(@PathParam("id") LongParam id, ChaveValor chaveValor) {
        try {
            int n = dao.atualizar(chaveValor);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return chaveValor;
    }
    
    @DELETE
    @Path("{id}")
    public Response remover(@PathParam("id") LongParam id) {
        int n = -1;
        try {
            n = dao.apagar(id.get());       
        } catch(Exception e) {
            e.printStackTrace();
            throw new WebApplicationException("Erro ao tentar apagar!", 500);
        }
        if (n <= 0) {
            throw new WebApplicationException("ChaveValor com id=" + id.get() + " não encontrado!", 404);
        }

        return Response.ok().build();
    }
    
}