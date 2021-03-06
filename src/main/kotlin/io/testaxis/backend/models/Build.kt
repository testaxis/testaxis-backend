package io.testaxis.backend.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.util.Date
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Build(
    @ManyToOne @JoinColumn(name = "project_id", nullable = false) var project: Project,
    var visible: Boolean = true,
    var status: BuildStatus = BuildStatus.UNKNOWN,
    var branch: String,
    var commit: String,
    var slug: String,
    var tag: String? = null,
    var pr: String? = null,
    var service: String? = null,
    var serviceBuild: String? = null,
    var serviceBuildUrl: String? = null,
    var serviceJob: String? = null,
    @JsonIgnore @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL]) @JoinColumn(name = "build_id")
    var testCaseExecutions: MutableList<TestCaseExecution> = mutableListOf(),
    @CreatedDate var createdAt: Date = Date(),
    @Suppress("ForbiddenComment") // TODO: Fix @CreatedDate and @LastModifiedDate annotations
    @LastModifiedDate var updatedAt: Date = Date(),
) : AbstractJpaPersistable<Long>()

enum class BuildStatus {
    SUCCESS,
    BUILD_FAILED,
    TESTS_FAILED,
    UNKNOWN
}
