import Controllers.AcervoControler;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Teste");
        AcervoControler controller = AcervoControler.getInstance("prod");
        controller.cadastrarAcervo();
        System.out.println(controller);

    }
}