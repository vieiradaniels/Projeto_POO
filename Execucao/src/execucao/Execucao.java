package execucao;

public class Execucao {

    public static void main(String[] args) {
        Produto prod = new Produto(0, "Geladeira", 2100.00);
        Produto prod1 = new Produto(1, "Fogão", 999.00);

        Caixa cx = new Caixa(2000, 1000, 650, "BRL23876", prod, "Rodrigo");
        Caixa cx1 = new Caixa(1200, 950, 550, "BRL89125", prod1, "Jairo");

        Caminhao veiculo = new Caminhao(100);
        Caixa[] lista = new Caixa[]{cx, cx1};
        veiculo.setCarga(lista);

        //System.out.println(veic.getCarga());
        System.out.println("Resultado: " + veiculo.buscaCaixa("BRL23876"));
        System.out.println("Soma do valor da carga: " + veiculo.somarValores());
    }

}