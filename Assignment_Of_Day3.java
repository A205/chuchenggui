package assignment;
import java.util.Scanner;
public class Assignment_Of_Day3 {



	public static void main(String[] args){
		
		Army troop=new Army(10);
		
		//����������
		boolean flag=true;
		int command;//��������
		Scanner in= new Scanner(System.in);
		while(flag)
		{
			System.out.println("1.����һ��ս����"+"\n2.����һ��̹��"+"\n3.����һ��ս��"+"\n4.������������"+"\n5.���������ƶ�"+"\n6.��ɱ�������");
			System.out.println("Your command, sir!");
			command=in.nextInt();
			switch(command)
			{
			case 1: Flighter flight=new Flighter();troop.addWeapon(flight);break;
			case 2: Tank tank=new Tank();troop.addWeapon(tank);break;
			case 3: WarShip ship=new WarShip();troop.addWeapon(ship);break;
			case 4: troop.attackAll();break;
			case 5: troop.moveAll();break;
			case 6: flag = false;System.out.println("Mission complete!");break;
			default:System.out.println("No such command, sir!");break;
			}
			System.out.println();
		}
		
		
	}
}




//Assaultable �ӿ�
interface Assaultable{ 
	void attack();
}
//Mobile �ӿ�
interface Mobile{
	void move();
}
//Weapon������
abstract class Weapon implements Assaultable, Mobile{
	abstract public void attack();
	abstract public void move();
}

//
class Tank extends Weapon{
	Tank(){
		System.out.println("We get a tank!");
	}
	public void attack(){
		System.out.println("Tank begin to attack!");
	}
	public void move(){
		System.out.println("Tank begin to move.");
	}
}

class Flighter extends Weapon{
	Flighter(){
		System.out.println("We get a flighter!");
	}
	
	public void attack(){
		System.out.println("Flighter begin to attack!   �����ˡ�������");
	}
	public void move(){
		System.out.println("Flighter begin to move.");
	}
}

class WarShip extends Weapon{
	WarShip(){
		System.out.println("We get a warship!");
	}
	
	public void attack(){
		System.out.println("WarShip begin to attack!");
	}
	public void move(){
		System.out.println("Warship begin to move.");
	}
}