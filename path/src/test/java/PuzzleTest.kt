import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

/**
 * Tests for the Puzzle.kt file.
 * 
 * @author James
 */
public class PuzzleTest {
    
    val testString : String = "7 4 4 6 6 3 2 2 6 8" +
            "3 3 6 5 4 3 7 2 8 3" +
            "4 1 6 6 2 4 4 4 7 4" +
            "4 5 3 4 3 5 4 4 8 5" +
            "5 1 4 6 6 5 0 7 1 4" +
            "2 6 9 4 9 7 7 9 1 4" +
            "3 5 4 0 6 4 5 5 5 6" +
            "6 6 2 3 4 7 1 2 3 3" +
            "3 5 4 3 6 5 4 5 2 6" +
            "3 9 3 5 1 1 5 4 6 0"
    
    fun fileTest() {
        
        val file : File = File("C:\\Users\\James\\Dropbox\\Birkbeck\\MSc Advanced Computing Technologies\\Programming Paradigms\\Coursework 3\\Kotlin Game Board 1.txt")
        val fileString : String = file.toString()
        Assert.assertEquals(fileString, testString, "Test are not equal: " + testString)
        
    }
    
}