package mack.lp3.ws.api;

import java.util.Date;

public class ChaveValor {
    private long id;
    private long datahora;
    private String chave;
    private String valor;

    
    public ChaveValor() {
    }

    public ChaveValor(long datahora, String chave, String valor) {
        this.datahora = datahora;
        this.chave = chave;
        this.valor = valor;
    }
    
    public ChaveValor(long id, long datahora, String chave, String valor) {
        this.id = id;
        this.datahora = datahora;
        this.chave = chave;
        this.valor = valor;
    }
    
    public long getId() { return id; }
    public long getDatahora() { return datahora; }
    public String getChave() { return chave; }
    public String getValor() { return valor; } 
    
    public void setId(long id) { this.id = id; }
    public void setDatahora(long datahora) { this.datahora = datahora; }
    public void setChave(String chave) { this.chave = chave; }
    public void setValor(String valor) { this.valor = valor; }
}
