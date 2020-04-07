<template>
    <div>
        <div class="pt-3">
            <h4 class="text-center">Danh sách khóa học</h4>
            <div class="input-group">
                <input type="text" class="form-control mb-2" v-model="keyword" @input="searchCourse" placeholder="Tìm kiếm..."/>
            </div>
            <div class="d-flex justify-content-between">
                <button type="button" class="btn btn-primary mb-2" data-toggle="modal" data-target="#addCourseModal">
                    <i class="fa fa-plus" aria-hidden="true"></i>
                    Thêm khóa học</button>
                <select class="form-control w-50" v-model="courseType" @change="loadCourseByType(courseType)">
                    <option :value="null">All</option>
                    <option value="0">Dài hạn</option>
                    <option value="1">Ngắn hạn</option>
                </select>
            </div>
            <div v-if="courseList.length == 0">
                <p>Không tìm thấy khóa học nào !</p>
            </div>
            <div class="btn-group-vertical w-100">
                <button type="button" class="btn btn-outline-primary mb-2"
                    v-for="(course) in courseList" :key="course.id"
                    :class="selectedCourse === course ? 'active': ''"
                    @click="onCourseButtonClicked(course)"
                >
                    {{course.maKhoaHoc}} | {{course.tenKhoaHoc}} | {{course.kieuKhoaHoc == 0 ? 'Dài hạn' : 'Ngắn hạn'}}
                </button>
            </div>
            <div class="d-flex justify-content-center">
                <paginate
                        :page-count="totalPages"
                        :prev-text="'<<'"
                        :prev-class="'page-item'"
                        :prev-link-class="'page-link'"
                        :next-text="'>>'"
                        :next-class="'page-item'"
                        :next-link-class="'page-link'"
                        :click-handler="loadData"
                        :container-class="'pagination'"
                        :page-class="'page-item'"
                        :page-link-class="'page-link'"
                >
                </paginate>
            </div>
        </div>
        <add-course-modal @updateCourseList="this.initData"></add-course-modal>
        <edit-course-modal></edit-course-modal>
    </div>

</template>

<script>
    import AddCourseModal from "@/views/course/AddCourseModal";
    import EditCourseModal from "@/views/course/EditCourseModal";
    import axios from 'axios';
    import {eventBus} from "@/main";
    import Paginate from 'vuejs-paginate';

    export default {
        name: "CourseList",
        created() {
            eventBus.$on('updateCourseList', ()=>{
                this.initData();
            })
        },
        mounted() {
            this.initData()
        },
        data() {
            return {
                courseList: [],
                size: 10,
                totalPages: null,
                keyword: '',
                courseType: null,
                selectedCourse: null
            }
        },
        components: {
            'add-course-modal': AddCourseModal,
            'edit-course-modal': EditCourseModal,
            'paginate': Paginate
        },
        methods: {
            initData() {
                axios.get('http://localhost:8080/api/course/page', {params: {size: this.size}})
                    .then(res => {
                        this.courseList = res.data.content;
                        this.totalPages = res.data.totalPages;
                    }).catch(err => console.log(err));
            },
            loadData(pageNum) {
                axios.get('http://localhost:8080/api/course/page',
                    {
                        params: {
                            page: pageNum - 1,
                            size: this.size
                        }
                    })
                    .then(res => {
                        this.courseList = res.data.content;
                    }).catch(err => console.log(err));
            },
            onCourseButtonClicked(course) {
                this.selectedCourse = course;
                eventBus.$emit('setCourse', this.selectedCourse);
            },
            searchCourse() {
                if (this.keyword == '') {
                    this.initData();
                } else {
                    axios.get('http://localhost:8080/api/course/search', {params: {keyword: this.keyword}})
                        .then(res => {
                            this.courseList = res.data.content;
                            this.totalPages = res.data.totalPages;
                        }).catch(err => console.log(err));
                }
            },
            loadCourseByType(courseType) {
                if (courseType == null) {
                    this.initData();
                } else {
                    axios.get('http://localhost:8080/api/course/pageType', {params: {type: this.courseType, size: this.size}})
                        .then(res => {
                            this.courseList = res.data.content;
                            this.totalPages = res.data.totalPages;
                        }).catch(err => console.log(err));
                }
            },
        }
    }
</script>

<style scoped>

</style>