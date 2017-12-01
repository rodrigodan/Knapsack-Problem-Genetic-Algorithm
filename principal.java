import java.util.Scanner;

public class principal{
	public static void main(String args[]) {
	   //GAUniforme meuGA=new GAUniforme(50,100,0.01);
	   //meuGA.executa();
		
		//insira o numero de elementos da mochila:
		
		Scanner entr = new Scanner(System.in);
		
		int n = entr.nextInt();
		
		System.out.println(n);
		
		Mochila bolsa[] = new Mochila[n];
		
		double pesoMaximo = entr.nextDouble();
		
		//insira os pesos na mochila:
		
		int i = 0;
		while(i < n){
			bolsa[i]= new Mochila();
			bolsa[i].value = entr.nextDouble();
			bolsa[i].strength = entr.nextDouble();
			bolsa[i].pesoMax = pesoMaximo;
			System.out.println(bolsa[i].value);
			System.out.println(bolsa[i].strength);
			i++;
		}
		//tamanho da população 2*n
		GA meuGA = new GA(50,50,0.05,bolsa,n,pesoMaximo);
		meuGA.executa();
	}
}
