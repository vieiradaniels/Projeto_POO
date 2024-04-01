package execucao;

public class Caminhao {

    private Caixa[] carga;
    private double volumeMaximo;

    public Caminhao(double volumeMaximo) {
        carga = new Caixa[100];
        this.volumeMaximo = volumeMaximo;
    }

    public Caixa[] getCarga() {
        return carga;
    }

    public void setCarga(Caixa[] carga) {
        this.carga = carga;
    }

    public double getVolumeMaximo() {
        return volumeMaximo;
    }

    public void setVolumeMaximo(double volumeMaximo) {
        this.volumeMaximo = volumeMaximo;
    }

    public boolean buscaCaixa(String codRastreio) {
        for (int i = 0; i < carga.length; i++) {
            if (carga[i] != null && carga[i].getCodRastreio().equals(codRastreio)) {
                return true;
            }
        }
        return false;
    }

    public double somarValores() {
        double montante = 0;
        for (int i = 0; i < carga.length; i++) {
            if (carga[i] != null && carga[i].getItem() != null) {
                montante += carga[i].getItem().getPreco();
            }
        }
        return montante;
    }
}