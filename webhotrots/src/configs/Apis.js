import axios from 'axios';
import cookie from 'vue-cookies';
const SERVER = 'http://localhost:8085';

export const authApi = () => {
    return axios.create({
        baseURL: SERVER,
        headers: {
            "Authorization":  cookie.get('token')
        }
    })
}


export const endpoints = {
  Banner:`/api/banner`,
  UpdateBanner:`/api/banner/update`,
  RecycleBinBanner:`/api/banner/recyclebin`,
  News:`/api/news`,
  NewsCxd:`/api/newscxd`,
  NewsDH:`/api/newsdh`,
  NewsSDH:`/api/newssdh`,
  NewsTT:`/api/newstt`,
  NewsTX:`/api/newstx`,
  PageSDH:`/api/newspagesdh`,
  PageDH:`/api/newspagedh`,
  PageTT:`/api/newspagett`,
  PageTX:`/api/newspagetx`,
  UpdateNews:`/api/news/update`,
  RecycleBinNews:`/api/news/recyclebin`,
  DuyetNews:`/api/news/check`,
  Department:`api/department`,
  UpdateDepartment:`api/department/update`,
  Login:`api/login`,
  Current_user:`api/current-user`,
  Register:`api/register`,
  
};

export default axios.create({
    baseURL: SERVER
})