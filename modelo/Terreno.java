package modelo;

public class Terreno extends Financiamento{
    private String tipoZona;

    // Construtor que inicializa os atributos do financiamento
    public Terreno(double valorDesejadoImovel, int prazoFinanciamentoAnos, double taxaJurosAnual, String tipoZona) {
        super(valorDesejadoImovel, prazoFinanciamentoAnos, taxaJurosAnual);
        this.tipoZona = tipoZona;
    }

    @Override
    public double calcularPagamentoMensal() {
        // Calcula a taxa mensal
        double taxaMensal = this.taxaJurosAnual / 12 / 100;
        // Calcula o valor em meses do financiamento
        int mesesFinanciamento = this.prazoFinanciamento * 12;
        // Calculo do pagamento mensal base
        return (super.getValorImovel() / mesesFinanciamento) * (1 + (taxaMensal)) * 1.02;
    }

    // Descrição salva sobre o financiamento do terreno
    @Override
    public String toString() {
        return String.format("""
                        ===== Financiamento Terreno =====
                        
                        Valor total do imóvel: %.2f
                        Prazo do financiamento: %d anos
                        Taxa de juros anual: %.2f%%
                        Tipo de zona: %s
                        Pagamento mensal: %.2f
                        Valor total do financiamento: %.2f
                        """, valorImovel, prazoFinanciamento, taxaJurosAnual, tipoZona, calcularPagamentoMensal(), calcularPagamentoTotal());
    }
}
