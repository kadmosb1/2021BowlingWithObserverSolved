package nl.hhs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Bowling {

	private final List<Lane> lanes;

	public Bowling() {
		this.lanes = new ArrayList<>();
		this.lanes.add(new Lane(1));
		this.lanes.add(new Lane(2));
		this.lanes.add(new Lane(3));
		this.lanes.add(new Lane(4));
	}

	public List<Lane> getLanes () {
		return lanes;
	}
}
