import java.util.*;
import java.io.*;

public class WER {
	
	static int wer(String r, String s){
		String [] ref_s = r.split(" ");
		String [] out_s = s.split(" ");
		int [][] dist = new int [ref_s.length+1][out_s.length+1];
		//Init
		for(int i =0;i <= ref_s.length; i++){
			for(int j =0;j <= out_s.length; j++){
				if(i == 0){
					dist[0][j] = j;
				}else if(j == 0){
					dist[i][0] = i;
				}
			}
		}
		//Cal
		for(int i =1;i <= ref_s.length; i++){
			for(int j =1;j <= out_s.length; j++){
				if(ref_s[i-1].equals(out_s[j-1]) ){
					dist[i][j] = dist[i-1][j-1];
				}else {
					int sub = dist[i-1][j-1]+1;
					int ins = dist[i][j-1]+1;
					int del = dist[i-1][j]+1;
					
					int temp_min = Math.min(sub, ins);
					dist[i][j] = Math.min(temp_min, del);
					//System.out.println(dist[i][j] + "");
				}
			}
		}
		return dist[ref_s.length][out_s.length];
	}

	public static void main(String[] args) {
		try{
			BufferedReader br = new BufferedReader(new FileReader("ref.txt"));
			PrintWriter pw = new PrintWriter(new File("out.txt"));
			
			String ref = br.readLine();
			String out = br.readLine();
			
			String [] size = ref.split(" ");
			
			pw.println("WER: " + wer(ref, out) + "/" + size.length);
			
			br.close();
			pw.close();
		
		} catch(IOException e){
			System.out.println("I/O Error");
		}
	}
}
