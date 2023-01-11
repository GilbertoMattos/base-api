package br.com.tecnoinfo.baseapi.wrapper.request

import br.com.tecnoinfo.baseapi.model.SizeEnum
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class SaveProjectRequest(

    @field:NotEmpty
    @field:Size(min = 5, max = 50)
    val name: String? = "",

    @field:NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/SaoPaulo")
    val creationDate: LocalDate? = null,

    @field:NotNull
    val size: SizeEnum? = SizeEnum.SMALL
)