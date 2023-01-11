package br.com.tecnoinfo.baseapi.mapper

import br.com.tecnoinfo.baseapi.model.Project
import br.com.tecnoinfo.baseapi.model.SizeEnum
import br.com.tecnoinfo.baseapi.wrapper.request.SaveProjectRequest
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class ProjectRequestToModelMapper : Mapper<SaveProjectRequest, Project> {
    override fun map(param: SaveProjectRequest): Project {
        return Project(
            id = null,
            name = param.name ?: "",
            creationDate = param.creationDate ?: LocalDate.now(),
            size = param.size ?: SizeEnum.SMALL
        )
    }
}