package main.java.com.thoughtworks.trens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class RotasDosTrens {
    
    private Map<Cidade<String>, List<Rota>> mapaDostrens;
    
    public RotasDosTrens(String entrada) {
        mapaDostrens = new HashMap<>();
        construirRotaDosTrens(entrada);
    }
    
    public void construirRotaDosTrens(String entrada) {
        Cidade<String> cidade = null;
        Cidade<String> rotaDaCidade = null;
        Rota rota = null;
        List<String> listaDeEstradas = Arrays.asList(entrada.split("[\\s]*,[\\s]*"));
        for (String string : listaDeEstradas) {
            cidade = new Cidade<>(string.substring(0, 1));
            rotaDaCidade = new Cidade<>(string.substring(1, 2));
            rota = new Rota(rotaDaCidade, Integer.parseInt(string.substring(2)));
            mapaDostrens.putIfAbsent(cidade, new ArrayList<>());
            mapaDostrens.get(cidade).add(rota);
        }
    }

    public String distanciaDaRota(List<Cidade<String>> listaDeCidades) {

        int distancia = 0;
        int indiceAtual = 0;
        List<Rota> rotas = null;
        Rota rota = null;
        
        for (Cidade<String> cidade : listaDeCidades) {
            indiceAtual = listaDeCidades.indexOf(cidade);
            if (indiceAtual <  listaDeCidades.size() -1) {
                final Cidade<String> proximaCidade = listaDeCidades.get(indiceAtual +1);
                rotas = mapaDostrens.get(cidade);
                rota = rotas.stream()
                    .filter(r -> r.getCidade().equals(proximaCidade))
                    .findFirst()
                    .orElse(null);
                if (null == rota) {
                    return Constantes.NAO_ENCONTRADA;
                } else {
                    distancia += rota.getDistancia();
                }
                
            }
        }               
        
        return String.valueOf(distancia);
    }

    public String numeroMaximoParadasViagens(Cidade<String> start, Cidade<String> end, int maxStops) {        
        AtomicInteger counter = new AtomicInteger();
        Deque<Rota> queue = new LinkedList<>();
        
        contadorMaximoParadasViagem(start, end, maxStops, queue, counter);
        
        return counter.toString();
    }
    
    private void contadorMaximoParadasViagem(Cidade<String> inicio, Cidade<String> fim, int maximoParadas, 
            Deque<Rota> fila, AtomicInteger contador) {
        
        if (fila.size() < maximoParadas) {
            Cidade<String> cidadeAtual = null;
            List<Rota> rotas = mapaDostrens.get(inicio);
            for (Rota rota : rotas) {                
                cidadeAtual = rota.getCidade();
                if (cidadeAtual.equals(fim)) {
                    contador.incrementAndGet();
                }
                fila.addLast(rota);
                contadorMaximoParadasViagem(cidadeAtual, fim, maximoParadas, fila, contador);
                fila.removeLast();
            } 
        }
    }
    
    public String quantidadeParadasViagem(Cidade<String> inicio, Cidade<String> fim, int paradasExatas) {
        AtomicInteger contador = new AtomicInteger();
        Deque<Rota> fila = new LinkedList<>();
        
        contarViagensQueParam(inicio, fim, paradasExatas, fila, contador);
        
        return contador.toString();
    }
    

    private void contarViagensQueParam(Cidade<String> inicio, Cidade<String> fim, int paradasExatas, 
            Deque<Rota> fila, AtomicInteger contador) {
       
        if (fila.size() < paradasExatas) {
            Cidade<String> cidateAtual = null;
            List<Rota> rotas = mapaDostrens.get(inicio);
            for (Rota rota : rotas) {

                cidateAtual = rota.getCidade();
                if (cidateAtual.equals(fim) && fila.size() == paradasExatas -1) {
                    contador.incrementAndGet();
                }
                fila.addLast(rota);
                contarViagensQueParam(cidateAtual, fim, paradasExatas, fila, contador);
                fila.removeLast();

            } 
        }      
    }
    
    public String rotaMaisCurta(Cidade<String> inicio, Cidade<String> fim) {
        ThreadLocal<Integer> distanciaMinima = new ThreadLocal<>();
        distanciaMinima.set(999999);
        int somaDistancia = 0;
        Deque<Cidade<String>> fila = new LinkedList<>();
        
        calcularRotaMaisCurta(inicio, fim, fila, distanciaMinima, somaDistancia);
        
        return distanciaMinima.get().toString();
    }
    
    
    private void calcularRotaMaisCurta(Cidade<String> inicio, Cidade<String> fim, Deque<Cidade<String>> fila, ThreadLocal<Integer> distanciaMinima, int somaDistancia) {

            Cidade<String> cidadeAtual = null;
            List<Rota> rotas = mapaDostrens.get(inicio);
            for (Rota rota : rotas) {

                cidadeAtual = rota.getCidade();

                if (cidadeAtual.equals(fim)) {
                    if (somaDistancia + rota.getDistancia() < distanciaMinima.get()) {
                        distanciaMinima.set(somaDistancia + rota.getDistancia());
                        continue;
                    }
                }
                
                if (!fila.contains(cidadeAtual)) {
                    fila.addLast(cidadeAtual);
                    somaDistancia += rota.getDistancia();
                    calcularRotaMaisCurta(cidadeAtual, fim, fila, distanciaMinima, somaDistancia);
                    somaDistancia -= rota.getDistancia();
                    fila.removeLast();
                }
            }
    }
    
    public String qtdRotasDiferentes(Cidade<String> inicio, Cidade<String> fim, int distanciaMaxima) {
        int distanciaSomada = 0;
        AtomicInteger contador = new AtomicInteger();
        
        calcularRotasDiferentes(inicio, fim, distanciaMaxima, contador, distanciaSomada);
        
        return contador.toString();
    }


    private void calcularRotasDiferentes(Cidade<String> inicio, Cidade<String> fim, int distanciaMaxima, 
            AtomicInteger contador, int distanciaSomada) {
        
        Cidade<String> cidadeAtual = null;
        List<Rota> rotas = mapaDostrens.get(inicio);
        for (Rota rota : rotas) {

            cidadeAtual = rota.getCidade();
            if (distanciaSomada + rota.getDistancia() >= distanciaMaxima) {
                continue;
            }
            
            if (cidadeAtual.equals(fim)) {
                contador.getAndIncrement();
            }
            
            distanciaSomada += rota.getDistancia();
            calcularRotasDiferentes(cidadeAtual, fim, distanciaMaxima, contador, distanciaSomada);
            distanciaSomada -= rota.getDistancia();
        }
        
    }


    public Map<Cidade<String>, List<Rota>> getMapaDosTrens() {
        return mapaDostrens;
    }


    public void setMapaDosTrens(Map<Cidade<String>, List<Rota>> trainsMap) {
        this.mapaDostrens = trainsMap;
    }

}
