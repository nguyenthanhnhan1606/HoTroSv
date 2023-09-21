import { createRouter, createWebHistory } from 'vue-router'
import Home from '../client/views/Home.vue'
import HomeAdmin from '../admin/views/HomeAdmin.vue'
import Banner from '../admin/components/Banner.vue'
import BannerDetail from '../admin/components/BannerDetail.vue'
import AddBanner from '../admin/components/AddBanner.vue'
import News from '../admin/components/News.vue'
import NewsDetail from '../admin/components/NewsDetail.vue'
import AddNews from '../admin/components/AddNews.vue'
import NewsCxd from '../admin/components/NewsCxd.vue'
import Department from '../admin/components/Department.vue'
import DepartmentDetail from '../admin/components/DepartmentDetail.vue'
import AddDep from '../admin/components/AddDepartment.vue'
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
import ListNewsDH from '../client/components/ListNewsDH.vue'
import ListNewsSDH from '../client/components/ListNewsSDH.vue'
import ListNewsTT from '../client/components/ListNewsTT.vue'
import ListNewsTX from '../client/components/ListNewsTX.vue'
import NewsDetailClient from '../client/components/NewsDetailClient.vue'
import DepartmentDetailCl from '../client/components/DepartmentDetailCl.vue'
import Live from '../admin/components/Live.vue'
import FaQuestion from '../client/components/FaQuestion.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
    children: [
      {
        path: 'login',
        name: "login",
        component: Login
      },
      {
        path: 'register',
        name: "register",
        component: Register
      },
      {
        path: 'newsdh',
        name: "newsdh",
        component: ListNewsDH
      },
      {
        path: 'newsSdh',
        name: "newssdh",
        component: ListNewsSDH
      },
      {
        path: 'newstt',
        name: "newstt",
        component: ListNewsTT
      },
      {
        path: 'newstx',
        name: "newstx",
        component: ListNewsTX
      },
      {
        path: 'news-detail/:id',
        name: "newsDetailCl",
        component: NewsDetailClient
      },
      {
        path: 'department/:id',
        name: "depDetailCl",
        component: DepartmentDetailCl
      },
      {
        path: 'fa-question',
        name:'faquestion', // Đây là trang chính, có thể sử dụng component Home.vue
        component:FaQuestion
      },
    ]
  },
  {
    path: '/admin',
    name: 'Homeadmin',
    component: HomeAdmin,
    children:[
      {
        path: 'banner',
        name:'banner', // Đây là trang chính, có thể sử dụng component Home.vue
        component:Banner
      },
      {
        path: 'banner/:id',
        name:'bannerDetail', // Đây là trang chính, có thể sử dụng component Home.vue
        component:BannerDetail
      },
      {
        path: 'add-banner',
        name:'addBanner', // Đây là trang chính, có thể sử dụng component Home.vue
        component:AddBanner
      },
      {
        path: 'news',
        name:'news', // Đây là trang chính, có thể sử dụng component Home.vue
        component:News
      },
      {
        path: 'live',
        name:'live', // Đây là trang chính, có thể sử dụng component Home.vue
        component:Live
      },
      {
        path: 'newscxd',
        name:'newsCxd', // Đây là trang chính, có thể sử dụng component Home.vue
        component:NewsCxd
      },
      {
        path: 'news/:id',
        name:'newsDetail', // Đây là trang chính, có thể sử dụng component Home.vue
        component:NewsDetail
      },
      {
        path: 'add-news',
        name:'addNews', // Đây là trang chính, có thể sử dụng component Home.vue
        component:AddNews
      },
      {
        path: 'department',
        name:'department', // Đây là trang chính, có thể sử dụng component Home.vue
        component:Department
      },
      {
        path: 'department/:id',
        name:'departmentDetail', // Đây là trang chính, có thể sử dụng component Home.vue
        component:DepartmentDetail
      },
      {
        path: 'add-department',
        name:'addDep', // Đây là trang chính, có thể sử dụng component Home.vue
        component:AddDep
      },
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
