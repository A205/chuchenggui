package example;
import java.util.*;


public class Assignment_Of_Day1 {
	public static void main(String[] arg) {
		boolean flag=true;
		System.out.println("boolean变量flag值是："+flag);
		flag=false;
		System.out.println("boolean变量flag值是："+flag);
		//flag=0; 无法将0赋值给boolean变量
		System.out.println("boolean变量值是："+flag);

		//创建char变量
		char ch='e';
		System.out.println(ch);
		ch='中';
		System.out.println(ch);
		
		//创建各种数值变量
		byte bt=5;
		int  integer=5;
		float flo=5.0f;
		System.out.println("byte:5/2="+(bt/2));
		System.out.println("int:5/2="+(integer/2));
		System.out.println("float:5/2="+(flo/2));
		
		//操作菜单
		
		System.out.println("|1.增加一个学生|");
		System.out.println("|2.显示所有学生|");
		System.out.println("|3.退出程序|");
		System.out.println("|2.请输入选择（1-3）");
		Scanner in=new Scanner(System.in);
		int choose=in.nextInt();
		switch(choose){
		case 1:System.out.println("增加了一个学生");break;
		case 2:System.out.println("所有学生已被显示");break;
		case 3:System.out.println("程序已退出");break;
		default:System.out.println("错误的输入");break;
		}
	}

}
