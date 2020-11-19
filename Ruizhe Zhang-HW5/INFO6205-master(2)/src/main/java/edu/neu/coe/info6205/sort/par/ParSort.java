package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
class ParSort {

    public static int cutoff = 1000;
   // private static final int asynDepth = (int)(Math.log(Runtime.getRuntime().availableProcessors())/Math.log(2));	
    //static int depth=1;
    public static void Merge(int[] array, int[] x,int[] y) {
    	  int i=0;
          int j=0;
          for(int k=0;k<array.length;k++) {
         	 if(i>=x.length) {
         		 array[k]=x[j++];
         	 }
         	 else if(j>=y.length) {
         		 array[k]=x[i++];
         	 }
         	 else if(x[i]<=y[j]) {
         		 array[k]=x[i++];
         	 }
         	 else{
         		 array[k]=y[j++];
         	 }           	 
          }
    }

    public static void sort(int[] array, int from, int to) {
        if (to - from < cutoff) Arrays.sort(array, from, to);
        else {
            CompletableFuture<int[]> parsort1 = parsort(array, from, from + (to - from) / 2); // TO IMPLEMENT
            CompletableFuture<int[]> parsort2 = parsort(array, from + (to - from) / 2, to); // TO IMPLEMENT
            CompletableFuture<int[]> parsort = parsort1.thenCombine(parsort2, (xs1, xs2) -> {
                int[] result = new int[xs1.length + xs2.length];
                // TO IMPLEMENT
            Merge(result,xs1,xs2);
             return result; 
            });
            
            
            parsort.whenComplete((result, throwable) -> System.arraycopy(result, 0, array, from, result.length));
//            System.out.println("# threads: "+ ForkJoinPool.commonPool().getRunningThreadCount());
            parsort.join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int[] result = new int[to - from];
                    // TO IMPLEMENT
                    System.arraycopy(array, from, result, 0, result.length);
                    sort(result,0,to-from);
                    return result;
                },Main.myPool
        );
    }
    
}


