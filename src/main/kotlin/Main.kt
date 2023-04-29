import utility.*



const val filePath = "C:\\Users\\user\\IdeaProjects\\lab5-3\\src\\main\\kotlin\\input.json"
val fileManager = FileManager(filePath)
val collection = CollectionManager(fileManager.fileRead())
val commandExecution = CommandExecution()
val commandReader = CommandReader(collection, commandExecution)

val requestManager = RequestManager(collection, fileManager)



fun main() {

    /*          C:\\Users\\user\\IdeaProjects\\lab5-3\\src\\main\\kotlin\\input.json          */
    commandReader.read()
}


/*17*/