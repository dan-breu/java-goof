

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
		    	severity: 'critical', failOnIssues: false, failOnError: false, monitorProjectOnBuild: true,
		    	additionalArguments: '--all-projects -debug', 
			)
/*
		snykSecurity(
        		snykInstallation: 'snyk@latest', snykTokenId: 'snyk-api-token',
		    	severity: 'high', failOnIssues: false, failOnError: false, monitorProjectOnBuild: true, <--- with true Create a project in snyk portal, Severity: only show the high and upper vuln.
		    	additionalArguments: '--all-projects -debug', 
			)
   */
            }
        }

	stage('Snyk SAST') {
            steps {
                snykSecurity(
        		snykInstallation: 'snyk@latest', snykTokenId: 'snyk-api-token',
		    	failOnIssues: false, monitorProjectOnBuild: false,
		    	additionalArguments: '--code -debug', 
			)
            }
        }


        
    }
} 
