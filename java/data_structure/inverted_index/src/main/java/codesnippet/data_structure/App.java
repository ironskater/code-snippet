package codesnippet.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * based on https://github.com/RomanHotsiy/LearningJava/tree/master/Data%20Structures
 */
public class App
{
	public static void
		main(String[] args) throws IOException
	{
		Index index = new Index();

		index.buildIndex(new Path[] {
			Paths.get("src/main/resources/testfile0.txt"),
			Paths.get("src/main/resources/testfile1.txt"),
			Paths.get("src/main/resources/testfile2.txt")
		});

		System.out.println("Print search phrase: ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String phrase = in.readLine();

		index.find(phrase);
	}
}
