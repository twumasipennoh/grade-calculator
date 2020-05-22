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
	
	public class GradeCalculator extends Application {
		protected int textFieldCount = 0;
		protected int dropDownMenuCount = 5;
		protected TextField [] assessmentInput = new TextField[10];
		protected TextField [] gradeInput = new TextField[10];
		protected TextField [] weightInput = new TextField[10];
		protected HBox [] textfield = new HBox[10]; //contains the set of text fields
		protected HBox [] dropDownMenus = new HBox[10]; //contains the drop down menus
		protected VBox secondPart;
		protected Stage newClass;
		protected TextArea result;
		protected TextField classInput;
		protected ArrayList<ComboBox<Text>> letterGradesList;
		protected ArrayList<ComboBox<Integer>> minValList;
		protected ArrayList<ComboBox<Integer>> maxValList;
		
		private VBox getInputFields() {
			VBox secondPart = new VBox();
			//contains the titles that let the user know what goes into the text fields
			HBox titles = new HBox(245); 
			HBox leftTitle = new HBox(); //contains assessment text
			HBox rightTitles = new HBox(100); //contains grade and weight text
			Text assessment = new Text("Assessment");
			assessment.setFont(new Font(15));
			leftTitle.getChildren().addAll(assessment);
			Text grade = new Text("Grade");
			grade.setFont(new Font(15));
			Text weight = new Text("Weight (%)");
			weight.setFont(new Font(15));
			rightTitles.getChildren().addAll(grade, weight);
			titles.getChildren().addAll(leftTitle, rightTitles);
			secondPart.getChildren().add(titles);
			secondPart.setMargin(titles, new Insets(5, 27.5, 0, 27.5)); //sets margin for titles HBox	
			for(int i = 0; i < 5; i++) { //sets up the first five set of text fields
				textfield[i] = new HBox(20);
				assessmentInput[i] = new TextField();
				assessmentInput[i].setPrefWidth(300);
				gradeInput[i] = new TextField();
				gradeInput[i].setPrefWidth(120);
				weightInput[i] = new TextField();
				weightInput[i].setPrefWidth(120);
				textfield[i].getChildren().addAll(assessmentInput[i], gradeInput[i], weightInput[i]);
				secondPart.getChildren().add(textfield[i]);
				secondPart.setMargin(textfield[i], new Insets(5, 27.5, 0, 27.5));
				textFieldCount++;
			}
			return secondPart;
		}
		
		public HBox getTopPart() {
			HBox firstPart = new HBox(270);
			HBox left = new HBox();
			Text className = new Text("Name of Class: ");
			className.setFont(new Font(20));
			classInput = new TextField();
			left.getChildren().addAll(className, classInput);
			HBox right = new HBox();
			Button save = new Button("Save");
			save.setOnAction(e -> {
				setSaveButtonAction();
			});
			right.getChildren().add(save);
			firstPart.getChildren().addAll(left, right);
			left.setMargin(className, new Insets(5, 0, 5, 10));
			left.setMargin(classInput, new Insets(7.5, 0, 5, 10));
			right.setMargin(save, new Insets(10, 10, 5, 0));
			
			return firstPart;
		}
		
		public void getDropDownMenus(VBox fourthPart) {
			//sets up five combo boxes
			//Since you cannot make a generic array, I made array lists to hold the combo boxes
			//for the 5 letter grades, 5 minVal and 5 maxVal
			letterGradesList = new ArrayList<>(); 
			for(int i = 0; i < 5; i++) {
				ComboBox<Text> letterGradesMenu = new ComboBox<Text>();
				letterGradesMenu.getItems().addAll(new Text("A+"), new Text("A"), new Text("A-"), 
						new Text("B+"), new Text("B"), new Text("B-"), new Text("C+"), new Text("C"), 
						new Text("C-"), new Text("D+"), new Text("D"), new Text("D-"), new Text("F"));
				letterGradesList.add(i, letterGradesMenu);
			}
			minValList = new ArrayList<>();
			maxValList = new ArrayList<>();
			for(int j = 0; j < 5; j++) {
				ComboBox<Integer> minValMenu = new ComboBox<Integer>();
				ComboBox<Integer> maxValMenu = new ComboBox<Integer>();
				for(int i = 100; i >= 0; i--) {
					minValMenu.getItems().add(new Integer(i));
					maxValMenu.getItems().add(new Integer(i));
				}
				minValList.add(j, minValMenu);
				maxValList.add(j, maxValMenu);
			}
			for(int i = 0; i < 5; i++) {
				dropDownMenus[i] = new HBox(48.5);
				dropDownMenus[i].getChildren().addAll(letterGradesList.get(i), 
						maxValList.get(i), minValList.get(i));
				fourthPart.getChildren().add(dropDownMenus[i]);
				fourthPart.setMargin(dropDownMenus[i], new Insets(5, 27.5, 0, 27.5)); //sets margin
			}
		}
		
		public VBox getGradeScalePart() {
			VBox fourthPart = new VBox();
			Text gradeScaleTitle = new Text("Grade Scale");
			gradeScaleTitle.setFont(new Font(20));
			
			HBox titles = new HBox(35);
			Text letterGrade = new Text("Letter Grade");
			letterGrade.setFont(new Font(15));
			Text minVal = new Text("Min Value");
			minVal.setFont(new Font(15));
			Text maxVal = new Text("Max Value");
			maxVal.setFont(new Font(15));
			titles.getChildren().addAll(letterGrade, maxVal, minVal);
			fourthPart.getChildren().addAll(gradeScaleTitle, titles);
			fourthPart.setMargin(gradeScaleTitle, new Insets(5, 27.5, 0, 27.5)); 
			fourthPart.setMargin(titles, new Insets(5, 27.5, 0, 27.5)); //sets margin for titles HBox
			
			getDropDownMenus(fourthPart);
			
			return fourthPart;
		}
		
		public VBox getAddGradeScaleButton(VBox fourthPart) {
			VBox fifthPart = new VBox();
			Button addGradeScale = new Button("Add Grade Scale");
			addGradeScale.setOnAction(e -> {
				int i = dropDownMenuCount;
				ComboBox<Text> letterGradesMenu = new ComboBox<Text>();
				letterGradesMenu.getItems().addAll(new Text("A+"), new Text("A"), new Text("A-"), 
						new Text("B+"), new Text("B"), new Text("B-"), new Text("C+"), new Text("C"), 
						new Text("C-"), new Text("D+"), new Text("D"), new Text("D-"), new Text("F"));
				letterGradesList.add(i, letterGradesMenu);
				ComboBox<Integer> minValMenu = new ComboBox<Integer>();
				ComboBox<Integer> maxValMenu = new ComboBox<Integer>();
				for(int j = 100; j >= 0; j--) {
					minValMenu.getItems().add(new Integer(j));
					maxValMenu.getItems().add(new Integer(j));
				}
				minValList.add(i, minValMenu);
				maxValList.add(i, maxValMenu);
				dropDownMenus[i] = new HBox(48.5);
				dropDownMenus[i].getChildren().addAll(letterGradesList.get(i), 
						maxValList.get(i), minValList.get(i));
				fourthPart.getChildren().add(dropDownMenus[i]);
				fourthPart.setMargin(dropDownMenus[i], new Insets(5, 27.5, 0, 27.5)); //sets margin
				newClass.sizeToScene();
			});
			fifthPart.getChildren().add(addGradeScale);
			fifthPart.setMargin(addGradeScale, new Insets(5, 27.5, 5, 27.5)); //sets margin
			return fifthPart;
		}
		
		public void setCalculateGradeAction() {
			GradeCalculation grade = new GradeCalculation();
			boolean gradeInputsGood = grade.checkInputsAreNumeric(gradeInput);
			boolean weightInputsGood = grade.checkInputsAreNumeric(weightInput);
			boolean weightsAddUp = grade.checkWeights(weightInput);
			double weightsTotal = grade.getWeightsTotal(weightInput);
			double[] weightsAsDecimals = grade.getWeightsAsDecimals(weightInput);
			double[] gradesAsDoubles = grade.getGradesAsDoubles(gradeInput);
			double overallGrade = grade.getOverallGrade(gradesAsDoubles, weightsAsDecimals, weightsTotal);
			DecimalFormat twodp = new DecimalFormat("#.##"); //sets format for decimals
			twodp.setRoundingMode(RoundingMode.CEILING);
			Text letterGrade = grade.getLetterGrade(overallGrade, letterGradesList, minValList, maxValList);
			result.setStyle("-fx-font-size: 20px");
			result.appendText("" + twodp.format(overallGrade) + "% " + letterGrade.getText());
			//newClass.sizeToScene();
		}
		
		public void setSaveButtonAction() {
			WriteInputs writer = new WriteInputs();
			writer.setNameOfClass(classInput.getText());
			writer.setOverallGrade(result.getText());
			writer.setAssessmentStrings(assessmentInput);
			writer.setGradeStrings(gradeInput);
			writer.setWeightStrings(weightInput);
			writer.setLetterGrades(letterGradesList);
			writer.setMaxVals(maxValList);
			writer.setMinVals(minValList);
			writer.writeToFile();
		}
		
		public void setAddButtonAction() {
			newClass = new Stage();
			newClass.initModality(Modality.APPLICATION_MODAL);
			VBox newInput = new VBox();
			
			HBox firstPart = getTopPart(); //contains text field for user to put in the class name
			secondPart = getInputFields(); //contains input fields for user to put in their grades
			
			VBox thirdPart = new VBox();
			Button addGrade = new Button("Add Grade");
			addGrade.setOnAction(e -> {
				if(textFieldCount < 10) {
					int i = textFieldCount;
					textfield[i] = new HBox(20);
					assessmentInput[i] = new TextField();
					assessmentInput[i].setPrefWidth(300);
					gradeInput[i] = new TextField();
					gradeInput[i].setPrefWidth(120);
					weightInput[i] = new TextField();
					weightInput[i].setPrefWidth(120);
					textfield[i].getChildren().addAll(assessmentInput[i], gradeInput[i], weightInput[i]);
					secondPart.getChildren().add(textfield[i]);
					secondPart.setMargin(textfield[i], new Insets(5, 27.5, 0, 27.5));
					textFieldCount++;
				}
				newClass.sizeToScene();
			});
			thirdPart.getChildren().add(addGrade);
			thirdPart.setMargin(addGrade, new Insets(5, 27.5, 5, 27.5));
			
			VBox fourthPart = getGradeScalePart(); //this contains the grade scale part
			
			VBox fifthPart = getAddGradeScaleButton(fourthPart);
			
			VBox sixthPart = new VBox();
			HBox overallGrade = new HBox();
			Button calculateGrade = new Button("Calculate Grade");
			calculateGrade.setOnAction(e -> {
				setCalculateGradeAction();
			});
			Text overallGradeText = new Text("Overall Grade: ");
			result = new TextArea(){
					/*This prevents user from typing input into textfield.
					  Got inspired to write this from a similar code found online at
					  "http://fxexperience.com/2012/02/restricting-input-on-a-textfield/"
					*/
					@Override public void replaceText(int start, int end, String text) {
					    // If the replaced text would end up being invalid, then simply
					    // ignore this call!
					    if (!(text.matches("[0-9]") || text.matches("[a-z]"))){
						super.replaceText(start, end, text);
					    }
					}
			 
					@Override public void replaceSelection(String text) {
					    if (!(text.matches("[0-9]") || text.matches("[a-z]"))){
						super.replaceSelection(text);
					    }
					}
			    };
		    result.setPrefHeight(15);
			overallGrade.getChildren().addAll(overallGradeText, result);
			sixthPart.getChildren().addAll(calculateGrade, overallGrade);
			sixthPart.setMargin(calculateGrade, new Insets(5, 27.5, 5, 27.5));
			sixthPart.setMargin(overallGrade, new Insets(5, 27.5, 10, 27.5));
			
			newInput.getChildren().addAll(firstPart, secondPart, thirdPart, fourthPart, fifthPart,
					sixthPart);
			
			Scene newClassScene = new Scene(newInput);
			newClass.setMinWidth(640);
			newClass.setMinHeight(480);
			newClass.setTitle("New Class");
			newClass.setScene(newClassScene);
			newClass.sizeToScene();
			newClass.show();
			newClass.sizeToScene();
		}
		
		@Override
		public void start(Stage stage) {
			//tool bar that allows the user to add a new class or delete class
			HBox mainBar = new HBox(355);
			
			HBox buttons = new HBox(10); //HBox to hold the buttons on the right side
			Button addClass = new Button("Add");
			addClass.setOnAction(e -> {
				setAddButtonAction();
			});
			Button deleteClass = new Button("Delete");
			buttons.getChildren().addAll(addClass,deleteClass);
			
			Text title = new Text("Grade Calculator");
			title.setFont(new Font(20));
			
			mainBar.getChildren().addAll(title, buttons);
			mainBar.setMargin(title, new Insets(5, 0, 5, 10));
			mainBar.setMargin(buttons, new Insets(5, 10, 5, 0));
			
			VBox savedClasses = new VBox();
			Rectangle region = new Rectangle(640, 480);
			region.setFill(Color.TRANSPARENT);
			region.setStroke(Color.WHITE);
			savedClasses.getChildren().add(region);
			
	        // Construct GUI; can replace
	        VBox pane = new VBox();
	        pane.getChildren().addAll(mainBar, savedClasses);
	        
	        Scene scene = new Scene(pane);
	        stage.setMaxWidth(640);
	        stage.setMaxHeight(480);
	        stage.setTitle("Grade Calculator");
	        stage.setScene(scene);
	        stage.sizeToScene();
	        stage.show();
		
	    } // start

	    public static void main(String[] args) {
	        try {
	            Application.launch(args);
	        } catch (UnsupportedOperationException e) {
	            System.out.println(e);
	            System.err.println("If this is a DISPLAY problem, then your X server connection");
	            System.err.println("has likely timed out. This can generally be fixed by logging");
	            System.err.println("out and logging back in.");
	            System.exit(1);
	        } // try
	    } // main

	} // GradeCalculator
