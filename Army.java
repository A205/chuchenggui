package assignment;

public class Army {
	
	//��Ա����
	static int amount;
	int count=0;
	Weapon[] w;
	
	//���췽��
	Army(int amount)
	{
		this.amount=amount;
		this.w= new Weapon[amount];
		System.out.println("Our army can take "+amount+" weapons!");
	}
	Army(){}
	
	//��������
	//�������
	void addWeapon(Weapon wa)
	{
		if(count<amount)
		{
			w[count]=wa;
			count +=1;
		}
		else
			System.out.println("�������������޷������������");
	}
	
	//ʹ�������������й���
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
	
	//���������ƶ�
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
