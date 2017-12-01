public class Gene{
	int bit_gene;
	double pesoTotal;

	public Gene(Mochila bolsa[], int i, double pesoTotal){
		this.pesoTotal = pesoTotal;
		if(Math.random() > 0.5 && (pesoTotal+bolsa[i].strength) <= bolsa[i].pesoMax){
			bit_gene = 1;
			this.pesoTotal += bolsa[i].strength;
		}
		else{
			bit_gene = 0;
		}
	}
}