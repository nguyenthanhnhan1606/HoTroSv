import { createRouter, createWebHistory } from 'vue-router'
import Home from '../client/views/Home.vue'
import HomeAdmin from '../admin/views/HomeAdmin.vue'
import Banner from '../admin/components/Banner.vue'
import BannerDetail from '../admin/components/BannerDetail.vue'
import AddBanner from '../admin/components/AddBanner.vue'
import News from '../admin/components/News.vue'
import NewsDetail from '../admin/components/NewsDetail.vue'
import AddNews from '../admin/components/AddNews.vue'
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
import LiveDetail from '../admin/components/LiveDetail.vue'
import AddLive from '../admin/components/AddLive.vue'
import LiveClient from '../client/components/LiveCl.vue'
import unauthorized from '@/components/Unauthorized.vue'
import { isAdmin, checkLogin } from '@/configs/Unauthorized'
import CurrentUser from '../client/components/CurrentUser.vue'
import ListUser from '../admin/components/ListUser.vue'
import UserDetail from '../admin/components/UserDetail.vue'
import AddUserAdviser from '../admin/components/AddUserAdviser.vue'
import ListQuestionLive from '../client/components/ListQuestionLive.vue'
import FaQuestionAdmin from '../admin/components/FaQuestionAdmin.vue'
import AddFAQ from '../admin/components/AddFAQ.vue'
import FaQuestionDetail from '../admin/components/FaQuestionDetail.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
    children: [
      {
        path: 'login',
        name: "login",
        beforeEnter: checkLogin,
        component: Login
      },
      {
        path: 'current-user',
        name: "currentUser",
        component: CurrentUser
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
        path: 'live-client',
        name: "livecl",
        component: LiveClient
      },
      {
        path: 'live-client/:id',
        name: "liveclDetail",
        component: ListQuestionLive
      },
      {
        path: 'fa-question',
        name: 'faquestion', // Đây là trang chính, có thể sử dụng component Home.vue
        component: FaQuestion
      },
    
    ]
  },
  {
    path: '/admin',
    name: 'Homeadmin',
    component: HomeAdmin,
    beforeEnter: isAdmin,
    children: [
      {
        path: 'banner',
        name: 'banner', // Đây là trang chính, có thể sử dụng component Home.vue
        component: Banner
      },
      {
        path: 'banner/:id',
        name: 'bannerDetail', // Đây là trang chính, có thể sử dụng component Home.vue
        component: BannerDetail
      },
      {
        path: 'add-banner',
        name: 'addBanner', // Đây là trang chính, có thể sử dụng component Home.vue
        component: AddBanner
      },
      {
        path: 'news',
        name: 'news', // Đây là trang chính, có thể sử dụng component Home.vue
        component: News
      },
      {
        path: 'live',
        name: 'live', // Đây là trang chính, có thể sử dụng component Home.vue
        component: Live
      },
      {
        path: 'live/:id',
        name: 'liveDetail', // Đây là trang chính, có thể sử dụng component Home.vue
        component: LiveDetail
      },
      {
        path: 'add-live',
        name: 'addLive', // Đây là trang chính, có thể sử dụng component Home.vue
        component: AddLive
      },
      {
        path: 'news/:id',
        name: 'newsDetail', // Đây là trang chính, có thể sử dụng component Home.vue
        component: NewsDetail
      },
      {
        path: 'add-news',
        name: 'addNews', // Đây là trang chính, có thể sử dụng component Home.vue
        component: AddNews
      },
      {
        path: 'department',
        name: 'department', // Đây là trang chính, có thể sử dụng component Home.vue
        component: Department
      },
      {
        path: 'department/:id',
        name: 'departmentDetail', // Đây là trang chính, có thể sử dụng component Home.vue
        component: DepartmentDetail
      },
      {
        path: 'add-department',
        name: 'addDep', // Đây là trang chính, có thể sử dụng component Home.vue
        component: AddDep
      },
      {
        path: 'list-user',
        name: 'listUser', // Đây là trang chính, có thể sử dụng component Home.vue
        component: ListUser
      },
      {
        path: 'list-user/:id',
        name: 'userDetail', // Đây là trang chính, có thể sử dụng component Home.vue
        component: UserDetail
      },
      {
        path: 'add-user',
        name: 'addUserAdviser', // Đây là trang chính, có thể sử dụng component Home.vue
        component: AddUserAdviser
      },
      {
        path: 'faquestion',
        name: 'faquestionAdmin', // Đây là trang chính, có thể sử dụng component Home.vue
        component: FaQuestionAdmin
      },
      {
        path: 'add-faquestion',
        name: 'addFaq', // Đây là trang chính, có thể sử dụng component Home.vue
        component: AddFAQ
      },
      {
        path: 'faquestion-detail/:id',
        name: 'faqDetail', // Đây là trang chính, có thể sử dụng component Home.vue
        component: FaQuestionDetail
      },
    ]
  },
  {
    path: '/error',
    name: 'unauthorized', // Đây là trang chính, có thể sử dụng component Home.vue
    component: unauthorized
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
