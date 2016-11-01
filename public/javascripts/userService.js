/**
 * Created by Zhukov on 23.10.2016.
 */

var userService = (
       function(){

           var getJwt =  function (getUserToken){
                axios.get('api/jwt').then(getUserToken)

           }
           return {
               token:getJwt

           }
       }
)()