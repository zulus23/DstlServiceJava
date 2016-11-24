/**
 * Created by Zhukov on 23.10.2016.
 */

var userService = (
       function(){

           var getJwt =  function (getUserToken){
                $.ajax({
                    type:"GET",
                    url:'/api/jwt',
                    success: getUserToken

                }).done(getUserToken);

           }
           return {
               token:getJwt

           }
       }
)()