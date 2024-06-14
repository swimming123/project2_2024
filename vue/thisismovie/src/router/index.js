import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/list', //axios 방식 
    name:'list',
    component : () => import('../views/ListView.vue')
  },

  { // 
    path: '/fileup', //axios 방식
    name:'UpLoadView',
    component : () => import('../views/UpLoadView.vue')
  },

  { //member 입력폼
    path: '/user/save', //axios 방식
    name:'AddForm',
    component : () => import('../views/AddForm.vue')
  },
  {
    path: '/member', //axios 방식 
    name:'MemListView',
    component : () => import('../views/MemListView.vue')
  },
  {
    // DetailView 
    //path: '/user/detail/',
    //path: '/user/detail/:num/:name/:email', //params 방식
    path: '/user/detail/:num', //axios 방식 //(A에서 받음) B.▣▣▣▣ →→→→→ DetailView C.▣▣▣▣
    name:'DetailView',
    component : () => import('../views/DetailView.vue')
  },
  { //member 입력폼
    path: '/addList', //axios 방식
    name:'addList',
    component : () => import('../views/addList.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
