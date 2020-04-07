<template>
    <div class="modal fade" id="editCourseModal" data-backdrop="static" data-keyboard="false" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">
                        Chỉnh sửa khóa học
                    </h5>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label>Mã khóa học:</label>
                            <div class="input-group">
                                <input type="text" class="form-control" v-model="course.maKhoaHoc"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Tên khóa học:</label>
                            <div class="input-group">
                                <input type="text" class="form-control" v-model="course.tenKhoaHoc"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Kiểu khóa học:</label>
                            <select class="form-control"  v-model="course.kieuKhoaHoc">
                                <option selected disabled :value="null">--Chọn kiểu khóa học--</option>
                                <option value="0">Dài hạn</option>
                                <option value="1">Ngắn hạn</option>
                            </select>
                        </div>
                        <div class="form-group" v-if="course.kieuKhoaHoc != 0 & course.kieuKhoaHoc !==null">
                            <label>Số buổi</label>
                            <div class="input-group">
                                <input type="text" class="form-control" v-model="course.soBuoi"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" @click="onSubmitForm">Lưu lại</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Thoát</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    import {eventBus} from "@/main";

    export default {
        name: "EditCourseModal",
        mounted() {
            eventBus.$on('setCourse', (selectedCourse) => {
                this.course = selectedCourse;
            })
        },
        data() {
            return {
                course: {
                    maKhoaHoc: '',
                    tenKhoaHoc: '',
                    kieuKhoaHoc: null,
                    soBuoi: null
                }
            }
        },
        methods: {
            onSubmitForm() {
                this.course.kieuKhoaHoc = parseInt(this.course.kieuKhoaHoc);
                if (this.course.kieuKhoaHoc == 1) {
                    this.course.soBuoi = parseInt(this.course.soBuoi);
                } else {
                    this.course.soBuoi = null;
                }
                //axios
                axios({
                    method: 'post',
                    url: 'http://localhost:8080/api/course/add',
                    data: this.course
                })
                    .then(res => {
                        if (res.status == 200) {
                            this.$emit('updateCourseList');
                        }
                    }).catch(err => console.log(err));
            }
        }
    }
</script>

<style scoped>

</style>