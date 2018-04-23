package mack.chavevalor.dao;


import java.sql.*;
import java.util.*;

public class ChaveValorApp {

    public static void main(String[] args) {
        try {
            ConexaoDb conexao;
            conexao = new ConexaoDb("jdbc:derby:faculdade;create=true", "app", "app");
            Dao<ChaveValor> dao;
            dao = new ChaveValorDao(conexao);
            ChaveValorUx ux;
            ux = new ChaveValorConsole();
            
            boolean querSair = false;
            while (!querSair) {
                ChaveValorUx.OPCOES opcao = ux.menuPrincipal();
                if (opcao == ChaveValorUx.OPCOES.CRIAR) {
                    ChaveValor p = ux.criarChaveValor();
                    long id = dao.criar(p);
                    ux.mostrarMensagem("Id do registro: " + id);
                } else if (opcao == ChaveValorUx.OPCOES.LER) {
                    List<ChaveValor> profs;
                    profs = dao.ler();
                    ux.listar(profs);
                    
                } else if (opcao == ChaveValorUx.OPCOES.ATUALIZAR) {
                    ChaveValor professor = ux.atualizarChaveValor();
                    int retorno = dao.atualizar(professor);
                    ux.mostrarMensagem("Registros atualizados: " + retorno);
                } else if (opcao == ChaveValorUx.OPCOES.APAGAR) {
                    // TODO: apagar o registro com este id
                    long id = ux.apagar();
                    int retorno = dao.apagar(id);
                    ux.mostrarMensagem("Registros apagados: " + retorno);
                } else if (opcao == ChaveValorUx.OPCOES.SAIR) {
                    querSair = true;
                } else {
                    ux.mostrarMensagem("Opção inválida!");
                }
            }
        } catch (ConexaoException ex) {
            ex.printStackTrace();
        } 
    }
}
