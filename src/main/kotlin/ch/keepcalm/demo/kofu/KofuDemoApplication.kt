package ch.keepcalm.demo.kofu

import org.springframework.fu.kofu.reactiveWebApplication

val app = reactiveWebApplication {

}

fun main() {
    app.run()
}
