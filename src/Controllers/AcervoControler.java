package Controllers;
import Model.*;
import Others.Menu;
import Views.TelaCadastro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import static Others.Menu.LISTAR_LIVROS;
import static Others.Menu.LISTAR_TODOS_ITENS;

public class AcervoControler {
    public static final int ITEM_EMPRESTADO = 1;
    public static final int ITEM_SEM_EXEMPLAR_DISPONIVEL = 2;
    public static final int ITEM_NAO_ENCONTRADO = 3;
    private static AcervoControler instance;
    private final ArrayList<AcervoBase> listAcervos;
    private final int idCounter;
    public String value;

    private static TelaCadastro tc;
    private AcervoControler(String value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.listAcervos = new ArrayList<>();
        this.value = value;
        this.idCounter = 0;
    }

    public static AcervoControler getInstance(String value) {
        if (instance == null) {
            instance = new AcervoControler(value);
        }
        return instance;
    }

    public static boolean cadastrarAcervo(AcervoBase item){
        try {
            int id = AcervoBase.getNextId();
            item.setId(id);
            item.inserirNoBanco(item);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean removerAcervo(int id){
        return AcervoBase.removerAcervo(id);
    }

    public static boolean editarAcervo(AcervoBase item){
        boolean status = AcervoBase.getItem(item.getId());

        if (!status) return false;

        item.atualizarNoBanco(item, item.getId());
        return true;
    }

    public boolean imprimirFichaCatalografica(int id){
        boolean status = AcervoBase.getItem(id);
        if (!status) return false;

        var obj = Utils.obterDoBanco(id);

        switch (obj) {
            case Livro livro -> livro.imprimirFicha();
            case Mapa mapa -> mapa.imprimirFicha();
            case Periodico periodico -> periodico.imprimirFicha();
            case Tcc tcc -> tcc.imprimirFicha();
            case Relatorio relatorio -> relatorio.imprimirFicha();
            case Cartaz cartaz -> cartaz.imprimirFicha();
            case Midia midia -> midia.imprimirFicha();
            case Documento documento -> documento.imprimirFicha();
            case AcervoBase acervoBase -> acervoBase.imprimirFicha();
            default -> throw new IllegalStateException("Unexpected value: " + obj);
        }
        return true;
    }

    public static Vector<String> listarAcervo(String tipo) {
        List<AcervoBase> listAcervos = Utils.obterTodosDoBanco();

        Vector<String> listaAc = new Vector<>();

        for (AcervoBase acervo : listAcervos) {
            String temp = acervo.getId() + "  -  " + acervo.getTipoAcervo() + "  -  " + acervo.getTitulo();
            listaAc.add(temp);
        }

        return listaAc;
    }
}
