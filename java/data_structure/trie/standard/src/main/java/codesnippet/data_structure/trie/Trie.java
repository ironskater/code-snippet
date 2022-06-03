package codesnippet.data_structure.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. To show only the most searched matching strings as relevant results.
 * 2. Store another value for the each node where isleaf=True
 *    which contains the number of hits for that query search.
 *    For example if “hat” is searched 10 times, then we store this 10 at the last node for “hat”.
 * 	  Now when we want to show the recommendations, we display the top k matches with the highest hits.
 */
public class Trie
{
	private TrieNode root;

	public Trie()
	{
		this.root = new TrieNode();
	}

	public Trie(List<String> words)
	{
		this.root = new TrieNode();

		for(String word : words) {
			// this.root.insert(word, 0); // use IMPL1
			this.insert(word); //use IMPL2
		}
	}

	public boolean
		isExist(String prefix)
	{
		return isExist(prefix, false);
	}

	public boolean
		isExistExact(String prefix)
	{
		return this.isExist(prefix, true);
	}

	private boolean
		isExist(String prefix, boolean isExact)
	{
		TrieNode lastNode = this.root;

		for(char c : prefix.toCharArray()) {
			lastNode = lastNode.getChildren().get(c);
			if(lastNode == null) {
				return false;
			}
		}

		// isExact用來決定要不要考慮非end of word node
		return !isExact || lastNode.isEndOfWord();
	}

	public List<String>
		suggest(String prefix)
	{
		List<String> list = new ArrayList<>();
		TrieNode lastNode = root;
		StringBuffer curr = new StringBuffer();

		for(char c : prefix.toCharArray()) {
			lastNode = lastNode.getChildren().get(c);
			if(lastNode == null) {
				return list;
			}
			curr.append(c);
		}

		this.suggestHelper(lastNode, list, curr);

		return list;
	}

	private void
		suggestHelper(TrieNode node, List<String> list, StringBuffer curr)
	{
		if(node.isEndOfWord()) {
			list.add(curr.toString());
		}

		if(node.getChildren() == null || root.getChildren().isEmpty()) {
			return;
		}

		for(TrieNode child : node.getChildren().values())
		{
			this.suggestHelper(child, list, curr.append(child.getC()));
			curr.setLength(curr.length() - 1);
		}
	}

	public void
		insert(String word)
	{
		if(word == null || word.isEmpty()) {
			return;
		}

		// IMPL2
		TrieNode currentNode = this.root;

		for(char ch : word.toCharArray()) {
			currentNode = currentNode.getChildren().computeIfAbsent(
								ch,
								key -> new TrieNode(key));
		}

		currentNode.setEndOfWord(true);
	}

	public void
		delete(String word)
	{
		delete(this.root, word, 0);
	}

	// Supposed that trie has the following words:
	// 詞庫一：
	// 	gene
	// 	genetic
	// 詞庫二：
	//	genetic
	//	genea
	private boolean
		delete(TrieNode currentNode, String word, int wordIdx)
	{
		if(wordIdx == word.length()) {
			// ex: delete gen
			if(!currentNode.isEndOfWord()) {
				return false;
			}

			currentNode.setEndOfWord(false);

			// ex: gene最後一個e node的children就非empty
			// genetic最後一個c node的children就是empty
			return currentNode.getChildren().isEmpty();
		}

		char ch = word.charAt(wordIdx);
		TrieNode childNode = currentNode.getChildren().get(ch);
		if(childNode == null) {
			return false;
		}

		boolean shouldDeleteChildNode =
			this.delete(childNode, word, wordIdx + 1) // 判斷子node是否還有refer to 其它node
				&& !childNode.isEndOfWord(); // 子node也非end of word
				// 若無end of word的判斷，在刪除genetic時會連gene一起刪掉

		if(shouldDeleteChildNode) {
			currentNode.getChildren().remove(ch);
			return currentNode.getChildren().isEmpty();
		}

		return false;
	}
}
