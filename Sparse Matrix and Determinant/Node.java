public class Node{
	private int row;
	private int column;
	private int data;
	private Node next;
	//Default constructor for Node
	public Node(){
		this.row = 0;
		this.column = 0;
		this.data = 0;
		this.next = null;
	}
	//Constructor for node that takes in ints: row, column and data.
	public Node(int row, int column, int data){
		this.row = row;
		this.column = column;
		this.data = data;
		this.next = null;
	}
	//Setter to change the row value
	public void setRow(int row){
		this.row = row;
	}
	//Getter to retrieve row value
	public int getRow(){
		return row;
	}
	//Setter to change column
	public void setColumn(int column){
		this.column = column;
	}
	//Getter to retrieve column value
	public int getColumn(){
		return column;
	}
	//Setter to change data
	public void setData(int data){
		this.data = data;
	}
	//Getter to retrieve data value
	public int getData(){
		return data;
	}
	//Setter to change the next node in list
	public void setNext(Node next){
		this.next = next;
	}
	//Getter to retrieve the next node
	public Node getNext(){
		return next;
	}
}