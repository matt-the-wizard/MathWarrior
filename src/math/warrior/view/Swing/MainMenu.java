/**
 * 
 */
package math.warrior.view.Swing;

import math.warrior.model.*;
import math.warrior.view.fx.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * @author QUAN TRAN
 *
 */

public class MainMenu extends JFrame
{

	private int countGameData(){
		int count=0;
		//NEEDING DBMS IMPLEMENT
		return count;
	}
	
	public void activate(){
		if (countComponents()>0) 
			loadGameButton.setEnabled(true);
		else
			loadGameButton.setEnabled(false);
		super.show();
		newGameForm.hide();
		loadGameForm.hide();
	}
	private GameUI UI;
	private NewGameForm newGameForm;
	private LoadGameForm loadGameForm;
	private JButton exitButton, 	newGameButton, 	loadGameButton,	helpButton;
	public MainMenu(){
		super();
		setTitle("[Matn Warrior] Main Menu  ~ Welcome ~");
		setSize(500, 400);
		setLocation(200, 200);
		
		newGameButton=new JButton("Create new game data");
		newGameButton.setSize(300, 60);
		newGameButton.setLocation(20, 30);

		loadGameButton=new JButton("Load an old game data");
		loadGameButton.setSize(300, 60);
		loadGameButton.setLocation(20, 100);
		

		helpButton = new JButton("Help");
		helpButton.setSize(100,50);
		helpButton.setLocation(20, 170);

		exitButton=new JButton("Exit");
		exitButton.setSize(100, 50);
		exitButton.setLocation(20, 300);

		setLayout(null);
		add(newGameButton);
		add(loadGameButton);
		add(helpButton);
		add(exitButton);
		
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		newGameForm = new NewGameForm(this);
		loadGameForm = new LoadGameForm(this);
		
		newGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newGameForm.activate();
			}
		});
		loadGameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadGameForm.activate();
			}
		});
	}
}
