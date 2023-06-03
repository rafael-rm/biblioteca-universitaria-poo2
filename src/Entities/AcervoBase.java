package Entities;

import Controllers.AcervoControler;

import java.util.ArrayList;
import java.util.Scanner;

public class AcervoBase {
    protected int id;
    protected String titulo;
    protected ArrayList<Autor> autores;
    protected int edicao;
    protected String cidade;
    protected String editora;
    protected int ano;
    protected String cdu;
    protected String assunto;
    protected ArrayList<String> palavras_chave;
    protected int qtd_exemplares;
    protected int emprestados;

    public AcervoBase() {
        id = 0;
        titulo = "Default";
        autores = new ArrayList<Autor>();
        edicao = 0;
        cidade = "Default";
        editora = "Default";
        ano = 0;
        cdu = "Default";
        assunto = "Default";
        palavras_chave = new ArrayList<String>();
        qtd_exemplares = 0;
        emprestados = 0;
    }

    public void cadastrar(){
        Scanner scan = new Scanner(System.in);
        AcervoControler controller = AcervoControler.getInstance("prod");
        int qtdAutores, qtdPalavrasChave;

        System.out.println("Digite o título: ");
        titulo = scan.nextLine();
        System.out.println("Digite a quantidade de autores: ");
        qtdAutores = scan.nextInt();
        for (int i = 0; i < qtdAutores; i++) {
            Autor autor = new Autor();
            autor.cadastrar();
            autores.add(autor);
        }
        System.out.println("Digite a edição: ");
        scan = new Scanner(System.in);
        edicao = scan.nextInt();
        System.out.println("Digite a cidade: ");
        scan = new Scanner(System.in);
        cidade = scan.nextLine();
        System.out.println("Digite a editora: ");
        editora = scan.nextLine();
        System.out.println("Digite o ano: ");
        scan = new Scanner(System.in);
        ano = scan.nextInt();
        System.out.println("Digite o CDU: ");
        scan = new Scanner(System.in);
        cdu = scan.nextLine();
        System.out.println("Digite o assunto: ");
        assunto = scan.nextLine();
        System.out.println("Digite a quantidade de palavras chave: ");
        scan = new Scanner(System.in);
        qtdPalavrasChave = scan.nextInt();
        for (int i = 0; i < qtdPalavrasChave; i++) {
            String palavraChave;
            System.out.println("Digite a palavra chave: ");
            scan = new Scanner(System.in);
            palavraChave = scan.nextLine();
            palavras_chave.add(palavraChave);
        }
        System.out.println("Digite a quantidade de exemplares: ");
        scan = new Scanner(System.in);
        qtd_exemplares = scan.nextInt();

        if (id == 0)
            id = controller.getIdCounter();
    }

    public int getId() {
        return id;
    }

    public void imprimir(){
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Autores: ");
        for (int i = 0; i < autores.size(); i++) {
            System.out.println("Nome: " + autores.get(i).getNome());
            System.out.println("Data de nascimento: " + autores.get(i).getDataNascimento());
        }
        System.out.println("Edição: " + edicao);
        System.out.println("Cidade: " + cidade);
        System.out.println("Editora: " + editora);
        System.out.println("Ano: " + ano);
        System.out.println("CDU: " + cdu);
        System.out.println("Assunto: " + assunto);
        System.out.println("Palavras chave:");
        for (int i = 0; i < palavras_chave.size(); i++) {
            System.out.print(" " + palavras_chave.get(i));
        }
        System.out.println("\nQuantidade de exemplares: " + qtd_exemplares);
        System.out.println("Quantidade de exemplares emprestados: " + emprestados);
    }

    public void imprimirFicha(){
        System.out.println("-----------------------------------------------------------------------------------------");
        for (int i = 0; i < autores.size(); i++) {
            System.out.print(autores.get(i).getNome() + " " + i+1+ ". ");
        }
        System.out.printf("\n%s - %d Edição - %s : %s, %d", getTitulo(), getEdicao(), getCidade(), getEditora(), getAno());
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Autor> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<Autor> autores) {
        this.autores = autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCdu() {
        return cdu;
    }

    public void setCdu(String cdu) {
        this.cdu = cdu;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public ArrayList<String> getPalavras_chave() {
        return palavras_chave;
    }

    public void setPalavras_chave(ArrayList<String> palavras_chave) {
        this.palavras_chave = palavras_chave;
    }

    public int getQtd_exemplares() {
        return qtd_exemplares;
    }

    public void setQtd_exemplares(int qtd_exemplares) {
        this.qtd_exemplares = qtd_exemplares;
    }

    public int getEmprestados() {
        return emprestados;
    }

    public void setEmprestados(int emprestados) {
        this.emprestados = emprestados;
    }
}
