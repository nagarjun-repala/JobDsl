job('maven_dsl') {
    description('Maven dsl project')

    scm{
        git('https://github.com/NagarjunRepala/simple-java-maven-app.git', 'master')
    }

    steps{
        maven{
            mavenInstallation('jenkins-maven')
            goals('-B -DskipTests clean package')
        }
        maven {
            mavenInstallation('jenkins-maven')
            goals('test')
        }        
        shell('''
            echo ************RUNNING THE JAR************************     
            java -jar /var/jenkins_home/workspace/maven_dsl/target/my-app-1.0-SNAPSHOT.jar
        ''')        
    }
    publishers {
        archiveArtifacts('target/*.jar')
        archiveJunit('target/surefire-reports/*.xml')
    }    
}
