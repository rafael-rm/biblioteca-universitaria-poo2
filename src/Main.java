import Controllers.AcervoControler;
import Controllers.AuthenticationController;
import Model.AcervoBase;
import Views.TelaCadastro;
import Views.TelaLogin;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.Scanner;
import static Others.Menu.*;


public class Main {

    public static void temaTelas(){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
    }

    public static void main(String[] args) {

        AcervoControler controllerAc = AcervoControler.getInstance("prod");

        temaTelas();

        TelaLogin tl = new TelaLogin(null);
        tl.setVisible(true);

        int opcao, subOpcao, id, status2;
        Scanner sc;
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
                    status2 = AcervoBase.emprestarItem(id);
                    if (status2 == 1)
                        System.out.println("Item emprestado com sucesso!");
                    else if (status2 == 2)
                        System.out.println("O item não tem exemplares disponíveis!");
                    else
                        System.out.println("Item não encontrado!");
                    break;
                case IMPRIMIR_FICHA_CATALOGRAFICA:
                    sc = new Scanner(System.in);
                    System.out.println("Digite o ID do item que deseja imprimir a ficha catalográfica: ");
                    id = sc.nextInt();
                    status = controllerAc.imprimirFichaCatalografica(id);
                    if (!status)
                        System.out.println("Item nao encontrado");
                    break;
                case PESQUISAR_ITEM_NO_ACERVO:
                    System.out.println("Digite o ID do item que deseja pesquisar: ");
                    sc = new Scanner(System.in);
                    id = sc.nextInt();
                    status = AcervoBase.getItem(id);
                    if (status)
                        AcervoBase.pesquisarItem(id);
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