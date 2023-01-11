package br.com.tecnoinfo.baseapi.wrapper.request

import br.com.tecnoinfo.baseapi.model.SizeEnum
import java.time.LocalDate

class ProjectFilterRequest(
    val id: Long? = null,
    val name: String? = null,
    val createAt: LocalDate? = null,
    val sizeProject: SizeEnum? = null
)