
import java.io.File;//1
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

    private Maze2 maze;
    private File file;
    public static int mazeSelected = 1;
    public int mazeCount = 1;
    boolean solveAll = false;

    // set mazeSelected method
    public static void setMazeSelected(int mazeSelected) {
        MazeGUI.mazeSelected = mazeSelected;

    }

    // get mazeSelected method
    public static int getMazeSelected() {
        return mazeSelected;
    }

    public void setMazeCount(int mazeCount) {
        this.mazeCount = mazeCount;
    }

    public int getMazeCount() {
        return mazeCount;
    }

    public File getFileFirstTime() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(null);
        setFile(file);
        return file;

    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

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

        // start
        Label st = new Label("Click \"Read Mazes\" To Find Your Maze File\n\n\t\tEnjoy!");
        st.setFont(font2);
        st.setTextFill(Color.GREEN);
        st.setTranslateX(200);
        st.setTranslateY(200);
        scrollBarPane.getChildren().add(st);

        // For the scroll bars to appear
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

        //////////////////// Read Button ////////////////////
        Button readMazesBt = new Button("Read Mazes");
        readMazesBt.setStyle("-fx-text-fill: green");
        readMazesBt.setFont(font);
        readMazesBt.setMaxSize(120, 50);
        readMazesBt.setMinSize(120, 50);
        EventHandler<ActionEvent> read = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                scrollBarPane.getChildren().clear();
                scrollBarPane.getChildren().add(sl);
                TextInputDialog numOfMazes = new TextInputDialog("5");
                numOfMazes.setTitle("Number of Mazes");
                numOfMazes.setHeaderText("Enter The Number of Mazes To Be Read In");
                numOfMazes.setContentText("Number of Mazes:");
                numOfMazes.showAndWait();
                // set mazeCount to the number of mazes to be read in
                setMazeCount(Integer.parseInt(numOfMazes.getEditor().getText()));

                try (Scanner fileInput = new Scanner(getFileFirstTime())) {
                    // maze = maze.getMaze();
                    maze = new Maze2(fileInput);
                    maze.setMaze(maze);

                    Label label2 = new Label(maze.toString());
                    label2.setFont(font2);
                    label2.setTextFill(Color.BLUE);
                    scrollBarPane.getChildren().add(label2);
                } catch (FileNotFoundException e1) {
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
                scrollBarPane.getChildren().clear();
                scrollBarPane.getChildren().add(sl);
                maze = maze.getMaze();
                BreadthFirstMazeRunner runner = new BreadthFirstMazeRunner(maze, maze.getStart(), maze.getFinish());
                runner.runMaze();
                System.out.println(runner.pathTaken.toString() + "setArr path");
                System.out.println(maze.toString(runner.pathTaken));

                Label label3 = new Label(maze.toString((runner.pathTaken)));
                label3.setFont(font2);
                label3.setTextFill(Color.BLUE);
                scrollBarPane.getChildren().add(label3);
                Image image = new Image(new FileInputStream());
                scrollBarPane.getChildren().add(new ImageView(image));

            }
        };
        findPathSingleBt.setOnAction(findPathSingle);

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
        Button quitBt = new Button("Quit");
        quitBt.setFont(font);
        quitBt.setMaxSize(120, 50);
        quitBt.setMinSize(120, 50);
        quitBt.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to close this application?",
                    ButtonType.YES, ButtonType.NO);
            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
            if (ButtonType.NO.equals(result)) {
                e.consume();
            } else {
                primaryStage.close();
            }
        });

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
            int mazeSelected = mazeSelectionCBox.getSelectionModel().getSelectedIndex() + 1;
            setMazeSelected(mazeSelected);
            if (mazeSelected > mazeCount) {
                // pop up error message
                System.out.println("error maze not imported on slot selected");
            } else {
                System.out.println("maze selected is: " + mazeSelected);
                scrollBarPane.getChildren().clear();
                scrollBarPane.getChildren().add(sl);
                try (Scanner fileInput = new Scanner(getFile())) {
                    maze = new Maze2(fileInput);
                    maze.setMaze(maze);
                    BreadthFirstMazeRunner runner = new BreadthFirstMazeRunner(maze, maze.getStart(), maze.getFinish());
                    runner.runMaze();
                    if (solveAll == true) {
                        Label label4 = new Label(maze.toString(runner.pathTaken));
                        label4.setFont(font2);
                        label4.setTextFill(Color.BLUE);
                        scrollBarPane.getChildren().add(label4);
                    }
                    Label label4 = new Label(maze.toString());
                    label4.setFont(font2);
                    label4.setTextFill(Color.BLUE);
                    scrollBarPane.getChildren().add(label4);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        //////////////////// Find Path All ////////////////////
        Button findPathAllBt = new Button("Find Path (All)");
        findPathAllBt.setFont(font);
        findPathAllBt.setMaxSize(120, 50);
        findPathAllBt.setMinSize(120, 50);
        EventHandler<ActionEvent> findPathAll = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                solveAll = true;
                findPathAllBt.setTextFill(Color.RED);
                mazeSelectionCBox.getSelectionModel().clearSelection();
                Label label5 = new Label("Pick A Solved Maze To View");
                label5.setFont(font2);
                label5.setTextFill(Color.BLUE);
                scrollBarPane.getChildren().clear();
                scrollBarPane.getChildren().add(sl);
                scrollBarPane.getChildren().add(label5);
            }
        };
        findPathAllBt.setOnAction(findPathAll);

        // Stage Configuration
        vbTop.getChildren().addAll(mazeSelectionCBoxTitle, mazeSelectionCBox);
        vbTop.setPadding(new Insets(20, 10, 250, 10));
        vbBottom.getChildren().addAll(readMazesBt, findPathSingleBt, findPathAllBt, writeMazesBt, quitBt);
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
