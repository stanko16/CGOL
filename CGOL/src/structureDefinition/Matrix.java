package structureDefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public abstract class Matrix {
	 private static Cell matrix[][];
	 private static int nRows;
	 private static int nColumns;
	 
	 /**
	  * Generates a void matrix, where each cell is healthy (state 0)
	  * @param nColumns Number of columns
	  * @param nRows Number of rows
	  */
	public static void GenerateVoidMatrix(int nColumns, int nRows) {
		Matrix.initializeMatrix(nColumns, nRows);
		for (int j = 0; j < nColumns; j++) {
			for (int i = 0; i < nRows; i++) {
				Cell c = new Cell(false,j,i);
				matrix[j][i]=c;
			}
		}
	}
	
	/**
	 * Generates a matrix of random-state cells
	 * @param nColumns Number of columns
	 * @param nRows Number of rows
	 */
	public static void GenerateRandomMatrix(int nColumns, int nRows){
		Matrix.initializeMatrix(nColumns, nRows);
		for (int j = 0; j < nColumns; j++) {
			for (int i = 0; i < nRows; i++) {
				boolean b;
				if(Math.random()*100<50){
					b = true;
				}else {
					b=false;
				}
				Cell c = new Cell(b,j,i);
				matrix[j][i]=c;
			}
		}
	}
	
	/**
	 * This method initializes a new matrix by specifying the rows and the columns
	 * @param nColumns Number of columns
	 * @param nRows Number of rows
	 */
	public static void initializeMatrix(int nColumns, int nRows){
		Matrix.setnColumns(nColumns);
		Matrix.setnRows(nRows);
		matrix = new Cell[nColumns][nRows];
	}
	
	/**
	 * Checks the status of each cell with respect to its neighbor cells (calls the check method on each cell of the matrix)
	 */
	public static void checkMatrix(){
		for (int i = 0; i < Matrix.getnColumns(); i++) {
			for (int j = 0; j < Matrix.getnRows(); j++) {
				matrix[i][j].check();
			}
		}
	}
	
	/**
	 * Updates the status of all cells
	 */
	public static void updateMatrix(){
		for (int i = 0; i < Matrix.getnColumns(); i++) {
			for (int j = 0; j < Matrix.getnRows(); j++) {
				matrix[i][j].update();
			}
		}
	}
		
	/**
	 * @return the nRows
	 */
	public static int getnRows() {
		return nRows;
	}
	/**
	 * @param nRows the nRows to set
	 */
	public static void setnRows(int nRows) {
		Matrix.nRows = nRows;
	}
	/**
	 * @return the nColumns
	 */
	public static  int getnColumns() {
		return nColumns;
	}
	/**
	 * @param nColumns the nColumns to set
	 */
	public static void setnColumns(int nColumns) {
		Matrix.nColumns = nColumns;
	}
	/**
	 * @return the matrix
	 */
	public static Cell[][] getMatrix() {
		return matrix;
	}
	 
	/**
	 * 
	 * @param column the column number
	 * @param row the row number
	 * @return Cell returns the cell having the indicated column and row. Some if's were added in order to avoid "out of range" exceptions.
	 */
	public static Cell getCell(int column, int row){
		if(column<0){
			column=nColumns-1;
		}
		if (column>nColumns-1){
			column=0;
		}
		if (row<0){
			row=nRows-1;
		}
		if (row>nRows-1){
			row=0;
		}
		return Matrix.getMatrix()[column][row];
	}
	/**
	 * Increments the state of the (clicked) cell in the specified column and row. 
	 * @param column the column number
	 * @param row the row number
	 */
	 public static void incrementCellState(int column, int row){
		boolean b = Matrix.getMatrix()[column][row].getState();
		if(!b){
			Matrix.getMatrix()[column][row].setState(true);
		} else {
			Matrix.getMatrix()[column][row].setState(false);
		}
	}
	
}
