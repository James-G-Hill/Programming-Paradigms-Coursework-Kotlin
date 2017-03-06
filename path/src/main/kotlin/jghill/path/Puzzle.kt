package jghill.path

import java.io.File
import java.util.Stack
import javax.swing.JFileChooser

var trail: Stack<Cell> = Stack()
val visited: MutableList<Cell> = arrayListOf()

/**
 *  The main function of the program.
 */
fun main(args: Array<String>) {
    
    val file = chooseFile()
    println(file ?: "No file selected.")
    
    if(file != null) {
        solve(file)
    }
    
}

fun solve(file: File) {
     
    val board = file.readText()
    val lineArray : List<String> = board.split(System.lineSeparator())
    val spaceArray : List<List<String>> = lineArray.map{s -> s.split(" ")}
    
    var currentCell: Cell = Cell(0, 0)
    val finalCell: Cell = Cell(lineArray.size - 1, spaceArray.size - 1)
    
    while(currentCell != finalCell) {
        
        if(!trail.contains(currentCell)) {
            trail.push(currentCell)
            visited.add(currentCell)
        }
        
        val row = currentCell.rowNo
        val col = currentCell.colNo
        
        val thisNumber: Int = getNumberAtCell(currentCell, spaceArray)
        
        var nextCell: Cell
        
        if(moveIsValid(currentCell, spaceArray, 'R', thisNumber)) {
            currentCell.Right = true
            nextCell = Cell(row, col + thisNumber)
            if (visited.contains(nextCell)) {
                currentCell = visited.get(visited.indexOf(nextCell))
            } else {
                currentCell = nextCell
            }
        } else if (moveIsValid(currentCell, spaceArray, 'D', thisNumber)) {
            currentCell.Down = true
            nextCell = Cell(row + thisNumber, col)
            if (visited.contains(nextCell)) {
                currentCell = visited.get(visited.indexOf(nextCell))
            } else {
                currentCell = nextCell
            }
        } else if (moveIsValid(currentCell, spaceArray, 'L', thisNumber)) {
            currentCell.Left = true
            nextCell = Cell(row, col - thisNumber)
            if (visited.contains(nextCell)) {
                currentCell = visited.get(visited.indexOf(nextCell))
            } else {
                currentCell = nextCell
            }
        } else if (moveIsValid(currentCell, spaceArray, 'U', thisNumber)) {
            currentCell.Up = true
            nextCell = Cell(row - thisNumber, col)
            if (visited.contains(nextCell)) {
                currentCell = visited.get(visited.indexOf(nextCell))
            } else {
                currentCell = nextCell
            }
        } else {
            currentCell.Right = true
            currentCell.Down = true
            currentCell.Left = true
            currentCell.Up = true
            trail.pop()
            currentCell = trail.last()
        }
        
    }
    
    printFinalResult(trail)
    
}

/**
 *  Print the final results of the program.
 */
fun printFinalResult(result: Stack<Cell>) {
    
    var output: String = "(9,9))"
    while(result.size > 0) {
        val c = result.pop()
        output = "(" + c.rowNo + "," + c.colNo + ")" + output
    }
    println("(" + output)
    
}

/**
 *  Checks whether a move to a new position is valid.
 */
fun moveIsValid(
        c: Cell,
        spaceArray: List<List<String>>,
        s: Char,
        n: Int): Boolean {
    
    val row = c.rowNo
    val col = c.colNo
    
    var valid: Boolean = true
    when(s) {
        'R' -> if(c.Right || (col + n) >= spaceArray.size || visited.contains(Cell(row, col + n))) valid = false
        'D' -> if(c.Down || (row + n) >= spaceArray.size || visited.contains(Cell(row + n, col))) valid = false
        'L' -> if(c.Left || (col - n) < 0 || visited.contains(Cell(row, col - n))) valid = false
        'U' -> if(c.Up || (row - n) < 0 || visited.contains(Cell(row - n, col))) valid = false
    }
    return valid
}

/**
 *  Added a function for returning the number at a given position in the
 *  multi-dimensional array.
 */
fun getNumberAtCell(c: Cell, spaceArray: List<List<String>>?): Int {
    return spaceArray!!.get(c.rowNo).get(c.colNo).toInt()
}

/**
 *  A data class to represent a cell in the array of cells.
 */
data class Cell(
    val rowNo: Int,
    val colNo: Int,
    var Right: Boolean = false,
    var Down: Boolean = false,
    var Left: Boolean = false,
    var Up: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        
        other as Cell
        if (this.rowNo == other.rowNo && this.colNo == other.colNo) return true
        
        return false
    }
}

/**
 *  A function for choosing the file containing the puzzle that must be solved.
 */
fun chooseFile(): File? {
    
    val chooser = JFileChooser()
    val theFileResult = chooser.showOpenDialog(null)
    
    chooser.setVisible(true)
    
    when(theFileResult) {
        JFileChooser.APPROVE_OPTION -> return chooser.getSelectedFile()
        JFileChooser.CANCEL_OPTION -> println("No longer choosing.")
        else -> println("Error: Unable to open the file.")
    }
    
    return null
}