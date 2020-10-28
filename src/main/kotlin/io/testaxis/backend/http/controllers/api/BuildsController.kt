package io.testaxis.backend.http.controllers.api

import io.testaxis.backend.http.MustExist
import io.testaxis.backend.http.transformers.BuildTransformer
import io.testaxis.backend.models.Project
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/projects/{project}/builds")
@Validated
class BuildsController(val transformer: BuildTransformer) {
    @GetMapping
    fun index(@PathVariable @MustExist project: Project) =
        transformer.transform(project.builds, transformer::summary)
}
