package assignment;
import java.lang.*;
public class Assignment_Of_Day2 {
	
	public static void main(String[] arg){
		int[][] student_Grade=new int[20][5];
		String[] courseName={"coreC++","coreJava","Servlet  ","JSP   ","EJB"};
		//随机给数组赋值
		for(int i=0;i<20;i++)
		{
			for(int j=0;j<5;j++)
			{
				student_Grade[i][j]=(int)(Math.random()*100);
			}
		}
		//输出每个学员没门课程的成绩
		String Index=new String();
		for(int i=0;i<5;i++)
			Index=Index+courseName[i]+"  ";
		System.out.println(Index);
		for(int i=0;i<20;i++)
		{
			for(int j=0;j<5;j++)
			{   
				System.out.print(student_Grade[i][j]+"        ");
				
			}
			System.out.print('\t');
			System.out.print('\n');
		}
		
		//求总分和平均分
		int[] studentTotalGrade=new int[20];
		int[] studentAvrGrade =new int[5];
		for(int i=0;i<20;i++)
		{
			for(int j=0;j<5;j++)
			{
				studentTotalGrade[i]=studentTotalGrade[i]+student_Grade[i][j];
			}
		}
		System.out.println("Total Grade  ");
		for(int i=0;i<20;i++)
		{
			System.out.println(studentTotalGrade[i]);
		}
		int temp=0;
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<20;j++)
			{
				temp=temp+student_Grade[j][i];
			}
			studentAvrGrade[i]=(int)(temp/20);
		}
		System.out.println("Average Grade");
		for(int i=0;i<5;i++){
			System.out.println(studentAvrGrade[i]);
		}
	}

}
