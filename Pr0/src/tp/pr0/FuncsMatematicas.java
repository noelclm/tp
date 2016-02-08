/*
	Practica 0: Primera aproximación a Java
    Copyright (C) 2015  Noel Clemente / Estefania Ortega

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
