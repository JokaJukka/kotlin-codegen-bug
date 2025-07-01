import com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask

plugins {
    id("com.netflix.dgs.codegen") version "8.1.0"
    java
    kotlin("jvm") version "2.1.20"
}

group = "com.jokajukka.dgs.codegen"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.18.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    testImplementation(kotlin("test"))
}

tasks.withType<GenerateJavaTask> {
    packageName = "com.jokajukka.dgs.codegen.generated"
    language = "kotlin"
    generateDataTypes = true
    generateClient = true
    generateKotlinNullableClasses = true
    generateKotlinClosureProjections = true
    skipEntityQueries = true
    includeQueries = mutableListOf("timelogs")
    includeMutations = mutableListOf()

    schemaPaths = mutableListOf("${project.projectDir}/src/main/resources/sdl.graphqls")

    typeMapping = mutableMapOf(
        "BigInt" to "java.math.BigInteger",
        "Color" to "String",
        "Date" to "java.time.LocalDate",
        "ISO8601Date" to "String",
        "Time" to "java.time.OffsetDateTime",
        "Upload" to "String",
        "GroupID" to "String",
        "IssueID" to "String",
        "LabelID" to "String",
        "MergeRequestID" to "String",
        "NoteID" to "String",
        "ProjectID" to "String",
        "TimelogID" to "String",
        "UserID" to "String",
        /**
         * GitLab other IDs
         * Provided only for the codegen to work; if they are really used,
         * they should be rewritten to typed IDs
         */
        "BoardID" to "String",
        "DiscussionID" to "String",
        "EmailID" to "String",
        "GitlabErrorTrackingDetailedErrorID" to "String",
        "IncidentManagementTimelineEventID" to "String",
        "IncidentManagementTimelineEventTagID" to "String",
        "IssuableID" to "String",
        "ListID" to "String",
        "MergeRequestsClosingIssuesID" to "String",
        "NamespaceID" to "String",
        "NoteableID" to "String",
        "OrganizationsOrganizationID" to "String",
        "ProjectImportStateID" to "String",
        "SnippetID" to "String",
        "SystemNoteMetadataID" to "String",
        "UploadID" to "String",
        "UsersSavedReplyID" to "String",
        "WorkItemID" to "String",
        "WorkItemsRelatedWorkItemLinkID" to "String",
        "WorkItemsTypeID" to "String",
    )
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}