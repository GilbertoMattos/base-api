package br.com.tecnoinfo.baseapi.wrapper.response

import br.com.tecnoinfo.baseapi.model.SizeEnum
import java.time.LocalDate

data class ProjectResponse(
        val id: Long = 0,
        val name: String = "",
        val creationDate: LocalDate = LocalDate.now(),
        val size: SizeEnum = SizeEnum.SMALL
)