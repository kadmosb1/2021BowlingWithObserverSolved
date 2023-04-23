package nl.hhs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

class EventHandlerToggle implements EventHandler<ActionEvent> {

	private final Lane lane;
	private final Status status;

	public EventHandlerToggle (Lane lane, Status status) {
		this.lane = lane;
		this.status = status;
	}

	@Override
	public void handle (ActionEvent actionEvent) {
		this.lane.setOccupied (!this.lane.isOccupied ());
		this.status.draw ();
	}
}

class EventHandlerClose implements EventHandler<WindowEvent> {

	private final Bowling bowling;
	private final Status status;

	public EventHandlerClose (Bowling bowling, Status status) {
		this.bowling = bowling;
		this.status = status;
	}

	@Override
	public void handle (WindowEvent windowEvent) {

		for (Lane lane : bowling.getLanes ()) {
			lane.deleteObserver (this.status);
		}
	}
}

public class Status extends Stage implements IObserver
{
	private final Bowling bowling;
	private final Pane rootPane;

	public Status (Bowling bowling) {

		this.bowling = bowling;

		for (Lane lane : bowling.getLanes ()) {
			lane.addObserver (this);
		}

		rootPane = new Pane ();
		setTitle ("Status");
		rootPane.setMinSize (200, 200);
		draw ();
		Scene scene = new Scene (rootPane);
		setScene (scene);
		setOnCloseRequest (new EventHandlerClose (bowling, this));
		show ();
	}

	public void draw () {

		this.rootPane.getChildren ().clear ();
		VBox vBox = new VBox ();
		this.rootPane.getChildren ().add (vBox);

		for (Lane lane : bowling.getLanes ())
		{
			HBox hBox = new HBox ();
			vBox.getChildren ().add (hBox);
			hBox.getChildren ().add (new Label ("Lane " + lane.getNumber () + "\t"));
			hBox.getChildren ().add (new Label (lane.isOccupied () ? "occupied\t" : "free\t"));
			Button button = new Button ("Toggle");
			button.setOnAction (new EventHandlerToggle (lane, this));
			hBox.getChildren ().add (button);
		}
	}

	@Override
	public void update (Observable o, Object arg) {
		draw ();
	}
}