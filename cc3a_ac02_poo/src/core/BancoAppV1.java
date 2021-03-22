package core;

import java.util.Scanner;

public class BancoAppV1 {

    public static Scanner entrada;

    public static void main(String[] args) {

        BankAccount[] contas = new BankAccount[5];

        contas[0] = new BankAccount("Marcos", 1000.00);
        contas[1] = new BankAccount("Júlia", 250.00);
        contas[2] = new BankAccount("João", 2500.00);
        contas[3] = new BankAccount("Roberto", 3000.00);
        contas[4] = new BankAccount("Janaína", 4500.00);
        
        entrada = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("Escolha uma operação:");
            System.out.println("[1] Mostrar informações de todas as contas");
            System.out.println("[2] Sacar");
            System.out.println("[3] Depositar");
            System.out.println("[4] Transferir");
            System.out.println("[5] Sair");
            System.out.println("Opção escolhida: ");

            int escolha = entrada.nextInt();
            switch (escolha) {
                case 1:
                    mostrarInfo(contas);
                    break;
                case 2:
                    interacaoSacar(contas);
                    break;
                case 3:
                    interacaoDepositar(contas);
                    break;
                case 4:
                    interacaoTransferir(contas);
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            System.out.println();
        }
        System.out.println(
                "Fim do programa!");
    }
    
    // EXERCICIO 1 - MÉTODO INTERACAO DEPOSITAR
    public static void interacaoDepositar(BankAccount[] contas) {
        
    	int indiceConta = validaCliente(contas);

        // O usuário digita o valor do depósito
        System.out.println("Qual o valor do depósito? ");
        double deposito = entrada.nextDouble();

        // O depósito é feito
        contas[indiceConta].deposit(deposito);
        System.out.println("Depósito finalizado. \n");
    }

    // EXERCICIO 3 - MÉTODO INTERACAO TRANSFERIR
    public static void interacaoTransferir(BankAccount[] contas) {
        
    	// Indice da conta de origem
    	int indiceConta = validaCliente(contas);
        
        // O usuário escolhe o valor que deseja transferir
        System.out.println("Qual o valor da transferência? ");
        double valor = entrada.nextDouble();

        // If que verifica se a conta possui o valor solicitado
        if (valor <= contas[indiceConta].getBalance()) {
        	// Indice da conta de destino
        	int contaIndice = validaCliente(contas);

             // If que verifica se a conta que deseja transferir é diferente da conta de destino.
            if (indiceConta != contaIndice) {
                 BankAccount conta = contas[contaIndice];
                 contas[indiceConta].transfer(valor, conta);
                 System.out.println("Transferência finalizada. \n");  
             } else
                 System.out.println("Você não pode efetuar uma transferência para a mesma conta.");
         }
        else 
        	System.out.println("Você não pode transferir um valor maior do que tem na conta.");
    }

    // Função que valida se o cliente digitado é válido
    public static Integer validaCliente(BankAccount[] contas) {
    	boolean clienteValido = false;
        int indiceConta = -1;

        while (!clienteValido) {
            mostrarInfo(contas);
            
            System.out.println("Digite o número do cliente para realizar a operação escolhida "
                    + "[0 a " + (contas.length - 1) + "]: ");

            indiceConta = entrada.nextInt();

            if (indiceConta >= 0 && indiceConta < contas.length)
                clienteValido = true;
            else
                System.out.println("Índice de cliente inválido!");
        }
        return indiceConta;
    }
    
    public static void mostrarInfo(BankAccount[] contas) {

        System.out.println("\nContas de todos os clientes:");

        for (int i = 0; i < contas.length; i++) {
            System.out.println("[" + i + "]" + contas[i].toString());
        }

        System.out.println("");
    }

    public static void interacaoSacar(BankAccount[] contas) {

        int indiceConta = validaCliente(contas);

        System.out.println("Qual o valor do saque? ");
        double saque = entrada.nextDouble();

        contas[indiceConta].withDraw(saque);
        System.out.println("Saque finalizado. \n");
    }
}