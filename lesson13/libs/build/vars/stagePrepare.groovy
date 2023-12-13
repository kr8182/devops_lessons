def call(apps, action, parallel_executors) {
    buildStageList = []
    buildParallelMap = [:]
    apps.eachWithIndex { app, value, i ->
        Integer lock_id = i % parallel_executors
        println lock_id
        if (action == "build") {
            buildParallelMap.put(app, stageBuildCreate(app, value, lock_id))
        }
        if (action == "upload") {
            buildParallelMap.put(app, stageUploadCreate(app, value, lock_id))
        }
    }
    buildStageList.add(buildParallelMap)
    return buildStageList
}