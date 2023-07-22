import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CamelcaseToSnakeCaseTests {
    private lateinit var converter: CamelcaseToSnakeCase

    @Before
    fun setup() {
        converter = CamelcaseToSnakeCase()
    }

    @Test
    fun `should convert camelCase keys to snake_case keys`() {
        val inputMap1 = mapOf(
            "bookName" to "The Pragmatic Programmer",
            "price" to 29.99,
            "author" to mapOf(
                "firstName" to "Andy",
                "lastName" to "Hunt"
            )
        )

        val expectedMap1 = mapOf(
            "book_name" to "The Pragmatic Programmer",
            "price" to 29.99,
            "author" to mapOf(
                "first_name" to "Andy",
                "last_name" to "Hunt"
            )
        )

        val inputMap2 = mapOf(
            "bookName" to "The Pragmatic Programmer",
            "price" to 29.99,
            "authors" to listOf(
                mapOf(
                    "firstName" to "Andy",
                    "lastName" to "Hunt",
                    "phoneNumbers" to listOf(
                        mapOf("type" to "home", "phoneNumber" to "49 228 42150-0"),
                        mapOf("type" to "mobile", "phoneNumber" to "0173-9568472")
                    )
                ),
                mapOf(
                    "firstName" to "Dave",
                    "lastName" to "Thomas",
                    "phoneNumbers" to listOf(
                        mapOf("type" to "home", "phoneNumber" to "49 228 42150-0"),
                        mapOf("type" to "mobile", "phoneNumber" to "0173-9568472")
                    )
                )
            )
        )

        val expectedMap2 = mapOf(
            "book_name" to "The Pragmatic Programmer",
            "price" to 29.99,
            "authors" to listOf(
                mapOf(
                    "first_name" to "Andy",
                    "last_name" to "Hunt",
                    "phone_numbers" to listOf(
                        mapOf("type" to "home", "phone_number" to "49 228 42150-0"),
                        mapOf("type" to "mobile", "phone_number" to "0173-9568472")
                    )
                ),
                mapOf(
                    "first_name" to "Dave",
                    "last_name" to "Thomas",
                    "phone_numbers" to listOf(
                        mapOf("type" to "home", "phone_number" to "49 228 42150-0"),
                        mapOf("type" to "mobile", "phone_number" to "0173-9568472")
                    )
                )
            )
        )

        Assert.assertEquals(expectedMap1, converter.camelcaseToSnakeCase(inputMap1))
        Assert.assertEquals(expectedMap2, converter.camelcaseToSnakeCase(inputMap2))
    }
}