package codesnippet.data_structure;

public class Tokenizer
{
	public String[]
		getTokens(String content)
	{
		return content.split("\\s+|[()]");
	}
}
