    fun fibonacci n =
        let
            fun fibIter(a, b, count) =
                if count = 0 then b
                else fibIter(b, a + b, count - 1)
        in
            fibIter(0, 1, n)
        end;
    x