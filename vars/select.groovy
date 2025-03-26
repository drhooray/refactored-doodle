#!/usr/bin/env groovy

def call() {
	return ['alpha1', 'beta', 'gamma']
	//return [ 
	//	choice(name: 'select para', choices: ['alpha', 'beta', 'gamma'], description: 'show the menu'),
	//	string(name: 'ci tag', defaultValue: 'list', description: 'enter a tag')
	//]
}

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

return this
