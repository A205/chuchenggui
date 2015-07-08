package daytwo;
import java.util.*;




public class Day2 {
	//public static final int constant1=1;
	//public static final int constant2=2;
	public enum Date{
		Mon,
		Tue,
		Wed,
		Tur,
		Fri,
		Sat,
		Sun
	}
	public static void main(String[] arg) {
		boolean start=true;
		if(start)
		{
			System.out.println("考试可以通过");
		}
		else {
			System.out.println("考试不可以通过");
		}		
		//演示键盘输入
		Scanner in = new Scanner(System.in);
		int score;
		System.out.println("请输入分数");
		score = in.nextInt();
		System.out.println("分数为："+score);
		//多重判断
		if(score<0||score>100)
			System.out.println("输入错误");
		else if(score<60)
			System.out.println("不及格");
		else if(score<80){
			System.out.println("等级C");
			
		}
		else if (score<90) {
			System.out.println("等级B");
		}
		else 
			System.out.println("等级A");
		
		//switch
		System.out.println("Please input a nmber:");
		int day=in.nextInt();
		String dateString="Today is ";
		switch(day){
		case 1:dateString +="Monday";break;
		case 2:dateString +="Tuesday";break;
		case 3:dateString +="Wednesday";break;
		case 4:dateString +="Thursday";break;
		case 5:dateString +="Friday";break;
		case 6:dateString +="Saturday";break;
		case 7:dateString +="Sunday";break;
		default: dateString = "Wrong Input";break;
		}
		System.out.println(dateString);
		//测试switch和枚举变量
		System.out.println("请输入一个数字：");
		int sign=in.nextInt();
		
		Date date=Date.Mon;
		switch(sign){
		case 1:date=Date.Mon;break;
		case 2:date=Date.Tue;break;
		case 3:date=Date.Wed;break;
		case 4:date=Date.Tur;break;
		case 5:date=Date.Fri;break;
		case 6:date=Date.Sat;break;
		case 7:date=Date.Sun;break;
		default: System.out.println("Wrong input");break;
		}
		System.out.println(date);
		//测试循环
		System.out.println("测试循环");
		int j=0;
		while(j<=10){
			System.out.println("数字为："+j);
			j++;
		}
		do{
			System.out.println("再次的数字为"+j);
			j--;
		}while(j>=0);
		
		//for循环
		for(int i=0;i<10;i++){
			System.out.println("Number is"+i);
		}
		//测试for-each类型
		int[] gar={1,2,3,4,7};
		for(int jar: gar)
			System.out.println(jar);
		System.out.println("//测试break和continue");
		for(int i=0;i<40;i++){
			if(i%5==0)
			{
				System.out.println(i);
				continue;
			}
		}
		
		//计算质数个数
		System.out.println("计算质数的个数");
		int i=2;
		
		while(i<=100){
			for(int jin=2;jin<i;jin++)
			{
				if(i%jin==0)
					break;
				if(i%jin!=0&&jin==i-1)
					System.out.println(i);
				
			}
			++i;
		}
		
		//乘法表
		for(int ii=1;ii<=9;ii++)
		{
			for(int jj=1;jj<=ii;jj++)
				System.out.print(ii+"*"+jj+"="+ii*jj+"  ");
			System.out.print('\n');
		}
		
		//打印三角形
		System.out.println("请输入行数：");
		int lineNum=in.nextInt();
		for(int ii=1;ii<=lineNum;ii++)
		{
			//打印空格
			for(int jj=lineNum-ii;jj>0;jj--)
				System.out.print(" ");
			
			//打印星号
			for(int jj=1;jj<=(2*ii-1);jj++)
				System.out.print("*");
			System.out.print('\n');
		}
	}
	private static int nextInt() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
