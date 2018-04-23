package mack.chavevalor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDb {
    private String url;
    private String usuario;
    private String senha;
    private Connection connection = null;

    public ConexaoDb() {
    }

    public ConexaoDb(String url, String usuario, String senha) {
        this.url = url;
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public Connection getConnection() throws ConexaoException {
        if (connection == null) {
            try {
                String clientDriver = "org.apache.derby.jdbc.ClientDriver";
                String embeddedDriver = "org.apache.derby.jdbc.EmbeddedDriver";
                Class.forName(embeddedDriver);
                connection = DriverManager.getConnection(url, usuario, senha);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                throw new ConexaoException("Driver não foi encontrado!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new ConexaoException("Erro de conexão!");
            }
        }
        return connection;
    }
    
    @Override
    public void finalize() {
        if (connection != null) {
            try {
                System.out.println("Fechando conexão...");
                connection.close();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
