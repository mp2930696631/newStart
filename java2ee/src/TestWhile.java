import java.util.*;

public class TestWhile{
	public static void main(String[] args){
		System.out.println("请选择商品编号：");
		System.out.println("1.T需\t2.网球鞋\t3.网球拍");
		System.out.println("****************************************************");
		Scanner scan = new Scanner(System.in);
		String flag = "y";
		double sum = 0;
		double thing1 = 245.0;
		double thing2 = 570.0;
		double thing3 = 320.0;
		while(flag.equals("y")){
			System.out.print("请输入商品编号：");
			int b = scan.nextInt();
			System.out.print("请输入购买数量：");
			int num = scan.nextInt();
			if(b==1){
				System.out.println("T需￥"+thing1+"\t"+"数量 "+num+"合计 ￥"+thing1*num);
				sum += thing1*num;
				System.out.print("是否继续（y/n）");
				// 不知道为啥需要加上下面这句话程序才可以正常运行
				// scan.nextLine();
				flag = scan.nextLine();
			}else if(b==2){
				System.out.println("网球鞋￥"+thing2+"\t"+"数量 "+num+"合计 ￥"+thing2*num);
				sum += thing2*num;
				System.out.println("是否继续（y/n）");
				// 不知道为啥需要加上下面这句话程序才可以正常运行
				// scan.nextLine();
				flag = scan.nextLine();
			}else{
				System.out.println("网球拍￥"+thing3+"\t"+"数量 "+num+"合计 ￥"+thing3*num);
				sum += thing3*num;
				System.out.println("是否继续（y/n）");
				// 不知道为啥需要加上下面这句话程序才可以正常运行
				// scan.nextLine();
				flag = scan.nextLine();
			}
		}
		double discount = 0.8;
		
		System.out.println("折扣："+discount);
		System.out.println("应付："+discount*sum);
		System.out.print("请付钱：");
		double actual = scan.nextDouble();
		while(actual<discount*sum){
			System.out.print("你输入的金额小于应付金额，请重新输入");
			actual = scan.nextDouble();
		}
		System.out.println("实付："+actual);
		
		System.out.println("找钱："+(actual-discount*sum));
	}
}
