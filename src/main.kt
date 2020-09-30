import java.lang.Exception
import java.util.*
import kotlin.system.exitProcess

var records : Map<String, Int> = mutableMapOf<String,Int>();
fun main() {
    var lives:Int = 8;
    var name:String="";
    var word:String="";
    var guessWord:String="";
    val scanner = Scanner(System.`in`);
    var chr:Char;
    var symbolsSet:MutableSet<Char> = mutableSetOf();

    while (name.length<2){
   println("Enter Name:")
    name = readLine().toString();
    }

    println("Hello $name, Let’s play Hangman!");

    while(word.length<2){
        println("Enter Word: ")
        word = readLine().toString();
    }
    for (i in word) guessWord+='_';
    println("Game is starting...")
    //let's go
    while(word != guessWord && lives>0){
        print("Current Word is: $guessWord \n ");
        chr = readChar(scanner).toLowerCase();

        var guessed:Boolean = false;
        for (i in word.indices){
            if(word[i].toLowerCase()==chr){
               guessed=true;
                guessWord = guessWord.slice(0 until i)+chr+guessWord.slice(i+1 until guessWord.length);
                }
            }
        if (guessed) println("Yes, it is there!!!");
        else {
            if(symbolsSet.contains(chr)){
                println("You already tried this character")
            } else {
                lives--;
                println("There is no such character");
            }
            println("Lives remaining: $lives");
        }
        symbolsSet.add(chr);
    }
    records = records+ mapOf(name to lives);
    if(lives==0) println("Sorry, you lost… The word was: $word");
    else println("Congratulations!")
    wannaPlayAgain(scanner);



}

fun readChar(scanner: Scanner):Char{
    var chr:Char;
    try {
        print("Enter Character : ");
        chr = scanner.next().single();
    } catch(e:Exception) {
        println("Please, enter a valid character!");
        return readChar(scanner);
    }
    return chr;
}

fun wannaPlayAgain(scanner: Scanner){
    println("Want to play again? (Y/N/H)");
    var ans:Char;
    try {
        ans = readChar(scanner).toUpperCase();
    }catch (e:Exception){
        println("Enter valid answer!");
        return wannaPlayAgain(scanner);
    }
    if (ans=='Y'){
        return main()
    }
    else if (ans=='H'){
        println(records);
        //sortireba damaviwyda, vici droshi daviwvi
        wannaPlayAgain(scanner);
    }
    else if (ans=='N'){
        print("Thanks for playing!");
    }
}

