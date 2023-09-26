import myStore from "@/store/MyStore";
export function isAdmin(to, from, next) {
    if (myStore.getters.getUser === null)
        next({ name: 'login' })
    else
        if (myStore.getters.getUser.userRole === 'ROLE_ADMIN') {
            next();
        } else {
            next({ name: 'unauthorized' });
        }
}
export function checkLogin(to, from, next) {
    if (myStore.getters.isAuth === false)
        next();
    else
        if (myStore.getters.isAuth === true) {
            next({ name: "home" })
        } else {
            next({ name: 'login' });
        }
}