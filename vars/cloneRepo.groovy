def call(String repoName = "ipu7-drivers") { 
	def repoUrls = [
		"ipu7-drivers": "https://github.com/intel/ipu7-drivers.git",
		"ipu6-drivers": "https://github.com/intel/ipu6-drivers.git"
	]

	def repoUrl = repoUrls[repoName]
	def targetDir = repoName

	echo "Cloning ${repoUrl}"

	try {
		sh """
		if [ ! -d "${targetDir}" ]; then
		git clone ${repoUrl} ${targetDir}
		echo "Repository cloned successfully into ${targetDir}!"
		else
		echo "Repository already exists: ${targetDir}"
		fi
		"""
		} catch (Exception e) {
		error "Failed to clone repository: ${e.message}"
	}
}

return this
