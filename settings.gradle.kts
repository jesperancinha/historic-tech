rootProject.name = "asnsei-the-right-waf"
include("talks:talk-10-kotlin-streams-good-bad:streams-gb-kafka")
include("talks:talk-10-kotlin-streams-good-bad:streams-gb-rabbitmq")
include("talks:talk-10-kotlin-streams-good-bad:streams-gb-single")
include("demo-projects:k2-compiler")
include("demo-projects:closed-world")
include("demo-projects:spot-on")
include("demo-projects:library-channels")
include("demo-projects:streams")
include("demo-projects:drinks-manager")
include("demo-projects:kotlin-keywords")
include("demo-projects:cooldrinks-juice-factory")
include("demo-projects:jeorg-overlay-group-1")
include("demo-projects:jeorg-overlays-group-1-spring")
include("demo-projects:coroutines-demo")
include("demo-projects:crac-service")
include("experiments:javalin-demo")
include("experiments:channels")
include("experiments:coroutine-builders")
include("experiments:annotations-demo")
include("demo-projects:k2-compiler")
findProject(":demo-projects:k2-compiler")?.name = "k2-compiler"
include("experiments:generics-demo")
findProject(":experiments:generics-demo")?.name = "generics-demo"
include("experiments:koin-example")
findProject(":experiments:koin-example")?.name = "koin-example"
include("experiments:supabase-demo")
findProject(":experiments:supabase-demo")?.name = "supabase-demo"
include("demo-projects:guessing-retries")
findProject(":demo-projects:guessing-retries")?.name = "guessing-retries"
include("demo-projects:the-arrow-shop")
findProject(":demo-projects:the-arrow-shop")?.name = "the-arrow-shop"
