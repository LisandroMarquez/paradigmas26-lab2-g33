// =====================================================================
// Ejercicio 2: Cargar diccionarios de entidades
// =====================================================================

/**
 * Responsable de cargar colecciones de entidades nombradas desde archivos.
 *
 * Un diccionario es un archivo de texto plano donde cada línea contiene
 * el nombre de una entidad conocida del mismo tipo.
 *
 * Ejemplo — data/people.txt:
 *   Martin Odersky
 *   Alan Turing
 *   Ada Lovelace
 *
 * Ejemplo — data/languages.txt:
 *   Scala
 *   Python
 *   Haskell
 */
object Dictionary {

  /**
   * Lee un archivo de diccionario y crea una lista de entidades del tipo indicado.
   *
   * @param filePath   ruta al archivo de diccionario (ej: "data/people.txt")
   * @param entityType tipo de entidad: "Person", "University", "ProgrammingLanguage", etc.
   * @return lista de NamedEntity del tipo correspondiente
   *
   * (Ejercicio 2): Implementar este método.
   *
   *   Pasos sugeridos:
   *     1. Leer las líneas del archivo
   *     2. Para cada línea, crear la instancia de la clase correcta
   *     3. Retornar la lista de entidades creadas
   *
   *   Para crear la clase correcta según el tipo se puede usar match:
   *
   */
  def loadFromFile(filePath: String, entityType: String): List[NamedEntity] = {
    
    FileIO.readLines(filePath).map(linea => entityType match {

      // Uso de match para decidir qué clase de entidad crear según el tipo indicado.

      case "Person" => new Person(linea)
      case "University" => new University(linea)
      case "Organization" => new Organization(linea)
      case "Place" => new Place(linea)
      case "Technology" => new Technology(linea)
      case "ProgrammingLanguage" => new ProgrammingLanguage(linea)
      case _ => throw new IllegalArgumentException(s"Tipo de entidad desconocido: $linea")
    })
  }

  /**
   * Carga todos los diccionarios disponibles y combina sus entidades.
   *
   * @return lista con todas las entidades de todos los diccionarios
   *
   * (Ejercicio 2): Implementar este método.
   *
   */
  def loadAll(): List[NamedEntity] = {

    // Cargar cada diccionario por separado usando loadFromFile, luego combinar los resultados.

    val languages = loadFromFile("data/languages.txt", "ProgrammingLanguage")
    val organizations = loadFromFile("data/organizations.txt", "Organization")
    val people = loadFromFile("data/people.txt", "Person")
    val places = loadFromFile("data/places.txt", "Place" )
    val universities = loadFromFile("data/universities.txt", "University")
    
    val All = languages ++ organizations ++ people ++ places ++ universities

    All
  }
}
