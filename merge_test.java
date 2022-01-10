import java.util.concurrent.*;
import java.util.*;

public class merge_test extends RecursiveAction
{
	//initial merge_test class
	int low;
	int high;
	int arr[];
	
	merge_test(int[] arr,int low,int high)
	{
		this.low = low;
		this.high = high;
		this.arr = arr;
	}
	
	protected void compute()
	{
		if(low < high)
		{
			int mid = (low+high)/2;
			merge_test lower = new merge_test(arr,low,mid);
			merge_test higher = new merge_test(arr,mid+1,high);
			invokeAll(lower,higher);
			//merge here
			MergeSort m = new MergeSort();
			m.merge(arr,low,mid,high);
		}
	}
	public static void main(String args[])
	{
		int arr[] = new int [100];
		Random rand = new Random();
		for(int j=0;j<100;j++)
		{
			arr[j] = rand.nextInt(100);
		}
		ForkJoinTask <?> task = new merge_test(arr,0,arr.length-1);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(task);
		MergeSort tmp = new MergeSort();
		tmp.test(arr);
		for(int i=0;i<arr.length;i++)
		{
			System.out.println(arr[i]);
		}
	}
}
class MergeSort
{
	void merge(int[] arr,int l,int m,int r)
	{
		int n1 = m-l+1;//l~m
		int n2 = r-m;//m+1~r
		int L[] = new int[n1];
		int R[] = new int[n2];
		for(int i=0;i<n1;i++)
			L[i] = arr[l+i];
		for(int j=0;j<n2;j++)
			R[j] = arr[m+1+j];
		int i=0,j=0;
		int k=l;
		while(i<n1 && j<n2)
		{
			if(L[i]<=R[j])
			{
				arr[k++] = L[i++]; 
			}
			else
			{
				arr[k++] = R[j++];
			}
		}
		while(i<n1)
		{
			arr[k++] = L[i++];
		}
		while(j<n2)
		{
			arr[k++] = R[j++];
		}
	}
	void sort(int[] arr,int l,int r)
	{
		int m = (l+r)/2;
		if(l<r)
		{
			sort(arr,l,m);
			sort(arr,m+1,r);
			merge(arr,l,m,r);
		}
	}
	public void test(int arr[])
	{
		MergeSort m = new MergeSort();
		m.sort(arr,0,arr.length-1);
	}
}
