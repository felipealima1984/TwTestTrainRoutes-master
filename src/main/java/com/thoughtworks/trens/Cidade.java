package main.java.com.thoughtworks.trens;

public class Cidade<E> {
    
    private String cidade;
    
    public Cidade(String cidade) {
        this.cidade = cidade;
    }

    
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object obj) {     
        return this.getCidade().equals(((Cidade<?>)obj).getCidade());
    }
    
    @Override
    public int hashCode() {
        return this.getCidade().hashCode();
    }
}
