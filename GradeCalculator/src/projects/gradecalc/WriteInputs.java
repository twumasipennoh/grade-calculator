package projects.gradecalc;

import javafx.application.Application;
import javafx.application.Platform;
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
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteInputs {
	String nameOfClass;
	String fileName;
	FileWriter writer;
	String[] assessmentString = new String[10];
	String[] gradeString = new String[10];
	String[] weightString = new String[10];
	String[] letterGrade;
	String[] maxVal;
	String[] minVal;
	String overallGradeResult;
	
	public void writeToFile() {
		  try {
			  	PrintWriter writer1 = new PrintWriter(fileName);
			  	writer1.print(""); //clears content in file
			  	writer1.close();
	            FileWriter writer = new FileWriter(fileName, true);
	            writer.write(nameOfClass);
	            writer.write("\r\n\r\n"); //two new lines
	            writer.write("Grades: ");
            	writer.write("\r\n"); //new line
	            for(int i = 0; i < 10; i++) {
	            	writer.write(assessmentString[i] + " " + gradeString[i] + " " +
	            			weightString[i]);
	            	writer.write("\r\n"); //new line
	            }
	            writer.write("\r\n");
	            writer.write("Grade Scale: ");
	            writer.write("\r\n");
	            for(int i = 0; i < letterGrade.length; i++) {
	            	writer.write(letterGrade[i] + " " + maxVal[i] + " " + minVal[i]);
	            	writer.write("\r\n"); //new line
	            }
	            writer.write("\r\n");//new line
	            writer.write("Overall Grade: " + overallGradeResult);
	            writer.close();
	        } 
		  catch (IOException e) {
	            e.printStackTrace();
	        }	
	}
	
	public void setNameOfClass(String nameOfClass) {
		this.nameOfClass = nameOfClass;
		String filePath = "C:\\Users\\Twumasi Pennoh\\Documents"
				+ "\\Java Projects\\GradeCalculator\\Saved files\\"; 
		fileName = filePath + nameOfClass + ".txt";
	}
	
	public void setOverallGrade(String overallGrade) {
		this.overallGradeResult = overallGrade;
	}
	
	public void setAssessmentStrings(TextField[] assessmentInput) {
		for(int i = 0; i < assessmentInput.length; i++) {
			if(assessmentInput[i] == null) {
				assessmentString[i] = "";
			}
			else {
				assessmentString[i] = assessmentInput[i].getText();
			}
		}
	}
	
	public void setGradeStrings(TextField[] gradeInput) {
		for(int i = 0; i < gradeInput.length; i++) {
			if(gradeInput[i] == null) {
				gradeString[i] = "";
			}
			else {
				gradeString[i] = gradeInput[i].getText();
			}
		}
	}
	
	public void setWeightStrings(TextField[] weightInput) {
		for(int i = 0; i < weightInput.length; i++) {
			if(weightInput[i] == null) {
				weightString[i] = "";
			}
			else {
				weightString[i] = weightInput[i].getText();
			}
		}
	}
	
	public void setLetterGrades(ArrayList<ComboBox<Text>> letterGradesList) {
		letterGrade = new String[letterGradesList.size()];
		for(int i = 0; i < letterGradesList.size(); i++) {
			if(letterGradesList.get(i).getValue().getText() == null) {
				letterGrade[i] = "";
			}
			else {
				letterGrade[i] = letterGradesList.get(i).getValue().getText();
			}
		}
	}
	
	public void setMinVals(ArrayList<ComboBox<Integer>> minValList) {
		minVal = new String[minValList.size()];
		for(int i = 0; i < minValList.size(); i++) {
			if(minValList.get(i).getValue() == null) {
				minVal[i] = "";
			}
			else {
				minVal[i] = "" + minValList.get(i).getValue();
			}
		}
	}
	
	public void setMaxVals(ArrayList<ComboBox<Integer>> maxValList) {
		maxVal = new String[maxValList.size()];
		for(int i = 0; i < maxValList.size(); i++) {
			if(maxValList.get(i).getValue() == null) {
				maxVal[i] = "";
			}
			else {
				maxVal[i] = "" + maxValList.get(i).getValue();
			}
		}	
	}
	
	
}
