package modelo.json

import kotlinx.serialization.*

@Serializable
data class Zona(
    val cd: String,
    val s: String,
    val e: String
)
