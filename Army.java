package assignment;

public class Army {
	
	//成员变量
	static int amount;
	int count=0;
	Weapon[] w;
	
	//构造方法
	Army(int amount)
	{
		this.amount=amount;
		this.w= new Weapon[amount];
		System.out.println("Our army can take "+amount+" weapons!");
	}
	Army(){}
	
	//其他方法
	//添加武器
	void addWeapon(Weapon wa)
	{
		if(count<amount)
		{
			w[count]=wa;
			count +=1;
		}
		else
			System.out.println("武器库已满，无法再添加武器！");
	}
	
	//使用所有武器进行攻击
	void attackAll()
	{
		if(count==0)
			System.out.println("We have no Weapon for attacking, sir!");
		else
		{
			for(int i=0;i<count;i++)
				w[i].attack();
		}
	}
	
	//所有武器移动
	void moveAll()
	{
		if(count==0)
			System.out.println("We have no weapon to move, sir");
		else
		{
			for(int i=0;i<count;i++)
				w[i].move();
		}
	}
}
