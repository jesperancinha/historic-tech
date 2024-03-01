object FibonacciIterative {
  def fibonacci(n: Double): Double = {
    var a = 0f
    var b = 1f
    var i = 0f
    while (i < n) {
      val temp = b
      b = a + b
      a = temp
      i += 1
    }
    a
  }

  def main(args: Array[String]): Unit = {
    val n = 100f
    val result = fibonacci(n)
    println(s"The $n-th Fibonacci number is: $result")
  }
}
