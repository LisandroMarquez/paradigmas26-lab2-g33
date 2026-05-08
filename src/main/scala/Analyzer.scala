// =====================================================================
// Ejercicios 3 y 5: Detección y conteo de entidades
// =====================================================================

/**
 * Responsable de detectar entidades nombradas en texto libre y
 * producir estadísticas sobre ellas.
 */
object Analyzer {

  /**
   * Detecta las entidades del diccionario que aparecen en el texto dado.
   *
   * @param text       texto a analizar (ej: título o cuerpo de un post)
   * @param dictionary lista de entidades conocidas (cargadas desde los diccionarios)
   * @return lista de entidades cuyo texto aparece en el texto analizado
   *
   * TODO (Ejercicio 3): Implementar este método.
   *
   *   Para cada entidad en el diccionario, verificar si su texto aparece en el
   *   texto del post. Retornar únicamente las entidades que aparecen.
   *
   *   Ejemplo:
   *     text       = "Scala fue creado en EPFL por Martin Odersky"
   *     dictionary = List(
   *                    ProgrammingLanguage("Scala"),
   *                    University("EPFL"),
   *                    Person("Martin Odersky"),
   *                    Person("Ada Lovelace")   ← no aparece en el texto
   *                  )
   *     resultado  = List(
   *                    ProgrammingLanguage("Scala"),
   *                    University("EPFL"),
   *                    Person("Martin Odersky")
   *                  )
   */
  def detectEntities(text: String, dictionary: List[NamedEntity]): List[NamedEntity] = {
    /* 
    Filtramos las palabras del texto con:
    - Regex: [\\p{L}0-9]+
        @ [abc]: todo lo que sea "a", "b" o "c"
        @ \p{L}: cualquier letra Unicode
        @ 0-9: cualquier digito
        @ �Por qu� doble \\? Representa \ en Strings de Scala y es necesario para su correcto funcionamiento idk
        @ +: Encuentra uno o varios de estos caracteres especificados dentro de []
    - findAllIn: Busca *todas* las coincidencias del regex (devuelve algo del tipo Iterator[String])
    - toSet: igual a listas pero sin repeticiones (convierte a Set[String])

    Luego filtramos el diccionario corroborando que si se encuentra la entidad dentro del texto, permanece en el diccionario devuelto
    */

    val words =
      "[\\p{L}0-9]+".r
        .findAllIn(text.toLowerCase)
        .toSet

    dictionary.filter { ent =>
      words.contains(ent.text.toLowerCase)
    }
  }

  /**
   * Cuenta cuántas entidades de cada tipo fueron detectadas.
   *
   * @param entities lista de entidades detectadas
   * @return mapa de entityType → cantidad de apariciones
   *
   * TODO (Ejercicio 5): Implementar este método.
   *
   *   Ejemplo:
   *     entities = List(
   *                  Person("Alan Turing"),
   *                  ProgrammingLanguage("Scala"),
   *                  Person("Ada Lovelace"),
   *                  University("MIT")
   *                )
   *     resultado = Map(
   *                   "Person"              -> 2,
   *                   "ProgrammingLanguage" -> 1,
   *                   "University"          -> 1
   *                 )
   */
  def countByType(entities: List[NamedEntity]): Map[String, Int] = {
    entities
      .groupBy(_.entityType)
      .view
      .mapValues(_.size)
      .toMap
  }
}
