def call(app, value, lock_id) {
    return {
        stage(app) {
            lock("Upload-Lock-${lock_id}") {
                dir("lesson13/apps/${app}") {
                    sh "mvn -DskipTests -s settings.xml -Dmaven.repo.local=${WORKSPACE}/.m2/repository deploy"
                }
            }
        }
    }
}