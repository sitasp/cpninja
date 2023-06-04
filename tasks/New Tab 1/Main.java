import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int T = scanner.nextInt();
        int[] C = new int[N];
        int[] R = new int[N];

        for (int i = 0; i < N; i++) C[i] = scanner.nextInt();
        for (int i = 0; i < N; i++) R[i] = scanner.nextInt();

        int indexT = -1;
        int maxT = -1;
        int indexOne = -1;
        int maxOne = -1;
        
        for (int i = 0; i < N; i++) {
			if(C[i] == T &&  maxT < R[i]) {
				indexT = i+1;
				maxT = Math.max(maxT, R[i]);
			}
			
			if(C[i] == C[0] && maxOne < R[i]) {
				indexOne = i+1;
				maxOne = Math.max(maxOne, R[i]);
			}
		}

        if(indexT!=-1) {
        	System.out.println(indexT);
            return;
        }else {
        	System.out.println(indexOne);
            return;
        }
    }
}
