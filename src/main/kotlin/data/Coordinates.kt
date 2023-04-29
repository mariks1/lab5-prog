package data

import kotlinx.serialization.Serializable


@Serializable
class Coordinates(private val x: Int,
                  private val y: Double) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Coordinates

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y.hashCode()
        return result
    }

    override fun toString(): String {
        return "Coordinates(x=$x, y=$y)"
    }

}

