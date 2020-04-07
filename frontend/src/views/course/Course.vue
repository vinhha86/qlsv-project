<template>
    <div class="row">
        <div class="col-sm-4" style="background-color: #eee">
            <course-list></course-list>
        </div>
        <div class="col-sm-8">
            <subject-list></subject-list>
        </div>
    </div>
</template>

<script>
    import CourseList from "@/views/course/CourseList";
    import SubjectList from "@/views/course/SubjectList";

    export default {
        data() {
            return {

            }
        },
        components: {
            'course-list': CourseList,
            'subject-list': SubjectList
        },
    }
</script>

<style scoped>

</style>