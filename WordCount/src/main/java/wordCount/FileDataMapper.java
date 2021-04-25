package wordCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import wordCount.model.FileData;

public final class FileDataMapper
{
	/**
	 *  This mapper decides the rules of which words are defined
	 *  I have decided that any group of characters split by whitespace is a word if it contains a letter of the alphabet
	 *  or a number. There is also one special character that can be considered a word on its own which is the '&'
	 *  This does unfortunately mean that languages that do not use A-Z are not going to work with this program. 
	 *  But given the specification I deemed that not necessary and they could be added later if that was deemed important.
	 *  
	 *  For word length I'm also extracting out any punctuation not including apostrophes and also line endings
	 */
	
	public static FileData map(String data)
	{
		FileData fileData = new FileData();
		HashMap<Integer,Integer> mapOfLengths = new HashMap<Integer, Integer>();
		AtomicInteger total = new AtomicInteger(0);
		
		List<String> words = Arrays.asList(data.split("\\s+"));
		
		
		//formats each word to what I have defined a word
		List<String> formattedWords = words.stream()
				.parallel()
				.filter(word -> word.matches("^.*[a-zA-Z0-9&].*$"))
				.map(word -> word.replaceAll("[\"!?<>:;,.\\n]", ""))
				.collect(Collectors.toList());
		
		
		fileData.setWordCount(formattedWords.size());
		
		//Creates a map of length of word and the quantity of that length appearing
		formattedWords.forEach(word -> {
			int length = word.length();
			total.addAndGet(length);
			
			if(mapOfLengths.containsKey(length))
			{
				mapOfLengths.put(length, mapOfLengths.get(length) + 1);
			}
			else
			{
				mapOfLengths.put(length,1);
			}
		});
		
		fileData.setLengthMap(mapOfLengths);
		fileData.setWordCountAverage(total.floatValue()/fileData.getWordCount());
		
		List<Integer> longestKey = new ArrayList<>();
		int highestValue = 0;
		
		//This finds the length/lengths with the highest quantity
		for (Entry<Integer, Integer> entry : mapOfLengths.entrySet()) {
			if(entry.getValue() == highestValue)
			{
				longestKey.add(entry.getKey());
			}
			if(entry.getValue() > highestValue)
			{
				longestKey.clear();
				longestKey.add(entry.getKey());
				highestValue = entry.getValue();
			}
		};
		
		fileData.setHighestValue(highestValue);
		fileData.setLongestKey(longestKey);
		
		return fileData;
	}
}
