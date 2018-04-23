package mack.chavevalor.dao;

import java.util.List;
public interface Dao<T> {
    long criar(T item) throws ConexaoException;
    int atualizar(T item) throws ConexaoException;
    List<T> ler() throws ConexaoException;
    T lerPeloId(long id) throws ConexaoException;
    int apagar(long id) throws ConexaoException;
}
