package Controllers;
import Model.*;
import Views.TelaCadastro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;


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

    public static boolean removerAcervo(int id){
        return AcervoBase.removerAcervo(id);
    }

    public static boolean editarAcervo(AcervoBase item){
        boolean status = AcervoBase.getItem(item.getId());

        if (!status) return false;

        item.atualizarNoBanco(item, item.getId());
        return true;
    }

    public static Vector<String> listarAcervo(String tipo) {
        List<AcervoBase> listAcervos = Utils.obterTodosDoBanco();

        Vector<String> listaAc = new Vector<>();

        if (tipo == null){
            for (AcervoBase acervo : listAcervos) {
                String temp = "[" + acervo.getId() + "]" + "  -  " + acervo.getTipoAcervo() + "  -  " + acervo.getTitulo();
                listaAc.add(temp);
            }
            return listaAc;
        }

        switch (tipo) {
            case "Livro" -> {
                for (AcervoBase acervo : listAcervos) {
                    if (acervo.getTipoAcervo().equals("Livro")) {
                        String temp = "[" + acervo.getId() + "]" + "  -  " + acervo.getTipoAcervo() + "  -  " + acervo.getTitulo();
                        listaAc.add(temp);
                    }
                }
            }
            case "Cartaz" -> {
                for (AcervoBase acervo : listAcervos) {
                    if (acervo.getTipoAcervo().equals("Cartaz")) {
                        String temp = "[" + acervo.getId() + "]" + "  -  " + acervo.getTipoAcervo() + "  -  " + acervo.getTitulo();
                        listaAc.add(temp);
                    }
                }
            }
            case "Documento" -> {
                for (AcervoBase acervo : listAcervos) {
                    if (acervo.getTipoAcervo().equals("Documento")) {
                        String temp = "[" + acervo.getId() + "]" + "  -  " + acervo.getTipoAcervo() + "  -  " + acervo.getTitulo();
                        listaAc.add(temp);
                    }
                }
            }
            case "Midia" -> {
                for (AcervoBase acervo : listAcervos) {
                    if (acervo.getTipoAcervo().equals("Midia")) {
                        String temp = "[" + acervo.getId() + "]" + "  -  " + acervo.getTipoAcervo() + "  -  " + acervo.getTitulo();
                        listaAc.add(temp);
                    }
                }
            }
            case "Tcc" -> {
                for (AcervoBase acervo : listAcervos) {
                    if (acervo.getTipoAcervo().equals("Tcc")) {
                        String temp = "[" + acervo.getId() + "]" + "  -  " + acervo.getTipoAcervo() + "  -  " + acervo.getTitulo();
                        listaAc.add(temp);
                    }
                }
            }
            case "Mapa" -> {
                for (AcervoBase acervo : listAcervos) {
                    if (acervo.getTipoAcervo().equals("Mapa")) {
                        String temp = "[" + acervo.getId() + "]" + "  -  " + acervo.getTipoAcervo() + "  -  " + acervo.getTitulo();
                        listaAc.add(temp);
                    }
                }
            }
            case "Relatório" -> {
                for (AcervoBase acervo : listAcervos) {
                    if (acervo.getTipoAcervo().equals("Relatório")) {
                        String temp = "[" + acervo.getId() + "]" + "  -  " + acervo.getTipoAcervo() + "  -  " + acervo.getTitulo();
                        listaAc.add(temp);
                    }
                }
            }
            case "Todos" -> {
                for (AcervoBase acervo : listAcervos) {
                    String temp = "[" + acervo.getId() + "]" + "  -  " + acervo.getTipoAcervo() + "  -  " + acervo.getTitulo();
                    listaAc.add(temp);
                }
            }

        }

        return listaAc;
    }

    public static int emprestar(int id){
        return AcervoBase.emprestarItem(id);
    }

    public static AcervoBase buscarAcervo(int id) {
        return AcervoBase.obterDoBanco(id);
    }
}
