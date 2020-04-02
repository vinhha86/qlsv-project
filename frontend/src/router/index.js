import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '../views/Home.vue'

import Student from '../views/student/Student.vue'
import StudentList from "../views/student/StudentList.vue";
import StudentForm from "../views/student/StudentForm.vue";

import Class from '../views/Class.vue'
import Schedule from '../views/Schedule.vue'
import Course from '../views/Course.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/student',
    name: 'Student',
    component: Student,
    children: [
      { path: '', component: StudentList },
      { path: 'add', component: StudentForm }
    ]
  },
  {
    path: '/class',
    name: 'Class',
    component: Class
  },
  {
    path: '/schedule',
    name: 'Schedule',
    component: Schedule
  },
  {
    path: '/course',
    name: 'Course',
    component: Course
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  routes,
  mode: 'history'
})

export default router
