package daythree;
//���������*��ʾ���������У����Ǹ�����Ҫȥ���롣
import daythree.boom.*;
public class Day3 {

	public static void main(String[] args){
		Phone p=new Phone();
		p.name="note";
		p.brand="���׻�";
		p.price=78;
		
		System.out.println("�ֻ�����  "+p.name);
		System.out.println("�ֻ�Ʒ�ƣ�"+p.brand);
		System.out.println("�ֻ��۸�"+p.price);
		p.sendMsg();
		
		
		//Person
		Person ps=new Person("zhangsan",56,"lunar Street");
		ps.introduce();
		
		//Fruit
		Fruit apple=new Fruit("ƻ��",13,"��������ũ��");
		apple.harvest();
		
		//��ʼ����
		Init example=new Init();
		
		//ʹ����һ�����е��ࣺ����.����
		daythree.pktest.PkTest table=new daythree.pktest.PkTest();
		
		//�������η�������
		daythree.pktest.Access acce=new daythree.pktest.Access();
		acce.say_1();
		
		//���ĵ���ʵ��
		Boom bo=new Boom();
		
		//��̳еķ�����д
		Tiger tig=new Tiger();
		tig.sleep("ſ��˯");
		System.out.println();
		
		//��̬��
		Animal a=new Animal();
		a.sleep("ſ��˯");
		Tiger t=new Tiger();
		t.sleep("ſ��˯");
		Animal g=new Tiger();
		g.sleep(g.name);
		System.out.println("��������ָ������ʵ�����ó��Ķ�������ԣ�"+g.name);
		g.sleep("diaozheshui");
		}
}

//Phone ��
class Phone{
	//����
	String name;
	String brand;
	int price;
	
	//��Ϊ
	void call(){
		System.out.println("��绰");
	}
	void sendMsg(){
		System.out.println("������");
		
	}
}

class Person{
	String name;
	int age;
	String address;
	
	void introduce(){
		System.out.println("��Һã�����"+name+"������"+age+"��"+"��ס��"+address);
	}
	Person(String name, int age){
		this.name=name;
		this.age=age;
	}
	//���췽�����޲���Ĭ�ϣ�
	Person (){
		System.out.println("���캯��������");
	}
	//�в������캯��
	Person(String nam,int ag, String add){
		this(nam,ag);
		address=add;
	}
}
//��ʾthisָ���ʹ��
class Fruit{
	String name;
	int price;
	String farmName;
	
	//���캯��
	Fruit(){
		
	}
	Fruit(String name,int price, String farmName){
		this.name=name;
		this.price=price;
		this.farmName=farmName;
	}
	void harvest(){
		System.out.println("�����ջ���599��ˮ��");
	}
}


//��ʾ��ʼ����
class Init{
	int num;
	Init(){
		num=5;
		System.out.println("���캯��������");
		
	}
	//��ʼ����
	{
		System.out.println("��ʼ���鱻����");
	}
}

//��̳еķ�����д
class Animal{
	String name="����";
	String sleepPose;
	Animal(){
		
	}
	Animal(String name){
		this.name = name;
	}
	public void sleep(String sleepPose){
		this.sleepPose=sleepPose;
		System.out.println(this.name+"��˯������˭Ҳ��֪��");
	}
}
class Tiger extends Animal
{
	String name="�ϻ�";
	String sleepPose;
	Tiger(){
		
	}
	Tiger(String name){
		this.name=name;
	}
	public void sleep(String sleepPose){
		this.sleepPose=sleepPose;
		System.out.println(this.name+"��"+sleepPose);
		//System.out.println(this.name);
	}
}


//������
//��̬�������ĳһ�������ڲ�ͬ���������в�ͬ����̬
abstract class Worker{
	String name;
	int id;
	public abstract void goToCmy();
	public abstract void goToHom();
	public abstract void getPayment();
}

class Singer extends Worker{
	public void goToCmy(){
		
	}
	public void goToHom(){
	
		
	}
	public void getPayment(){
		
	}
	
}