package views;

import javax.swing.Scrollable;

import Model.Player;
import Model.Track;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class PlaylistController  {
	
//	private Player player;
	public Pane root;
	public ObservableList<> oList;
	public ListView<Track> lView;

	public PlaylistController(Player player) {
//		this.player=player;
		this.oList=FXCollections.observableArrayList();
		oList.clear();
		oList.addAll(player.currPlaylist.get().getTracks());
		this.lView=new ListView<Track>();
		lView.setItems(oList);
		lView.setEditable(true);
		lView.autosize();
		
		lView.setCellFactory(
				new Callback<ListView<Track>, ListCell<Track>>(){

					@Override
					public ListCell<Track> call(ListView<Track> param) {
						return new TrackCell();
					}
					
				});
		root=new Pane();
		
		root.getStyleClass().add(getClass().getResource("Playlist.css").toExternalForm());
		lView.getStyleClass().add("list");
		root.getChildren().add(lView);
//		lView.heightProperty().bind(root.heightProperty());
		
	}
	
	public Node getView() {
		return lView;
	}
	
	public void initialize() {
		oList.addListener(new ListChangeListener<Track>() {
			public void onChanged(ListChangeListener.Change<? extends Track> change) {
				while(change.next()) {
					if(change.wasPermutated()) {
						for(int oldIndex=change.getFrom();
								oldIndex<change.getTo();++oldIndex) {
							int newIndex=change.getPermutation(oldIndex);
							
						}
					} else if(change.wasUpdated()) {
						
					} else {
						for (Track item:change.getRemoved()) {
							
						}
						for (Track item:change.getAddedSubList()) {
							
						}
					}
				}
			}
		});
		
	}

}
