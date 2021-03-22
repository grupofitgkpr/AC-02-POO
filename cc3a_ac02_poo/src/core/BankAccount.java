package core;

import java.util.Random;

public class BankAccount {
    
    private static int lastAccountNumber = 1000;
    
    private String owner;
    private int accountNumber;
    private double balance;
    private double totalCpmf;
    private String password;
    
    public BankAccount(String owner){
        this(owner, 0);
    }
    
    public BankAccount(String owner, double balance){
        accountNumber = lastAccountNumber++;
        this.balance = balance;
        this.owner = checkName(owner);
        this.password = makePassword();
        
        if (this.owner == "Nome inválido")
        	System.exit(0);
    }
    
    // EXERCICIO 2 - MÉTODO TRANSFER
    public void transfer(double valor, BankAccount target) {
    	//Tira o valor da conta de origem
	double newBalance = this.balance - valor;
        this.balance = newBalance;
        
	//Adiciona na conta de destino
        double newBalance1 = target.balance + valor;
        target.balance = newBalance1;
    }
    
    // EXERCICIO 4 - MÉTODO CHECKNAME
    private static String checkName(String owner) {
    	// Checa se tem digitos ou caracteres especiais no owner
    	if (owner.matches(".*[ -@].*")) {
    		System.out.printf("Nome inválido de conta '%s'", owner);
		System.exit(0);
    		return "Qualquer coisa só para evitar o erro KKKKK";
    	} 
    	else 
    		return owner;
    }
    
    // EXERCICIO 5 - MÉTODO MAKE PASSWORD
    private static String makePassword() {
    	// Letras usadas na senha
    	String characters = "abcdefghijklmnopqrstuvwxyz";
    	int lengthS = 3;
    	
    	// Números usados na senha
    	String numbers = "0123456789";
    	int lengthN = 4;
    	
    	// String que armazenará a senha gerada
    	String randomPassword = "";
    	
    	Random rand = new Random();
    	
    	// Gera 3 caracteres aleatoriamente e adiciona na senha
    	for (int i = 0; i < lengthS; i++) {
    		randomPassword += characters.charAt(rand.nextInt(26));
    	}
    	
    	// Gera 4 números aleatoriamente e adiciona na senha
    	for (int i = 0; i < lengthN; i++) {
    		randomPassword += numbers.charAt(rand.nextInt(10));
    	}

    	return randomPassword;
    }
    
    // EXERCICIO 6 - CPMF 
    public void withDraw(double amount){
        double cpmf = amount * 0.0025;
        double newBalance = balance - amount;
        balance = newBalance - cpmf;
        totalCpmf = totalCpmf + cpmf;
    }
        
    public String getOwner(){
        return owner;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void deposit(double amount){
        double newBalance = balance + amount;
        balance = newBalance;
        
    }
    
    public double getBalance(){
        return balance;
    }
    
    public int getAccountNumber(){
        return accountNumber;
    }
    
    public String toString(){
        return "Conta de " + owner + " - Saldo de R$ " + balance + " - Total de CPMF recolhido R$ " + totalCpmf + " - Senha gerada: " + password;
    }
}
