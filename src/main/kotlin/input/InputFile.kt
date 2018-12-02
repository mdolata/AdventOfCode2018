package input

import java.io.File


class InputFile {
    val rootPath = "src\\main\\resources\\"

    fun getFile(path: String) = File(rootPath+path)

}