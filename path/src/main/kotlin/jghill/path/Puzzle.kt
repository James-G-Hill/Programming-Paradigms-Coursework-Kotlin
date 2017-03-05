package jghill.path

import java.io.File
import javax.swing.JFileChooser

/**
 *  The main function of the program.
 */
fun main(args: Array<String>) {
    
    val file = chooseFile()
    println(file ?: "No file selected.")
    
    val board = file?.readText()
    val lineArray : List<String>? = board?.split(System.lineSeparator())
    val spaceArray : List<List<String>>? = lineArray?.map{s -> s.split(" ")}
    
    var atZero: Boolean = false
    
    val currentCell: Cell = Cell(0, 0)
    var trail: MutableList<Cell> = arrayListOf()
    
    while(atZero) {
        
        trail.add(currentCell)
        val thisNumber = getNumberAtCell(currentCell, spaceArray)
        
    }
    
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
data class Cell(val rowNo: Int, val colNo: Int)

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