package daythree;
//导入包，加*表示不导入所有，而是根据需要去导入。
import daythree.boom.*;
public class Day3 {

	public static void main(String[] args){
		Phone p=new Phone();
		p.name="note";
		p.brand="爆米花";
		p.price=78;
		
		System.out.println("手机名称  "+p.name);
		System.out.println("手机品牌："+p.brand);
		System.out.println("手机价格"+p.price);
		p.sendMsg();
		
		
		//Person
		Person ps=new Person("zhangsan",56,"lunar Street");
		ps.introduce();
		
		//Fruit
		Fruit apple=new Fruit("苹果",13,"兰州王宏农场");
		apple.harvest();
		
		//初始化块
		Init example=new Init();
		
		//使用另一个包中的类：包名.类名
		daythree.pktest.PkTest table=new daythree.pktest.PkTest();
		
		//访问修饰符的作用
		daythree.pktest.Access acce=new daythree.pktest.Access();
		acce.say_1();
		
		//包的导入实验
		Boom bo=new Boom();
		
		//类继承的方法重写
		Tiger tig=new Tiger();
		tig.sleep("趴着睡");
		System.out.println();
		
		//动态绑定
		Animal a=new Animal();
		a.sleep("趴着睡");
		Tiger t=new Tiger();
		t.sleep("趴着睡");
		Animal g=new Tiger();
		g.sleep(g.name);
		System.out.println("父类引用指向子类实例，得出的对象的属性："+g.name);
		g.sleep("diaozheshui");
		}
}

//Phone 类
class Phone{
	//属性
	String name;
	String brand;
	int price;
	
	//行为
	void call(){
		System.out.println("打电话");
	}
	void sendMsg(){
		System.out.println("发短信");
		
	}
}

class Person{
	String name;
	int age;
	String address;
	
	void introduce(){
		System.out.println("大家好，我是"+name+"，今年"+age+"岁"+"，住在"+address);
	}
	Person(String name, int age){
		this.name=name;
		this.age=age;
	}
	//构造方法（无参数默认）
	Person (){
		System.out.println("构造函数被调用");
	}
	//有参数构造函数
	Person(String nam,int ag, String add){
		this(nam,ag);
		address=add;
	}
}
//演示this指针的使用
class Fruit{
	String name;
	int price;
	String farmName;
	
	//构造函数
	Fruit(){
		
	}
	Fruit(String name,int price, String farmName){
		this.name=name;
		this.price=price;
		this.farmName=farmName;
	}
	void harvest(){
		System.out.println("今年收获了599吨水果");
	}
}


//演示初始化块
class Init{
	int num;
	Init(){
		num=5;
		System.out.println("构造函数被调用");
		
	}
	//初始化块
	{
		System.out.println("初始化块被调用");
	}
}

//类继承的方法重写
class Animal{
	String name="动物";
	String sleepPose;
	Animal(){
		
	}
	Animal(String name){
		this.name = name;
	}
	public void sleep(String sleepPose){
		this.sleepPose=sleepPose;
		System.out.println(this.name+"的睡觉姿势谁也不知道");
	}
}
class Tiger extends Animal
{
	String name="老虎";
	String sleepPose;
	Tiger(){
		
	}
	Tiger(String name){
		this.name=name;
	}
	public void sleep(String sleepPose){
		this.sleepPose=sleepPose;
		System.out.println(this.name+"是"+sleepPose);
		//System.out.println(this.name);
	}
}


//抽象类
//多态：父类的某一个类型在不同的子类中有不同的形态
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