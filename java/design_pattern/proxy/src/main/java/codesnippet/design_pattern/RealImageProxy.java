package codesnippet.design_pattern;

public final class RealImageProxy implements IImage
{
	// The actual instance
	private RealImage realImage;
	private String fileName;

	public RealImageProxy(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void display() {
		// lazy-init
		if(realImage == null) {
			realImage = new RealImage(this.fileName);
		}

		// Perform additional logic and security
		//Even we can block the operation execution
		System.out.println("Delegating work on real object");

		realImage.display();
	}
}