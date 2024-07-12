package modelo;

public class Apartamento extends Financiamento {
    private int numeroVagasGaragem;
    private int numeroAndar;

    // Construtor que inicializa os atributos do financiamento
    public Apartamento(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, int numeroVagasGaragem, int numeroAndar) {
        super(valorDesejadoImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.numeroVagasGaragem = numeroVagasGaragem;
        this.numeroAndar = numeroAndar;
    }

    @Override
    public double calcularPagamentoMensal() {
        // Calcula a taxa mensal
        double taxaMensal = this.taxaJurosAnual / 12 / 100;
        // Calcula o valor em meses do financiamento
        int mesesFinanciamento = this.prazoFinanciamento * 12;
        // Retorna o valor do pagamento mensal
        return (this.valorImovel * taxaMensal * Math.pow(1 + taxaMensal, mesesFinanciamento)) / (Math.pow(1 + taxaMensal, mesesFinanciamento) - 1);
    }

    // Descrição salva sobre o financiamento do Apartamento
    @Override
    public String toString() {
        return String.format("""
                        ===== Financiamento Apartamento =====
                        
                        Valor total do imóvel: %.2f
                        Prazo do financiamento: %d anos
                        Taxa de juros anual: %.2f%%
                        Número de vagas na garagem: %d
                        Número do andar: %d
                        Pagamento mensal: %.2f
                        Valor total do financiamento: %.2f
                        """, valorImovel, prazoFinanciamento, taxaJurosAnual, numeroVagasGaragem, numeroAndar, calcularPagamentoMensal(), calcularPagamentoTotal());
    }
}
