structure IntInf = IntInf

fun fibonacci n =
  let
    fun fibIter n a b =
      if n = 0 then
        a
      else
        fibIter (n - 1) b (IntInf.+(a, b))
  in
    fibIter n 0 1
  end;
