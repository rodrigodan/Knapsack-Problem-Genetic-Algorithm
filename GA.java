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
   protected double pesoMax;
   
   protected int num_size;//numero de genes
   protected Mochila bolsa[];
   
   Arquivo escritor;
   
 
   //inicio a característica do meu algoritmo:
	public GA(int num_geracoes,int tam_populacao, double prob_mut, Mochila[] bolsa2, int n,double pesoMax) {
	   	this.chance_mutacao=prob_mut;
	   	this.numero_geracoes=num_geracoes;
	   	this.tamanho_populacao=tam_populacao;
	   	bolsa = bolsa2;
	   	num_size = n;
	   	this.pesoMax = pesoMax;
	   	escritor = new Arquivo();
   }

	protected void avaliaTodos(int i) {
		ElementoGA aux;
		System.out.println("Avaliando todos...\n");
		for(i=i;i<this.populacao.size();++i) {
			aux=(ElementoGA)this.populacao.get(i);
			aux.calculaAvaliacao();
		}
		//this.somaAvaliacoes=calculaSomaAvaliacoes();
		System.out.println("A soma das avaliacoes eh "+this.somaAvaliacoes);
	}
	
    private double calculaSomaAvaliacoes() {
		int i;
		this.somaAvaliacoes=0;
		int tam = populacao.size();//tamanho da minha população
		for(i=0;i<tam;++i) {
			this.somaAvaliacoes+=((ElementoGA) populacao.get(i)).avaliacao;//avaliação de cada elemento
		}
		return(this.somaAvaliacoes);//retorno a soma da minha avaliação
   }
	
	
	 public int roleta() {
			int i;
			double aux=0;
			calculaSomaAvaliacoes();
			double limite=Math.random()*this.somaAvaliacoes;	
			for(i=0;( (i<this.populacao.size())&&(aux<limite) );++i) {
			   aux+=((ElementoGA) populacao.get(i)).avaliacao;
			}
			/*Como somamos antes de testar, então tiramos 1 de i pois
			  o anterior ao valor final consiste no elemento escolhido*/
			i--;	
			System.out.println("Escolhi o elemento de indice "+i);
			return(i);
	}
	
	 public int roletaInversiva() {
			int i;
			double aux=0;
			double auxTemp = 0;
			calculaSomaAvaliacoes();
			double limite=Math.random()*this.somaAvaliacoes;	
			for(i=0;( (i<this.populacao.size())&&(aux<limite) );++i) {
			   auxTemp = ((ElementoGA) populacao.get(i)).avaliacao;
			   if(auxTemp != 0){
				   aux += 1/auxTemp;
			   }
			   aux+=auxTemp;
			}
			/*Como somamos antes de testar, então tiramos 1 de i pois
			  o anterior ao valor final consiste no elemento escolhido*/
			i--;	
			System.out.println("Escolhi o elemento de indice "+i);
			return(i);
	}
	
	  public void geracao() {
			nova_populacao=new Vector();
	        ElementoGA pai1,pai2, filho = null;
			int i;
			System.out.println("Calculando nova geracao...\n");
			for(i=0;i<this.populacao.size();++i) {
				pai1 = (ElementoGA)populacao.get(this.roleta());
			
				pai2 = (ElementoGA) populacao.get(this.roletaInversiva());		
			    filho= pai1.crossoverUmPonto(pai2);
				
			    filho.mutacao(chance_mutacao);
				System.out.println("Vou adicionar...");
				nova_populacao.add(filho);
			}
	   }
	
	  public void moduloPopulacao(){
	        //Vector populacaoAtualEAntiga = new Vector();
	        populacao.addAll(nova_populacao);
	        this.avaliaTodos(tamanho_populacao);
	        Ordena();
	        
	        //System.out.println("##################################### " + populacao.size());
	        for(int i = 0; i < tamanho_populacao; i++){
	        	populacao.remove(0);
	        }
	   }
	  
	  public void Ordena(){
		  Collections.sort(populacao, new Comparator() {
	          @Override
	          public int compare(Object o1, Object o2) {
	              ElementoGA p1 = (ElementoGA) o1;
	              ElementoGA p2 = (ElementoGA) o2;
	              return p1.avaliacao < p2.avaliacao ? -1 : (p1.avaliacao > p2.avaliacao ? +1 : 0);
	          }
	      });
	  }
	  
   public void executa() {
	int i;	//geração
	this.inicializaPopulacao();
	

	printaMochila();
	
	this.avaliaTodos(0);
	Ordena();
	for (i=0;i<this.numero_geracoes;++i){
		System.out.println("Geracao "+i+"\n");
		
		escritor.escreverPulaLinha("geracao = " + i);
		
		printa();
		
		this.geracao();
		
		moduloPopulacao();
		
		
		
		
		/*ElementoGA e = null;
		int auxGene;
		
		for(int x = 0; x < tamanho_populacao; x++){
			e = (ElementoGA)(populacao.get(x));
			
			for(int j = 0; j < num_size; j++){
				
				auxGene = e.valor[j].bit_gene;
				escritor.escrever(auxGene + "|");
			}
			escritor.escrever("\tAvaliacao = " + e.avaliacao);
			escritor.escreverPulaLinha("");
			
		}*/
		
		
		
		
		//break;
		
		//this.moduloPopulacao();
	}
	escritor.fechar();
	/*i=this.determinaMelhor();
	System.out.println((ElementoGA) this.populacao.get(i));*/
   
   }

   
   public void inicializaPopulacao(){   
		int i;
		this.populacao=new Vector();
		
		for(i=0;i<this.tamanho_populacao;++i) {
		   //System.out.println("Aqui!!!");
		   this.populacao.add(new ElementoGA(num_size,bolsa,pesoMax)); 
		}
	}


	public void printa(){
		ElementoGA e = null;
		int auxGene;
		
		for(int i = 0; i < tamanho_populacao; i++){
			e = (ElementoGA)(populacao.get(i));
			for(int j = 0; j < num_size; j++){
				
				auxGene = e.valor[j].bit_gene;
				escritor.escrever(auxGene + "|");
			}
			escritor.escrever("\tAvaliacao = " + e.avaliacao);
			
			escritor.escreverPulaLinha("");
			
		}
		
	}
	
	public void printaMochila(){
		//pesos e valores da mochila:
		escritor.escreverPulaLinha(num_size + " elementos F(valor,peso): ");
		for(int i = 0; i < num_size; i++){
			escritor.escrever("(" + bolsa[i].value + "," + bolsa[i].strength + ")");
		}
		escritor.escreverPulaLinha("");
		escritor.escreverPulaLinha("");
	}

}



