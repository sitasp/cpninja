//   Problem: B - Trick Taking
//   Contest: AtCoder - Tokio Marine & Nichido Fire Insurance Programming Contest 2023（AtCoder Beginner Contest 299)
//   URL: https://atcoder.jp/contests/abc299/tasks/abc299_b
//   Memory Limit: 1024
//   Time Limit: 2000
//   CP Ninja


import java.util.*;
import java.io.*;

class Main {
  public static void main (String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = Integer.parseInt(sc.next());
    int t = Integer.parseInt(sc.next());
    List<Integer> c = new ArrayList<>();
    List<Integer> r = new ArrayList<>();
    int max = 0;
    int index = -1;
    boolean exist = false;
    for (int i = 0; i < n; i++) {
      c.add(i, Integer.parseInt(sc.next()));
    }
    for (int i = 0; i < n; i++) {
      r.add(i, Integer.parseInt(sc.next()));
    }

    for (int i = 0; i < n; i++) {
      if (c.get(i).equals(t)) {
        exist = true;
        if (max < r.get(i)) {
          max = r.get(i);
          index = i;
        }
      }
    }
    for (int i = 0; i < n; i++) {
      if (c.get(i).equals(c.get(0)) && exist == false) {
        if (max < r.get(i)) {
          max = r.get(i);
          index = i;
        }
      }
    } 
    System.out.println(index + 1);
  }
}
        

