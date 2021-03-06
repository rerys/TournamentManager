/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author remy
 */
public class Composite extends Composant {

    private final ObservableList<Composant> ls = FXCollections.observableArrayList();

    public Composite(String name) {
        super(name);
    }

    public void add(Composant c) {
        ls.add(c);
    }

    public void remove(Composant c) {
        ls.remove(c);
    }

    @Override
    public ObservableList<Question> getQuestions() {
        ObservableList<Question> res = FXCollections.observableArrayList();
        for (Composant c : ls) {
            res.addAll(c.getQuestions());     
        }
        return res;
    }
    
    public boolean containCategory(Composant category){
        if(this.equals(category)) return true;
        for(Composant c : ls){
            if(c instanceof Composite){
                if(c.equals(category)){
                    return true;
                }else{
                    return ((Composite)c).containCategory(category);
                }
            }
        }
        return false;
    }

}

