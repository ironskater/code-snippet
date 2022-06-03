package codesnippet.data_structure.trie;

import java.util.List;

public class App
{
	public static void
		main(String[] args)
	{
		List<String> words = List.of(
			"hello", "dog", "hell", "cat", "a", "hel","help","helps","helping",
			"陳時大", "陳時中", "陳時小", "蘇火球", "菜天皇");

		Trie trie = new Trie(words);

		System.out.println(trie.suggest("陳時"));
		System.out.println(trie.suggest("hel"));
	}
}
