package wordCount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import wordCount.model.FileData;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("Welcome to the WordCounter! Please type the path of the file you want to count.");
		
		// Read string from file
		boolean fileRead = false;
		String fileString = null;
		do
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			String filePath = null;

			try
			{
				filePath = reader.readLine();
			} 
			catch ( IOException e)
			{
				System.out.println("An error occurred in getting the file please enter the file path again.");
			}

			if (filePath != null)
			{
				try
				{
					File file = new File(filePath);
					Scanner fileReader = new Scanner(file);

					while (fileReader.hasNextLine())
					{
						fileString = fileReader.nextLine();
					}
					fileReader.close();
					fileRead = true;
				} 
				catch (FileNotFoundException e)
				{
					System.out.println("An error occurred in getting the file please enter the file path again.");
				}
			}
		} 
		while (!fileRead);

		// Map the file to the file data object
		FileData fileData = FileDataMapper.map(fileString);

		// Output that object to screen
		System.out.println();
		System.out.println(String.format("Word count = %d", fileData.getWordCount()));
		System.out.println(String.format("Average word length = %.3f", fileData.getWordCountAverage()));

		fileData.getLengthMap().entrySet().forEach(entry -> 
		{
			System.out.println(String.format("Number of words of length %d is %d", entry.getKey(), entry.getValue()));
		});

		if (fileData.getLongestKey().size() > 1)
		{
			System.out.print(String.format("The most frequently occurring word length is %d, for word lengths of %d",
					fileData.getHighestValue(), fileData.getLongestKey().get(0)));
			for (int i = 1; i < fileData.getLongestKey().size(); i++)
			{
				System.out.print(String.format(" & %d", fileData.getLongestKey().get(i)));
			}
		} 
		else
		{
			System.out.print(String.format("The most frequently occurring word length is %d, for word length of %d",
					fileData.getHighestValue(), fileData.getLongestKey().get(0)));
		}
	}

}
