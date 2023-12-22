import io.javalin.Javalin
import io.javalin.http.Context
import io.javalin.http.Handler

fun main() {
    Javalin.create()
        .get("/") { ctx -> ctx.result("Hello World") }
        .before { ctx: Context? -> }
        .before("/path/*", Handler { ctx: Context? -> })
        .start(7070)

}
