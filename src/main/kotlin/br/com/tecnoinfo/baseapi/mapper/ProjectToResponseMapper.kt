package br.com.tecnoinfo.baseapi.mapper

import br.com.tecnoinfo.baseapi.model.Project
import br.com.tecnoinfo.baseapi.wrapper.response.ProjectResponse
import org.springframework.stereotype.Component

@Component
class ProjectToResponseMapper : Mapper<List<Project>, List<ProjectResponse>> {
    override fun map(param: List<Project>): List<ProjectResponse> {
        return param.map {
            ProjectResponse(it.id ?: -1, it.name, it.creationDate, it.size)
        }
    }
}