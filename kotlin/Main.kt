import utility.*
import java.util.*


val scan = Scanner(System.`in`)
val request = Request(scan)
val fileManager = FileManager(request.askForFileName())
val collection = CollectionManager(fileManager.fileRead())
val commandExecution = CommandExecution()
val commandReader = CommandReader(collection, commandExecution)
val requestManager = RequestManager(collection, fileManager)



fun main() {

    /*          C:\\Users\\user\\IdeaProjects\\lab5-3\\src\\main\\kotlin\\l1l2.json          */
    commandReader.read()
}


/*17*/