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
			System.out.println("���Կ���ͨ��");
		}
		else {
			System.out.println("���Բ�����ͨ��");
		}		
		//��ʾ��������
		Scanner in = new Scanner(System.in);
		int score;
		System.out.println("���������");
		score = in.nextInt();
		System.out.println("����Ϊ��"+score);
		//�����ж�
		if(score<0||score>100)
			System.out.println("�������");
		else if(score<60)
			System.out.println("������");
		else if(score<80){
			System.out.println("�ȼ�C");
			
		}
		else if (score<90) {
			System.out.println("�ȼ�B");
		}
		else 
			System.out.println("�ȼ�A");
		
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
		//����switch��ö�ٱ���
		System.out.println("������һ�����֣�");
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
		//����ѭ��
		System.out.println("����ѭ��");
		int j=0;
		while(j<=10){
			System.out.println("����Ϊ��"+j);
			j++;
		}
		do{
			System.out.println("�ٴε�����Ϊ"+j);
			j--;
		}while(j>=0);
		
		//forѭ��
		for(int i=0;i<10;i++){
			System.out.println("Number is"+i);
		}
		//����for-each����
		int[] gar={1,2,3,4,7};
		for(int jar: gar)
			System.out.println(jar);
		System.out.println("//����break��continue");
		for(int i=0;i<40;i++){
			if(i%5==0)
			{
				System.out.println(i);
				continue;
			}
		}
		
		//������������
		System.out.println("���������ĸ���");
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
		
		//�˷���
		for(int ii=1;ii<=9;ii++)
		{
			for(int jj=1;jj<=ii;jj++)
				System.out.print(ii+"*"+jj+"="+ii*jj+"  ");
			System.out.print('\n');
		}
		
		//��ӡ������
		System.out.println("������������");
		int lineNum=in.nextInt();
		for(int ii=1;ii<=lineNum;ii++)
		{
			//��ӡ�ո�
			for(int jj=lineNum-ii;jj>0;jj--)
				System.out.print(" ");
			
			//��ӡ�Ǻ�
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
