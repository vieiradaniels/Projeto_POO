package execucao;

public class Caixa {

    private int largura;
    private int altura;
    private int profundidade;
    private String codRastreio;
    private Produto item;
    private String destinatario;

    public Caixa(int largura, int altura, int profundidade, String codRastreio, Produto item, String destinatario) {
        this.largura = largura;
        this.altura = altura;
        this.profundidade = profundidade;
        this.codRastreio = codRastreio;
        this.item = item;
        this.destinatario = destinatario;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(int profundidade) {
        this.profundidade = profundidade;
    }

    public String getCodRastreio() {
        return codRastreio;
    }

    public void setCodRastreio(String codRastreio) {
        this.codRastreio = codRastreio;
    }

    public Produto getItem() {
        return item;
    }

    public void setItem(Produto item) {
        this.item = item;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
}
