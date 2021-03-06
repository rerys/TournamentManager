/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package undoableBuilding;

import memento.CareTaker;
import memento.Memento;
import model.ConstructGame;

/**
 *
 * @author Matilde
 */
public class GameMemento extends UndoableBuilding {

    private final CareTaker careTaker;

    public GameMemento(ConstructGame game) {
        super(game);
        careTaker = new CareTaker();
    }

    public void addQuestion(int numQuest, int numRep) {
        super.setNumCurrentQuestion(numQuest);
        super.setNumRepDonner(numRep);
        careTaker.gardeMemento(createMemento());

    }

    public boolean isEmptyMemento() {
        return careTaker.isEmptyMemento();
    }

    @Override
    public void undo() {
        setMemento(careTaker.getMemento());
    }

    private Memento createMemento() {
        return new MementoImpl(numCurrentQuest, numRepDonner);

    }

    private void setMemento(Memento m) {
        MementoImpl memento = (MementoImpl) m;
        numCurrentQuest = memento.getNumQuest();
        numRepDonner = memento.getNumReponse();

    }

    private class MementoImpl implements Memento {

        private final int numQuest;
        private final int reponsedonner;

        private MementoImpl(int numQuest, int numRep) {
            this.numQuest = numQuest;
            this.reponsedonner = numRep;
        }

        private int getNumQuest() {
            return numQuest;
        }

        private int getNumReponse() {
            return reponsedonner;
        }
    }
}
