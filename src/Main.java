import Controllers.AcervoControler;

import static Others.Menu.*;

public class Main {
    public static void main(String[] args) {
        AcervoControler controllerAc = AcervoControler.getInstance("prod");

        int opcao, subOpcao;
        do {
            opcao = menuPrincipal();
            switch (opcao) {
                case GERENCIAR_ACERVO:
                    do {
                        subOpcao = menuCrud();
                        switch (subOpcao) {
                            case LISTAR_ITEM:
                                controllerAc.listarAcervo();
                                break;
                            case CADASTRAR_ITEM:
                                controllerAc.cadastrarAcervo();
                                break;
                            case REMOVER_ITEM:
                                //controllerAc.removerAcervo();
                                break;
                            case EDITAR_ITEM:
                                //controllerAc.editarAcervo();
                                break;
                            case VOLTAR_PARA_O_MENU_PRINCIPAL:
                                break;
                        }
                    } while (subOpcao != 0);
                case EMPRESTAR_ITEM:
                    break;
                case IMPRIMIR_FICHA_CARTOGRAFICA:
                    break;
                case PESQUISAR_ITEM_NO_ACERVO:
                    break;
                case 0:
                    break;

            }
        } while (opcao != 0);

    }
}