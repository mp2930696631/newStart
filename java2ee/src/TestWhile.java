import java.util.*;

public class TestWhile{
	public static void main(String[] args){
		System.out.println("��ѡ����Ʒ��ţ�");
		System.out.println("1.T��\t2.����Ь\t3.������");
		System.out.println("****************************************************");
		Scanner scan = new Scanner(System.in);
		String flag = "y";
		double sum = 0;
		double thing1 = 245.0;
		double thing2 = 570.0;
		double thing3 = 320.0;
		while(flag.equals("y")){
			System.out.print("��������Ʒ��ţ�");
			int b = scan.nextInt();
			System.out.print("�����빺��������");
			int num = scan.nextInt();
			if(b==1){
				System.out.println("T�裤"+thing1+"\t"+"���� "+num+"�ϼ� ��"+thing1*num);
				sum += thing1*num;
				System.out.print("�Ƿ������y/n��");
				// ��֪��Ϊɶ��Ҫ����������仰����ſ�����������
				// scan.nextLine();
				flag = scan.nextLine();
			}else if(b==2){
				System.out.println("����Ь��"+thing2+"\t"+"���� "+num+"�ϼ� ��"+thing2*num);
				sum += thing2*num;
				System.out.println("�Ƿ������y/n��");
				// ��֪��Ϊɶ��Ҫ����������仰����ſ�����������
				// scan.nextLine();
				flag = scan.nextLine();
			}else{
				System.out.println("�����ģ�"+thing3+"\t"+"���� "+num+"�ϼ� ��"+thing3*num);
				sum += thing3*num;
				System.out.println("�Ƿ������y/n��");
				// ��֪��Ϊɶ��Ҫ����������仰����ſ�����������
				// scan.nextLine();
				flag = scan.nextLine();
			}
		}
		double discount = 0.8;
		
		System.out.println("�ۿۣ�"+discount);
		System.out.println("Ӧ����"+discount*sum);
		System.out.print("�븶Ǯ��");
		double actual = scan.nextDouble();
		while(actual<discount*sum){
			System.out.print("������Ľ��С��Ӧ��������������");
			actual = scan.nextDouble();
		}
		System.out.println("ʵ����"+actual);
		
		System.out.println("��Ǯ��"+(actual-discount*sum));
	}
}
