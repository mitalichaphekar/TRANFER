import java.io.File
import java.io.IOException

fun main() {
    print("Enter the file path: ")
    val filePath = readLine() ?: return

    openFile(filePath)
}

fun checkFile(filepath: String): String? {
    val file = File(filepath)
    // Check if the file exists
    if (!file.exists()) {
        return "Error: File '$filepath' does not exist."
    }

    // Check if the file format is supported
    val fileExtension = file.extension.lowercase()
    val supportedFiles = setOf("txt", "pdf", "jpg", "png", "docx", "xlsx")
    
    if (fileExtension !in supportedFiles) {
        return "Error: File format '$fileExtension' is not supported."
    }

    return null
}

fun openFile(filepath: String) {
    val error = checkFile(filepath)
    if (error != null) {
        println(error)
        return
    }

    try {
        // Attempt to open the file using the default application
        println("Opening $filepath...")
        val process = ProcessBuilder("cmd", "/c", "start", filepath)
        process.start()
    } catch (e: IOException) {
        println("Error: Failed to open '$filepath'. ${e.message}")
    } catch (e: Exception) {
        println("An unexpected error occurred: ${e.message}")
    }
}
