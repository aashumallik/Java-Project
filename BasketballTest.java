package lab2;

public class BasketballTest {
	public static void main (String[] args) {
		Basketball b;
		Basketball b2;
		b = new Basketball (4.0);
		System.out.println(b.getDiameter());
		System.out.println(b.isDribbleable());
		b2 = new Basketball(6.0);
				b.inflate();
		System.out.println(b.isDribbleable());  // should be true now
		System.out.println(b2.isDribbleable()); // should still be false

		

	}

}
