import java.util.*

fun main() {
    
    //Task 1
    val list = listOf(1, 1, 2, 2, 3, 3, 4, 4, 5)
    val result = singleNumber(list)
    println("single num: $result")
    
    //Task 2
    val coins = listOf(1, 5, 10, 20, 50)
    println("min coins: ${minCoinChange(coins, 200)}")
    
    //Task 3
    val words = listOf("kotlin", "kong", "kombucha")
    println("longest prefix: ${longestPrefix(words)}")
    
    //Task 4
  	var a = "1010"
   	var b = "1011"
 	val resultBinary = addBinary(a, b)
    println("sum: $resultBinary")
    
    //Task 5
    val steps = stairsCount(6)
    println("number of steps: $steps")
    
    //Task 6
    val myStructure = CustomStructure()
    myStructure.add(5)
    myStructure.add(10)
    println(myStructure.arr)
    myStructure.remove(10)
    println(myStructure.arr)
    
}

// Task 1
fun singleNumber(arr: List<Int>): Int? {
    val nonRepeating = mutableListOf<Int>()
    for (n in arr) {
        if (n in nonRepeating) {
            nonRepeating.remove(n)
        } else {
            nonRepeating.add(n)
        }
    }
    return nonRepeating.firstOrNull()
}



// Task 2
fun minCoinChange(coins: List<Int>, amount: Int): Int {
    val minCoins = IntArray(amount + 1) { Int.MAX_VALUE }
    minCoins[0] = 0

    for (coin in coins) {
        for (i in coin..amount) {
            if (i - coin >= 0) {
                minCoins[i] = minOf(minCoins[i], minCoins[i - coin] + 1)
            }
        }
    }

    return if (minCoins[amount] != Int.MAX_VALUE) minCoins[amount] else -1
}

// Task 3
fun longestPrefix(strs: List<String>): String {
    if (strs.isEmpty()) {
        return ""
    }

    for (i in strs[0].indices) {
        val c = strs[0][i]
        for (j in 1 until strs.size) {
            if (i == strs[j].length || strs[j][i] != c) {
                return strs[0].substring(0, i)
            }
        }
    }

    return strs[0]
}

// Task 4
fun addBinary(a: String, b: String): String {
    var result = ""
    var i = a.length - 1
    var j = b.length - 1
    var carry = 0

    while (i >= 0 || j >= 0) {
        var sum = carry
        if (i >= 0) {
            sum += a[i--] - '0'
        }
        if (j >= 0) {
            sum += b[j--] - '0'
        }
        carry = if (sum > 1) 1 else 0
        result = (sum % 2).toString() + result
    }

    if (carry == 1) {
        result = "1$result"
    }

    return result
}


// Task 5
fun stairsCount(n: Int): Int {
    var one = 1
    var two = 1

    for (i in 1 until n) {
        val temp = one
        one += two
        two = temp
    }

    return one
}


//Task 6
class CustomStructure {
    val arr = mutableListOf<Int>()
    private val map = HashMap<Int, Int>()

    fun add(x: Int) {
        if (!map.containsKey(x)) {
            val index = arr.size
            arr.add(x)
            map[x] = index
        }
    }

    fun remove(x: Int) {
        if (map.containsKey(x)) {
            val index = map[x]!!
            val last = arr.size - 1
            Collections.swap(arr, index, last)
            val lastElement = arr[last]
            arr.removeAt(last)
            map[lastElement] = index
            map.remove(x)
        }
    }
}