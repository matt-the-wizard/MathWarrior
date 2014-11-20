package math.warrior.view.Swing;

import java.awt.event.*;
import javax.swing.*;

public class LoadGameForm extends JFrame{
	private MainMenu mainMenu;
	private JLabel usernameLabel,passwordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton logInButton, cancelButton;
	public void activate(){
		mainMenu.hide();
		usernameField.setText("");
		passwordField.setText("");
		logInButton.setEnabled(true);
		cancelButton.setEnabled(true);
		show();
	}
	
	private boolean checkUsernameExisted(String username){
		//DBMS IMPLEMENT
		return false;
		
	}
	private void logInButtonClicked(){
		if ((usernameField.getText().length()==0)||
				(passwordField.getText().length()==0)){
			JOptionPane.showMessageDialog(null,"Null field(s) are not allowed.","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (checkUsernameExisted(usernameField.getText())==false){
			JOptionPane.showMessageDialog(null,"This username doesn't exist.","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// password checking ... nneed DBMS implemment
		/*
		if (passwordField.getText().equals(.....)){
			JOptionPane.showMessageDialog(null,"Password is not correct. Try again!","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		*/
		
	}
	
	public LoadGameForm(MainMenu theMenu) {
		super("[MathWarrior] LOG-IN");
		this.mainMenu=theMenu;
		
		this.setSize(500, 400);
		this.setLocation(300, 100);
		this.setLayout(null);
		
		
		usernameLabel = new JLabel("Username:");
		usernameLabel.setSize(200, 30);
		usernameLabel.setLocation(20, 30);		
		
		usernameField = new JTextField();
		usernameField.setSize(200, 30);
		usernameField.setLocation(200, 30);
		
		

		passwordLabel= new JLabel("Password: ");
		passwordLabel.setSize(200, 30);
		passwordLabel.setLocation(20, 70);
		
		passwordField = new JPasswordField();
		passwordField.setSize(200, 30);
		passwordField.setLocation(200, 70);

		
		add(usernameLabel);
		add(usernameField);

		add(passwordLabel);
		add(passwordField);
		
		
		logInButton=new JButton("Log in");
		logInButton.setSize(150, 75);
		logInButton.setLocation(50, 200);
		
		cancelButton=new JButton("Cancel");
		cancelButton.setSize(100, 75);
		cancelButton.setLocation(220, 200);
		
		
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainMenu.activate();
			}
		});
		
		logInButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				logInButtonClicked();
			}
		});
		add(logInButton); add(cancelButton);
	}

}
