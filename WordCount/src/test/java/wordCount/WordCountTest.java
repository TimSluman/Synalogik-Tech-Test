package wordCount;


import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.unitils.reflectionassert.ReflectionAssert;

import wordCount.model.FileData;

public class WordCountTest
{	
	@ParameterizedTest
	@MethodSource("wordTest")
	public void wordTest(final String sentence, final FileData expected)
	{
		FileData actual = FileDataMapper.map(sentence);
		ReflectionAssert.assertReflectionEquals("File Data did not match expected",expected, actual);
	}
	
	@SuppressWarnings({ "unused" })
	private static Stream<Arguments> wordTest()
	{
		return Stream.of(
				Arguments.of("This is a\" test sentencee!!",new FileData()
						.setHighestValue(2)
						.setLongestKey(Arrays.asList(4))
						.setWordCount(5)
						.setWordCountAverage(4.0f)
						.setLengthMap(new HashMap<Integer,Integer>(){{
					        put(1, 1);
					        put(2, 1);
					        put(4, 2);
					        put(9, 1);
					    }})),
				Arguments.of("This is !! a test sentencee \n nice",new FileData()
						.setHighestValue(3)
						.setLongestKey(Arrays.asList(4))
						.setWordCount(6)
						.setWordCountAverage(4.0f)
						.setLengthMap(new HashMap<Integer,Integer>(){{
					        put(1, 1);
					        put(2, 1);
					        put(4, 3);
					        put(9, 1);
					    }})),
				Arguments.of("T s !! a t s \n n &",new FileData()
						.setHighestValue(7)
						.setLongestKey(Arrays.asList(1))
						.setWordCount(7)
						.setWordCountAverage(1.0f)
						.setLengthMap(new HashMap<Integer,Integer>(){{
					        put(1, 7);
					    }})),
				Arguments.of("07/14 is the date",new FileData()
						.setHighestValue(1)
						.setLongestKey(Arrays.asList(2,3,4,5))
						.setWordCount(4)
						.setWordCountAverage(3.5f)
						.setLengthMap(new HashMap<Integer,Integer>(){{
					        put(2, 1);
					        put(3, 1);
					        put(4, 1);
					        put(5, 1);
					    }})),
				Arguments.of("(this) should be 4 letters long",new FileData()
						.setHighestValue(2)
						.setLongestKey(Arrays.asList(4))
						.setWordCount(6)
						.setWordCountAverage(4.0f)
						.setLengthMap(new HashMap<Integer,Integer>(){{
					        put(1, 1);
					        put(2, 1);
					        put(4, 2);
					        put(6, 1);
					        put(7, 1);
					    }})),
				Arguments.of("[this] should be 4 letters long",new FileData()
						.setHighestValue(2)
						.setLongestKey(Arrays.asList(4))
						.setWordCount(6)
						.setWordCountAverage(4.0f)
						.setLengthMap(new HashMap<Integer,Integer>(){{
					        put(1, 1);
					        put(2, 1);
					        put(4, 2);
					        put(6, 1);
					        put(7, 1);
					    }})));
				
	}
}
