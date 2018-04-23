package mack.chavevalor.dao;


import static java.lang.System.out;
import java.util.List;
import java.util.Scanner;

public class ChaveValorConsole implements ChaveValorUx {


    private Scanner sc;

    public ChaveValorConsole() {
        sc = new Scanner(System.in);
    }

    public OPCOES menuPrincipal() {
        out.println("\nMenu:");
        out.println("(1) Criar registro");
        out.println("(2) Ler registros");
        out.println("(3) Atualizar registro");
        out.println("(4) Apagar registro");
        out.println("(5) Sair");
        out.print("Opção escolhida: ");
        int opcao = Integer.parseInt(sc.nextLine());
        OPCOES op;
        switch(opcao) {
            case 1: op = OPCOES.CRIAR; break;
            case 2: op = OPCOES.LER; break;
            case 3: op = OPCOES.ATUALIZAR; break;
            case 4: op = OPCOES.APAGAR; break;
            case 5: op = OPCOES.SAIR; break;
            default: op = OPCOES.INVALIDA;
        }
        return op;
    }

    public ChaveValor criarChaveValor() {
        long datahora = System.currentTimeMillis();
        out.print("Chave do novo registro: ");        
        String chave = sc.nextLine();
        out.print("Valor do novo registro: ");
        String valor = sc.nextLine();

        ChaveValor p = new ChaveValor(0, datahora, chave, valor);
        return p;
    }

    public void mostrarMensagem(String msg) {
        out.println(msg);
    }

    public void listar(List<ChaveValor> profs) {
        out.println("Chaves e Valores:");
        for (ChaveValor p : profs) {
            out.println(p.getId() + " - " + p.getDatahora() + " - "
                    + p.getChave() + " - " + p.getValor());
        }
    }

    public ChaveValor atualizarChaveValor() {
        out.print("id do registro a ser atualizado: ");
        long id = Long.parseLong(sc.nextLine());
        out.print("nova datahora: ");
        long datahora = Long.parseLong(sc.nextLine());
        out.print("nova chave: ");
        String chave = sc.nextLine();
        out.print("novo valor: ");
        String valor = sc.nextLine();
        ChaveValor professor;
        professor = new ChaveValor(id, datahora, chave, valor);
        return professor;
    }

    public long apagar() {
        out.print("id do registro a ser apagado: ");
        long id = Long.parseLong(sc.nextLine());
        return id;
    }
}
