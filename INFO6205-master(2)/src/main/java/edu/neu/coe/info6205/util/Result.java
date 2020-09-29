package edu.neu.coe.info6205.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

import edu.neu.coe.info6205.sort.simple.InsertionSort;

public class Result {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double a=Result.ddoit(300, 4);
		double b=Result.ddoit(600, 4);
		double c=Result.ddoit(1200, 4);
		double d=Result.ddoit(2400, 4);
		double e=Result.ddoit(4800, 4);
		System.out.println(a+"  "+b+"  "+c+"  "+d+"  "+e+"  ");
	}
	public static double ddoit(int N,int type) {
			InsertionSort<Integer> insertsort= new InsertionSort<Integer>();
			Supplier<Integer[]> supplier= ()->arr1(N,type);
			Consumer<Integer[]> consumer=(t)->{insertsort.sort(t,0,t.length);};
			Benchmark_Timer<Integer[]> bench= new	Benchmark_Timer<Integer[]>("resu",consumer);
			double time =bench.runFromSupplier(supplier, 500);
			return time;
	}

	private static Integer[] arr1(int n,int type) {
		// TODO Auto-generated method stub
		Integer[] arr=new Integer[n];
		
		if(type==1)
		{
			for(int i=0;i<arr.length;i++)
			{
				arr[i]=i;
			}
		}else if(type==2)
		{
			for(int i=0;i<arr.length;i++)
			{
				arr[i]=n-i;
			}
		}else if(type==3)
		{
			for(int i=0;i<arr.length;i++)
			{
				arr[i]=(int)Math.random()*n;
			}
		}
		else
		{
			for(int i=0;i<arr.length;i++)
			{
				if(i<n/2) arr[i]=(int)Math.random()*n;
				else arr[i]=i;
			
			}
		}	
		return arr;
	} 
}
