import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConsultaCotacao consulta = new ConsultaCotacao();

        int opcao = 0;

        while (opcao != 7) {
            System.out.println("*************************************************");
            System.out.println("Seja bem-vindo/a ao Conversor de Moeda =]\n");
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileiro");
            System.out.println("4) Real brasileiro =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Sair");
            System.out.println("Escolha uma opção válida:");
            System.out.println("*************************************************");

            try {
                String entrada = leitura.nextLine();
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("\n>>> Erro: Por favor, digite apenas o NÚMERO da opção.\n");
                opcao = 0;
                continue;
            }

            if (opcao == 7) {
                System.out.println("Saindo...");
                break;
            }

            String base = "";
            String alvo = "";

            switch (opcao) {
                case 1:
                    base = "USD"; alvo = "ARS";
                    break;
                case 2:
                    base = "ARS"; alvo = "USD";
                    break;
                case 3:
                    base = "USD"; alvo = "BRL";
                    break;
                case 4:
                    base = "BRL"; alvo = "USD";
                    break;
                case 5:
                    base = "USD"; alvo = "COP";
                    break;
                case 6:
                    base = "COP"; alvo = "USD";
                    break;
                default:
                    System.out.println("\n>>> Opção inválida! Tente novamente.\n");
                    continue;
            }

            try {
                Moeda moeda = consulta.buscaMoeda(base, alvo);

                System.out.println("Digite o valor que deseja converter:");

                String entradaValor = leitura.nextLine().replace(",", ".");
                double valorParaConverter = Double.parseDouble(entradaValor);

                double valorConvertido = valorParaConverter * moeda.conversion_rate();

                System.out.printf("Valor %.2f [%s] corresponde ao valor final de =>>> %.2f [%s]\n\n",
                        valorParaConverter, base, valorConvertido, alvo);

            } catch (NumberFormatException e) {
                System.out.println("Erro: Valor digitado inválido.");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Finalizando a aplicação.");
            }
        }
        leitura.close();
    }
}