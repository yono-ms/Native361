/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.repository

import com.example.native361.Native361Application
import com.example.native361.repository.database.model.Repo
import com.example.native361.repository.database.model.User
import com.example.native361.repository.network.GitHubApi
import com.example.native361.repository.network.model.ReposResponse
import com.example.native361.repository.network.model.UsersResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

class GitHubRepository {
    companion object {
        private val logger: Logger =
            LoggerFactory.getLogger(GitHubRepository::class.java.simpleName)

        private const val API_USERS = "https://api.github.com/users/"
        private const val API_REPOS = "/repos"

        suspend fun getRepos(login: String): List<Repo> {
            logger.info("getRepos login=$login")
            var user = Native361Application.db.userDao().findByLogin(login)
            logger.info("user=$user")
            if (user == null) {
                // API
                val json = GitHubApi.get("$API_USERS$login")
                val model = jacksonObjectMapper().readValue<UsersResponse>(json)
                user = createEntity(model)
                logger.info("user=$user")
                Native361Application.db.userDao().insertAll(user)
            }
            var repos = Native361Application.db.repoDao().getAllByUser(user.id)
            logger.info("repos=$repos")
            if (repos.count() == 0) {
                // API
                val json = GitHubApi.get("$API_USERS$login$API_REPOS")
                val models = jacksonObjectMapper().readValue<List<ReposResponse>>(json)
                val items = mutableListOf<Repo>()
                for (model in models) {
                    val item = createEntity(model)
                    items.add(item)
                }
                repos = items.toList()
                logger.info("repos=$repos")
                Native361Application.db.repoDao().insertAll(*(repos.toTypedArray()))
            }
            return repos
        }

        private fun createEntity(response: UsersResponse): User {
            return User(
                response.avatar_url,
                response.bio,
                response.blog,
                response.company,
                response.created_at,
                response.email,
                response.events_url,
                response.followers,
                response.followers_url,
                response.following,
                response.following_url,
                response.gists_url,
                response.gravatar_id,
                response.hireable,
                response.html_url,
                response.id,
                response.location,
                response.login,
                response.name,
                response.node_id,
                response.organizations_url,
                response.public_gists,
                response.public_repos,
                response.received_events_url,
                response.repos_url,
                response.site_admin,
                response.starred_url,
                response.subscriptions_url,
                response.type,
                response.updated_at,
                response.url,
                Date()
            )
        }

        private fun createEntity(response: ReposResponse): Repo {
            return Repo(
                response.archive_url,
                response.archived,
                response.assignees_url,
                response.blobs_url,
                response.branches_url,
                response.clone_url,
                response.collaborators_url,
                response.comments_url,
                response.commits_url,
                response.compare_url,
                response.contents_url,
                response.contributors_url,
                response.created_at,
                response.default_branch,
                response.deployments_url,
                response.description,
                response.disabled,
                response.downloads_url,
                response.events_url,
                response.fork,
                response.forks,
                response.forks_count,
                response.forks_url,
                response.full_name,
                response.git_commits_url,
                response.git_refs_url,
                response.git_tags_url,
                response.git_url,
                response.has_downloads,
                response.has_issues,
                response.has_pages,
                response.has_projects,
                response.has_wiki,
                response.homepage,
                response.hooks_url,
                response.html_url,
                response.id,
                response.issue_comment_url,
                response.issue_events_url,
                response.issues_url,
                response.keys_url,
                response.labels_url,
                response.language,
                response.languages_url,
                response.license?.get("key") as String?,
                response.merges_url,
                response.milestones_url,
                response.mirror_url,
                response.full_name,
                response.node_id,
                response.notifications_url,
                response.open_issues,
                response.open_issues_count,
                response.owner["id"] as Int,
                response.private,
                response.pulls_url,
                response.pushed_at,
                response.releases_url,
                response.size,
                response.ssh_url,
                response.stargazers_count,
                response.stargazers_url,
                response.statuses_url,
                response.subscribers_url,
                response.subscription_url,
                response.svn_url,
                response.git_tags_url,
                response.teams_url,
                response.trees_url,
                response.updated_at,
                response.url,
                response.watchers,
                response.watchers_count,
                Date()
            )
        }
    }
}