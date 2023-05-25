package script

class Greeter {
    String name;

    String sayHello() {
        def greet = new Dependency().message
//        def greet = "hello,$name"
        println(greet)
        greet
    }
}

new Greeter()