package wordCount.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FileData 
{
	private int wordCount;
	private float wordCountAverage;
	private HashMap<Integer, Integer> lengthMap;
	private List<Integer> longestKey = Arrays.asList();
	private Integer highestValue = 0;
	
	public int getWordCount()
	{
		return wordCount;
	}
	
	public FileData setWordCount(int wordCount)
	{
		this.wordCount = wordCount;
		return this;
	}
	
	public float getWordCountAverage()
	{
		return wordCountAverage;
	}
	
	public FileData setWordCountAverage(float wordCountAverage)
	{
		this.wordCountAverage = wordCountAverage;
		return this;
	}
	
	public HashMap<Integer, Integer> getLengthMap()
	{
		return lengthMap;
	}
	
	public FileData setLengthMap(HashMap<Integer, Integer> lengthMap)
	{
		this.lengthMap = lengthMap;
		return this;
	}

	public List<Integer> getLongestKey()
	{
		return longestKey;
	}

	public FileData setLongestKey(List<Integer> longestKey)
	{
		this.longestKey = longestKey;
		return this;
	}

	public Integer getHighestValue()
	{
		return highestValue;
	}

	public FileData setHighestValue(Integer highestValue)
	{
		this.highestValue = highestValue;
		return this;
	}

}
