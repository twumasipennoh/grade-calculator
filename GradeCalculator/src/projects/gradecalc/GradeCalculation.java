package projects.gradecalc;

import javafx.application.Application;
import javafx.application.Platform;
import java.lang.NumberFormatException;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

public class GradeCalculation {
	
	private boolean checkIfNumeric(String str) {
		try {
			if(!(str.equals(""))) {
				double d = Double.parseDouble(str);
			}
		}
		catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public boolean checkInputsAreNumeric(TextField[] inputs) {
		for(int i = 0; i < inputs.length; i++) {
			if(!(inputs[i] == null)) {
				System.out.println(inputs[i].getText());
				if(!(checkIfNumeric(inputs[i].getText()))) {
					System.out.println("Invalid Input");
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean checkWeights(TextField[] inputs) {
		double sum = 0;
		for(int i = 0; i < inputs.length; i++) {
			if( inputs[i] == null || inputs[i].getText().equals("")) {
				sum = sum + 0;
			}
			else {
				double temp = Double.parseDouble(inputs[i].getText());
				sum = sum + temp;
			}
		}
		if(sum <= 100) {
			return true;
		}
		return false;
	}
	
	public double getWeightsTotal(TextField[] inputs) {
		double sum = 0;
		for(int i = 0; i < inputs.length; i++) {
			if(inputs[i] == null || inputs[i].getText().equals("")) {
				sum = sum + 0;
			}
			else {
				double temp = Double.parseDouble(inputs[i].getText());
				sum = sum + temp;
			}
		}
		return sum;
	}
	
	public double[] getWeightsAsDecimals(TextField[] inputs) {
		double[] weights = new double [inputs.length];
		for(int i = 0; i < inputs.length; i++) {
			if(inputs[i] == null || inputs[i].getText().equals("")) {
				weights[i] = 0;
			}
			else {
				weights[i] = Double.parseDouble(inputs[i].getText()) * 0.01;
			}
		}
		return weights;
	}
	
	public double[] getGradesAsDoubles(TextField[] inputs) {
		double[] grades = new double [inputs.length];
		for(int i = 0; i < inputs.length; i++) {
			if(inputs[i] == null || inputs[i].getText().equals("")) {
				grades[i] = 0;
			}
			else {
				grades[i] = Double.parseDouble(inputs[i].getText());
			}
		}
		return grades;
	}
	
	public double getOverallGrade(double[] grades, double[] weights, double sumOfWeights) {
		double overallGrade = 0;
		double sum = 0;
		if(grades.length != weights.length) {
			System.out.println("Either a grade or grade weight is missing. Cannot make calculation");
			return 0;
		}
		else {
			for(int i = 0; i < grades.length; i++) {
				double temp = grades[i] * weights[i];
				sum = sum + temp;
			}
			if(sum == 100) {
				overallGrade = sum + 0;
			}
			else if(sum < 100) {
				overallGrade = (sum/sumOfWeights) * 100;
			}
		}
		return overallGrade;
	}
	
	public Text getLetterGrade(double overallGrade, ArrayList<ComboBox<Text>> letterGrades, 
			ArrayList<ComboBox<Integer>> minValList, ArrayList<ComboBox<Integer>> maxValList) {
		Text letterGrade = new Text("");
		for(int i = 0; i < letterGrades.size(); i++) {
			if(!(minValList.get(i).getValue() == null || maxValList.get(i).getValue() == null)){
				int minVal = minValList.get(i).getValue();
				int maxVal = maxValList.get(i).getValue();
				if(overallGrade >= minVal && overallGrade <= maxVal) {
					letterGrade = letterGrades.get(i).getValue();
					break;
				}
			}
			else {
				System.out.println("You are missing some grade scales.");
			}
		}
		return letterGrade;
	}
	
}
