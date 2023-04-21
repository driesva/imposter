package io.gatehill.imposter.plugin.openapi.service.valueprovider

import io.gatehill.imposter.util.DateTimeUtil
import io.swagger.v3.oas.models.media.Schema
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit
import java.util.*

internal val DEFAULT_VALUE_PROVIDERS: Map<String, DefaultValueProvider<*>> = mapOf(
        "string" to StringDefaultValueProvider(),
        "number" to object : DefaultValueProvider<Double> {
            override fun provide(schema: Schema<*>) = 0.0
        },
        "integer" to object : DefaultValueProvider<Int> {
            override fun provide(schema: Schema<*>) = 0
        },
        "boolean" to object : DefaultValueProvider<Boolean> {
            override fun provide(schema: Schema<*>) = false
        },
)

internal interface DefaultValueProvider<T> {
    fun provide(schema: Schema<*>): T
}

internal class StringDefaultValueProvider : DefaultValueProvider<String> {
    override fun provide(schema: Schema<*>): String {
        // TODO make these configurable
        return schema.format?.let {
            // see https://swagger.io/docs/specification/data-models/data-types/
            when (schema.format) {
                "date" -> return DateTimeUtil.DATE_FORMATTER.format(
                        LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
                )
                "date-time" -> return DateTimeUtil.DATE_TIME_FORMATTER.format(
                        OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS)
                )
                "password" -> return "changeme"

                // base64-encoded characters
                "byte" -> return "SW1wb3N0ZXI0bGlmZQo="

                "email" -> return "test@example.com"
                "uuid", "guid" -> return UUID.randomUUID().toString()
                else -> ""
            }
        } ?: ""
    }
}
