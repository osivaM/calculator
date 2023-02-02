import java.io.*;

class Main{
	public static void main(String args[]){
		BufferedReader us_inp = new BufferedReader(new InputStreamReader(System.in));
		String us_str;

		System.out.println("Enter \"stop\" to exit.");
		try{
			us_str = us_inp.readLine();
			while(!us_str.equals("stop")){
				System.out.println(calc(us_str));
				us_str = us_inp.readLine();
			}
		}
		catch(IOException exc){
			System.out.println(exc);
		}
	}

	public static String calc(String input) throws IOException{
		String arr[] = input.split(" ");
		int a = 0, b = 0;
		int result;
		boolean rom_num = false;
		
		if(arr.length != 3)
			throw new IOException();
		try{
			a = Integer.parseInt(arr[0]);
			b = Integer.parseInt(arr[2]);
		}
		catch(NumberFormatException exc){
			rom_num = true;
		}
		if(a > 10 || b > 10)
			throw new IOException();
		if(rom_num)
			if((a = r_to_a(arr[0])) == 0 || (b = r_to_a(arr[2])) == 0)
				throw new IOException();
		switch(arr[1]){
			case "+" :
				result = a + b;
				break;
			case "-" :
				result = a - b;
				break;
			case "*" :
				result = a * b;
				break;
			case "/" :
				result = (int)(a / b);
				break;
			default :
				throw  new IOException();
		}
		if(rom_num && result <= 0)
			throw new IOException();
		if(rom_num)
			return a_to_r(result);
		else
			return String.valueOf(result);
	}

	private static int r_to_a(String r_numb){
		String rom_arr[] = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

		for(int i = 0; i < 10; i++)
			if(r_numb.equals(rom_arr[i]))
				return i + 1;
		return 0;
	}

	private static String a_to_r(int numb){
		String rom_one[] = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		String rom_ten[] = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};

		if(numb == 100)
			return "C";
		if(numb > 10 && numb % 10 != 0)
			return rom_ten[(int)(numb / 10) - 1].concat(rom_one[(numb % 10) - 1]);
		else if(numb >= 10)
			return rom_ten[(int)(numb / 10) - 1];
		else
			return rom_one[numb - 1];			
	}
}