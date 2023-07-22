import java.util.*

class CamelcaseToSnakeCase {

    fun camelcaseToSnakeCase(map: Map<String, Any>): Map<String, Any> {
        return map.mapKeys { entry ->
            // Convert camelCase to snake_case
            entry.key.replace(Regex("([a-z])([A-Z])"), "$1_$2").lowercase(Locale.getDefault())
        }.mapValues { entry ->
            // Recursively convert nested maps and lists
            when (val value = entry.value) {
                is Map<*, *> -> {
                    @Suppress("UNCHECKED_CAST")
                    if (value.all { (k, _) -> k is String }) {
                        camelcaseToSnakeCase(value as Map<String, Any>)
                    } else {
                        value
                    }
                }
                is List<*> -> value.map {
                    if (it is Map<*, *> && it.all { (k, _) -> k is String }) {
                        @Suppress("UNCHECKED_CAST")
                        camelcaseToSnakeCase(it as Map<String, Any>)
                    } else it
                }
                else -> value
            }
        }
    }
}