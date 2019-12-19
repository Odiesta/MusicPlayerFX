package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class SimpleMediaExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set up the audio
        Path audioPath = FileSystems.getDefault().getPath("ojamamushi.mp3");
        Media audioMedia = new Media(audioPath.toUri().toURL().toExternalForm());
        MediaPlayer audioPlayer = new MediaPlayer(audioMedia);

        String name = audioPath.getFileName().toString();
        Label playingStatus = new Label("Now Playing: ");

        // Set the play and stop button
        Button playButton = new Button("Play");
        playButton.setPrefWidth(60);
        Button stopButton = new Button("Stop");
        stopButton.setPrefWidth(60);

        playButton.setOnAction(e -> {
            if (audioPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                audioPlayer.stop();
                audioPlayer.play();
                playingStatus.setText("Now replaying: " + name);
            } else {
                audioPlayer.play();
                playingStatus.setText("Now playing: " + name);
            }
        });

        stopButton.setOnAction(e -> {
            audioPlayer.stop();
            playingStatus.setText("Audio stopped");
        });

        HBox buttonSection = new HBox(playButton, stopButton);
        buttonSection.setSpacing(10);
        buttonSection.setPadding(new Insets(15));

        VBox root = new VBox(playingStatus, buttonSection);
        root.setPadding(new Insets(50));

        primaryStage.setTitle("Simple Media Example");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
