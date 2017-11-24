import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class GA{


   protected Vector populacao;//minha população em um tempo t
   protected double somaAvaliacoes;//soma das avaliação da minha população em t
   protected double chance_mutacao;//probalidade de mutação
   protected Vector nova_populacao;//população intermediária
   protected int numero_geracoes,tamanho_populacao;//numero de gerações e tamanho da minha população
   
   protected Mochila bolsa[];
   protected int num_size;//numero de elementos
   
 
   
    private double calculaSomaAvaliacoes() {
	int i;
	this.somaAvaliacoes=0;
	int tam = populacao.size();//tamanho da minha população
	for(i=0;i<tam;++i) {
		this.somaAvaliacoes+=((ElementoGA) populacao.get(i)).getAvaliacao();//avaliação de cada elemento
	}
	return(this.somaAvaliacoes);//retorno a soma da minha avaliação
   }


 public int roleta() {
	int i;
	double aux=0;
	calculaSomaAvaliacoes();
	double limite=Math.random()*this.somaAvaliacoes;	
	for(i=0;( (i<this.populacao.size())&&(aux<limite) );++i) {
	   aux+=((ElementoGA) populacao.get(i)).getAvaliacao();
	}
	/*Como somamos antes de testar, então tiramos 1 de i pois
	  o anterior ao valor final consiste no elemento escolhido*/
	i--;	
	System.out.println("Escolhi o elemento de indice "+i);
	return(i);
   }

   




   public void inicializaPopulacao() {   
	int i;
	this.populacao=new Vector();
	
	for(i=0;i<this.tamanho_populacao;++i) {
	   //System.out.println("Aqui!!!");
	   this.populacao.add(new ElementoGA(num_size,bolsa)); 
	}
   }


   public void moduloPopulacao() {
        populacao.removeAllElements();
        populacao.addAll(nova_populacao);	
   }



   protected int determinaMelhor() {
	int i,ind_melhor=0;
	ElementoGA aux;
	this.avaliaTodos();
	double aval_melhor=((ElementoGA)this.populacao.get(0)).getAvaliacao();
	for(i=1;i<this.populacao.size();++i) {
		aux=(ElementoGA)this.populacao.get(i);		
		if (aux.getAvaliacao()>aval_melhor) {
		   aval_melhor=aux.getAvaliacao();
		   ind_melhor=i;
		}
	}
	return(ind_melhor);
   }




	public GA(int num_geracoes,int tam_populacao, double prob_mut, Mochila[] bolsa2, int n) {
	   	this.chance_mutacao=prob_mut;
	   	this.numero_geracoes=num_geracoes;
	   	this.tamanho_populacao=tam_populacao;
	   	bolsa = bolsa2;
	   	num_size = n;
   }





protected void avaliaTodos() {
	int i;
	ElementoGA aux;
	System.out.println("Avaliando todos...\n");
	for(i=0;i<this.populacao.size();++i) {
		aux=(ElementoGA)this.populacao.get(i);
		aux.calculaAvaliacao();
	}
	this.somaAvaliacoes=calculaSomaAvaliacoes();
	System.out.println("A soma das avaliacoes eh "+this.somaAvaliacoes);
   }




   public void geracao() {
		nova_populacao=new Vector();
        ElementoGA pai1,pai2, filho;
		int i;
		System.out.println("Calculando nova geracao...\n");
		for(i=0;i<this.populacao.size();++i) {
			pai1 = (ElementoGA)populacao.get(this.roleta());
			pai2 = (ElementoGA) populacao.get(this.roleta());		
		        filho= pai1.crossoverUmPonto(pai2);
			
		        //filho.mutacao(chance_mutacao);
			System.out.println("Vou adicionar...");
			nova_populacao.add(filho);
		}
   }
  
   
   public void printAll(int i, File A){//put os elementos com as suas avaliações em um arquivo
	   A = new File("meusElementosGeracao" + i + ".txt");//crio um arquivo para cada geracao
	   try {
			A.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   

		BufferedWriter buffWrite;
		ElementoGA aux;
		int bitt;
		/*String curva;
		double velocidade = 0;
		double energia = 0;*/
		try {
			buffWrite = new BufferedWriter(new FileWriter("meusElementosGeracao" + i + ".txt"));
			buffWrite.append("Geração : " + i + "\r\n");//qual a geração onde irei conter um conjunto de individuos
			for(int j = 0; j < tamanho_populacao; j++){
				aux = (ElementoGA)(populacao.get(j));
				buffWrite.append("INDIVÍDUO : " + j + "\r\n");//qual indivíduo ( 1,2,...,100)
				for(int k = 0; k < num_size; k++){
					bitt = aux.valor[i].bit_gene;
					buffWrite.append(bitt + ", ");
					
					
				}
				
				buffWrite.append("Avaliacao: " + aux.avaliacao  );
				buffWrite.flush();
				buffWrite.append("\r\n");
				buffWrite.append("\r\n");
				
				buffWrite.append("Soma de todas as avaliações: " + somaAvaliacoes);
				buffWrite.flush();
			}
			
		} 
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
   }
				/*	buffWrite.append("Curva " + k + "\r\n");//deterrmina a curva k
					
					//velocidade e energia na curva k
					
					velocidade = aux.valor[k].velocidade;
					buffWrite.append("velocidade: " + velocidade);
					
					energia = aux.valor[k].energia;
					buffWrite.append("\tEnergia: " + energia+ "\r\n");
				}

				buffWrite.append("Avaliacao: " + aux.avaliacao  );
				buffWrite.flush();
				buffWrite.append("\r\n");
				buffWrite.append("\r\n");
			}
			buffWrite.append("Soma de todas as avaliações: " + somaAvaliacoes);
			buffWrite.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
   }*/

   public void executa() {
	int i;	//geração
	this.inicializaPopulacao();
	for (i=0;i<this.numero_geracoes;++i) {
		System.out.println("Geracao "+i+"\n");
		this.avaliaTodos();
		
		File A = null;  
	   	

		
		
		printAll(i,A);
		
		break;
		
		//this.geracao();
		
		//this.moduloPopulacao();
	}
	i=this.determinaMelhor();
	System.out.println((ElementoGA) this.populacao.get(i));
   
   }

}








