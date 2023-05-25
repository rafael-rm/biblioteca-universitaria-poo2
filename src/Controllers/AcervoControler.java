package Controllers;
import Entities.AcervoBase;
import Entities.Cartaz;
import Entities.Livro;
import Entities.Mapa;
import Entities.Midia;
import Entities.Periodico;
import Entities.Relatorio;
import Entities.Tcc;
import Others.Menu;
import java.util.ArrayList;

public class AcervoControler {
    private static AcervoControler instance;
    private ArrayList<AcervoBase> listAcervos;
    private int idCounter;
    public String value;

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

    public int getIdCounter() {
        this.idCounter++;
        return this.idCounter;
    }

    public void cadastrarAcervo(){

        AcervoControler controller = AcervoControler.getInstance("prod");
        int opcao = Menu.menuCadastrar();

        if (opcao == 0) return;

        new AcervoBase();
        AcervoBase item = switch (opcao) {
            case 1 -> new Livro();
            case 2 -> new Mapa();
            case 3 -> new Periodico();
            case 4 -> new Tcc();
            case 5 -> new Relatorio();
            case 6 -> new Cartaz();
            case 7 -> new Midia();
            default -> throw new IllegalStateException("Unexpected value: " + opcao);
        };
        item.cadastrar();
        controller.listAcervos.add(item);

    }
}
