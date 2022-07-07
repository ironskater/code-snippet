package codesnippet.design_pattern;

public class RealImage implements IImage
{
	private String fileName;

	public RealImage(String fileName) {
		this.fileName = fileName;
		loadFromDisk(fileName);
	}

	@Override
	public void display() {
		System.out.println("Displaying " + this.fileName);
	}

	private void loadFromDisk(String fileName) {
		System.out.println("Loading " + this.fileName);
	}
}