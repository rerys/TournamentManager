package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author remy
 */
public class ConstructGame {

    private static final int GAMEMAXPOINTS = 15;

    private final Player p1;
    private final Player p2;
    private boolean canceled = false;
    private final ListQuestions lsQuestions = new ListQuestions();
    private final ObservableList<Question> lsQuestionsChoix = FXCollections.observableArrayList();
    private String categorySelected;

    public ConstructGame(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    //retourne le joueur 1
    public Player getPlayer1() {
        return p1;
    }

    //retourne le joueur 2
    public Player getPlayer2() {
        return p2;
    }

    //retourne une question de la liste des questions, à une position donnée 
    public Question getQuestionList(int index) {
        return lsQuestions.get(index);
    }

    //retourne une question de la liste des questions choisis, à une position donnée
    public Question getQuestionChoix(int index) {
        return lsQuestionsChoix.get(index);
    }

    //retourne une observable liste de la liste des questions
    public ObservableList<Question> getListQuestions() {
        return lsQuestions.getQuestions();
    }

    //retourne une observable liste de la liste des questions d'une category
    public ObservableList<Question> getListQuestions(String category) {
        return lsQuestions.getQuestions(category);
    }

    //retourn une observable liste des questions chosis
    public ObservableList<Question> getListQuestionsChoix() {
        return lsQuestionsChoix;
    }

    //ajout une question de la liste des questions à la liste des questions choisis
    //et supprime la question choisit de la liste des questions
    public boolean add(Question q) {
        return lsQuestionsChoix.add(q) && lsQuestions.remove(q);
    }

    //enleve une question de la liste des questions choisit
    //et l'ajoute dans la liste des questions
    public boolean remove(Question q) {
        return lsQuestions.add(categorySelected,q) && lsQuestionsChoix.remove(q);
    }

    //retourne le nombre de points de la liste des quéstions
    public int getPoints() {
        return lsQuestions.getPoints();
    }

    //retourn le nombre de points maximum pour une partie
    public int getMaxPoint() {
        return GAMEMAXPOINTS;
    }

    //change l'état de la partie
    public void cancel() {
        canceled = true;
    }
    
    //change la category courrente
    public void changeCategory(String s){
        categorySelected = s;
    }

    //retourne si une partie est annulée
    public boolean canceled() {
        return canceled;
    }

    //retourne la liste des categories
    public ObservableList<String> getCategories() {
        return lsQuestions.getCategories();
    }

}
