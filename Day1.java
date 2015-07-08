package helloworld;

public class HelloWorld {
	static int num;
	public static void main(String[] args)
	{
		System.out.println("Hello World");
		System.out.println("Byte类型的位数为："+Byte.SIZE);
		System.out.println("Float类型的位数为："+Float.SIZE);
		char ch1 = 'A';
		char ch2 = 65;
		System.out.println(ch2);
		System.out.println(ch1);
		example();
	}
	public static void example()
	{
		HelloWorld.num = 30;
		System.out.println(HelloWorld.num);
		HelloWorld ex = new HelloWorld();
		System.out.println(ex.num);
		ex.kill();
		
		System.out.println("sdlkfjdlfjds");
	}
	public void kill()
	{
		System.out.println("实验成功！");
	}
}
