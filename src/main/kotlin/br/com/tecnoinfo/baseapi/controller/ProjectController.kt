package br.com.tecnoinfo.baseapi.controller

import br.com.tecnoinfo.baseapi.model.Project
import br.com.tecnoinfo.baseapi.projection.ProjectNameProjection
import br.com.tecnoinfo.baseapi.service.ProjectService
import br.com.tecnoinfo.baseapi.wrapper.request.ProjectFilterRequest
import br.com.tecnoinfo.baseapi.wrapper.request.SaveProjectRequest
import br.com.tecnoinfo.baseapi.wrapper.response.ApiResponse
import br.com.tecnoinfo.baseapi.wrapper.response.ProjectResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("project")
class ProjectController(private val projectService: ProjectService) {

    @GetMapping
    fun getAll(
        @RequestBody(required = false) filter: ProjectFilterRequest? = null,
        @PageableDefault(size = 10, page = 0) pageable: Pageable
    ): Page<Project> {
        return projectService.find(filter, pageable)
    }

    @GetMapping("paginacao")
    fun find(
        filter: ProjectFilterRequest? = null,
        @PageableDefault(size = 10, page = 0) pageable: Pageable
    ): ApiResponse<List<ProjectResponse>> {
        return projectService.findPaginacao(filter, pageable)
    }

    @GetMapping("{id}")
    fun findByName(@PathVariable id: Long): ApiResponse<ProjectResponse> {
        return ApiResponse(data = projectService.findById(id))
    }

    @GetMapping("projection")
    fun findNameAllProject(): ApiResponse<List<ProjectNameProjection>> {
        return ApiResponse(data = projectService.findNameAllProjects())
    }

    @PostMapping
    fun save(
        @RequestBody @Valid payload: SaveProjectRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<ApiResponse<ProjectResponse>> {
        with(projectService.save(payload)) {
            val uri = uriBuilder.path("/project/{id}").buildAndExpand(this.id).toUri()
            return ResponseEntity.created(uri).body(ApiResponse(data = this))
        }
    }
}