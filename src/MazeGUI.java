
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

        Pane p = new Pane();
        p.setMinSize(400, 100);
        p.setMaxSize(400, 100);
        p.setBackground(wallpaper);
        p.translateXProperty().set(-100);
        Pane p2 = new Pane();
        p2.setMinSize(1700, 100);
        p2.setMaxSize(1700, 100);
        p2.setBackground(wallpaper2);
        Pane p3 = new Pane();
        p3.setMaxSize(1100, 600);
        p3.setMinSize(1100, 600);
        p3.setBackground(wallpaper3);
        Pane p4 = new Pane();
        p4.setMaxSize(2000, 2000);
        p4.setMinSize(2000, 2000);
        p4.setBackground(wallpaper4);

        Label sTLabel = new Label("| Maze 1 Selected");
        sTLabel.setFont(font2);
        sTLabel.setTextFill(Color.YELLOW);
        sTLabel.translateXProperty().set(190);
        sTLabel.translateYProperty().set(30);
        p2.getChildren().add(sTLabel);


        scrollBarPane.getChildren().add(p4);
        scrollBarPane.getChildren().add(p3);
        // start
        Label st = new Label("Click \"Read Mazes\" To Find Your Maze File");
        st.setFont(font2);
        st.setTextFill(Color.YELLOW);
        st.setTranslateX(200);
        st.setTranslateY(50);
        scrollBarPane.getChildren().add(st);

        // For the scroll bars to appear, I know it's not the best way to do it but it
        // works
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
                scrollBarPane.getChildren().addAll(sl, p4);
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
                    Label label2 = new Label(maze.toString());
                    label2.setFont(font2);
                    label2.setTextFill(Color.CYAN);
                    label2.setTranslateX(200);
                    label2.setTranslateY(150);
                    scrollBarPane.getChildren().add(label2);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                scrollBarPane.getChildren().addAll(p2, p);
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
                scrollBarPane.getChildren().addAll(sl, p4);
                scrollBarPane.getChildren().addAll(p2, p);
                Label sLabel = new Label("And Is Solvable!");
                sLabel.setFont(font2);
                sLabel.setTranslateX(515);
                sLabel.setTranslateY(30);
                sLabel.setTextFill(Color.GREEN);
                scrollBarPane.getChildren().add(sLabel);


                maze = maze.getMaze();
                BreadthFirstMazeRunner runner = new BreadthFirstMazeRunner(maze, maze.getStart(), maze.getFinish());
                boolean b = runner.runMaze();
                if (b == true) {
                    Label label3 = new Label(maze.toString((runner.pathTaken)));
                    label3.setFont(font2);
                    label3.setTextFill(Color.YELLOW);
                    label3.setTranslateX(200);
                    label3.setTranslateY(150);
                    Label label33 = new Label(maze.toString());
                    label33.setFont(font2);
                    label33.setTextFill(Color.CYAN);
                    label33.setTranslateX(200);
                    label33.setTranslateY(150);

                    scrollBarPane.getChildren().addAll(label3, label33);

                } else {
                    Label label3 = new Label(maze.toString());
                    label3.setFont(font2);
                    label3.setTextFill(Color.RED);
                    label3.setTranslateX(200);
                    label3.setTranslateY(150);
                    scrollBarPane.getChildren().add(label3);

                    Label label5 = new Label("Maze Has No Solution Read single");
                    label5.setFont(font2);
                    label5.setTextFill(Color.RED);
                    label5.setTranslateX(200);
                    scrollBarPane.getChildren().add(label5);
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
                scrollBarPane.getChildren().addAll(sl, p4);
                sTLabel.setText("| Maze " + mazeSelected + " Selected");
                scrollBarPane.getChildren().addAll(p2, p);
                try (Scanner fileInput = new Scanner(getFile())) {
                    maze = new Maze(fileInput);
                    maze.setMaze(maze);
                    BreadthFirstMazeRunner runner = new BreadthFirstMazeRunner(maze, maze.getStart(), maze.getFinish());
                    boolean b = runner.runMaze();
                    if (solveAll == true && b == true) {
                        Label label4 = new Label(maze.toString(runner.pathTaken));
                        label4.setFont(font2);
                        label4.setTranslateX(200);
                        label4.setTranslateY(150);
                        label4.setTextFill(Color.YELLOW);
                        Label label44 = new Label(maze.toString());
                        label44.setFont(font2);
                        label44.setTranslateX(200);
                        label44.setTranslateY(150);
                        label44.setTextFill(Color.CYAN);

                        Label sLabel = new Label("And Is Solvable!");
                        sLabel.setFont(font2);
                        sLabel.setTranslateX(515);
                        sLabel.setTranslateY(30);
                        sLabel.setTextFill(Color.GREEN);
                        scrollBarPane.getChildren().addAll(label4, label44, sLabel);
                    } else {
                        Label label4 = new Label(maze.toString());
                        label4.setFont(font2);
                        label4.setTextFill(Color.CYAN);
                        label4.setTranslateX(200);
                        label4.setTranslateY(150);
                        scrollBarPane.getChildren().add(label4);
                        if (b == false) {
                            Label label5 = new Label("And Has No Solution!");
                            label5.setFont(font2);
                            label5.setTextFill(Color.RED);
                            label5.setTranslateX(515);
                            label5.setTranslateY(30);
                            label4.setTextFill(Color.RED);
                            scrollBarPane.getChildren().add(label5);
                        }
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
                Label label5 = new Label("Pick A Solved Maze To View");
                label5.setFont(font2);
                label5.setTextFill(Color.YELLOW);
                label5.setTranslateX(270);
                label5.setTranslateY(50);

                scrollBarPane.getChildren().clear();
                scrollBarPane.getChildren().addAll(sl, p4, p3);

                scrollBarPane.getChildren().add(label5);
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
                        scrollBarPane.getChildren().clear();
                        scrollBarPane.getChildren().addAll(sl, p4);
                        scrollBarPane.getChildren().add(p);
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

        // background image

        // Stage Configuration
        vbTop.getChildren().addAll(mazeSelectionCBoxTitle, mazeSelectionCBox);
        vbTop.setPadding(new Insets(20, 10, 250, 10));
        vbBottom.getChildren().addAll(readMazesBt, findPathSingleBt, findPathAllBt, writeMazesBt, quitBt);
        vbBottom.setPadding(new Insets(0, 10, 0, 10));
        vb.getChildren().addAll(vbTop, vbBottom);

        hb.getChildren().addAll(scrollBar, vb);
        // hb.setBackground(wallpaper);
        Scene sc = new Scene(hb);
        primaryStage.setScene(sc);
        primaryStage.setMaxHeight(640);
        primaryStage.setMaxWidth(1250);
        primaryStage.setMinHeight(640);
        primaryStage.setMinWidth(1250);
        primaryStage.show();
    }

}
