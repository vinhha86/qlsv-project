<template>
    <div class="row pt-3" v-if="this.course != null">
        <div class="col-sm-12">
            <h4>Thông tin khóa học</h4>
            <div class="row pb-3 mt-4">
                <p class="col-sm-6">Mã khóa học: {{this.course.maKhoaHoc}}</p>
                <p class="col-sm-6">Tên khóa học: {{this.course.tenKhoaHoc}}</p>
                <p class="col-sm-6">Kiểu khóa học: {{this.course.kieuKhoaHoc == 0? 'Dài hạn':'Ngắn hạn'}}</p>
                <p class="col-sm-6">Số buổi học: {{this.course.soBuoi}}</p>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editCourseModal">Sửa thông tin</button>
                    &nbsp;
                    <button type="button" class="btn btn-danger" @click="deleteCourse(course.id)">Xóa khóa học</button>
                </div>
            </div>
            <hr>
        </div>
        <div class="col-sm-12">
            <h4>Danh sách các môn học</h4>
            <button type="button" class="btn btn-primary mb-2" >Thêm môn học</button>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <td class="text-center" scope="col" style="width: 2%">STT</td>
                    <td class="text-center" scope="col">Mã môn học</td>
                    <td class="text-center" scope="col">Tên môn học</td>
                    <td class="text-center" scope="col">Tổng số buổi học</td>
                    <td class="text-center" scope="col">Thao tác</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="text-center" scope="col" style="width: 2%">1</td>
                    <td class="text-center" scope="col">WEB-001</td>
                    <td class="text-center" scope="col">Nhập môn web cơ bản</td>
                    <td class="text-center" scope="col">10</td>
                    <td class="text-center" scope="col">
                        <button type="button" class="btn btn-outline-primary"><i class="fa fa-pencil"></i></button>
                        &nbsp;
                        <button type="button" class="btn btn-outline-danger"><i class="fa fa-trash-o"></i></button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

    </div>

</template>

<script>
    import {eventBus} from '../../main'
    import axios from 'axios';

    export default {
        name: "SubjectList",
        // props: ['selectedCourse'],
        mounted() {
           eventBus.$on('setCourse', (seletedCourse) => {
               this.course = seletedCourse;
           })
        },
        data() {
            return {
                course: null,
            }
        },
        methods: {
            deleteCourse(id) {
                axios.delete('http://localhost:8080/api/course/delete', {params: {id: id}})
                    .then(res => {
                        if (res.status == 200) {
                            eventBus.$emit('updateCourseList');
                            this.course = null;
                        }
                    }).catch(err => console.log(err));
            }
        }
    }
</script>

<style scoped>

</style>