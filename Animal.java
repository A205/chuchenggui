package daythree.pktest;
//������ʹ��abstract�����Σ�ֻҪ���г��󷽷�������ǳ�����

public abstract class Animal {

	public abstract void sleep();
}


class Tiger extends Animal{
	public void sleep(){
		System.out.println("ſ��˯����������");
	}
}
class Monkey extends Animal{
	public void sleep(){
		System.out.println("����˯������������");
	}
}

//ĳ�����ǳ����࣬�������п���û�г��󷽷�
//�����಻��ʵ����
//final �����Ժ�abstractһ��ʹ��

//�ӿںͳ�����������ǣ������з���Ĭ��Ϊ���󷽷�
//���Ա�����ʵ�֣�implements������Java���಻���Ա����ؼ̳�