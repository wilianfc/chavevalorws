package mack.chavevalor.dao;


import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class ChaveValorDao implements Dao<ChaveValor> {

    private ConexaoDb conexaoDb;
    private final static String sqlCriarTabela = "CREATE TABLE chave_valor " 
        + "(id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
        + "datahora BIGINT NOT NULL,"
        + "chave VARCHAR(100) NOT NULL,"
        + "valor VARCHAR(100) NOT NULL,"
        + "PRIMARY KEY (id))";
    private final static String sqlCriar = "INSERT INTO chave_valor (datahora, chave, valor) VALUES (?,?,?)";
    private final static String sqlAtualizar = "UPDATE chave_valor SET datahora=?, chave=?, valor=? WHERE id=?";
    private final static String sqlLer = "SELECT * from chave_valor";
    private final static String sqlLerPeloId = "SELECT * FROM chave_valor WHERE id=?";
    private final static String sqlApagar = "DELETE FROM chave_valor WHERE id=?";
    private PreparedStatement stmCriar;
    private PreparedStatement stmAtualizar;
    private PreparedStatement stmLer;
    private PreparedStatement stmLerPeloId;
    private PreparedStatement stmApagar;

    public ChaveValorDao() {
    }

    public ChaveValorDao(ConexaoDb conexaoDb) throws ConexaoException {
        this.conexaoDb = conexaoDb;
        try {
            criarTabela();
            stmCriar = conexaoDb.getConnection().prepareStatement(sqlCriar, Statement.RETURN_GENERATED_KEYS);
            stmAtualizar = conexaoDb.getConnection().prepareStatement(sqlAtualizar);
            stmLer = conexaoDb.getConnection().prepareStatement(sqlLer);
            stmLerPeloId = conexaoDb.getConnection().prepareStatement(sqlLerPeloId);
            stmApagar = conexaoDb.getConnection().prepareStatement(sqlApagar);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ConexaoException("Erro ao preparar statement.");
        }
    }

    private void criarTabela() throws ConexaoException {
        try {
            Statement stm = conexaoDb.getConnection().createStatement();
            stm.execute(sqlCriarTabela);
            System.out.println("Tabela criada com sucesso!");
        } catch( SQLException ex ) {
            System.out.println("Tabela já existe!");
        }

    }

    @Override
    public long criar(ChaveValor chavevalor) throws ConexaoException {
        long id = -1;
        try {
            stmCriar.setLong(1, chavevalor.getDatahora());
            stmCriar.setString(2, chavevalor.getChave());
            stmCriar.setString(3, chavevalor.getValor());
            int retorno = stmCriar.executeUpdate();
            if (retorno > 0) {
                ResultSet rs = stmCriar.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getLong(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ConexaoException("Erro ao criar registro!");
        }
        return id;
    }

    @Override
    public int atualizar(ChaveValor chavevalor) throws ConexaoException {
        int nRegistros = 0;
        try {
            stmAtualizar.setLong(1, chavevalor.getDatahora());
            stmAtualizar.setString(2, chavevalor.getChave());
            stmAtualizar.setString(3, chavevalor.getValor());
            stmAtualizar.setLong(4, chavevalor.getId());
            nRegistros = stmAtualizar.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ConexaoException("Erro ao atualizar registro!");
        }
        return nRegistros;
    }

    @Override
    public List<ChaveValor> ler() throws ConexaoException {
        List<ChaveValor> professores = new ArrayList<>();
        try {
            ResultSet rs = stmLer.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                long datahora = rs.getLong("datahora");
                String chave = rs.getString("chave");
                String valor = rs.getString("valor");
                ChaveValor p = new ChaveValor(id, datahora, chave, valor);
                professores.add(p);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ConexaoException("Erro ao ler registros!");
        }
        return professores;
    }

    @Override
    public ChaveValor lerPeloId(long id)
            throws ConexaoException {
        ChaveValor p = null;
        try {
            stmLerPeloId.setLong(1, id);
            ResultSet rs = stmLerPeloId.executeQuery();
            if (rs.next()) {
                long datahora = rs.getLong("datahora");
                String chave = rs.getString("chave");
                String valor = rs.getString("valor");
                p = new ChaveValor(id, datahora, chave, valor);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ConexaoException("Erro ao ler registro pelo id!");
        }
        return p;
    }

    @Override
    public int apagar(long id) throws ConexaoException {
        int numRegistros = 0;
        try {
            stmApagar.setLong(1, id);
            numRegistros = stmApagar.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
            throw new ConexaoException("Erro ao apagar!");            
        }
        return numRegistros;
    }
}
