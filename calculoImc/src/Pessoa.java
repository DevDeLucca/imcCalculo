package calculoImc.src;

public class Pessoa {

    private String nome;
    private String sobrenome;
    private double peso;
    private double altura;


    public Pessoa(String nome, String sobrenome, double peso, double altura) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.peso = peso;
        this.altura = altura;
    }

    public double calcularIMC() {
        return peso / (altura * altura);
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public double getPeso() {
        return peso;
    }
}
