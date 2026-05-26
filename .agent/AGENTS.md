# Agent Instructions

## Checklist Pre-Commit

- Cada vez crees código, crea un nuevo commit. Pero no hagas push si no te lo digo. 
- Antes de cada commit, ejecuta la tarea `./gradlew :app:compileDebugKotlin` para asegurarte de que el código compila correctamente.

1. **Compilación**: Ejecuta la tarea `./gradlew :app:compileDebugKotlin` para asegurarte de que el código compila correctamente.
2. **Análisis Lint**: Realiza un `analyze_file` de todos los ficherso modificados.
3. **Calidad de código**: Asegurar que no se han añadido nuevos warnings.
4. **Commit**: Crear un commit con los cambios (un commit por función específica).
5. **Verificación de cambios**: No realizar push a menos que se indique explicitamente en las instrucciones.