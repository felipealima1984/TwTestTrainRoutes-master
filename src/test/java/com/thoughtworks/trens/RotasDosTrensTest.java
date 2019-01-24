package test.java.com.thoughtworks.trens;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.java.com.thoughtworks.trens.Constantes;
import main.java.com.thoughtworks.trens.Rota;
import main.java.com.thoughtworks.trens.Cidade;
import main.java.com.thoughtworks.trens.RotasDosTrens;


public class RotasDosTrensTest {


    RotasDosTrens rotasDosTrens = new RotasDosTrens("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");        
    
    @Test
    public void construirRotaDosTrensTest() {
        Cidade<String> cidade = new Cidade<>("A");
        List<Rota> rotas = rotasDosTrens.getMapaDosTrens().get(cidade);
        assertEquals(rotas.size(), 3);
        
        cidade = new Cidade<>("C");
        rotas = rotasDosTrens.getMapaDosTrens().get(cidade);
        assertEquals(rotas.size(), 2);        
    }
    
    @Test
    public void distanciaDaRotaTest() {
        List<Cidade<String>> cidades = new ArrayList<>();
        Cidade<String> cidade = new Cidade<>("A");
        cidades.add(cidade);
        cidade = new Cidade<>("B");
        cidades.add(cidade);
        cidade = new Cidade<>("C");
        cidades.add(cidade);        
        assertEquals(rotasDosTrens.distanciaDaRota(cidades), "9");

        cidades.clear();
        cidade = new Cidade<>("A");
        cidades.add(cidade);
        cidade = new Cidade<>("E");
        cidades.add(cidade);
        cidade = new Cidade<>("D");
        cidades.add(cidade);        
        assertEquals(rotasDosTrens.distanciaDaRota(cidades), Constantes.NAO_ENCONTRADA);
        
    }
    
    @Test
    public void numeroMaximoParadasViagensTest() {
        Cidade<String> start = new Cidade<>("C");
        Cidade<String> end = new Cidade<>("C");
        assertEquals(rotasDosTrens.numeroMaximoParadasViagens(start, end, 3), "2");
        
    }
    
    @Test
    public void quantidadeParadasViagemTest() { 
        Cidade<String> start = new Cidade<>("A");
        Cidade<String> end = new Cidade<>("C");
        assertEquals(rotasDosTrens.quantidadeParadasViagem(start, end, 4), "3");
    }
    
    @Test
    public void rotaMaisCurtaTest() {
        Cidade<String> start = new Cidade<>("A");
        Cidade<String> end = new Cidade<>("C");
        assertEquals(rotasDosTrens.rotaMaisCurta(start, end), "9");        

        start = new Cidade<>("B");
        end = new Cidade<>("B");
        assertEquals(rotasDosTrens.rotaMaisCurta(start, end), "9");  
        
    }
    
    @Test
    public void qtdRotasDiferentesTest() {
        Cidade<String> start = new Cidade<>("C");
        Cidade<String> end = new Cidade<>("C");
        assertEquals(rotasDosTrens.qtdRotasDiferentes(start, end, 30), "7");

    }
}
