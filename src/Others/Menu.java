package Others;
import java.util.Scanner;

public class Menu {

    public static int menuPrincipal(){
        Scanner scan = new Scanner(System.in);
        int opcao = 0;
        do {
            System.out.println("[INFO] Menu principal");
            System.out.println("1 - Gerenciar acervo");
            System.out.println("2 - Emprestar item");
            System.out.println("3 - Imprimir ficha cartografica");
            System.out.println("4 - Pesquisar item no acervo");
            System.out.println("0 - Encerrar programa");
            System.out.println("Opcao: ");
            opcao = scan.nextInt();
        } while (!(opcao >= 0 && opcao <= 4));

        return opcao;

    }

    public static int menuCrud(){
        Scanner scan = new Scanner(System.in);
        int opcao = 0;
        do {
            System.out.println("[INFO] Menu CRUD");
            System.out.println("1 - Listar item");
            System.out.println("2 - Cadastrar item");
            System.out.println("3 - Remover item");
            System.out.println("4 - Editar item");
            System.out.println("0 - Voltar para o menu principal");
            System.out.println("Opcao: ");
            opcao = scan.nextInt();
        } while (!(opcao >= 0 && opcao <= 4));

        return opcao;
    }

    public static int menuListar() {
        Scanner scan = new Scanner(System.in);
        int opcao = 0;
        do {
            System.out.println("[INFO] Informe o item que deseja Listar");
            System.out.println("1 - Listar todos os itens");
            System.out.println("2 - Listar Livro");
            System.out.println("3 - Listar Mapa");
            System.out.println("4 - Listar Periodico");
            System.out.println("5 - Listar TCC");
            System.out.println("6 - Listar Relatório");
            System.out.println("7 - Listar Cartaz");
            System.out.println("8 - Listar Midia");
            System.out.println("0 - Voltar");
            System.out.println("Opcao: ");
            opcao = scan.nextInt();
        } while (!(opcao >= 0 && opcao <= 8));

        return opcao;
    }

    public static int menuCadastrar() {
        Scanner scan = new Scanner(System.in);

        int opcao = 0;
        do {

            System.out.println("[INFO] Informe o item a ser cadastrado");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Cadastrar Mapa");
            System.out.println("3 - Cadastrar Periodico");
            System.out.println("4 - Cadastrar TCC");
            System.out.println("5 - Cadastrar Relatório");
            System.out.println("6 - Cadastrar Cartaz");
            System.out.println("7 - Cadastrar Midia");
            System.out.println("0 - Voltar");
            System.out.println("Opcao: ");
            opcao = scan.nextInt();

        } while (!(opcao >= 0 && opcao <= 7));

        return opcao;
    }
}
