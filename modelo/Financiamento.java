/* Aluno: Alex Augusto Soares
 * Curso: Análise e Desenvolvimento de Sistemas
 * Faculdade: PUCPPR
 */

package modelo;
import java.text.NumberFormat;
import java.io.Serializable;

public abstract class Financiamento implements Serializable {

    // Atributos
    protected double valorImovel;
    protected int prazoFinanciamento;
    protected double taxaJurosAnual;

    //Incluindo getter para cada um dos atributos que estão privados
    public double getValorImovel() {
        return this.valorImovel;
    }
    public int getPrazoFinanciamento() {
        return this.prazoFinanciamento;
    }
    public double getTaxaJurosAnual() {
        return this.taxaJurosAnual;
    }

    // Construtor
    public Financiamento (double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual) {
        this.valorImovel = valorDesejadoImovel;
        this.prazoFinanciamento = prazoFinanciamentoAnos;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    // Métodos
    public abstract double calcularPagamentoMensal();

    public double calcularPagamentoTotal() {
        return this.calcularPagamentoMensal() * this.prazoFinanciamento * 12; // Cálculo do valor total do financimento
    }

    // Apresenta alguns dados do financiamento, como o valor total do imóvel, valor total do financiamento, taxa de juros anual e valor total do financiamento
    public void dadosFinanciamento() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        System.out.println("Valor total do imóvel: " + nf.format(getValorImovel()));
        System.out.println("Prazo do financiamento: " + getPrazoFinanciamento() + " anos");
        System.out.println("Taxa de juros anual: " + getTaxaJurosAnual() + "%");
        System.out.println("Pagamento mensal: " + nf.format(calcularPagamentoMensal()));
        System.out.println("Valor total do financiamento: " + nf.format(calcularPagamentoTotal()));;
    }
}


