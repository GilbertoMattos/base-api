package br.com.tecnoinfo.baseapi.service

import br.com.tecnoinfo.baseapi.exception.RegistryNotFoundException
import br.com.tecnoinfo.baseapi.mapper.PagetoPageDetMapper
import br.com.tecnoinfo.baseapi.mapper.ProjectRequestToModelMapper
import br.com.tecnoinfo.baseapi.mapper.ProjectToResponseMapper
import br.com.tecnoinfo.baseapi.model.Project
import br.com.tecnoinfo.baseapi.projection.ProjectNameProjection
import br.com.tecnoinfo.baseapi.repository.ProjectRepository
import br.com.tecnoinfo.baseapi.specification.QueryOperation
import br.com.tecnoinfo.baseapi.specification.SpecificationProject
import br.com.tecnoinfo.baseapi.wrapper.request.ProjectFilterRequest
import br.com.tecnoinfo.baseapi.wrapper.request.SaveProjectRequest
import br.com.tecnoinfo.baseapi.wrapper.response.ApiResponse
import br.com.tecnoinfo.baseapi.wrapper.response.ProjectResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val requestToModelMapper: ProjectRequestToModelMapper,
    private val projectToResponseMapper: ProjectToResponseMapper,
    private val pagetoPageDetMapper: PagetoPageDetMapper,
    private val lojaService: LojaService
) {

    fun find(filter: ProjectFilterRequest? = null, pegeable: Pageable): Page<Project> {
        return projectRepository.findAll(
            Specification.where(
                SpecificationProject.name(filter?.name ?: ProjectFilterRequest().name, QueryOperation.LIKE)
                    .and(
                        SpecificationProject.id(
                            filter?.id
                                ?: ProjectFilterRequest().id, QueryOperation.EQUALS
                        )
                    )
                    .and(
                        SpecificationProject.creationDate(
                            filter?.createAt
                                ?: ProjectFilterRequest().createAt
                        )
                    )
            ),
            pegeable
        )
    }

    fun findPaginacao(filter: ProjectFilterRequest? = null, pegeable: Pageable): ApiResponse<List<ProjectResponse>> {
        return projectRepository.findAll(
            Specification.where(
                SpecificationProject.name(filter?.name ?: ProjectFilterRequest().name, QueryOperation.LIKE)
                    .and(
                        SpecificationProject.id(
                            filter?.id
                                ?: ProjectFilterRequest().id, QueryOperation.EQUALS
                        )
                    )
                    .and(
                        SpecificationProject.creationDate(
                            filter?.createAt
                                ?: ProjectFilterRequest().createAt
                        )
                    ).and(
                        SpecificationProject.size(
                            filter?.sizeProject
                                ?: ProjectFilterRequest().sizeProject
                        )
                    )
            ),
            pegeable
        ).let {
            ApiResponse(data = projectToResponseMapper.map(it.content), page = pagetoPageDetMapper.map(it))
        }
    }

    fun findByName(name: String, pegeable: Pageable) = projectRepository.findByName(name, pegeable)

    fun findById(id: Long): ProjectResponse {
        with(projectRepository.findById(id)) {
            return if (this.isPresent) {
                projectToResponseMapper.map(arrayListOf(this.get()))[0]
            } else {
                throw RegistryNotFoundException()
            }
        }
    }

    fun findNameAllProjects(): List<ProjectNameProjection> {
        return projectRepository.findNameProjects()
    }

    fun save(payload: SaveProjectRequest): ProjectResponse {
        with(projectRepository.save(requestToModelMapper.map(payload))) {
            return projectToResponseMapper.map(arrayListOf(this))[0]
        }
    }
}