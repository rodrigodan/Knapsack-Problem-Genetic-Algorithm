import java.util.*;

public class ElementoGA implements Comparable {
	protected Gene valor[];
	protected double avaliacao;
    protected int tamanho;
    Mochila bolsa[];
    double pesoMax;
    double pesoTotal;
	
    public ElementoGA(int tamanho, Mochila bolsa[], double pesoMax) {
    	   this.bolsa = bolsa;
    	   this.pesoMax = pesoMax;
    	   pesoTotal = 0;
    	   inicializaElemento(tamanho);//chamo a função para inicializar elemento

    }
    
    protected void inicializaElemento(int tamanho) {
    	   int i;
    	   this.tamanho = tamanho;
    	   this.valor= new Gene[tamanho];//o meu cromossomo terá tamanho igual ao numero de curva
    	   for(i=0;i<tamanho;++i) {
    	   		valor[i] = new Gene(bolsa,i,pesoTotal);
    	   		pesoTotal = valor[i].pesoTotal;
    	   		//valor[i].geraParametros(i);
    	   }
    	   pesoTotal = 0;
    }

    
    public double calculaAvaliacao() {
    	   
    	   int i;
    	   double auxValor = 0;
    	   for(i = 0; i < tamanho; i++){
    			  pesoTotal = pesoTotal + (valor[i].bit_gene*bolsa[i].strength);
    			  auxValor = auxValor + (valor[i].bit_gene*bolsa[i].value);
    	
    	   }
    	   if(pesoTotal > bolsa[0].pesoMax){
    		   //avaliacao = auxValor - 2*pesoTotal;
    		   avaliacao = 0;
    		   if(avaliacao < 0){
    			   avaliacao = 0;
    		   }
    	   }
    	   else{
    		   avaliacao = auxValor;
    	   }
    	   
    	   pesoTotal = 0;
    	   
    	   /*double auxValor = 0;//valor a ser maximizado
    	   
    	   double auxPeso = 0;//valor a ser minimizado
    	   
    	   for(i = 0; i < tamanho; i++){
    		    
    		   auxValor += valor[i].bit_gene*bolsa[i].value;
    		   
    		   auxPeso += valor[i].bit_gene*bolsa[i].strength;
    		   System.out.println("aqui!!!!!!!!" + i);

    		   System.out.println(auxValor);
    		   
    		   System.out.println(auxPeso);
    		   
    	   }
    	   
    	   if(auxValor == 0 || auxPeso == 0){
    		   avaliacao = 0;
    		   return(this.avaliacao);
    	   }
    	   
    	   avaliacao = auxValor/auxPeso;
    	   System.out.println("Avaliacao = " + avaliacao);

    	   return(this.avaliacao);*/
    	   
    	   return (this.avaliacao);
    	}
    
    
    
    public Gene[] getValor() {
 	   return this.valor;
    }

    public void setValor(Gene []aux) {
 	   this.valor=aux;
    }
    
    public ElementoGA crossoverUmPonto(ElementoGA outroPai)  {
    	   Gene []aux1 = new Gene[tamanho];	   
    	   ElementoGA retorno = new ElementoGA(tamanho,bolsa,pesoMax);
    	   int pontoCorte=(new Double(java.lang.Math.random()*this.valor.length)).intValue();
    	   System.out.println("pontoDeCorteeeeeeeee: " + pontoCorte);
    	   if (java.lang.Math.random()<0.5) {
    		   int i;
    		   for(i = 0; i < pontoCorte; i++){
    			   retorno.valor[i] = valor[i];
    			   
    			   
    		   }
    		   
    		   for(i = pontoCorte; i < tamanho; i++){
    			   retorno.valor[i] = outroPai.valor[i];
    		   }
    		   
    		   //aux1=this.valor.substring(0,pontoCorte)+outroPai.getValor().substring(pontoCorte,outroPai.getValor().length());
    	   } else {		
    		//aux1=outroPai.getValor().substring(0,pontoCorte)+this.valor.substring(pontoCorte,this.valor.length());
    		   int i;
    		   for(i = 0; i < pontoCorte; i++){
    			   retorno.valor[i] = outroPai.valor[i];
    		   }
    		   
    		   for(i = pontoCorte; i < tamanho; i++){
    			   retorno.valor[i] = valor[i];
    		   }
    	   }
    	   /*try {
    	      retorno=(ElementoGA) outroPai.getClass().newInstance();
    	      retorno.setValor(aux1);*/

    	   /*} catch (Exception e) {
    	   }*/
    	   for(int i = 0; i < tamanho; i++){
    		   System.out.println(retorno.valor[i].bit_gene);
    	   }
    	   return(retorno);
    	}
		 
    
    public void mutacao(double chance) {
    	   int i;
    	   int aux = 0;
	    	if (java.lang.Math.random()<chance) {		   
	    		aux = (int) ((Math.random())*(tamanho-1));
	    	}
    }
    
	@Override
	public int compareTo(Object outro) {
		// TODO Auto-generated method stub
		
		/*int retorno=-1;
        ElementoGA aux=(ElementoGA) outro;
        if (avaliacao>aux.getAvaliacao()) {retorno=1;}
        if (avaliacao==aux.getAvaliacao()) {retorno=0;}
        return(retorno);*/
		return 0;
	}

}