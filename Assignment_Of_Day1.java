package example;
import java.util.*;


public class Assignment_Of_Day1 {
	public static void main(String[] arg) {
		boolean flag=true;
		System.out.println("boolean����flagֵ�ǣ�"+flag);
		flag=false;
		System.out.println("boolean����flagֵ�ǣ�"+flag);
		//flag=0; �޷���0��ֵ��boolean����
		System.out.println("boolean����ֵ�ǣ�"+flag);

		//����char����
		char ch='e';
		System.out.println(ch);
		ch='��';
		System.out.println(ch);
		
		//����������ֵ����
		byte bt=5;
		int  integer=5;
		float flo=5.0f;
		System.out.println("byte:5/2="+(bt/2));
		System.out.println("int:5/2="+(integer/2));
		System.out.println("float:5/2="+(flo/2));
		
		//�����˵�
		
		System.out.println("|1.����һ��ѧ��|");
		System.out.println("|2.��ʾ����ѧ��|");
		System.out.println("|3.�˳�����|");
		System.out.println("|2.������ѡ��1-3��");
		Scanner in=new Scanner(System.in);
		int choose=in.nextInt();
		switch(choose){
		case 1:System.out.println("������һ��ѧ��");break;
		case 2:System.out.println("����ѧ���ѱ���ʾ");break;
		case 3:System.out.println("�������˳�");break;
		default:System.out.println("���������");break;
		}
	}

}
