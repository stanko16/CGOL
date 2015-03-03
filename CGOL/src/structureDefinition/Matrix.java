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
	 
	 
	public static void GenerateVoidMatrix(int nColumns, int nRows) {
		Matrix.initializeMatrix(nColumns, nRows);
		for (int j = 0; j < nColumns; j++) {
			for (int i = 0; i < nRows; i++) {
				Cell c = new Cell(false,j,i);
				matrix[j][i]=c;
			}
		}
	}
	
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
	
	
	public static void initializeMatrix(int nColumns, int nRows){
		Matrix.setnColumns(nColumns);
		Matrix.setnRows(nRows);
		matrix = new Cell[nColumns][nRows];
	}
	
	/**
	 * Checks the status of each cell with respect to its neighbor cells
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
	
	public static void setCell(int column, int row){
		boolean b = Matrix.getMatrix()[column][row].getState();
		if(!b){
			Matrix.getMatrix()[column][row].setState(true);
		} else {
			Matrix.getMatrix()[column][row].setState(false);
		}
	}
	
	
	/**
	 * Matrix-->	
	 * |		______yUp_______
	 * |		|				|
	 * V		|				|
	 *	   xLeft|	subMatrix	|xRight
	 * 			|				|
	 * 			|_______________|
	 * 				 yDown
	 * 
	 * @return subMatrix
	 */
	public static Boolean[][] getSubMatrix(){
		int right=0,left=0,down= 0;
		int up = 99999999;
		boolean first = true;
		for (int j = 0; j < nColumns; j++) {
			for (int i = 0; i < nRows; i++) {
				Cell c = Matrix.matrix[j][i];
				if(c.getState()){
					if (first){
						left=c.getColumn();
						right=c.getColumn();
						first=false;
						
						if(c.getRow()>down){
							down=c.getRow();
						}
						if(c.getRow()<up){
							up=c.getRow();
						}
						
					} else {
						right=c.getColumn();
						if(c.getRow()>down){
							down=c.getRow();
						}
						if(c.getRow()<up){
							up=c.getRow();
						}
					}
				}
			}
		}
		int smlength = right - left;
		int smheight = down - up;

		
		Boolean[][] subMatrix = new Boolean[smlength+1][smheight+1];
		for (int j = left; j < right; j++) {
			for (int i = up; i < down; i++) {
				subMatrix[j-left][i-up] = Matrix.matrix[j][i].getState();
			}
		}
		return subMatrix;
	}
	
	

	public static void save(String name, Boolean[][] subMatrix){
		try
	      {
			File f =new File(name+".life");
	         FileOutputStream fileOut = new FileOutputStream(f);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(subMatrix);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public static void load(int x, int y, String path){
		Boolean[][] subMatrix = null;
	      try
	      {
	         FileInputStream fileIn = new FileInputStream(path);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         subMatrix = (Boolean[][]) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         c.printStackTrace();
	         return;
	      }
	      for (int j = x; j < subMatrix.length; j++) {
			for (int i = y; i < subMatrix[0].length; i++) {
				if (j>Matrix.getnColumns()){j-=Matrix.getnColumns();}
				if (i>Matrix.getnRows()){i-=Matrix.getnRows();}
				Matrix.matrix[j][i].setState(subMatrix[j-x][i-y]);
			}
		}
	}
	
}
