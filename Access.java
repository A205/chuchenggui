package daythree.pktest;

public class Access {
	public Access(){
		say_1();
	}
	
	public void say_1(){
		System.out.println("���еķ������Է���");
		say_3();
	}
	private void say_2(){
		System.out.println("˽�е��޷��ڰ����������������ʹ��");
	}
	void say_3(){
		System.out.println("default����");
		say_2();
	}
}