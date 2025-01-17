package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProdottoDao implements ProdottoDaoInterfaccia{

	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	private static final String TABLE_NAME = "prodotto";

	@Override
	public synchronized void doSave(ProdottoBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProdottoDao.TABLE_NAME
				+ " (NOME, CATEGORIA, DESCRIZIONE, PREZZO, QUANTITA, MATERIALE, DATA_USCITA, IN_VENDITA, IVA, IMMAGINE, DESCRIZIONE_DETTAGLIATA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getNome());
			preparedStatement.setString(2, product.getCategoria());
			preparedStatement.setString(3, product.getDescrizione());
			preparedStatement.setDouble(4, product.getPrezzo());
			preparedStatement.setInt(5, product.getQuantità());
			preparedStatement.setString(6,product.getMateriale());
			preparedStatement.setString(7, product.getDataUscita());
			preparedStatement.setBoolean(8, product.isInVendita());
			preparedStatement.setString(9, product.getIva());
			preparedStatement.setString(10, product.getImmagine());
			preparedStatement.setString(11, product.getDescrizioneDettagliata());


			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	@Override
	public synchronized ProdottoBean doRetrieveByKey(int idProdotto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProdottoBean bean = new ProdottoBean();

		String selectSQL = "SELECT * FROM " + ProdottoDao.TABLE_NAME + " WHERE ID_PRODOTTO = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idProdotto);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdProdotto(rs.getInt("ID_PRODOTTO"));
				bean.setNome(rs.getString("NOME"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setPrezzo(rs.getDouble("PREZZO"));
				bean.setQuantità(rs.getInt("QUANTITA"));
				bean.setCategoria(rs.getString("CATEGORIA"));
				bean.setIva(rs.getString("IVA"));
				bean.setDataUscita(rs.getString("DATA_USCITA"));
				bean.setInVendita(rs.getBoolean("IN_VENDITA"));
				bean.setImmagine(rs.getString("IMMAGINE"));
				bean.setMateriale(rs.getString("MATERIALE"));
				bean.setDescrizioneDettagliata(rs.getString("DESCRIZIONE_DETTAGLIATA"));


			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}

	@Override
	public synchronized boolean doDelete(int idProdotto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProdottoDao.TABLE_NAME + " WHERE ID_PRODOTTO = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, idProdotto);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized ArrayList<ProdottoBean> doRetrieveAll(String order) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    ArrayList<ProdottoBean> products = new ArrayList<ProdottoBean>();

	    // Lista delle colonne consentite per l'ordinamento
	    List<String> allowedColumns = List.of("ID_PRODOTTO", "NOME", "PREZZO", "QUANTITA", "CATEGORIA", "IVA", "DATA_USCITA", "IN_VENDITA", "IMMAGINE", "MATERIALE");

	    // Query base
	    String selectSQL = "SELECT * FROM " + ProdottoDao.TABLE_NAME;

	    // Validazione del parametro 'order'
	    if (order != null && allowedColumns.contains(order.toUpperCase())) {
	        selectSQL += " ORDER BY " + order;
	    }

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            ProdottoBean bean = new ProdottoBean();

	            bean.setIdProdotto(rs.getInt("ID_PRODOTTO"));
	            bean.setNome(rs.getString("NOME"));
	            bean.setDescrizione(rs.getString("DESCRIZIONE"));
	            bean.setPrezzo(rs.getDouble("PREZZO"));
	            bean.setQuantità(rs.getInt("QUANTITA"));
	            bean.setCategoria(rs.getString("CATEGORIA"));
	            bean.setIva(rs.getString("IVA"));
	            bean.setDataUscita(rs.getString("DATA_USCITA"));
	            bean.setInVendita(rs.getBoolean("IN_VENDITA"));
	            bean.setImmagine(rs.getString("IMMAGINE"));
	            bean.setMateriale(rs.getString("MATERIALE"));
	            bean.setDescrizioneDettagliata(rs.getString("DESCRIZIONE_DETTAGLIATA"));

	            products.add(bean);
	        }

	    } finally {
	        try {
	            if (preparedStatement != null)
	                preparedStatement.close();
	        } finally {
	            if (connection != null)
	                connection.close();
	        }
	    }
	    return products;
	}

	
	@Override
	public synchronized void doUpdateQnt(int id, int qnt) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE " + ProdottoDao.TABLE_NAME
				+ " SET QUANTITA = ? "
				+ " WHERE ID_PRODOTTO = ? ";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setInt(1, qnt);
			preparedStatement.setInt(2, id);

			

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}
	
	public synchronized void doUpdate(ProdottoBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE " + ProdottoDao.TABLE_NAME
				+ " SET NOME = ? , QUANTITA = ? , CATEGORIA = ?, DESCRIZIONE = ?, PREZZO = ?, MATERIALE = ?, DATA_USCITA = ?, IN_VENDITA = ?, IVA = ?, IMMAGINE = ?, DESCRIZIONE_DETTAGLIATA = ?"
				+ " WHERE ID_PRODOTTO = ? ";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, product.getNome());
			preparedStatement.setInt(2, product.getQuantità());
			preparedStatement.setString(3, product.getCategoria());
			preparedStatement.setString(4, product.getDescrizione());
			preparedStatement.setDouble(5, product.getPrezzo());
			preparedStatement.setString(6,product.getMateriale());
			preparedStatement.setString(7, product.getDataUscita());
			preparedStatement.setBoolean(8, true);
			preparedStatement.setString(9, product.getIva());
			preparedStatement.setString(10, product.getImmagine());
			preparedStatement.setString(11, product.getDescrizioneDettagliata());
			preparedStatement.setInt(12, product.getIdProdotto());

			

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}
	
	@Override
	public synchronized ArrayList<ProdottoBean> doRetrieveByCategoria(String categoria) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProdottoBean> prodotti = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + ProdottoDao.TABLE_NAME + " WHERE CATEGORIA = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, categoria);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProdottoBean bean = new ProdottoBean();
				bean.setIdProdotto(rs.getInt("ID_PRODOTTO"));
				bean.setNome(rs.getString("NOME"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
				bean.setPrezzo(rs.getDouble("PREZZO"));
				bean.setQuantità(rs.getInt("QUANTITA"));
				bean.setCategoria(rs.getString("CATEGORIA"));
				bean.setIva(rs.getString("IVA"));
				bean.setDataUscita(rs.getString("DATA_USCITA"));
				bean.setInVendita(rs.getBoolean("IN_VENDITA"));
				bean.setImmagine(rs.getString("IMMAGINE"));
				bean.setMateriale(rs.getString("MATERIALE"));
				bean.setDescrizioneDettagliata(rs.getString("DESCRIZIONE_DETTAGLIATA"));
				
				prodotti.add(bean);


			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return prodotti;
	}
	
	

}