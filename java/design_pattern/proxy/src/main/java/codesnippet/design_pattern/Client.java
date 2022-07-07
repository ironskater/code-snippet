package codesnippet.design_pattern;

public class Client
{
	public static void
		main( String[] args )
	{
		IImage image = new RealImageProxy("test_img.jpg");

		// image will be loaded from disk
		image.display();
		System.out.println("");

		// image will not be loaded from disk
		image.display();
	}
}
