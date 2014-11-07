import java.util.Set;
/**Class: GameItem.java
 * @author: Brock Bearchell
* @version 1.0
 * Date Written/Updated: Nov 6, 2014
 * Class Description:Game Puzzle that has a description, terminator, hint and solved message.
 * Business Class that sets up the data for a Game Puzzle. 
 */


public class GamePuzzle 
{
	//Attributes
		private String description;
		private String terminator;
		private boolean isSolved;
		private String hint;
		private String solvedMessage;
		
		
		/**Constructor
		 * @param description description of the game puzzle
		 * @param terminator...
		 * @param isSolved sets the puzzle to solved if solved
		 * @param hint a hint to solve the puzzle
		 * @param solvedMessage message displayed once puzzle is solved
		 */
		public GamePuzzle(String description, String terminator,
				String isSolved, String hint, String solvedMessage)
		{
			this.setDescription(description.trim());
			this.setTerminator(terminator.trim());
			this.setSolved(isSolved.trim());
			this.setHint(hint.trim());
			this.setSolvedMessage(solvedMessage.trim());
		}

		/**Method: getDesription
		 * Getter method for this instances's description
		 * @return the description
		 */
		public String getDescription()
		{
			return description;
		}

		/**Method: setDescription
		 * Setter method for this instances's description
		 * @return sets the description
		 */
		public void setDescription(String description)
		{
			this.description = description;
		}

		/**Method: getTerminator
		 * Getter method for this instances's terminator
		 * @return the terminator
		 */
		public String getTerminator()
		{
			return terminator;
		}

		/**Method: setTerminator
		 * Setter method for this instances's Terminator
		 * @return sets the Terminator
		 */
		public void setTerminator(String terminator)
		{
			this.terminator = terminator;
		}

		/**Method: isSolved
		 * Getter method for this instances's isSolved
		 * @return the true or false
		 */
		public boolean isSolved()
		{
			return isSolved;
		}

		/**Method: setSolved
		 * Setter method for this instances's isSolved
		 * @return sets the isSolved
		 */
		public void setSolved(boolean isSolved)
		{
			this.isSolved = isSolved;
		}
		
		/**Method: setSolved
		 * Setter method for this instances's isSolved
		 * @return sets the isSolved
		 */
		public void setSolved(String isSolved)
		{
			try
			{
				this.isSolved = Boolean.parseBoolean(isSolved);
			}
			catch(Exception e)
			{
				this.isSolved = false;
				System.out.println("The puzzle defaulted to false for being solved.");
			}
		}

		/**Method: getHint
		 * Getter method for this instances's hint
		 * @return the hint
		 */
		public String getHint()
		{
			return hint;
		}

		/**Method: setHint
		 * Setter method for this instances's hint
		 * @return sets the hint
		 */
		public void setHint(String hint)
		{
			this.hint = hint;
		}

		/**Method: getSolvedMessage
		 * Getter method for this instances's solvedMessage
		 * @return the solvedMessage
		 */
		public String getSolvedMessage()
		{
			return solvedMessage;
		}

		/**Method: setSolvedMessage
		 * Setter method for this instances's solvedMessage
		 * @return sets the solvedMessage
		 */
		public void setSolvedMessage(String solvedMessage)
		{
			this.solvedMessage = solvedMessage;
		}
		

		@Override
		public String toString()
		{
			return "Description:\t" + description + "\nTerminator:\t"
					+ terminator + "\nSolved:\t" + isSolved + "\nHint:\t" + hint
					+ "\nSolved Message:\t" + solvedMessage + "";
		}
		
		/**public static void main(String[] args)
		{
			GamePuzzle puzzle = new GamePuzzle("desc", "term", "batman", "hint", "smess");
			System.out.println(puzzle);
		}
		 **/
}
