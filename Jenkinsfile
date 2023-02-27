
pipeline { 
    agent any 
    stages {
        stage('Build') { 
            steps { 
                echo "Build    11111111111111"
                sh 'make' 
            }
        }
        stage('Test'){
            steps {
               echo "Test  2222222222222222222"
                sh 'make check'
                junit 'reports/**/*.xml' 
            }
        }
        stage('Deploy') {
            steps {
                echo "Deploy  2222222222222222222"
                sh 'make publish'
            }
        }
    }
}
