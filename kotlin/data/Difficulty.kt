package data

enum class Difficulty(val level: Int) {
    VERY_EASY(1),
    EASY(2),
    NORMAL(3),
    INSANE(4),
    IMPOSSIBLE(5);
    companion object{
        fun getAllDiff(): String {
            return "VERY_EASY\n" +
                    "EASY\n" +
                    "NORMAL\n" +
                    "INSANE\n" +
                    "IMPOSSIBLE"
        }
        }
}

