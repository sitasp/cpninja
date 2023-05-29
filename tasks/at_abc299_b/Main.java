//   Problem: B - Trick Taking
//   Contest: AtCoder - Tokio Marine & Nichido Fire Insurance Programming Contest 2023（AtCoder Beginner Contest 299)
//   URL: https://atcoder.jp/contests/abc299/tasks/abc299_b
//   Memory Limit: 1024
//   Time Limit: 2000
//   CP Ninja


import java.util.Scanner;
 
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
 
		int n = sc.nextInt();
		long t = sc.nextLong();
 
		long[][] player = new long[2][n];
		
		boolean flg = false;
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < n; j++) {
				player[i][j] = sc.nextLong();
				if(i == 0 && player[i][j] == t) {
					flg = true;
				}
			}
		}
		
		if(!flg) {
			t = player[0][0];
		}
		
		long max = -1;
		int ans = -1;
		
		for(int i = 0; i < n; i++) {
			if(player[0][i] == t && max < player[1][i]) {
				max = player[1][i];
				ans = i + 1;
			}
		}
		
		System.out.println(ans);
		
		sc.close();
	}
}