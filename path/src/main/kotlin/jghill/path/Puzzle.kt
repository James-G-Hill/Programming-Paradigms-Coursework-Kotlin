package jghill.path

import java.io.File
import javax.swing.JFileChooser

var atZero: Boolean = false

/**
 *  The main function of the program.
 */
fun main(args: Array<String>) {
    
    val file = chooseFile()
    println(file ?: "No file selected.")
    
    val board = file?.readText()
    val lineArray : List<String> = board!!.split(System.lineSeparator())
    val spaceArray : List<List<String>> = lineArray.map{s -> s.split(" ")}
    
    val currentCell: Cell = Cell(0, 0)
    val finalCell: Cell = Cell(lineArray.size - 1, spaceArray.size - 1)
    
    var trail: MutableList<Cell> = arrayListOf()
    val visited: MutableList<Cell> = arrayListOf()
    
    while(atZero) {
        
        trail.add(currentCell)
        visited.add(currentCell)
        val thisNumber = getNumberAtCell(currentCell, spaceArray)
        
        when() {
            
            
            
        }
        
    }
    
}

/**
 *  Checks whether a move to a new position is valid.
 */
fun moveIsValid(
        c: Cell,
        spaceArray: List<List<String>>,
        s: String,
        n: Int): Boolean {
    
    var valid: Boolean = false
    when(s) {
        "R" -> if(!c.Right && (c.colNo + n) < spaceArray.size) valid = true
        "D" -> if(!c.Down && (c.rowNo + n) < spaceArray.size) valid = true
        "L" -> if(!c.Left && (c.colNo - n) >= 0) valid = true
        "U" -> if(!c.Up && (c.rowNo + n) >= 0) valid = true
    }
    return valid
}

/**
 *  Added a function for returning the number at a given position in the
 *  multi-dimensional array.
 */
fun getNumberAtCell(c: Cell, spaceArray: List<List<String>>?): Int? {
    return spaceArray?.get(c.rowNo)?.get(c.colNo)?.toInt()
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
    var Up: Boolean = false)

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