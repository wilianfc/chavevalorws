package mack.chavevalor.dao;


import java.util.List;

public interface ChaveValorUx {
    public enum OPCOES {
        CRIAR, ATUALIZAR, LER, APAGAR, SAIR, INVALIDA
    };  
    public OPCOES menuPrincipal();
    public ChaveValor criarChaveValor();
    public void mostrarMensagem(String msg);
    public void listar(List<ChaveValor> profs);
    public ChaveValor atualizarChaveValor();
    public long apagar();
}
