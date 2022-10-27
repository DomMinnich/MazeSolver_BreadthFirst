
//Updated to new 10/27/ 4:41pm
import javafx.application.Application; // 7
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
//Updated 10/27/2022 5:37pm R
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MazeGUI extends Application {
    public void start(Stage primaryStage) throws Exception {

        // Main Font
        Font font = Font.font("yugothic", FontWeight.BOLD, FontPosture.ITALIC, 10);
        Font font2 = Font.font("Monospaced", FontWeight.BOLD, FontPosture.REGULAR, 30);

        // Boxes
        HBox hb = new HBox();
        VBox vbTop = new VBox();
        VBox vbBottom = new VBox();
        VBox vb = new VBox();
        BackgroundFill background = new BackgroundFill(Color.LIGHTGREY, null, null);
        vb.setBackground(new Background(background));

        // Scrolling Pane
        Pane scrollBarPane = new Pane();
        ScrollPane scrollBar = new ScrollPane(scrollBarPane);
        scrollBar.setMaxSize(1100, 600);
        scrollBar.setMinSize(1100, 600);

        //////////////////// ComboBox ////////////////////
        Text mazeSelectionCBoxTitle = new Text("Maze Selection");
        Font font3 = Font.font("yugothic", FontWeight.BOLD, FontPosture.ITALIC, 17);
        mazeSelectionCBoxTitle.setFont(font3);
        mazeSelectionCBoxTitle.setFill(Color.BLUE);
        Text mazeSelectionText = new Text();
        mazeSelectionText.setFont(font);
        ComboBox<String> mazeSelectionCBox = new ComboBox<String>();
        mazeSelectionCBox.setMaxSize(120, 50);
        mazeSelectionCBox.setMinSize(120, 50);
        mazeSelectionCBox.getItems().addAll("1\'st Maze", "2\'nd Maze", "3\'rd Maze", "4\'th Maze", "5\'th Maze");
        mazeSelectionCBox.getSelectionModel().selectFirst();
        mazeSelectionCBox.setOnAction(e -> {

            // TODO: Retrieve the selected maze and display it after read in

        });

        //////////////////// Read Button ////////////////////
        Button readMazesBt = new Button("Read Memos");
        readMazesBt.setFont(font);
        readMazesBt.setMaxSize(120, 50);
        readMazesBt.setMinSize(120, 50);
        char[][] maze = {
                { '.', '.', '.', '0', '0', '0', '0', '0', '0', '0' },
                { '0', '0', '.', '.', '.', '0', '.', '.', '.', '0' },
                { '0', '0', '.', '0', '.', '0', '.', '0', '.', '0' },
                { '.', '.', '.', '0', '.', '0', '.', '0', '.', '0' },
                { '.', '0', '0', '0', '.', '.', '.', '0', '.', '0' },
                { '.', '.', '.', '.', '0', '0', '0', '.', '.', '0' },
                { '.', '0', '0', '.', '.', '.', '0', '.', '.', '0' },
                { '.', '.', '.', '0', '0', '.', '0', '0', '.', '.' },
                { '0', '0', '.', '0', '0', '.', '.', '.', '0', '0' },
                { '0', '0', '0', '0', '0', '0', '0', '.', '.', '.' },
        };
        EventHandler<ActionEvent> read = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {

                Label r = new Label();
                r.setTextFill(Color.BLUE);
                r.setFont(font2);
                r.setText("-----------------------\n");
                for (int x = 0; x < 10; x++) {
                    r.setText(r.getText() + "| ");
                    for (int y = 0; y < 10; y++) {
                        r.setText(r.getText() + maze[x][y] + " ");
                    }
                    r.setText(r.getText() + "|\n");
                }
                r.setText(r.getText() + "-----------------------");

                scrollBarPane.getChildren().add(r);

                // TODO: Read Mazes
                Text example = new Text(
                        "1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n612345678901234567890123456789012345678901234567891234567890123456789012345678901234567890123456789123456789012345678901234567890123456789012345678912345678901234567890123456789012345678901234567891234567890123456789012345678901234567890123456789123456789012345678901234567890123456789012345678912345678901234567890123456789012345678901234567891234567890123456789012345678901234567890123456789123456789012345678901234567890123456789012345678912345678901234567890123456789012345678901234567891234567890123456789012345678901234567890123456789123456789012345678901234567890123456789012345678912345678901234567890123456789012345678901234567891234567890123456789012345678901234567890123456789123456789012345678901234567890123456789012345678912345678901234567890123456789012345678901234567891234567890123456789012345678901234567890123456789123456789012345678901234567890123456789012345678912345678901234567890123456789012345678901234567891234567890123456789012345678901234567890123456789123456789012345678901234567890123456789012345678912345678901234567890123456789012345678901234567891234567890123456789012345678901234567890123456789123456789012345678901234567890123456789012345678912345678901234567890123456789012345678901234567891234567890123456789012345678901234567890123456789123456789012345678901234562345678901234567890123456789\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n1\n2\n3\n4\n5\n6\n");
                example.setFont(font);
                scrollBarPane.getChildren().add(example);

            }
        };

        readMazesBt.setOnAction(read);

        //////////////////// Find Path Single ////////////////////
        Button findPathSingleBt = new Button("Find Path (Current)");
        findPathSingleBt.setFont(font);
        findPathSingleBt.setMaxSize(120, 50);
        findPathSingleBt.setMinSize(120, 50);
        EventHandler<ActionEvent> findPathSingle = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                // TODO: Find path of the current single maze

            }
        };

        findPathSingleBt.setOnAction(findPathSingle);

        //////////////////// Find Path All ////////////////////
        Button findPathAllBt = new Button("Find Path (All)");
        findPathAllBt.setFont(font);
        findPathAllBt.setMaxSize(120, 50);
        findPathAllBt.setMinSize(120, 50);
        EventHandler<ActionEvent> findPathAll = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                // TODO: Find path of all the mazes

            }
        };

        findPathAllBt.setOnAction(findPathAll);

        //////////////////// Write Mazes ////////////////////
        Button writeMazesBt = new Button("Write Mazes");
        writeMazesBt.setFont(font);
        writeMazesBt.setMaxSize(120, 50);
        writeMazesBt.setMinSize(120, 50);
        EventHandler<ActionEvent> write = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                // TODO: Write Mazes to file

            }
        };

        writeMazesBt.setOnAction(write);

        /////////////////////////// Quit Button ///////////////////////////
        Button quitMemoBt = new Button("Quit");
        quitMemoBt.setFont(font);
        quitMemoBt.setMaxSize(120, 50);
        quitMemoBt.setMinSize(120, 50);
        quitMemoBt.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to close this application?",
                    ButtonType.YES, ButtonType.NO);
            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
            if (ButtonType.NO.equals(result)) {
                e.consume();
            } else {
                primaryStage.close();
            }
        });

        // Stage Configuration
        vbTop.getChildren().addAll(mazeSelectionCBoxTitle, mazeSelectionCBox);
        vbTop.setPadding(new Insets(20, 10, 250, 10));
        vbBottom.getChildren().addAll(readMazesBt, findPathSingleBt, findPathAllBt, writeMazesBt, quitMemoBt);
        vbBottom.setPadding(new Insets(0, 10, 0, 10));
        vb.getChildren().addAll(vbTop, vbBottom);
        hb.getChildren().addAll(scrollBar, vb);
        Scene sc = new Scene(
                hb);
        primaryStage.setScene(sc);
        primaryStage.setMaxHeight(640);
        primaryStage.setMaxWidth(1250);
        primaryStage.setMinHeight(640);
        primaryStage.setMinWidth(1250);
        primaryStage.show();
    }

}
