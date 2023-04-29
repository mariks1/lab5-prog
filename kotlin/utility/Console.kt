package utility

import java.util.Scanner


class Console {
    companion object {
        private val scannerIn: Scanner = Scanner(System.`in`)



        fun nextLine(): Array<String> {
            return (scannerIn.nextLine().trim() + " ").split(" ").toTypedArray()
        }
        fun next(): String {
            return scannerIn.next()
        }




    }



}