package structureDefinition;

public class Cell {

	private boolean state;
	private int column;
	private int row;
	private boolean nextState;

	public Cell(boolean state, int column, int row) {
		this.state = state;
		this.column = column;
		this.row = row;
	}
	
	/**
	 * Updates the status of this cell, putting the next status value into
	 * the current status value
	 */
	public void update(){
		this.setState(this.getNextState());
	}
	
	
	/**
	 * Checks the status of the neighbor cells and sets the future status
	 * of this cell
	 */
	public void check(){
		int numOfAdj = 0;
		//count the number of alive nearby cells
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if(i==0 && j==0){
				}else{
					try{
						Cell x = Matrix.getCell(this.getColumn()+i, this.getRow()+j);
						if (x.getState()){
							numOfAdj++;
							}
					}catch(Exception e){
					}
				}
			}
		}
		if (numOfAdj==2 && this.getState() || numOfAdj==3){
			this.setNextState(true);
		} else {
			this.setNextState(false);
			}
	}
	
	/**
	 * @param state the state to set
	 */
	public void setState(boolean state) {
		this.state = state;
	}
	
	/**
	 * @return the state
	 */
	public boolean getState(){
		return this.state;
	}
	
	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * @return the nextState
	 */
	public boolean getNextState(){
		return this.nextState;
	}
	
	/**
	 * @param nextState the nextState to set
	 */
	public void setNextState(boolean nextState) {
		this.nextState = nextState;
	}	
}
