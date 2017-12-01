import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class Arquivo {
	
	Writer writer = null;
	
	public Arquivo(){
		

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("filename.txt"), "utf-8"));
		    
		} catch (IOException ex) {
		  // report
		}
	}
	
	//pula uma linha
	public void escrever(String subject){
		try {
			writer.write(subject);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public void escreverPulaLinha(String subject){
		try {
			writer.write(subject + "\r\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public void fechar(){
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public static void main(String []args){
		Arquivo a = new Arquivo();
		a.escrever("testando");
		a.escrever("teste2");
		a.fechar();
		
	}*/
}
