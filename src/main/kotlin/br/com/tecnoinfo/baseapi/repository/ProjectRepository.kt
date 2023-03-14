package br.com.tecnoinfo.baseapi.repository

import br.com.tecnoinfo.baseapi.model.Project
import br.com.tecnoinfo.baseapi.projection.ProjectNameProjection
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface ProjectRepository : JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    fun findByName(name: String, pageable: Pageable): Page<Project>

    @Query("select name from PROJECT", nativeQuery = true)
    fun findNameProjects(pageable: Pageable): List<ProjectNameProjection>
}