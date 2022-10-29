
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.application.Application;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
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
        Text sl = new Text(
                "------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------" +
                        "------------------------------------------------------------------------------" +
                        "---------------------\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|" +
                        "\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|" +
                        "\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|" +
                        "\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|\n|");
        sl.setFont(font);
        sl.setFill(Color.WHITE);
        scrollBarPane.getChildren().add(sl);

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
        mazeSelectionCBox.getItems().addAll("1\'st Maze", "2\'nd Maze", "3\'rd Maze", "4\'th Maze", "5\'th Maze",
                "6\'th Maze", "7\'th Maze", "8\'th Maze", "9\'th Maze", "10\'th Maze");
        mazeSelectionCBox.getSelectionModel().selectFirst();
        mazeSelectionCBox.setOnAction(e -> {
            // TODO: Retrieve the selected maze and display it after read in
        });

        //////////////////// Read Button ////////////////////
        Button readMazesBt = new Button("Read Memos");
        readMazesBt.setFont(font);
        readMazesBt.setMaxSize(120, 50);
        readMazesBt.setMinSize(120, 50);
        EventHandler<ActionEvent> read = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                TextInputDialog numOfMazes = new TextInputDialog("5");
                numOfMazes.setTitle("Number of Mazes");
                numOfMazes.setHeaderText("Enter The Number of Mazes To Be Read In");
                numOfMazes.setContentText("Number of Mazes:");
                numOfMazes.showAndWait();
                // right now broken because maze variable is null (scanner)
                // Maze maze = new Maze(null);
                // int mazesAmount = Integer.parseInt(numOfMazes.getResult());
                // maze.setNumMazes(mazesAmount);

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save File");
                // fileChooser.setInitialFileName(fName);
                File file = fileChooser.showOpenDialog(null);
                try (Scanner fileInput = new Scanner(file)) {
                    Maze maze = new Maze(fileInput);
                    System.out.println(maze.toStringAll());
                    Label label2 = new Label(maze.toStringAll());
                    label2.setFont(font2);
                    label2.setTextFill(Color.BLUE);
                    scrollBarPane.getChildren().add(label2);
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
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
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save File");
                // fileChooser.setInitialFileName(fName);
                File file = fileChooser.showSaveDialog(null);
                try (PrintWriter fOut = new PrintWriter(file)) {
                    // Also print the row and column numbers of the maze
                    // fOut.print(maze.toStringAll());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
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
