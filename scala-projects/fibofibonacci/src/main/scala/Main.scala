object Fibonacci {
  def fibonacciIterative(n: Double): Double = {
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

  /**
   * This will fail for many reasons, but mostly because it is not tail recursive
   * The compiler only applies optimizations in this case only if this function matches the criteria of a tail recursive functions.
   * @param n
   */
  def fibonacciRecursive(n: Double): Double = {
    if (n <= 1f) {
      n
    } else {
      fibonacciRecursive(n - 1f) + fibonacciRecursive(n - 2f)
    }
  }

  def fibonacciTailRec(n: Double): Double = {
    def fibHelper(n: Double, a: Double, b: Double): Double = {
      if (n == 0.0) a
      else fibHelper(n - 1.0, b, a + b)
    }

    fibHelper(n, 0.0, 1.0)
  }


  def fibonacciTailRecManualTCO(n: Double): Double = {
    def fibHelper(n: Double, a: Double, b: Double): Double = {

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

      if (n == 0.0) a
      else fibHelper(n - 1.0, b, a + b)
    }

    fibHelper(n, 0.0, 1.0)
  }


  /**
   * @annotation.tailrec only marks a function to be optimized for the compiler
   * The compiler, will however, optimize this function regardless
   * @param n
   * @return
   */
  def fibonacciTailRecTCO(n: Double): Double = {
    @annotation.tailrec
    def fibHelper(n: Double, a: Double, b: Double): Double = {
      if (n == 0f) a
      else fibHelper(n - 1f, b, a + b)
    }

    fibHelper(n, 0f, 1f)
  }

//  /**
//   * This function is not a tail recursive function because of n as a multiplication factor of the last call
//   * This generates stack frames and so this function is not compilable in Scala
//   * @param n
//   * @return
//   */
//
//  def factorialMarkedTailRecTCO(n: Double): Double = {
//    @tailec
//    def factorialHelper(n: Double): Double = {
//      if (n <= 1f) 1f
//      else n * factorialHelper(n - 1f)
//    }
//
//    factorialHelper(n)
//  }

  def factorialUnMarkedTailRecTCO(n: Double): Double = {
    def factorialHelper(n: Double): Double = {
      if (n <= 1f) 1f
      else n * factorialHelper(n - 1f)
    }

    factorialHelper(n)
  }

  def main(args: Array[String]): Unit = {
    val n = 100f
    fibonacciIterativeRun(n)
//    fibonacciRecursiveRun(n)
    fibonacciTailRecRun(n)
    fibonacciTailRecTCORun(n)
    val factN = 100f
//    factorialMarkedTailRecTCORun(factN)
    factorialUnMarkedTailRecTCORun(factN)
  }

  def fibonacciIterativeRun(n: Double): Unit = {
    val result = fibonacciIterative(n)
    println(s"The $n-th Fibonacci number is: $result")
  }

  def fibonacciRecursiveRun(n: Double): Unit = {
    val result = fibonacciRecursive(n)
    println(s"The $n-th Fibonacci number is: $result")
  }

  def fibonacciTailRecRun(n: Double): Unit = {
    val result = fibonacciTailRec(n)
    println(s"The $n-th Fibonacci number is: $result")
  }
  def fibonacciTailRecTCORun(n: Double): Unit = {
    val result = fibonacciTailRecTCO(n)
    println(s"The $n-th Fibonacci number is: $result")
  }
//  def factorialMarkedTailRecTCORun(n: Double): Unit = {
//    val result = factorialMarkedTailRecTCO(n)
//    println(s"The $n-th Factorial number is: $result")
//  }

  def factorialUnMarkedTailRecTCORun(n: Double): Unit = {
    val result = factorialUnMarkedTailRecTCO(n)
    println(s"The $n-th Factorial number is: $result")
  }
}

