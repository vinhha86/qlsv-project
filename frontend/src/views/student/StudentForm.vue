<template>
    <div class="row">
        <router-link
                class="btn btn-primary mb-2"
                tag="button"
                active-class="active"
                to="/student"
                exact
        >
            <i class="fa fa-undo"></i>
            Danh sách sinh viên
        </router-link>
        <form
                method="post"
                class="border rounded p-3"
                style="background-color: #eee"
                name="studentForm"
                enctype="multipart/form-data"
        >
            <div class="row">
                <div class="col-sm-4 text-center">
                    <img class="w-75 mb-4" :src="previewImage == null? require(`@/assets/placeholder.jpg`) : previewImage"/>
                    <input type="file" class="d-none" ref="avatarInput" @change="onAvatarSelected"/>
                    <button type="button" class="btn btn-primary" @click="$refs.avatarInput.click()"><i class="fa fa-upload"></i>&nbsp;
                        Tải lên ảnh hồ sơ
                    </button>
                </div>
                <div class="col-sm-8">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Họ/Tên đệm:</label>
                                <input type="text" class="form-control" v-model="student.ho"/>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Tên:</label>
                                <div class="input-group">
                                    <input class="form-control" v-model="student.ten"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Mã học viên:</label>
                                <input type="text" class="form-control" v-model="student.masv"/>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Ngày sinh:</label>
                                <div class="input-group">
                                    <input class="form-control" v-model="student.ngaysinh"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Địa chỉ:</label>
                        <input
                                type="text"
                                class="form-control"
                                required="required"
                                v-model="student.address.chitiet"
                        />
                    </div>
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label>Tỉnh/Thành phố:</label>
                                <select name="thanhpho" class="form-control" v-model="selectedProvice" @change="onSelectedProvince">
                                    <option :value="null" disabled>--Chọn tỉnh/thành phố--</option>
                                    <option v-for="province in provinceList" :key="province.matp" :value="province">{{province.name}}</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label>Quận/Huyện:</label>
                                <select name="quanhuyen" class="form-control" v-model="selectedDistrict" @change="onSelectedDistrict">
                                    <option :value="null" disabled>--Chọn quận/huyện--</option>
                                    <option v-for="district in districtList" :key="district.maqh" :value="district">{{district.name}}</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label>Xã/Phường:</label>
                                <select name="xaphuong" class="form-control" v-model="selectedWard" @change="onSelectedWard">
                                    <option :value="null" disabled>--Chọn xã/phường--</option>
                                    <option v-for="ward in wardList" :key="ward.xaid" :value="ward">{{ward.name}}</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label>Số CMTND:</label>
                                <input
                                        type="text"
                                        class="form-control"
                                        required="required"
                                        v-model="student.socmt"
                                />
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label>Email:</label>
                                <input
                                        type="text"
                                        class="form-control"
                                        required="required"
                                        v-model="student.email"
                                />
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label>Giới tính:</label>
                                <div class="form-control pl-0 border-0">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" value=1 v-model="student.gioitinh"/>
                                        <label class="form-check-label">Nam</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" value=0 v-model="student.gioitinh"/>
                                        <label class="form-check-label">Nữ</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Số điện thoại cá nhân:</label>
                                <input
                                        type="text"
                                        class="form-control"
                                        required="required"
                                        v-model="student.sdt_canhan"
                                />
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Số điện thoại phụ huynh:</label>
                                <input
                                        type="text"
                                        class="form-control"
                                        v-model="student.sdt_phuhuynh"
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center border-top p-2">
                <button type="button" class="btn btn-success mr-2" @click="submitStudentForm">
                    <i class="fa fa-floppy-o"></i>
                    Lưu hồ sơ
                </button>
                <button type="reset" class="btn btn-danger">
                    <i class="fa fa-refresh"></i>
                    Làm mới
                </button>
            </div>
        </form>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        created() {
            axios.get('http://localhost:8080/api/address/province/all')
                .then(res =>{
                    this.provinceList = res.data;
                }).catch(err => console.log(err));
        },
        data() {
            return {
                provinceList: [],
                districtList: [],
                wardList: [],
                selectedProvice: null,
                selectedDistrict: null,
                selectedWard: null,
                previewImage: null,

                student: {
                    ho: '',
                    ten: '',
                    masv: '',
                    ngaysinh: '',
                    socmt: '',
                    email: '',
                    gioitinh: 1,
                    sdt_canhan: '',
                    sdt_phuhuynh: '',
                    address: {
                        chitiet: '',
                        province: {},
                        district: {},
                        ward: {}
                    }
                },

                avatar: {}

            }
        },
        methods: {
            onAvatarSelected(event) {
                // file image uploaded
                this.avatar = event.target.files[0];

                const reader = new FileReader();
                reader.readAsDataURL(this.avatar);
                reader.onload = event =>{
                    this.previewImage = event.target.result;
                };
            },
            onSelectedProvince() {
                this.student.address.province = this.selectedProvice;
                axios({
                    method: 'get',
                    url: 'http://localhost:8080/api/address/district/all',
                    params: {
                        matp: this.selectedProvice.matp
                    }
                })
                    .then(res =>{
                        this.districtList = res.data;
                    }).catch(err => console.log(err));
            },
            onSelectedDistrict() {
                this.student.address.district = this.selectedDistrict;
                axios({
                    method: 'get',
                    url: 'http://localhost:8080/api/address/ward/all',
                    params: {
                        maqh: this.selectedDistrict.maqh
                    }
                })
                    .then(res =>{
                        this.wardList = res.data;
                    }).catch(err => console.log(err));
            },
            onSelectedWard() {
                this.student.address.ward = this.selectedWard;
            },
            submitStudentForm() {
                const fd = new FormData();
                fd.append('student', JSON.stringify(this.student));
                fd.append('avatar', this.avatar);
                // console.log(fd.get('avatar'));
                axios({
                    method: 'post',
                    url: 'http://localhost:8080/api/student/add',
                    data: fd
                })
                    .then(res =>{
                        console.log(res);
                    }).catch(err => console.log(err));

            }
        },
    };
</script>

<style scoped></style>
