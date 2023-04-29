package utility

import java.lang.IllegalArgumentException

class CommandReader(collection: CollectionManager, commandExecution: CommandExecution) {
    private val collectionManager: CollectionManager
    private val commandExecution: CommandExecution
    init {
        this.commandExecution = commandExecution
        this.collectionManager = collection
    }


    fun read() {
        println("Начало работы программы, для справки по командам вызовите help")
        println("Введите команду")
        try {
            var newInput = Console.nextLine()
            var str1 = newInput[0]; var str2 = newInput[1]
            while (true) {
                commandExecution.executeCommand(str1,str2)
                println("Введите команду")
                newInput = Console.nextLine()
                str1 = newInput[0]; str2 = newInput[1]
            }
        } catch (e: NoSuchElementException) {
            println("Некоректный ввод")
        } catch (e: IllegalArgumentException) {
            println("Некоректный ввод")
        }

    }
}

