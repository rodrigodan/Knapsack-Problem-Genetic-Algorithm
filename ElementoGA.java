import java.util.*;

public class ElementoGA implements Comparable {
	protected Gene valor[];
	protected double avaliacao;
    protected int tamanho;
    Mochila bolsa[];
	
    public boolean equals(ElementoGA outro) {
        //Usada para testar se dois indivíduos são iguais - importante
        //quando formos ver os módulos de população mais avançados
        return(valor.equals(outro.getValor()));
    }
    
    public int compareTo(Object outro) {
        //Será usada na hora de inserir os elementos em ordem em uma população
        //para o GA com steady state (capítulo 7)
        int retorno=-1;
        ElementoGA aux=(ElementoGA) outro;
        if (avaliacao>aux.getAvaliacao()) {retorno=1;}
        if (avaliacao==aux.getAvaliacao()) {retorno=0;}
        return(retorno);
    }

  /*  public double geraVelocidadee(){
    	int i;
    	double aux = 0;
    	for(i = 0; i < 5; i++){
    		aux += valor[i].geraVelocidade();
    	}
    	return aux;
    }*/

  /*  public double geraEnergiaa(){
    	int i;
    	double aux = 0;
    	for(i = 0; i < 5; i++){
    		aux += valor[i].geraEnergia();
    	}
    	return aux;
    }*/


public double calculaAvaliacao() {
/*Esta função deve ser sobreescrita por uma função dentro da sub-classe
  a ser usada como definidora dos cromossomos do GA*/


   
   int i;
   double auxValor = 0;//valor a ser maximizado
   
   double auxPeso = 0;//valor a ser minimizado
   
   for(i = 0; i < tamanho; i++){
	    
	   auxValor += valor[i].bit_gene*bolsa[i].value;
	   
	   auxPeso += valor[i].bit_gene*bolsa[i].strength;
	   System.out.println("aqui!!!!!!!!" + i);

	   System.out.println(auxValor);
	   
	   System.out.println(auxPeso);
	   
   }
   
   if(auxValor == 0 || auxPeso == 0){
	   avaliacao = -2;
	   return(this.avaliacao);
   }
   
   avaliacao = auxValor/auxPeso;

   return(this.avaliacao);
}

public double getAvaliacao() {
   return(this.avaliacao);
}


public Gene[] getValor() {
	   return this.valor;
}

public void setValor(Gene []aux) {
	   this.valor=aux;
}


protected void inicializaElemento(int tamanho) {
   int i;
   this.tamanho = tamanho;
   this.valor= new Gene[tamanho];//o meu cromossomo terá tamanho igual ao numero de curva
   for(i=0;i<tamanho;++i) {
   		valor[i] = new Gene();
   		System.out.println(valor[i].bit_gene);
   		//valor[i].geraParametros(i);
   }
}

/****************/
/* Construtores */
/****************/

/*temppppppppppppppppp*/
/*public ElementoGA(String novoValor) {
   this.valor=novoValor;
}*/
/*temppppppppppppppppp*/

public ElementoGA(int tamanho, Mochila bolsa[]) {
   inicializaElemento(tamanho);//chamo a função para inicializar elemento
   this.bolsa = bolsa;
}

/*public ElementoGA() {//construtor principal
	   this(5);//numero de curvas
}*/



/************************/
/* Operadores Geneticos */
/************************/


/*temppppppppppppppppp*/
/*
public void mutacao(double chance) {
   int i;
   int tamanho=this.valor.length;
   String aux,inicio,fim;
   for(i=0;i<tamanho;i++) {		
	if (java.lang.Math.random()<chance) {		   
	   aux=this.valor.substring(i,i+1);
	         if (aux.equals("1")) {
		aux="0";
	   } else {
		aux="1";
	   }		   
	   inicio=this.valor.substring(0,i);
	   fim=this.valor.substring(i+1,tamanho);		   
	   this.valor=inicio+aux+fim;
	}
   }
}



public void mutacao() {
	this.mutacao(0.005);
}
*/
/*temppppppppppppppppp*/


public ElementoGA crossoverUmPonto(ElementoGA outroPai)  {
   Gene []aux1 = new Gene[5];	   
   ElementoGA retorno=null;
   int pontoCorte=(new Double(java.lang.Math.random()*this.valor.length)).intValue();;	   
   if (java.lang.Math.random()<0.5) {
	   int i;
	   for(i = 0; i < pontoCorte; i++){
		   aux1[i] = valor[i];
	   }
	   
	   for(i = pontoCorte; i < 5; i++){
		   aux1[i] = outroPai.valor[i];
	   }
	   
	   //aux1=this.valor.substring(0,pontoCorte)+outroPai.getValor().substring(pontoCorte,outroPai.getValor().length());
   } else {		
	//aux1=outroPai.getValor().substring(0,pontoCorte)+this.valor.substring(pontoCorte,this.valor.length());
	   int i;
	   for(i = 0; i < pontoCorte; i++){
		   aux1[i] = outroPai.valor[i];
	   }
	   
	   for(i = pontoCorte; i < 5; i++){
		   aux1[i] = valor[i];
	   }
   }
   try {
      retorno=(ElementoGA) outroPai.getClass().newInstance();
      retorno.setValor(aux1);
   } catch (Exception e) {
   }	   	   
   return(retorno);
} 

/*
public ElementoGA crossoverUniforme(ElementoGA outroPai){
	int vetor[] = new int[tamanho];
	ElementoGA filho[] = new ElementoGA[tamanho];
	int i;
	for(i = 0; i < tamanho; i++){
		if(Math.random() > 0.5){
			vetor[i] = 1;
		}
		else{
			vetor[0] = 0;
		}
	}

	for(i = 0; i < tamanho; i++){
		if(vetor[i] == 1){
			filho[i] = valor[i];
		}
		else{
			filho[i] = outroPai.valor[i];
		}
	}
}

*/

/********************************/
/* Metodos Basicos de Classe    */
/********************************/

public String toString() {
	return("String:"+this.valor+"\nAvaliacao:"+this.avaliacao);
}


}