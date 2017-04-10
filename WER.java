import java.util.Arrays;

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
				}
			}
		}
		return dist[ref_s.length][out_s.length];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ref = "Israeli officials are responsible for airport security";
		String out = "Israeli official responsible airport is security";
		
		String [] size = ref.split(" ");
		
		System.out.println(wer(ref,out) + "/" + size.length);
	}

}
