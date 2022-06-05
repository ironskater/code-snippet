package codesnippet.data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Index
{
	private Map<Integer, String> docById;
	private Map<String, HashSet<Integer>> invertedIndex;
	private Tokenizer tokenizer = new Tokenizer();

	public Index()
	{
		docById = new HashMap<Integer,String>();
		invertedIndex = new HashMap<String, HashSet<Integer>>();
	}

	public void
		buildIndex(Path[] pathes)
	{
		int ix = 0;

		for(Path path : pathes) {
			String fileName = path.getFileName().toString();

			try(BufferedReader file = Files.newBufferedReader(path))
			{
				docById.put(ix, fileName);

				String ln;

				while((ln = file.readLine()) != null) {

					String[] words = tokenizer.getTokens(ln);

					for(String word : words) {
						HashSet<Integer> value =
							invertedIndex.computeIfAbsent(
								word,
								key -> new HashSet<>());

						value.add(ix);
					}
				}
				ix++;
			} catch (IOException e) {
				System.out.println("File " + fileName +" not found. Skip it");
			}
		}

	}

	public void
		find(String keyword)
	{
		HashSet<Integer> res = invertedIndex.get(keyword);

		if(res == null || res.isEmpty()) {
			System.out.println("Not found");
			return;
		}

		System.out.println("Found in: ");

		for(int num : res) {
			System.out.println("\t" + docById.get(num));
		}
	}
}