package math.warrior.view.Swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NewGameForm extends JFrame{
	private MainMenu mainMenu;
	private JLabel usernameLabel,passwordLabel, confirmPasswordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField, confirmPasswordField;
	private JButton createButton, cancelButton;
	public void activate(){
		mainMenu.hide();
		usernameField.setText("");
		passwordField.setText("");
		confirmPasswordField.setText("");
		createButton.setEnabled(true);
		cancelButton.setEnabled(true);
		show();
	}
	
	private boolean checkUsernameExisted(String username){
		//DBMS IMPLEMENT
		return false;
		
	}
	private void createButtonClicked(){
		if ((usernameField.getText().length()==0)||
				(passwordField.getText().length()==0)
				||(confirmPasswordField.getText().length()==0)){
			JOptionPane.showMessageDialog(null,"Null field(s) are not allowed","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (checkUsernameExisted(usernameField.getText())==true){
			JOptionPane.showMessageDialog(null,"This username is already used, you can't use this username anymore.","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (passwordField.getText().equals(confirmPasswordField.getText())==false){
			JOptionPane.showMessageDialog(null,"Passwords you typed are not matched. Try again","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
	}
	
	public NewGameForm(MainMenu theMenu) {
		super("[MathWarrior] Create new game data");
		this.mainMenu=theMenu;
		
		this.setSize(500, 400);
		this.setLocation(300, 100);
		this.setLayout(null);
		
		
		usernameLabel = new JLabel("New username:");
		usernameLabel.setSize(200, 30);
		usernameLabel.setLocation(20, 30);		
		
		usernameField = new JTextField();
		usernameField.setSize(200, 30);
		usernameField.setLocation(200, 30);
		
		

		passwordLabel= new JLabel("New password: ");
		passwordLabel.setSize(200, 30);
		passwordLabel.setLocation(20, 70);
		
		passwordField = new JPasswordField();
		passwordField.setSize(200, 30);
		passwordField.setLocation(200, 70);

		
		confirmPasswordLabel= new JLabel("Confirm the password: ");
		confirmPasswordLabel.setSize(200, 30);
		confirmPasswordLabel.setLocation(20, 110);
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setSize(200, 30);
		confirmPasswordField.setLocation(200, 110);

		add(usernameLabel);
		add(usernameField);

		add(passwordLabel);
		add(passwordField);
		
		add(confirmPasswordLabel);
		add(confirmPasswordField);
		
		
		createButton=new JButton("Crate game data");
		createButton.setSize(150, 75);
		createButton.setLocation(50, 200);
		
		cancelButton=new JButton("Cancel");
		cancelButton.setSize(100, 75);
		cancelButton.setLocation(220, 200);
		
		
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainMenu.activate();
			}
		});
		createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createButtonClicked();
			}
		});
		add(createButton); add(cancelButton);
	}

}
