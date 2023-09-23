/**
 * Add comments at the heading describing what the program does
 * as well as who authored it.
 * @Arogya
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Snowman {
	List<String> wordList = new ArrayList<String>();
	public void readWords(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));

		while (s.hasNext())
			wordList.add(s.next());

		s.close();
	}
	public String getWord() {
		// returns a random word from wordList

		Random r = new Random();

		return wordList.get(r.nextInt(wordList.size()));
	}

	public void playGame(String word) {
		char nextChoice;

		//SETS FOR STORING CORRECT WORD AND LETTERS USED
		Set <Character> correctWord = new ArraySet<Character>();
		Set<Character> lettersUsed = new ArraySet<Character>();

		char[] guess = new char[word.length()];
		
		for (int i = 0; i < guess.length; i++) { guess[i] = '_'; }

		//STORING ALL THE CORRECT CHARACTERS
		for (char character: word.toCharArray()) {
			correctWord.add(character);
		}

		Scanner reader = new Scanner(System.in);

		//COUNTING NUMBER OF INCORRECT GUESSES
		int incorrectGuesses = 0;
		
		while (true) {
			for (int i = 0; i < guess.length; i++) {
				System.out.print(" " + guess[i] + " ");
			}
			System.out.println();

			System.out.print("Your choice: ");
			nextChoice = reader.next().charAt(0);
			nextChoice = Character.toLowerCase(nextChoice);

			if(!Character.isAlphabetic(nextChoice)) continue; //CHECKS IF CHARACTER IS ALPHABET

			//CHECKING IF THE USER ALREADY GUESSED THE LETTER
			if(lettersUsed.contains(nextChoice)){
				System.out.println("You have already guessed the letter " + nextChoice);
				System.out.println();
				continue;
			}

			//IF IS NOT GUESSED...
			lettersUsed.add(nextChoice);

			if(correctWord.contains(nextChoice)){ //IF GUESSED CORRECT
				while (correctWord.contains(nextChoice)) { //CHECK FOR ALL THE OCCURRENCES
					correctWord.remove(nextChoice); //REMOVING FROM THE CORRECT WORD SET TO OVERCOME REDUNDANCY
					int i = 0;
					for (char character: word.toCharArray()) {
						if (character == nextChoice){
							guess[i] = character; //REPLACING DASHES WITH LETTERS
						}
						i++;
					}
				}
				System.out.println("Correct Guess!");
			}else { //IF NOT CORRECT
				System.out.println("Incorrect Guess!");
				incorrectGuesses++;
			}
			System.out.println( incorrectGuesses + " Incorrect Guesses");
			System.out.println();
			if(incorrectGuesses >= guess.length){ //GUESS N TIMES
				System.out.println("You Lost!");
				System.out.println("The word was " + word);
				break;
			}
			if(correctWord.getSize() == 0){ //IF NO CHARACTERS LEFT IN CORRECT WORD
				System.out.println("Congratulations, You Won!");
				break;
			}
		}
		
	}

	public static void main(String[] args) {
		Snowman game = new Snowman();
		try {
			game.readWords("words.txt");
			String word = game.getWord();
//			System.out.println("The word is '" + word + "'");
			game.playGame(word);
		} catch (FileNotFoundException fnf) {
			System.err.println("words.txt file Not Found");
		}
	}

}
