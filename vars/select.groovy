#!/usr/bin/env groovy

def call() {
	return [//return ['alpha1', 'beta', 'gamma']
	choice(name: 'REPO_CHOICE', choices: ['ipu7-drivers', 'ipu6-drivers'], description: 'Select the driver repo'),
	choice(name: 'TAG_CHOICE', choices: getTagsForUI('ipu7-drivers'), description: 'Select a Git tag for the chosen repo'),
	]
	//return [ 
	//	choice(name: 'select para', choices: ['alpha', 'beta', 'gamma'], description: 'show the menu'),
	//	string(name: 'ci tag', defaultValue: 'list', description: 'enter a tag')
	//]
}

def getTagsForUI(String repoName) {
	def repoPath = repoName

	if (!fileExists(repoPath)) {
		return ['(repo not cloned)']
	}

	sh "cd ${repoPath} && git fetch --tags --prune"
	def output = sh(script: "cd ${repoPath} && git tag", returnStdout: true).trim()
	def tags = output ? output.split('\n') : []

	return tags.sort().reverse()
}

def getGitTags(String repoName) {
	return getTagsForUI(repoName)
}

def selectTag(tags) {
	println "Available tags: ${tags.join(', ')}"
	def selectedTag = tags ? tags[0] : ""
	println "Selected Tag: ${selectedTag}"
	return selectedTag
}

/* original
def getGitTags() {
	def repoPath = "ipu7-drivers"

	if (!fileExists(repoPath)) {
	error "Repository not found at ${repoPath}. Run cloneRepo() first."
	}

	sh "cd ${repoPath} && git fetch --tags --prune"
	def output = sh(script: "cd ${repoPath} && git tag", returnStdout: true).trim()

	return output ? output.split('\n') : ['20241206', '20250108_1730_388']
}

def selectTag(tags) {
	println "Available tags: ${tags.join(', ')}"

	def selectedTag = tags[-1]
	println "Selected Tag: ${selectedTag}"

	return selectedTag
}

*/

return this
