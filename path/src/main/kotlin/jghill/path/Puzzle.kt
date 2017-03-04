package jghill.path

import java.io.File
import javax.swing.JFileChooser

fun main(args: Array<String>) {
    
    val file = chooseFile()
    println(file ?: "No file selected.")
    
    val board = file?.readText()
    val lineArray : List<String>? = board?.split(System.lineSeparator())
    val spaceArray : List<List<String>>? = lineArray?.map{s -> s.split(" ")}
    
    
}

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