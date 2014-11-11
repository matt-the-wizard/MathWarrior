/**
 * 
 */
package math.warrior.view;

import sun.applet.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 * @author QUAN TRAN
 *
 */
public class MainMenu extends Application 
{

	/**
	 * combobox  sele
	 */
	private Button exitButton, 	newGameButton, 	loadGameButton,	helpButton;
	private Scene mainMenuScene;
	private FlowPane mainMenuGroup;

	@Override
	public void start(Stage stage)
	{
		exitButton=new Button("Exit");
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				System.exit(0);
			}
		});
		this.helpButton = new Button("Help");
		helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0){
				
				Application.launch(Gam, arg1);
			}
		});
		mainMenuGroup=new FlowPane();
		mainMenuGroup.getChildren().add(helpButton);
		mainMenuGroup.getChildren().add(exitButton);

		mainMenuScene=new Scene(mainMenuGroup,700,600);{
			stage.setTitle("[Math Warrior]Main Menu");
			stage.setScene(mainMenuScene);
			stage.show();
		}

	}
}
