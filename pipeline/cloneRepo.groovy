def cloneRepo() {
    def repoUrl = "https://github.com/intel/ipu7-drivers.git"
    def targetDir = "ipu7-drivers"

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
