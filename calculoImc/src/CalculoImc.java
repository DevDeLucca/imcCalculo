package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculoImc {
    public static void main(String[] args) {
        String arquivoCSV = "database.csv";
        List<calculoImc.src.Pessoa> pessoas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 4) {
                    String nome = dados[0].trim();
                    String sobrenome = dados[1].trim();
                    double peso = Double.parseDouble(dados[2].trim());
                    double altura = Double.parseDouble(dados[3].trim());
                    calculoImc.src.Pessoa pessoa = new calculoImc.src.Pessoa(nome, sobrenome, peso, altura);
                    pessoas.add(pessoa);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 1 -> Pessoa com maior peso
        double maiorPeso = 0;
        List<calculoImc.src.Pessoa> pessoasComMaiorPeso = new ArrayList<>();
        for (calculoImc.src.Pessoa pessoa : pessoas) {
            if (pessoa.getPeso() > maiorPeso) {
                maiorPeso = pessoa.getPeso();
                pessoasComMaiorPeso.clear();
                pessoasComMaiorPeso.add(pessoa);
            } else if (pessoa.getPeso() == maiorPeso) {
                pessoasComMaiorPeso.add(pessoa);
            }
        }

        // 2 -> Calcular IMC e exibir quantas pessoas existem em cada faixa
        int[] faixasIMC = new int[5];
        for (calculoImc.src.Pessoa pessoa : pessoas) {
            double imc = pessoa.calcularIMC();
            if (imc < 18.5) {
                faixasIMC[0]++;
            } else if (imc < 25.0) {
                faixasIMC[1]++;
            } else if (imc < 30.0) {
                faixasIMC[2]++;
            } else if (imc < 40.0) {
                faixasIMC[3]++;
            } else {
                faixasIMC[4]++;
            }
        }

        // 4 -> Exibir nome e sobrenome de todas as pessoas obesas
        List<String> pessoasObesas = new ArrayList<>();
        for (calculoImc.src.Pessoa pessoa : pessoas) {
            double imc = pessoa.calcularIMC();
            if (imc >= 30.0) {
                pessoasObesas.add(pessoa.getNome() + " " + pessoa.getSobrenome());
            }
        }

        // 5 -> Função mais encontrada + quantidade
        Map<String, Integer> funcaoMaisEncontrada = new HashMap<>();
        for (calculoImc.src.Pessoa pessoa : pessoas) {
            String nomeCompleto = pessoa.getNome() + " " + pessoa.getSobrenome();
            funcaoMaisEncontrada.put(nomeCompleto, funcaoMaisEncontrada.getOrDefault(nomeCompleto, 0) + 1);
        }
        int maxQuantidade = 0;
        List<String> funcoesMaisEncontradas = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : funcaoMaisEncontrada.entrySet()) {
            int quantidade = entry.getValue();
            if (quantidade > maxQuantidade) {
                maxQuantidade = quantidade;
                funcoesMaisEncontradas.clear();
                funcoesMaisEncontradas.add(entry.getKey());
            } else if (quantidade == maxQuantidade) {
                funcoesMaisEncontradas.add(entry.getKey());
            }
        }

        // Exibir resultados
        System.out.println("Pessoa(s) com maior peso:");
        for (calculoImc.src.Pessoa pessoa : pessoasComMaiorPeso) {
            System.out.println(pessoa.getNome() + " " + pessoa.getSobrenome() + " - Peso: " + pessoa.getPeso());
        }

        System.out.println("\nQuantidade de pessoas em cada faixa de IMC:");
        System.out.println("MENOR QUE 18.5 (MAGREZA): " + faixasIMC[0]);
        System.out.println("ENTRE 18.5 E 24.9 (NORMAL): " + faixasIMC[1]);
        System.out.println("ENTRE 25.0 E 29.9 (SOBREPESO): " + faixasIMC[2]);
        System.out.println("ENTRE 30.0 E 39.9 (OBESIDADE): " + faixasIMC[3]);
        System.out.println("MAIOR QUE 40.0 (OBESIDADE GRAVE): " + faixasIMC[4]);

        System.out.println("\nPessoa(s) obesa(s):");
        for (String nomeSobrenome : pessoasObesas) {
            System.out.println(nomeSobrenome);
        }

        System.out.println("\nFunção(s) mais encontrada(s) + quantidade:");
        for (String funcao : funcoesMaisEncontradas) {
            System.out.println(funcao + " - Quantidade: " + maxQuantidade);
        }
    }
}
