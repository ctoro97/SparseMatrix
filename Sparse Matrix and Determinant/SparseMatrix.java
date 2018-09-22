/*Christian Toro
  Project 1: Recursive Determinant of Sparse Matrix
  COP3530
  Due Date: February 18, 2018
*/

public class SparseMatrix implements SparseInterface{	
	private int size;
	private Node head;
	private Node tail;
	
	//Creates the default constructor with size 5
	public SparseMatrix(){
		this.size = 5;
		this.head = null;
		this.tail = null;
	}
	//Creates constructor with user input for the size of the array
	public SparseMatrix(int size){
		this.size = size;
		this.head = null;
		this.tail = null;
	}
	//Clears the array
	public void clear(){
		this.head = null;
		this.tail = null;
	}
	//Clears the array and sets the size of the array
	public void setSize(int size){
		this.head = null;
		this.tail = null;
		this.size = size;
	}

/*This method adds a node at the given coordinate with the data point. If the coordinate combination
  is out of bounds, it prints an error. If the data point is zero, then it does nothing. If the list is empty,
  then the node is added at the beginning. If there is only one item, then it compares the two coordinates, and
  places them in order. Otherwise, the list is traversed and the node is placed in numerical order. If the row/col
  combination happens to be used, then the data point is replaced with the new one.
*/
	public void addElement(int row, int col, int data){
		//Out of bounds error.
		if(row >= this.size || col >= this.size || row < 0 || col < 0){
			throw new IndexOutOfBoundsException("The row and column combination is out of bound.");
		}
		//Removes if the data point is 0.
		else if(data == 0){
			removeElement(row, col);
		}
		//Adds to head of list if the list is empty
		else if(this.head == null){
			Node ele = new Node(row, col, data);
			this.head = ele;
			this.tail = ele;
			this.head.setNext(this.tail);
		}
		//Adds before or after head if the list only has one item
		else if(this.head != null && this.tail == this.head){
			Node ele = new Node(row, col, data);
			//Makes the new element the head
			if(ele.getRow() < this.head.getRow() || (ele.getRow() == this.head.getRow() && ele.getColumn() < this.head.getColumn())){
				this.head = ele;
				ele.setNext(this.tail);
				this.tail.setNext(null);
			}
			//Makes the new element the tail
			else{
				this.head.setNext(ele);
				this.tail = ele;
			}
		}
		//Adds the new nodes in order
		else if(head != null){
			Node ele = new Node(row, col, data);
			//Adds the new element at the end of the list
			if(ele.getRow() > this.tail.getRow() || (ele.getRow() == this.tail.getRow() && ele.getColumn() > this.tail.getColumn())){
				this.tail.setNext(ele);
				this.tail = ele;
			}
			//Adds element at the front of the list
			else if(ele.getRow() < this.head.getRow() || (ele.getRow() == this.head.getRow() && ele.getColumn() < this.head.getColumn())){
				ele.setNext(this.head);
				this.head = ele;
			}
			else{
				//Temp node created to traverse list and set to head.
				Node temp = new Node();
				temp = this.head;
				while(temp != null){
					if(temp.getNext() != null){
						//Adds element if it is in the previous row of next node and ahead of current node
						if(ele.getRow() < temp.getNext().getRow() && (ele.getRow() > temp.getRow() || (ele.getRow() == temp.getRow() && ele.getColumn() > temp.getColumn()))){
							ele.setNext(temp.getNext());
							temp.setNext(ele);
							break;
						}
						//Adds element if it is in the same row, but different column
						else if(ele.getRow() == temp.getNext().getRow() && ele.getColumn() != temp.getNext().getColumn()){
							if(ele.getColumn() < temp.getNext().getColumn()){
								ele.setNext(temp.getNext());
								temp.setNext(ele);
								break;
							}
						}
					}
					//Replaces data point if same row and same column
					else if(ele.getRow() == temp.getRow() && ele.getColumn() == temp.getColumn()){
						temp.setData(ele.getData());
						break;
					}
					//Iterates to next data point.
					temp = temp.getNext();
				}
			}
		}
	}
//------------------------------------------------------------------------------------------------------------------------
//The top is for adding the element. Done minus exception
/*This method is for removing the node at the row/col combination in the parameters.
  If it is out of boubds, than an error will be printed. If the list is empty than it'll say that
  the matrix only contains 0's. Then it checks if the head contains the coordinate and if so removes
  it and then checks to see if that was the only item in the list. If so then delete the list and if not,
  then make the head, the next node. Otherwise it locates the row and column and removes it from the list.
*/
	public void removeElement(int row, int col){
		//Out of bounds error
		if(row >= this.size || col >= this.size || row < 0 || col < 0){
			throw new IndexOutOfBoundsException("The row and column combination is out of bound.");
		}
		//List is empty
		else if(this.head == null){
			System.out.println("All values in the matrix are zero.");
		}
		//The coordinate matches head
		else if(row == this.head.getRow() && col == this.head.getColumn()){
			//If more than one item then remove head and make the next node head.
			if(this.head != this.tail){
				this.head = this.head.getNext();
			}
			//If head is only item, clear matrix
			else{
				clear();
			}
		}
		else{
			//Creates a temp node and sets to head to iterate through matrix
			Node temp = new Node();
			temp = this.head;
			//Loops through the linked list
			while(temp != null){
				//If the next value of temp is not null to prevent null exception
				if(temp.getNext() != null){
					//Remove the element if the next value matches the coordinate and set its next to the next of the one removed.
					if(row == temp.getNext().getRow() && col == temp.getNext().getColumn()){
						temp.setNext(temp.getNext().getNext());
						//If temp's next is null, then it must be the tail
						if(temp.getNext() == null){
							this.tail = temp;
						}
						break;
					}
				}
				temp = temp.getNext();
			}
		}
	}

/*This method returns the value that is called for the row/col combination.
  The data point starts at 0 and wil return a 0 if that row/col combination
  is not within the list of nodes. Will print an error if the row/col
  combination is out of bounds. Missing throwing exception.
*/
	public int getElement(int row, int col){
		int data = 0;
		//Out of bounds error for row/column combination
		if(row >= this.size || col >= this.size || row < 0 || col < 0){
			throw new IndexOutOfBoundsException("The row and column combination is out of bound.");
		}
		//If the list is empty then only 0's exist in the matrix, so return 0
		else if(this.head == null){
			data = 0;
		}
		//If the head contains the row/column combo, then return it's value
		else if(this.head.getRow() == row && this.head.getColumn() == col){
			data = this.head.getData();
		}
		//Otherwise go through the list and if the coordinate is found return that value.
		//If not return 0.
		else{
			Node temp = new Node();
			temp = this.head.getNext();
			while(temp != null){
				if(temp.getRow() == row && temp.getColumn() == col){
					data = temp.getData();
					break;
				}
				temp = temp.getNext();
			}
		}
		return data;
	}

//This method uses recursion to return the determinant value.
/*There are three base cases:
	1.The linked list is empty so the determinant is zero.
	2.The linked list is of size one, so the determinant is just the value of that one element.
	3.The linked list is of size 2, so the determinant is (a*d)-(b*c)
  If none of those base cases are met, then a while loop is implemented to use recursion.
  The determinant is the sum of (-1^(i+j))*(the data point at (0,j))*(the minor at (0,j)) where j is from 0 to size-1 
  If the minor does not meet the base cases, then it repeats the recursive part.
  Will have a computational complexity of n! in worst case.
*/
	public int determinant(){
		int answer = 0;
		//Base Case 1
		if(this.head == null){
			answer = 0;
		}
		//Base Case 2
		else if(this.size == 1){
			answer = this.head.getData();
		}
		//Base Case 3
		else if(this.size == 2){
			int a = getElement(0,0);
			int b = getElement(0,1);
			int c = getElement(1,0);
			int d = getElement(1,1);
			answer = (a * d) - (b * c);
		}
		//Recursive step
		else{
			int j = 0;
			while(j != this.size){
				//Will only add if getElement isn't 0, becuase otherwise 
				//(-1^(i+j))*(the data point at (0,j))*(the minor at (0,j)) is 0.
				if(getElement(0,j) != 0){
					SparseInterface minor = new SparseMatrix(this.size - 1);
					minor = minor(0,j);
					int k = (int) Math.pow(-1, j);
					answer = answer + (k * getElement(0,j) * minor.determinant());
				}
				//Iterate through the loop the length of the list.
				j++;
			}
		}
		return answer;
	}
	
/*This method if for creating the minor matrix. Takes in the row and column that will be eliminated to create
  the minor matrix. What this method does is add elements from this matrix into the minor matrix. Using
  if statements, the elements are added adjusting the row, column combination to keep with the size
  of the matrix and reach the base case of the determinant method.
*/
	public SparseInterface minor(int row, int col){
		//Creates the minor matrix which is one size smaller than the original 
		//Because a whole row and column is removed.
		SparseMatrix minor = new SparseMatrix(this.size - 1);
		//Node to iterate through the matrix. Sets to head of current matrix.
		Node temp = new Node();
		temp = this.head;
		//Loop goes through all the items in the linked list.
		while(temp != null){
			//If the row and/or column of the current matrix does not equal the row or column of the parameters
			if(temp.getRow() != row && temp.getColumn() != col){
				//If the row of the current matrix is greater than the row parameter
				if(temp.getRow() > row){
					//If the column of the current matrix is greater than the col paramter
					if(temp.getColumn() > col){
						//Adds new element with row and column adjusted by -1.
						minor.addElement(temp.getRow() - 1, temp.getColumn() - 1, temp.getData());
					}
					else{
						//Adds element with row adjusted by -1.
						minor.addElement(temp.getRow() - 1, temp.getColumn(), temp.getData());
					}
				}
				else{
					if(temp.getColumn() > col){
						//Adds element with column adjusted by -1.
						minor.addElement(temp.getRow(), temp.getColumn() - 1, temp.getData());
					}
					else{
						//Adds element with no adjustments.
						minor.addElement(temp.getRow(), temp.getColumn(), temp.getData());
					}
				}
			}
			//Used to get next value in linked list and continue loop
			temp = temp.getNext();
		}
		//Returns the minor matrix.
		return minor;
	}

//This method prints out the entire Linked List (Matrix without 0's)
	public String toString(){
		String output = "";
		Node temp = new Node();
		temp = this.head;
		while(temp != null){
			output = output + temp.getRow() + " " + temp.getColumn() + " " + temp.getData() + "\n";
			temp = temp.getNext();
		}
		return output;
	}

//This method will return the size N of the matrix.
	public int getSize(){
		return this.size;
	}
}