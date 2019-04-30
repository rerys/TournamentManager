package mediator;

import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ConstructGame;
import model.ListTournament;
import model.Match.Result;
import model.Player;
import mvvm.ViewModel;
import mvvm.ViewModelConstructQuest;
import mvvm.ViewModelGame;
import undoableBuilding.GameMemento;
import view.View;
import view.ViewContructQuestionGame;
import view.ViewGame;
import view.ViewText;

/**
 *
 * @author 3009rerys
 */
public class Mediator {

    private static Mediator INSTANCE = null;

    private final ListTournament lsTournament = new ListTournament();

    private Mediator() {
    }

    ;
    
        public static Mediator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Mediator();
        }
        return INSTANCE;
    }

    public void managerView(Stage primaryStage) {
        imageStart();
        ViewModel viewModel = new ViewModel(lsTournament);
        View view = new View(primaryStage, viewModel);
        primaryStage.show();
    }

    public Result partie(Player p1, Player p2) {
        Result res = null;
        ConstructGame newGame = new ConstructGame(p1, p2);
        constructionPartie(newGame);
        if (!newGame.canceled()) {
            GameMemento game = new GameMemento(newGame,-1);
            playGame(game);
            if (!game.canceled()) {
                res = game.result();
            }
        }
        return res;
    }

    private void imageStart() {
        Stage stage = new Stage();
        ViewText view = new ViewText(stage);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void constructionPartie(ConstructGame newGame) {
        Stage stage = new Stage();
        ViewModelConstructQuest viewConstruct = new ViewModelConstructQuest(newGame);
        ViewContructQuestionGame view = new ViewContructQuestionGame(stage, viewConstruct);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void playGame(GameMemento game) {
        Stage stage = new Stage();
        ViewModelGame viewModel = new ViewModelGame(game);
        ViewGame view = new ViewGame(stage, viewModel);
        stage.initModality(Modality.APPLICATION_MODAL);
        
        stage.showAndWait();
    }
}
