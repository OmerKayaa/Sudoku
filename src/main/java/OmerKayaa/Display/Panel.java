package OmerKayaa.Display;

import OmerKayaa.Interfaceses.ArrayReceiver;
import OmerKayaa.Interfaceses.Converter;
import OmerKayaa.Interfaceses.Solution;
import OmerKayaa.Model.SimpleCell;
import OmerKayaa.Model.Sudoku;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Panel extends Application implements Runnable
{
	TextField[][] Cells = new TextField[9][9];
	
	@Override
	public void start ( Stage primaryStage )
	{
		BorderPane pane = new BorderPane ();
		{ // setting center
			GridPane centralPane = new GridPane ();
			GridPane[] innerPanes = new GridPane[9];
			for ( int i = 0 ; i < 3 ; i++ )
			{
				for ( int j = 0 ; j < 3 ; j++ )
				{
					int tmp = i*3+j;
					innerPanes[tmp] = new GridPane ();
					
					for ( int k = 0 ; k < 3 ; k++ )
					{
						for ( int l = 0 ; l < 3 ; l++ )
						{
							Cells[tmp][k*3+l] = new TextField ();
							Cells[tmp][k*3+l].setPrefColumnCount ( 1 );
							Cells[tmp][k*3+l].setFont ( new Font ( "Calibri" ,18 ) );
							Cells[tmp][k*3+l].setAlignment ( Pos.CENTER );
							Cells[tmp][k*3+l].setOnKeyTyped ( event ->
							{
								if(event.getCharacter ().matches ( "[1-9]" ))
								{
									((TextField)(event.getSource ())).setDisable ( true );
								}
								else
									((TextField)(event.getSource ())).setText ( "" );
							} );
							innerPanes[tmp].add ( Cells[tmp][k*3+l] , l , k);
						}
					}
					//innerPanes[tmp].setGridLinesVisible ( true );
					centralPane.add ( innerPanes[tmp] , j , i );
				}
			}
			pane.setPadding ( new Insets ( 10 ) );
			centralPane.setVgap ( 10 );
			centralPane.setHgap ( 10 );
			pane.setCenter ( centralPane );
			BorderPane.setAlignment ( centralPane , Pos.CENTER );
			//centralPane.setGridLinesVisible ( true );
		}
		{ //setting Bottom
			Button solveButton = new Button ( "Solve" );
			solveButton.setFont ( new Font ( "Calibri" ,18 ) );
			solveButton.setOnAction ( event -> solve () );
			Button resetButton = new Button ( "Reset" );
			resetButton.setFont ( new Font ( "Calibri" ,18 ) );
			resetButton.setOnAction ( event -> reset () );
			solveButton.setMinWidth ( 100 );
			resetButton.setMinWidth ( 100 );
			HBox hBox = new HBox ( solveButton , resetButton );
			BorderPane.setAlignment ( hBox , Pos.CENTER );
			hBox.setPadding ( new Insets ( 20,0,0,0 ) );
			hBox.setAlignment ( Pos.CENTER );
			hBox.setSpacing ( 50 );
			pane.setBottom ( hBox );
		}
		FlowPane root = new FlowPane ( pane );
		root.setAlignment ( Pos.CENTER );
		Scene scene = new Scene ( root , 450 , 500 );
		primaryStage.setMinHeight ( 450 );
		primaryStage.setMinWidth ( 400 );
		primaryStage.setScene ( scene );
		primaryStage.setTitle ( "Sudoku" );
		primaryStage.show ();
	}
	
	private void solve()
	{
		int[][] sudokuArray = new int[9][9];
		
		for ( int i = 0 ; i < 9 ; i++ )
		{
			for ( int j = 0 ; j < 9 ; j++ )
			{
				sudokuArray[i][j] = Converter.ContainerConverter ( i , j , ( a , b ) -> Integer.valueOf
						            (Cells[b][a].getText().isEmpty ()?"0":Cells[b][a].getText()) );
			}
		}
		
		DataStore data = new DataStore ( sudokuArray );

		setText(Solution.findSolution(new Sudoku ((x , y ) ->
										new SimpleCell (data.SudokuArray[y][x], x , y ) ) ).getReciver());

	}

	private void setText(ArrayReceiver r)
	{
		for (int i = 0; i < 9 ; i++)
		{
			for (int j = 0; j < 9 ; j++)
			{
				Converter.CartesianConverter( j , i , (a, b) -> Cells[a][b])
						.setText(String.valueOf(r.cellReceiver(j,i).getValue()));
				Cells[i][j].setDisable(true);
			}
		}
	}
	
	private void reset()
	{
		for ( int i = 0 ; i < 9 ; i++ )
		{
			for ( int j = 0 ; j < 9 ; j++ )
			{
				Cells[i][j].setText ( "" );
				Cells[i][j].setDisable ( false );
			}
		}
	}
	
	@Override
	public void run ()
	{
		launch ();
	}
	
	private class DataStore
	{
		final int[][] SudokuArray;
		
		DataStore(int[][] sudokuArray)
		{
			SudokuArray = sudokuArray;
		}

	}
	
}

