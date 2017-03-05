package jghill.path

import java.io.File
import java.util.Stack
import javax.swing.JFileChooser

/**
 *  The main function of the program.
 */
fun main(args: Array<String>) {
    
    val file = chooseFile()
    println(file ?: "No file selected.")
    
    val board = file?.readText()
    val lineArray : List<String> = board!!.split(System.lineSeparator())
    val spaceArray : List<List<String>> = lineArray.map{s -> s.split(" ")}
    
    var currentCell: Cell = Cell(0, 0)
    val finalCell: Cell = Cell(lineArray.size - 1, spaceArray.size - 1)
    
    var trail: Stack<Cell> = Stack()
    val visited: MutableList<Cell> = arrayListOf()
    
    while(currentCell != finalCell) {
        
        trail.push(currentCell)
        visited.add(currentCell)
        
        val row = currentCell.rowNo
        val col = currentCell.colNo
        
        val thisNumber: Int = getNumberAtCell(currentCell, spaceArray)
        
        if(moveIsValid(currentCell, spaceArray, 'R', thisNumber)) {
            currentCell.Right = true
            currentCell = Cell(row, col + thisNumber)
        } else if (moveIsValid(currentCell, spaceArray, 'D', thisNumber)) {
            currentCell.Down = true
            currentCell = Cell(row + thisNumber, col)
        } else if (moveIsValid(currentCell, spaceArray, 'L', thisNumber)) {
            currentCell.Left = true
            currentCell = Cell(row, col - thisNumber)
        } else if (moveIsValid(currentCell, spaceArray, 'U', thisNumber)) {
            currentCell.Up = true
            currentCell = Cell(row - thisNumber, col)
        } else {
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
    
    var output: String = ")"
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
    
    var valid: Boolean = false
    when(s) {
        'R' -> if(!c.Right && (c.colNo + n) < spaceArray.size) valid = true
        'D' -> if(!c.Down && (c.rowNo + n) < spaceArray.size) valid = true
        'L' -> if(!c.Left && (c.colNo - n) >= 0) valid = true
        'U' -> if(!c.Up && (c.rowNo + n) >= 0) valid = true
    }
    return valid
}

/**
 *  Added a function for returning the number at a given position in the
 *  multi-dimensional array.
 */
fun getNumberAtCell(c: Cell, spaceArray: List<List<String>>?): Int {
    return spaceArray!!.get(c.rowNo)?.get(c.colNo)?.toInt()
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
)

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