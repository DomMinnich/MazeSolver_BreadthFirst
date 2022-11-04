
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
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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

    private Maze maze;
    private File file;
    public static int mazeSelected = 1;
    public int mazeCount = 1;
    boolean solveAll = false;

    public static void setMazeSelected(int mazeSelected) {
        MazeGUI.mazeSelected = mazeSelected;
    }

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

        // Labels

        Label mazeNormalString = new Label("");
        // set text to maze.toString();
        mazeNormalString.setFont(font2);
        mazeNormalString.setTextFill(Color.CYAN);
        mazeNormalString.setTranslateX(200);
        mazeNormalString.setTranslateY(150);

        Label mazeSolvedString = new Label("");
        // set text to maze.toString(runner.pathTaken());
        mazeSolvedString.setFont(font2);
        mazeSolvedString.setTextFill(Color.YELLOW);
        mazeSolvedString.setTranslateX(200);
        mazeSolvedString.setTranslateY(150);

        Label mazeFailedString = new Label("");
        // set text to maze.toString();
        mazeFailedString.setFont(font2);
        mazeFailedString.setTextFill(Color.RED);
        mazeFailedString.setTranslateX(200);
        mazeFailedString.setTranslateY(150);

        Label titleNoSolution = new Label("And Has No Solution!");
        titleNoSolution.setFont(font2);
        titleNoSolution.setTextFill(Color.RED);
        titleNoSolution.setTranslateX(515);
        titleNoSolution.setTranslateY(30);

        Label titleHasSolution = new Label("And Is Solvable!");
        titleHasSolution.setFont(font2);
        titleHasSolution.setTextFill(Color.GREEN);
        titleHasSolution.setTranslateX(515);
        titleHasSolution.setTranslateY(30);

        Label titlePickAMaze = new Label("Pick A Solved Maze To View");
        titlePickAMaze.setFont(font2);
        titlePickAMaze.setTextFill(Color.YELLOW);
        titlePickAMaze.setTranslateX(270);
        titlePickAMaze.setTranslateY(50);

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
        Image image = new Image("giphy3.gif", 200, 100, false, false);
        BackgroundImage bg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background wallpaper = new Background(bg);

        Image image2 = new Image("black.png", 2300, 100, false, false);
        BackgroundImage bg2 = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background wallpaper2 = new Background(bg2);

        Image image3 = new Image("StartWP.gif", 1100, 600, false, false);
        BackgroundImage bg3 = new BackgroundImage(image3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background wallpaper3 = new Background(bg3);

        Image image4 = new Image("greyWP.png", 2000, 2000, false, false);
        BackgroundImage bg4 = new BackgroundImage(image4, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background wallpaper4 = new Background(bg4);

        Pane runningPacMan = new Pane();
        runningPacMan.setMinSize(400, 100);
        runningPacMan.setMaxSize(400, 100);
        runningPacMan.setBackground(wallpaper);
        runningPacMan.translateXProperty().set(-100);
        Pane blackPng = new Pane();
        blackPng.setMinSize(1700, 100);
        blackPng.setMaxSize(1700, 100);
        blackPng.setBackground(wallpaper2);
        Pane pacManMaze = new Pane();
        pacManMaze.setMaxSize(1100, 600);
        pacManMaze.setMinSize(1100, 600);
        pacManMaze.setBackground(wallpaper3);
        Pane greyBack = new Pane();
        greyBack.setMaxSize(2000, 2000);
        greyBack.setMinSize(2000, 2000);
        greyBack.setBackground(wallpaper4);
        Label sTLabel = new Label("| Maze 1 Selected");
        sTLabel.setFont(font2);
        sTLabel.setTextFill(Color.YELLOW);
        sTLabel.translateXProperty().set(190);
        sTLabel.translateYProperty().set(30);
        blackPng.getChildren().add(sTLabel);
        scrollBarPane.getChildren().addAll(greyBack, pacManMaze);

        // start
        Label st = new Label("Click \"Read Mazes\" To Find Your Maze File");
        st.setFont(font2);
        st.setTextFill(Color.YELLOW);
        st.setTranslateX(200);
        st.setTranslateY(50);
        scrollBarPane.getChildren().add(st);

        // For the scroll bars to appear at the start, I know it's not the best way to
        // do it but it works
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
        sl.setFill(Color.BLACK);
        scrollBarPane.getChildren().addAll(sl);

        //////////////////// Read Button ////////////////////
        Button readMazesBt = new Button("Read Mazes");
        readMazesBt.setStyle("-fx-text-fill: green");
        readMazesBt.setFont(font);
        readMazesBt.setMaxSize(120, 50);
        readMazesBt.setMinSize(120, 50);
        EventHandler<ActionEvent> read = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                scrollBarPane.getChildren().clear();
                scrollBarPane.getChildren().addAll(sl, greyBack);
                TextInputDialog numOfMazes = new TextInputDialog("5");
                numOfMazes.setTitle("Number of Mazes");
                numOfMazes.setHeaderText("Enter The Number of Mazes To Be Read In");
                numOfMazes.setContentText("Number of Mazes:");
                numOfMazes.showAndWait();
                // set mazeCount to the number of mazes to be read in
                setMazeCount(Integer.parseInt(numOfMazes.getEditor().getText()));
                try (Scanner fileInput = new Scanner(getFileFirstTime())) {
                    maze = new Maze(fileInput);
                    maze.setMaze(maze);
                    mazeNormalString.setText(maze.toString());
                    mazeNormalString.setTextFill(Color.CYAN);
                    scrollBarPane.getChildren().add(mazeNormalString);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                scrollBarPane.getChildren().addAll(blackPng, runningPacMan);
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
                scrollBarPane.getChildren().addAll(sl, greyBack, blackPng, runningPacMan);
                maze = maze.getMaze();
                BreadthFirstMazeRunner runner = new BreadthFirstMazeRunner(maze, maze.getStart(), maze.getFinish());
                boolean b = runner.runMaze();
                if (b == true) {
                    mazeSolvedString.setText(maze.toString((runner.pathTaken)));
                    mazeNormalString.setText(maze.toString());
                    mazeNormalString.setTextFill(Color.CYAN);
                    scrollBarPane.getChildren().addAll(mazeSolvedString, mazeNormalString, titleHasSolution);

                } else {
                    mazeFailedString.setText(maze.toString());
                    scrollBarPane.getChildren().addAll(mazeFailedString, titleNoSolution);
                }
            }
        };
        findPathSingleBt.setOnAction(findPathSingle);

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
                System.out.println("Error Maze Not Imported On Slot Selected");
                Alert a = new Alert(Alert.AlertType.ERROR, "Error Maze Not Imported On Slot Selected",
                        ButtonType.OK);
                a.showAndWait();
            } else {
                scrollBarPane.getChildren().clear();
                scrollBarPane.getChildren().addAll(sl, greyBack);
                sTLabel.setText("| Maze " + mazeSelected + " Selected");
                scrollBarPane.getChildren().addAll(blackPng, runningPacMan);
                try (Scanner fileInput = new Scanner(getFile())) {
                    maze = new Maze(fileInput);
                    maze.setMaze(maze);
                    BreadthFirstMazeRunner runner = new BreadthFirstMazeRunner(maze, maze.getStart(), maze.getFinish());
                    boolean b = runner.runMaze();
                    if (solveAll == true && b == true) {
                        mazeNormalString.setText(maze.toString());
                        mazeNormalString.setTextFill(Color.CYAN);
                        mazeSolvedString.setText(maze.toString(runner.pathTaken));
                        scrollBarPane.getChildren().addAll(mazeSolvedString, mazeNormalString, titleHasSolution);

                    } else if (solveAll == true && b == false) {
                        mazeFailedString.setText(maze.toString());
                        scrollBarPane.getChildren().addAll(mazeFailedString, titleNoSolution);

                    } else {
                        mazeNormalString.setText(maze.toString());
                        scrollBarPane.getChildren().add(mazeNormalString);
                    }

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
                scrollBarPane.getChildren().clear();
                scrollBarPane.getChildren().addAll(sl, greyBack, pacManMaze, titlePickAMaze);
            }
        };
        findPathAllBt.setOnAction(findPathAll);

        //////////////////// Write Mazes ////////////////////
        Button writeMazesBt = new Button("Write Paths");
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
                    int mazeSelected = mazeSelectionCBox.getSelectionModel().getSelectedIndex() + 1;
                    setMazeSelected(mazeSelected);
                    if (mazeSelected > mazeCount) {
                        // pop up error message
                        System.out.println("Error Maze Not Imported On Slot Selected");
                        Alert a = new Alert(Alert.AlertType.ERROR, "Error Maze Not Imported On Slot Selected",
                                ButtonType.OK);
                        a.showAndWait();

                    } else {
                        // System.out.println("maze selected is: " + mazeSelected);
                        Label printFinished = new Label("Printed All The Solved Paths To File!");
                        printFinished.setFont(font2);
                        printFinished.setTextFill(Color.YELLOW);
                        printFinished.setTranslateX(200);
                        printFinished.setTranslateY(50);
                        scrollBarPane.getChildren().clear();
                        scrollBarPane.getChildren().addAll(sl, greyBack, pacManMaze, printFinished);
                        for (int i = 0; i < mazeCount; i++) {
                            int m = getMazeSelected();
                            m = i + 1;
                            setMazeSelected(m);
                            try (Scanner fileInput = new Scanner(getFile())) {
                                maze = new Maze(fileInput);
                                maze.setMaze(maze);
                                BreadthFirstMazeRunner runner = new BreadthFirstMazeRunner(maze, maze.getStart(),
                                        maze.getFinish());
                                boolean b = runner.runMaze();
                                fOut.println(maze.getRows());
                                fOut.println(maze.getCols());
                                if (b == true) {
                                    fOut.println(maze.toString(runner.pathTaken));
                                } else {
                                    fOut.println(maze.toString());
                                }
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
        };
        writeMazesBt.setOnAction(write);

        // Stage Configuration
        vbTop.getChildren().addAll(mazeSelectionCBoxTitle, mazeSelectionCBox);
        vbTop.setPadding(new Insets(20, 10, 250, 10));
        vbBottom.getChildren().addAll(readMazesBt, findPathSingleBt, findPathAllBt, writeMazesBt, quitBt);
        vbBottom.setPadding(new Insets(0, 10, 0, 10));
        vb.getChildren().addAll(vbTop, vbBottom);
        hb.getChildren().addAll(scrollBar, vb);
        Scene sc = new Scene(hb);
        primaryStage.setScene(sc);
        primaryStage.setMaxHeight(640);
        primaryStage.setMaxWidth(1250);
        primaryStage.setMinHeight(640);
        primaryStage.setMinWidth(1250);
        primaryStage.show();
    }
}
