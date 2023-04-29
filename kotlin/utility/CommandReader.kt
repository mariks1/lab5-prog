package utility

import request
import java.io.File
import java.io.FileNotFoundException
import java.lang.IllegalArgumentException
import java.util.Scanner

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

    fun scriptMode(str: String) {
        try {
            val file = File(str)
            val scanFile = Scanner(file)
            if (!scanFile.hasNext()) throw IsEmptyException()
            val oldScan = request.getScan()
            request.setScan(scanFile)
            var newInput: Array<String>

            while (scanFile.hasNextLine()) {
                newInput = (scanFile.nextLine().trim() + " ").split(" ").toTypedArray()
                if ((newInput.size == 3 || newInput.size == 4) && newInput[1][0].toString() == "-") {
                    commandExecution.executeCommand(newInput[0] + " " + newInput[1], newInput[2])
                } else {
                    commandExecution.executeCommand(newInput[0], newInput[1])
                }
            }
            request.setScan(oldScan)
        } catch (e: FileNotFoundException) {
            println("error: Файл с таким именем не найден!")
        } catch (e: IsEmptyException) {
            println("error: Файл пустой!")
        }
    }

}

