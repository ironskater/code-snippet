package codesnippet.data_structure.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode
{
	private Map<Character, TrieNode> children;
	private char c;
	private boolean isEndOfWord;

	public TrieNode(char c)
	{
		this.c = c;
		children = new HashMap<>();
	}

	public TrieNode()
	{
		children = new HashMap<>();
	}

	public Map<Character, TrieNode>
		getChildren()
	{
		return this.children;
	}

	public boolean
		isEndOfWord()
	{
		return this.isEndOfWord;
	}

	public char
		getC()
	{
		return this.c;
	}

	public void
		setEndOfWord(boolean isEndOfWord)
	{
		this.isEndOfWord = isEndOfWord;
	}

	// IMPL1 : by recurssive
	public void
		insert(String word, int wordIdx)
	{
		if(word == null || word.isEmpty()) {
			return;
		}

		char firstChar = word.charAt(wordIdx++);
		TrieNode child = children.get(firstChar);

		if(child == null) {
			child = new TrieNode(firstChar);
			children.put(firstChar, child);
		}

		if(word.length() > wordIdx) {
			child.insert(	word,
							wordIdx);
		} else {
			child.isEndOfWord = true;
		}
	}
}
