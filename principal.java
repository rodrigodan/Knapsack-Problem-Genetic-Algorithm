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
		
		//insira os pesos na mochila:
		
		int i = 0;
		while(i < n){
			bolsa[i]= new Mochila();
			bolsa[i].value = entr.nextDouble();
			bolsa[i].strength = entr.nextDouble();
			System.out.println(bolsa[i].value);
			System.out.println(bolsa[i].strength);
			i++;
		}
		
		GA meuGA = new GA(50,100,0.01,bolsa,n);
		meuGA.executa();
	}
}
