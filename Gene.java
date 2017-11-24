public class Gene{
	/*double velocidade; //50 <=v <= 100
	double energia; // 1900 <= e <= 5000
	String curva;*/
	
	int bit_gene;

	public Gene(){
		if(Math.random() > 0.5){
			bit_gene = 1;
		}
		else{
			bit_gene = 0;
		}
	}
	//public void geraParametros(int esimo){//geração do subgenes de forma aleatoria, mas obedecendo ao intervalo
		/*double aux1 = 0;
		double aux2 = 0;
		if(esimo == 0){
			aux1 = 50*Math.random() + 50;
			aux2 = 3100*Math.random() + 1900;
		}

		if(esimo == 1){
			aux1 = 50*Math.random() + 50;
			aux2 = 3100*Math.random() + 1900;	
		}

		if(esimo == 2){
			
			aux1 = 50*Math.random() + 50;
			aux2 = 3100*Math.random() + 1900;	
		}

		if(esimo == 3){
			
			aux1 = 50*Math.random() + 50;
			aux2 = 3100*Math.random() + 1900;
		}

		if(esimo == 4){
			
			aux1 = 50*Math.random() + 50;
			aux2 = 3100*Math.random() + 1900;
				
		}

		if(aux1 < 0){
				aux1 = -aux1;
		}

		velocidade = aux1;	
		energia = aux2; 
	}

	public double geraVelocidade(){
		return velocidade;
	}

	public double geraEnergia(){
		return energia;*/
	//}
}