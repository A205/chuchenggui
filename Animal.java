package daythree.pktest;
//抽象类使用abstract类修饰，只要含有抽象方法的类就是抽象类

public abstract class Animal {

	public abstract void sleep();
}


class Tiger extends Animal{
	public void sleep(){
		System.out.println("趴着睡。。。。。");
	}
}
class Monkey extends Animal{
	public void sleep(){
		System.out.println("躺着睡。。。。。。");
	}
}

//某个类是抽象类，但是其中可以没有抽象方法
//抽象类不能实例化
//final 不可以和abstract一起使用

//接口和抽象类的区别是：其所有方法默认为抽象方法
//可以被多重实现（implements），但Java中类不可以被多重继承