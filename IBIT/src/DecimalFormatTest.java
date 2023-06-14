import java.text.DecimalFormat;  
   
public class DecimalFormatTest  
{  
   public static void main(String[] args)  
   { 
	   double swr=10.596;
	  DecimalFormat df1 = new DecimalFormat("0.##");  
		double newSWR=Double.parseDouble(df1.format(swr).toString());
		System.out.println(newSWR);
   }  
}  