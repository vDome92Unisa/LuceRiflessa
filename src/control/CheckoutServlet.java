package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/Checkout")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProdottoDao daoProd = new ProdottoDao();
        OrdineDao daoOrd = new OrdineDao();
        ComposizioneDao daoComp = new ComposizioneDao();
        IndirizzoSpedizioneDao daoSped = new IndirizzoSpedizioneDao();
        MetodoPagamentoDao daoPag = new MetodoPagamentoDao();

        UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");
        OrdineBean ordine = new OrdineBean();
        ComposizioneBean comp = new ComposizioneBean();
        IndirizzoSpedizioneBean sped = new IndirizzoSpedizioneBean();
        MetodoPagamentoBean pag = new MetodoPagamentoBean();

        Carrello cart = (Carrello) request.getSession().getAttribute("cart");
        Double prezzoTot = cart.calcolaCosto();

        Date now = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mysqlDateString = formatter.format(now);

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String telefono = request.getParameter("tel");
        String città = request.getParameter("città");
        String ind = request.getParameter("ind");
        String cap = request.getParameter("cap");
        String prov = request.getParameter("prov");

        String tit = request.getParameter("tit");
        String numC = request.getParameter("numC");
        String scad = request.getParameter("scad");

        try {
            // Controlla e salva indirizzo di spedizione se non esiste
            if(daoSped.doRetrieveByKey(ind,cap)==null){
                sped.setNome(nome);
                sped.setCognome(cognome);
                sped.setIndirizzo(ind);
                sped.setTelefono(telefono);
                sped.setCap(cap);
                sped.setProvincia(prov);
                sped.setCittà(città);
                daoSped.doSave(sped);
            }

            // Controlla e salva metodo di pagamento se non esiste
            if(daoPag.doRetrieveByKey(numC)==null){
                pag.setTitolare(tit);
                pag.setNumero(numC);
                pag.setScadenza(scad);
                daoPag.doSave(pag);
            }

            // Recupera l'ID del metodo di pagamento salvato
            pag = daoPag.doRetrieveByKey(numC);

            // Salva l'ordine
            ordine.setEmail(user.getEmail());
            ordine.setIndirizzo(ind);
            ordine.setCap(cap);
            ordine.setCartaCredito(numC); // Assicurati che questo numero esista nella tabella metodo_pagamento
            ordine.setData(mysqlDateString);
            ordine.setStato("confermato");
            ordine.setImportoTotale(prezzoTot);
            daoOrd.doSave(ordine);

            // Recupera l'ID dell'ordine appena salvato
            ArrayList<OrdineBean> ordini = daoOrd.doRetrieveByEmail(user.getEmail());
            int newId = ordini.get(ordini.size() - 1).getIdOrdine();

            // Salva le composizioni dell'ordine e aggiorna la quantità dei prodotti
            for(int i = 0; i < cart.size() ; i++) {
                int qnt = cart.get(i).getQuantitàCarrello();
                ProdottoBean prod = cart.get(i).getProdotto();
                int newQnt = prod.getQuantità() - qnt;

                daoProd.doUpdateQnt(cart.get(i).getId(), newQnt);

                comp.setIdOrdine(newId);
                comp.setIdProdotto(cart.get(i).getId());
                comp.setPrezzoTotale(cart.get(i).getTotalPrice());
                comp.setIva(cart.get(i).getProdotto().getIva());
                comp.setQuantità(cart.get(i).getQuantitàCarrello());
                daoComp.doSave(comp);
            }

        } catch(SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/errorPage.jsp"); // Redirect to an error page
            return;
        }

        // Rimuove il carrello dalla sessione
        request.getSession().removeAttribute("cart");

        // Redirect alla home page dopo il checkout
        response.sendRedirect(request.getContextPath() + "/Home.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

