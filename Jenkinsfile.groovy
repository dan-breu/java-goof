

pipeline {
    agent any


    stages {
        
        stage('git clone') {
            steps {
                git branch: 'main',
                url: 'https://github.com/dan-breu/java-goof'
            }
        }

        stage('Build') {
            steps {
                echo 'Building..'
            }
        }

         stage('Snyk SCA') {
            steps {
                snykSecurity(
        		snykInstallation: 'snyk@latest', snykTokenId: 'snyk-api-token',
		    	severity: 'high', failOnIssues: false, monitorProjectOnBuild: false,
		    	additionalArguments: '--all-projects -debug', 
			)
/*
		snykSecurity(
        		snykInstallation: 'snyk@latest', snykTokenId: 'snyk-api-token',
		    	severity: 'critical', failOnIssues: false, monitorProjectOnBuild: true, <--- with true Create a project in snyk portal, Severity: only show the critical vuln.
		    	additionalArguments: '--all-projects -debug', 
			)
   */
            }
        }


        
    }
} 
