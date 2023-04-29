package utility

import data.Difficulty
import java.lang.IllegalArgumentException
import java.util.Scanner


class IsEmptyException: Exception(){
}
class NotInTrueFormatException: Exception(){}


class Request {

    companion object {
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

        private fun askForFilePath(): String {
            println("Введите путь к файлу")
            var filePath: String = Console.nextLine()[0]
            while (filePath.isEmpty()) {
                println("путь не может быть пустым!")
                filePath = Console.nextLine()[0]
            }
            return filePath
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
}

