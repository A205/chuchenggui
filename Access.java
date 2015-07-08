package daythree.pktest;

public class Access {
	public Access(){
		say_1();
	}
	
	public void say_1(){
		System.out.println("公有的方法可以访问");
		say_3();
	}
	private void say_2(){
		System.out.println("私有的无法在包外和其他非子类中使用");
	}
	void say_3(){
		System.out.println("default方法");
		say_2();
	}
}