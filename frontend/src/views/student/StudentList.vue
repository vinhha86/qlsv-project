<template>
    <div>
        <div class="row mb-2">
            <router-link class="btn btn-primary" tag="button" active-class="active" to="/student/add" exact>
                <i class="fa fa-plus"></i>&nbsp;Thêm mới hồ sơ
            </router-link>
        </div>
        <div v-if="studentList.length > 0">
            <div class="row justify-content-between mb-2">
                <div class="input-group col-sm-3 p-0">
                    <div class="input-group-prepend">
                        <label class="input-group-text">Số bản ghi:</label>
                    </div>
                    <select class="custom-select">
                        <option value="15">15</option>
                        <option value="20">20</option>
                        <option value="25">25</option>
                        <option value="30">30</option>
                    </select>
                </div>
                <div class="input-group col-sm-3 p-0">
                    <input type="text" class="form-control" placeholder="Tìm kiếm..."/>
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </div>
            <div class="row">
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
                            <button
                                    type="button"
                                    class="btn btn-outline-info"
                                    data-toggle="tooltip"
                                    data-placement="top"
                                    title="Xem chi tiết"
                            >
                                <i class="fa fa-eye"></i>
                            </button>&nbsp;
                            <button
                                    type="button"
                                    class="btn btn-outline-primary"
                                    data-toggle="tooltip"
                                    data-placement="top"
                                    title="Chỉnh sửa"
                            >
                                <i class="fa fa-pencil"></i>
                            </button>&nbsp;
                            <button
                                    type="button"
                                    class="btn btn-outline-danger"
                                    data-toggle="tooltip"
                                    data-placement="top"
                                    title="Xóa hồ sơ"
                            >
                                <i class="fa fa-trash-o"></i>
                            </button>&nbsp;
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="row justify-content-end">
                <nav>
                    <ul class="pagination">
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="#">1</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="#">2</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="#">...</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="#">10</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="row" v-else>
            <h3 class="text-center p-5 w-100" style="background-color: #eee">Chưa có hồ sơ sinh viên nào được tạo !</h3>
        </div>

    </div>
</template>

<script>
    import axios from 'axios';
    import moment from 'moment';

    export default {
        created() {
            axios.get('http://localhost:8080/api/student/all')
                .then(res => {
                    this.studentList = res.data;
                }).catch(err => console.log(err));
        },
        data() {
            return {
                studentList: []
            };
        },
        methods: {
            formatDate(date) {
                return moment(date).format('DD/MM/YYYY');
            }
        }
    };
</script>

<style scoped>
</style>
