/* Aluno: Alex Augusto Soares
 * Curso: Análise e Desenvolvimento de Sistemas
 * Faculdade: PUCPPR
 */

package main;
import java.text.NumberFormat;
import util.InterfaceUsuario;
import modelo.Financiamento;
import modelo.Apartamento;
import modelo.Casa;
import modelo.Terreno;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        List<Financiamento> listaDeFinanciamentos = new ArrayList<>(); // Criação da lista para armazenar os Financiamentos

        // Solicita ao usuário dados para o financiamento
        System.out.println("\n===== Financiamento Casa =====\n");
        double valorImovel = interfaceUsuario.pedirValorImovel();
        int prazoFinanciamentoAnos = interfaceUsuario.pedirPrazoFinanciamento();
        double taxaJuros = interfaceUsuario.pedirTaxaJuros();
        double areaConstruida = interfaceUsuario.pedirAreaConstruida();
        double areaTerreno = interfaceUsuario.pedirAreaTerreno(areaConstruida);

        // Cria um financiamento para casa, com os dados fornecidos pelo usuário
        Casa casaFinanciamentoUsuario = new Casa(valorImovel, prazoFinanciamentoAnos, taxaJuros, areaConstruida, areaTerreno);
        listaDeFinanciamentos.add(casaFinanciamentoUsuario); // Adiciona o novo financimento preenchido pelo usuário para Lista de financiamentos

        listaDeFinanciamentos.add(new Casa(350000, 10, 10, 250, 300));
        listaDeFinanciamentos.add(new Apartamento(550000, 10, 8, 2, 8));
        listaDeFinanciamentos.add(new Apartamento(950000, 15, 10, 2, 12));
        listaDeFinanciamentos.add(new Terreno(500000, 10, 10, "residencial"));

        double totalValorImoveis = 0;
        double totalValorFinanciamentos = 0;

        int numeroFinanciamento = 1;
        // Loop para mostrar os dados do Financiamento
        for (Financiamento financiamento : listaDeFinanciamentos) {
            totalValorImoveis += financiamento.getValorImovel();
            totalValorFinanciamentos += financiamento.calcularPagamentoTotal();

            // Mostra o tipo de financiamento (casa, apartamento ou terreno)
            if (financiamento instanceof Casa) {
                System.out.println("\n===== Financiamento " + numeroFinanciamento + " (Casa) =====\n");
            } else if (financiamento instanceof Apartamento) {
                System.out.println("\n===== Financiamento " + numeroFinanciamento + " (Apartamento) =====\n");
            } else if (financiamento instanceof Terreno) {
                System.out.println("\n===== Financiamento " + numeroFinanciamento + " (Terreno) =====\n");
            }

            financiamento.dadosFinanciamento();
            numeroFinanciamento++;
        }

        // Mostra os valores digitados em formato de moeda
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        // Apresenta os valores totais do Financiamento
        System.out.println("\n===== Dados Gerais dos Financiamentos =====\n");
        System.out.println("Valor total de todos os imóveis: " + nf.format(totalValorImoveis));
        System.out.println("Valor total de todos os financiamentos: " + nf.format(totalValorFinanciamentos));
        System.out.println("\n============================================\n");


        // Salvar os dados do financiamento em um arquivo de texto (.txt)
        salvarArquivoEmTexto(listaDeFinanciamentos);

        // Salvar o Array list em arquivo Serializado
        salvarArquivoSerializado(listaDeFinanciamentos);

        // Leitura dos dados serializados
        List<Financiamento> listaLida = lerArquivoSerializados();
        if (listaLida != null) {
            System.out.println("\n===== Leitura do Arquivo Serializado =====\n");
            for (Financiamento financiamento : listaLida) {
                System.out.println(financiamento);
            }
        }
    }

    // Método para salvar os dados do financiamento em arquivo de texto (.txt)
    private static void salvarArquivoEmTexto(List<Financiamento> listaDeFinanciamentos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ProjetoFinanciamento.txt"))) { // Arquivo Texto salvo como "ProjetoFinanciamento.txt"
            for (Financiamento financiamento : listaDeFinanciamentos) {
                writer.write(financiamento.toString());
                writer.newLine();
            }
            System.out.println("Dados dos financiamentos salvos em 'ProjetoFinanciamento.txt' com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados dos financiamentos: " + e.getMessage()); // Mensagem de erro caso de algum problema na hora de salvar
        }
    }

    // Método para salvar os dados do financiamento serializado
    private static void salvarArquivoSerializado(List<Financiamento> listaDeFinanciamentos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ProjetoFinanciamento.ser"))) { // Arquivo Serializado salvo como "ProjetoFinanciamento.ser"
            oos.writeObject(listaDeFinanciamentos);
            System.out.println("Dados dos financiamentos serializados salvos em 'ProjetoFinanciamento.ser' com sucesso!");
        }
        catch (IOException e) {
            System.out.println("Erro ao salvar os dados dos financiaments serializado: " + e.getMessage()); // Mensagem de erro caso de algum problema na hora de salvar
        }
    }

    // Método para ler os dados do financiamento serializado
    private static List<Financiamento> lerArquivoSerializados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ProjetoFinanciamento.ser"))) {
            return (List<Financiamento>) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao ler os dados dos financiamentos serializado: " + e.getMessage()); // Mensagem de erro para leitura
            return null;
        }
    }
}

