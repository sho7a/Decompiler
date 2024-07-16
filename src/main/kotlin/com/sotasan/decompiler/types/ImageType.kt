package com.sotasan.decompiler.types

import com.sotasan.decompiler.models.FileModel

class ImageType : Type("icons/image.png", null) {

    override fun isFormat(fileModel: FileModel): Boolean = fileModel.name.lowercase().let {
        return it.endsWith(".png")
                || it.endsWith(".jpg")
                || it.endsWith(".jpeg")
                || it.endsWith(".gif")
    }

}