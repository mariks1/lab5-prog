package utility

import data.Difficulty
import java.io.FileNotFoundException
import java.lang.IllegalArgumentException
import java.util.Scanner


class IsEmptyException: Exception(){
}
class NotInTrueFormatException: Exception(){}


class Request(private var scan: Scanner)    {

    fun setScan(scan: Scanner) {
        this.scan = scan
    }
    fun getScan(): Scanner {
        return scan
    }
    private fun askForName(): String {
        var name: String
        while (true) {
            try {
                println("Введите название лабораторной работы")
                name = Console.nextLine()[0]
                if (name == "") throw IsEmptyException()
                if (name.contains(Regex("[^a-z^A-z]"))) throw NotInTrueFormatException()
                break
            } catch (e: IsEmptyException) {
                println("Название не может быть пустым!")
            } catch (e: NotInTrueFormatException) {
                println("Название должно содержать только буквы!")
            }
        }
        return name
    }


    private fun askForX(): Int {
        while (true) {
            println("Введите координату X")
            try {
                return Console.nextLine()[0].toInt()
            } catch (e: IllegalArgumentException) {
                println("Координата X должна быть целым числом!")
            }
        }
    }

    private fun askForY(): Double {
        while (true) {
            println("Введите координату Y")
            try {
                return Console.nextLine()[0].toDouble()
            } catch (e:IllegalArgumentException) {
                println("Координата Y должна быть вещественным числом!")
            }
        }
    }

    private fun askForMinimalPoint(): Int {
        while (true) {
            println("Введите минимальный балл")
            try {
                val minimalPoint: Int = Console.nextLine()[0].toInt()
                if (minimalPoint <= 0) {
                    throw NotInTrueFormatException()
                }
                return minimalPoint
            } catch (e: IllegalArgumentException) {
                println("Минимальный балл должен быть целым положительным числом!")
            } catch (e: NotInTrueFormatException) {
                println("Минимальный балл должен быть целым положительным числом!")
            }
        }
    }

    private fun askForDescription(): String {
        println("Введите описание лабораторной работы")
        var description: String = Console.nextLine()[0]
        while (description.isEmpty()) {
            println("Название не может быть пустым!")
            description = Console.nextLine()[0]
        }
        return description
    }

    private fun askForTunedInWorks(): Long {
        while (true) {
            println("Введите поле tunedInWorks")
            try {
                return Console.nextLine()[0].toLong()
            } catch (e: IllegalArgumentException) {
                println("Минимальный балл должен быть целым положительным числом!")
            }
        }
    }

    private fun askForDifficulty(): Difficulty {
        while (true) {
            println("Введите сложность лабораторной работы")
            Difficulty.getAllDiff()
            try {
                return Difficulty.valueOf(Console.nextLine()[0].uppercase())
            } catch (e: IllegalArgumentException) {
                println("Такой сложности не существует!")
            }
        }
    }

    private fun askForDisciplineName(): String {
        println("Введите название дисциплины")
        var name: String = Console.nextLine()[0]
        while (name.isEmpty()) {
            println("Название не может быть пустым!")
            name = Console.nextLine()[0]
        }
        return name
    }

    private fun askForDisciplineHours(): Int {
        while (true) {
            println("Введите количество часов для практики")
            try {
                return Console.nextLine()[0].toInt()
            } catch (e: IllegalArgumentException) {
                println("Количество часов должно быть целым числом!")
            }
        }
    }

    fun askForFileName(): String{
        var fileName: String
        while(true){
            try {
                print("Введите название файла:\n>")
                fileName = scan.nextLine().trim()
                if(fileName == "") throw IsEmptyException()
                fileName += ".json"
                break
            } catch (e: IsEmptyException) {
                println("error: Название не может быть пустым!")
            } catch (e: NotInTrueFormatException){
                println("error: Название должно содержать только буквы!")
            } catch (e: FileNotFoundException) {
                println("error: Файла с таким названием нету!")
            }
        }
        return fileName
    }

    fun askForArgument(str1: String): String {
        if (str1 in listOf("insert", "update")) {
            val list = listOf(askForName(),askForX(),askForY(),askForMinimalPoint(),askForDescription(),askForTunedInWorks(),askForDifficulty(),askForDisciplineName(),askForDisciplineHours())
            return list.joinToString(separator = " ")
        }
        if (str1 in listOf("remove_greater")) {
            return askForMinimalPoint().toString()
        }
        return ""
    }
}


