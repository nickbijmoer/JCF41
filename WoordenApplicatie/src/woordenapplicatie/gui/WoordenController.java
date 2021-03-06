package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {

    private List<String> words;
    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Heb je dan geen hoedje meer\n"
            + "Maak er één van bordpapier\n"
            + "Eén, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "En als het hoedje dan niet past\n"
            + "Zetten we 't in de glazenkas\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier";

    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }

    @FXML
    private void aantalAction(ActionEvent event) {
        String temp = DEFAULT_TEXT;
        temp = temp.replaceAll("(\r\n|\n)", " ");
        temp = temp.replaceAll(",", " ");
        temp = temp.replaceAll("  ", " ");

        words = Arrays.asList(temp.split(" "));
        System.out.println(words.size());

        Set<String> UniqueWords = new HashSet();
        for (String word : words) {
            UniqueWords.add(word);
        }
        System.out.println(UniqueWords.size());

    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        String temp = DEFAULT_TEXT;
        temp = temp.replaceAll("(\r\n|\n)", " ");
        temp = temp.replaceAll(",", " ");
        temp = temp.replaceAll("\'t", " ");
        temp = temp.replaceAll("  ", " ");
        temp = temp.replaceAll("   ", " ");

        words = Arrays.asList(temp.split(" "));
        
        Set<String> UniqueWords = new TreeSet(Collections.reverseOrder());
        for (String word : words) {
            UniqueWords.add(word.toLowerCase());
        }
        for (String word : UniqueWords) {
            taOutput.appendText("\n" + word);
        }
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        TreeMap<String, Integer> myMap = new TreeMap<String, Integer>();

        String temp = DEFAULT_TEXT;
        temp = temp.replaceAll("(\r\n|\n)", " ");
        temp = temp.replaceAll(",", " ");
        temp = temp.replaceAll("  ", " ");

        String[] input = temp.split(" ");

        for (int f = 0; f < input.length; f++) {
            String key = input[f].toUpperCase();
            if (input[f].length() > 1) {
                if (myMap.get(key) == null) {
                    myMap.put(key, 1);
                } else {
                    int value = myMap.get(key).intValue();
                    value++;
                    myMap.put(key, value);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : myMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        Map<String, Set<Integer>> concordance = new HashMap<String, Set<Integer>>();
        int zinnummer = 0;
        String temp = DEFAULT_TEXT;
        temp = temp.replaceAll("(\r\n|\n)", "-");
        temp = temp.replaceAll(",", " ");
        temp = temp.replaceAll("  ", " ");
        
        words = Arrays.asList(temp.split("-"));    
        

            
        
        System.err.println("");
        
        for (String zin : words) {
        if(zin.length() < 1)
        {
            
        }
        else           
        {      
        zinnummer++;
        String[] Woordzin  = zin.split(" ");    
        for (String woord : Woordzin) {
        if(concordance.get(woord) == null) {
        Set<Integer> set= concordance.get(woord);
        if(set == null)
        {
            set = new TreeSet<Integer>();
        }
        else
        {
            
        }
        set.add(zinnummer);
        concordance.put(woord, set);
        }   
            else {
                    Set<Integer> set= concordance.get(woord);
                    set.add(zinnummer);
                    concordance.put(woord, set);
            }
        }
        taOutput.setText(concordance.toString().replaceAll(", (?!\\d)", "\n"));
        }
        }
    }

}
