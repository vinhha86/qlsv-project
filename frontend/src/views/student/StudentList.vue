<template>
    <div>
        <div class="row mb-2">
            <router-link class="btn btn-primary" tag="button" active-class="active" to="/student/add" exact>
                <i class="fa fa-plus"></i>&nbsp;Thêm mới hồ sơ
            </router-link>
        </div>
        <div>
            <div class="row justify-content-between mb-2">
                <div class="input-group col-sm-3 p-0">
                    <div class="input-group-prepend">
                        <label class="input-group-text">Số bản ghi:</label>
                    </div>
                    <select class="custom-select" v-model="size">
                        <option value="10">10</option>
                        <option value="20">20</option>
                        <option value="30">30</option>
                    </select>
                </div>
                <div class="input-group col-sm-3 p-0">
                    <input type="text" v-model="keyword" @input="searchStudent" class="form-control" placeholder="Tìm kiếm..."/>
<!--                    <div class="input-group-append">-->
<!--                        <button class="btn btn-outline-secondary" type="button" @click="searchStudent">-->
<!--                            <i class="fa fa-search"></i>-->
<!--                        </button>-->
<!--                    </div>-->
                </div>
            </div>
            <div v-if="studentList.length > 0">
                <div  class="row">
                    <table class="table table-bordered">
                        <thead class="thead-dark">
                        <tr>
                            <td class="text-center" scope="col" style="width: 2%">STT</td>
                            <td class="text-center" scope="col">Mã SV</td>
                            <td class="text-center" scope="col">Họ tên</td>
                            <td class="text-center" scope="col">Ngày sinh</td>
                            <td class="text-center" scope="col">Điện thoại</td>
                            <td class="text-center" scope="col">Địa chỉ</td>
                            <td class="text-center" scope="col" style="width: 15%">Thao tác</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(student, index) in studentList" :key="student.id">
                            <td class="align-middle text-center">{{index+1}}</td>
                            <td class="align-middle text-center">{{student.masv}}</td>
                            <td class="align-middle text-center">{{student.ho+' '+student.ten}}</td>
                            <td class="align-middle text-center">{{formatDate(student.ngaysinh)}}</td>
                            <td class="align-middle text-center">{{student.sdt_canhan}}</td>
                            <td class="align-middle text-center">{{student.address.province.name}}</td>
                            <td class="align-middle text-center">
                                <router-link tag="button" class="btn btn-outline-info" :to="'/student/edit/'+student.id"><i class="fa fa-eye"></i></router-link>
                                &nbsp;
                                <router-link tag="button" class="btn btn-outline-primary" :to="'/student/edit/'+student.id" ><i class="fa fa-pencil"></i></router-link>
                                &nbsp;
                                <button class="btn btn-outline-danger" v-on:click="deleteStudent(student.id)">
                                    <i class="fa fa-trash-o"></i>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="row justify-content-end">
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
            <div v-else>
                <h3 class="text-center p-5 w-100" style="background-color: #eee">Không tìm thấy hồ sơ sinh viên nào !</h3>
            </div>
        </div>


    </div>
</template>

<script>
    import axios from 'axios';
    import moment from 'moment';
    import Paginate from 'vuejs-paginate';

    export default {
        mounted() {
            this.initData();
        },
        components: {
          'paginate': Paginate
        },
        data() {
            return {
                keyword: '',
                size: 10,
                totalPages: null,
                studentList: [],
            };
        },
        methods: {
            initData() {
                axios.get('http://localhost:8080/api/student/page', {params: {size: this.size}})
                    .then(res => {
                        this.studentList = res.data.content;
                        this.totalPages = res.data.totalPages;
                    }).catch(err => console.log(err));
            },
            loadData(pageNum) {
                axios.get('http://localhost:8080/api/student/page',
                    {params : {
                            page: pageNum-1,
                            size: this.size
                        }
                    })
                    .then(res => {
                        this.studentList = res.data.content;
                    }).catch(err => console.log(err));
            },
            searchStudent() {
                axios.get('http://localhost:8080/api/student/search',
                    {params: {
                        keyword: this.keyword,
                        size: this.size,
                    }}
                    )
                    .then(res => {
                        this.studentList = res.data.content;
                        this.totalPages = res.data.totalPages;
                        console.log(this.studentList)
                    }).catch(err => console.log(err));
            },
            formatDate(date) {
                return moment(date).format('DD/MM/YYYY');
            },
            deleteStudent(id) {
                axios.delete('http://localhost:8080/api/student/delete', {params: {id: id}}).then(res => {
                    if (res.status == 200) {this.loadData()}
                });
            }
        }
    };
</script>

<style scoped>
</style>
