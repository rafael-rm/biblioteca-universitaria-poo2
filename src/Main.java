import Controllers.AcervoControler;
import Entities.AcervoBase;
import Entities.Documento;
import Entities.Utils;
import Infrastructure.DatabaseMysql;
import java.sql.Connection;
import java.util.Scanner;
import static Others.Menu.*;

public class Main {
    public static void main(String[] args) {

        AcervoControler controllerAc = AcervoControler.getInstance("prod");
        Scanner sc = new Scanner(System.in);

        int opcao, subOpcao, id, status2;
        boolean status;
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
                                System.out.println("Digite o ID do item que deseja remover: ");
                                sc = new Scanner(System.in);
                                id = sc.nextInt();
                                status = controllerAc.removerAcervo(id);
                                if (status)
                                    System.out.println("Item removido com sucesso!");
                                else
                                    System.out.println("Item não encontrado!");
                                break;
                            case EDITAR_ITEM:
                                System.out.println("Digite o ID do item que deseja editar: ");
                                sc = new Scanner(System.in);
                                id = sc.nextInt();
                                status = controllerAc.editarAcervo(id);
                                if (status)
                                    System.out.println("Item editado com sucesso!");
                                else
                                    System.out.println("Item não encontrado!");
                                break;
                            case VOLTAR_PARA_O_MENU_PRINCIPAL:
                                break;
                        }
                    } while (subOpcao != 0);
                    break;
                case EMPRESTAR_ITEM:
                    System.out.println("Digite o ID do item que deseja emprestar: ");
                    sc = new Scanner(System.in);
                    id = sc.nextInt();
                    status2 = controllerAc.emprestarItem(id);
                    if (status2 == 1)
                        System.out.println("Item emprestado com sucesso!");
                    else if (status2 == 2)
                        System.out.println("O item não tem exemplares disponíveis!");
                    else
                        System.out.println("Item não encontrado!");
                    break;
                case IMPRIMIR_FICHA_CATALOGRAFICA:
                    status = controllerAc.imprimirFichaCatalografica();
                    if (!status)
                        System.out.println("Item nao encontrado");
                    break;
                case PESQUISAR_ITEM_NO_ACERVO:
                    System.out.println("Digite o ID do item que deseja pesquisar: ");
                    sc = new Scanner(System.in);
                    id = sc.nextInt();
                    status = controllerAc.getItem(id);
                    if (status)
                        controllerAc.pesquisarItem(id);
                    else
                        System.out.println("Item não encontrado!");
                    System.out.println("Aperte ENTER para continuar...");
                    sc = new Scanner(System.in);
                    sc.nextLine();
                    break;
                case 0:
                    break;

            }
        } while (opcao != 0);

    }
}