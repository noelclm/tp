package tp.pr0;

public class FuncsMatematicas {
	
	public static void main(java.lang.String[] args) {
		for (int i = 0; i < 6; ++i) {
			for (int j = 0; j <= i; ++j)
				System.out.print(FuncsMatematicas.combinatorio(i,j) + " ");
			
			System.out.println();
		}
	}
	
	public static int combinatorio (int n, int k){
		return factorial(n)/(factorial(k)*factorial(n-k));
	}
	
	public static int factorial (int n){
		
		int num = 1;
		
		if(n==0)
			num = 1;
		else if(n<0)
			num = 0;
		else{
			for(int j=n; j>1 ; j--){
				num = num*j;
			}
		}
		return num;
	}

}
