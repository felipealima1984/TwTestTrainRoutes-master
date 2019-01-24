package main.java.com.thoughtworks.trens;

public class Rota {

    private Cidade<String> cidade;
    private int distancia;
    
    public Rota(Cidade<String> cidade, int distancia) {
        this.cidade = cidade;
        this.distancia = distancia;
    }

    public Cidade<String> getCidade() {
        return cidade;
    }

    public void setCidade(Cidade<String> cidade) {
        this.cidade = cidade;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
    
    
}
