package main.java.com.thoughtworks.trens;

import java.util.ArrayList;
import java.util.List;

/**
 * Teste
 * TW Teste: Trains
 */
public class App 
{
    public static void main( String[] args )
    {

        RotasDosTrens rotasDosTrens = new RotasDosTrens(Constantes.EXEMPLO);
     
        
        List<Cidade<String>> cidades = new ArrayList<>();
        cidades.add(new Cidade<>("A"));
        cidades.add(new Cidade<>("B"));
        cidades.add(new Cidade<>("C"));                
        System.out.println("Saída #1: " + rotasDosTrens.distanciaDaRota(cidades));
        
        cidades.clear();
        cidades.add(new Cidade<>("A"));
        cidades.add(new Cidade<>("D"));
        System.out.println("Saída #2: " + rotasDosTrens.distanciaDaRota(cidades));

        cidades.add(new Cidade<>("C"));                
        System.out.println("Saída #3: " + rotasDosTrens.distanciaDaRota(cidades));
        
        cidades.clear();
        cidades.add(new Cidade<>("A"));
        cidades.add(new Cidade<>("E"));
        cidades.add(new Cidade<>("B"));
        cidades.add(new Cidade<>("C"));
        cidades.add(new Cidade<>("D"));
        System.out.println("Saída #4: " + rotasDosTrens.distanciaDaRota(cidades));
        
        cidades.clear();
        cidades.add(new Cidade<>("A"));
        cidades.add(new Cidade<>("E"));
        cidades.add(new Cidade<>("D"));
        System.out.println("Saída #5: " + rotasDosTrens.distanciaDaRota(cidades));

        System.out.println("Saída #6: " + rotasDosTrens.numeroMaximoParadasViagens(new Cidade<>("C"), new Cidade<>("C"), 3));

        System.out.println("Saída #7: " + rotasDosTrens.quantidadeParadasViagem(new Cidade<>("A"), new Cidade<>("C"), 4));

        System.out.println("Saída #8: " + rotasDosTrens.rotaMaisCurta(new Cidade<>("A"), new Cidade<>("C")));
        System.out.println("Saída #9: " + rotasDosTrens.rotaMaisCurta(new Cidade<>("B"), new Cidade<>("B")));
        
        System.out.println("Saída #10: " + rotasDosTrens.qtdRotasDiferentes(new Cidade<>("C"), new Cidade<>("C"), 30));

    }
}
