import Vue from 'vue'
import Router from 'vue-router'

// 登录注册模块
const Initialization = () => import("../views/premise/Initialization")
const LoginMain = () => import("../views/premise/login/main")
const LoginAdmin = () => import("../views/premise/login/admin_login")
const LoginCompany = () => import("../views/premise/login/company_login")
const RegisterMain = () => import("../views/premise/register/main")
const RegisterAdmin = () => import("../views/premise/register/admin_register")
const RegisterCompany = () => import("../views/premise/register/company_register")
// 管理员模块
const AdminMain = () => import("../views/admin/main")
const AdminIndex = () => import("../views/admin/admin_index")
const AdminProfile = () => import("../views/admin/admin_profile")
const AdminManageAdmin = () => import("../views/admin/manage_admin")
const AdminManageCompany = () => import("../views/admin/manage_company")
const AdminManageUser = () => import("../views/admin/manage_user")
const AdminAddCompany = () => import("../views/admin/add_company")
const AdminEditCompany = () => import("../views/admin/edit_company")
// 公司模块
const CompanyMain = () => import("../views/company/main")
const CompanyIndex = () => import("../views/company/home")
const CompanyProfile = () => import("../views/company/profile")
const CompanyList = () => import("../views/company/list")
const CompanyHrDetail = () => import("../views/company/detail/hr_detail")

Vue.use(Router)

const routes = [
  {
    path: '',
    redirect: '/UserShunt'
  },
  {
    path: '/UserShunt',
    component: Initialization
  },
  {
    path: '/login',
    component: LoginMain,
    // 配置子路由
    children: [
      {
        path: 'admin_login',
        component: LoginAdmin
      },
      {
        path: 'company_login',
        component: LoginCompany
      }
    ]
  },
  {
    path: '/register',
    component: RegisterMain,
    // 配置子路由
    children: [
      {
        path: 'admin_register',
        component: RegisterAdmin
      },
      {
        path: 'company_register',
        component: RegisterCompany
      }
    ]
  },
  {
    path: '/admin',
    component: AdminMain,
    // 配置子路由
    children: [
      {
        path: 'admin_home',
        component: AdminIndex
      },
      {
        path: 'manage_admin',
        component: AdminManageAdmin
      },
      {
        path: 'manage_company',
        component: AdminManageCompany
      },
      {
        path: 'add_company',
        component: AdminAddCompany
      },
      {
        path: 'edit_company',
        component: AdminEditCompany
      },
      {
        path: 'manage_user',
        component: AdminManageUser
      },
      {
        path: 'admin_profile',
        component: AdminProfile
      }
    ]
  },
  {
    path: '/company',
    component: CompanyMain,
    // 配置子路由
    children: [
      {
        path: 'index',
        component: CompanyIndex
      },
      {
        path: 'profile',
        component: CompanyProfile
      },
      {
        path: 'list',
        component: CompanyList
      },
      {
        path: 'hr_detail',
        component: CompanyHrDetail
      },
    ]
  }
]
const router = new Router({
  routes,
  mode: "history"
})
const VueRouterPush = Router.prototype.push
Router.prototype.push = function push (to) {
  return VueRouterPush.call(this, to).catch(err => err)
}

router.beforeEach((to, from, next) => {
  if (to.path === '/UserShunt'||to.path ==='/login/admin_login'||to.path ==='/login/company_login') {
    next();
  } else {
    let token = localStorage.getItem('token')+"";
    // console.log(token === 'null')
    if (token === 'null' || token === '') {
      next('/login/admin_login');
    } else {
      next();
    }
  }
})  ;


export default router
