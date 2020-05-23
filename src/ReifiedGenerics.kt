import java.util.*
import kotlin.random.Random

class Person(firstName: String, lastName: String, yearOfBirth: Int) {
    var fullName = "$firstName $lastName"
    var age: Int

    init {
        age = Year.actualYear - yearOfBirth
    }
}

object Year {
    val actualYear = Calendar.getInstance().get(Calendar.YEAR)
}

fun <T : Person> printPersonFullName(
        clazz: Class<T>,
        list: List<Person>,
        fullName: Person.() -> String
) {
    if (list.isNotEmpty()) {
        list.forEach {
            if (clazz.isInstance(it)) println("${it.javaClass.name} - ${it.fullName()}")
        }
    }
}

inline fun <reified T : Person> printPersonFullName(
        list: List<Person>,
        fullName: Person.() -> String
) {
    if (list.isNotEmpty()) {
        list.filterIsInstance<T>()
                .forEach { println("${it.javaClass.name} - ${it.fullName()}") }
    }
}

fun main() {
    var firstName = listOf("José", "Maria", "Carlos", "Juliana", "Pedro", "Fernanda", "Gabriel", "Bruna")
    var lastName = listOf("Silva", "Souza", "Costa", "Santos", "Oliveira", "Pereira", "Rodrigues", "Almeida")

    var listPerson =
            mutableListOf(Person(firstName.random(), lastName.random(), Random.nextInt(1900, Year.actualYear)))

    for (index in 0..5) {
        listPerson.add(Person(firstName.random(), lastName.random(), Random.nextInt(1900, Year.actualYear)))
    }

    printPersonFullName(Person::class.java, listPerson, Person::fullName)
    printPersonFullName<Person>(listPerson, Person::fullName)
}