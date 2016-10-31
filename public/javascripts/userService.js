/**
 * Created by Zhukov on 23.10.2016.
 */

var userService = (
       function(){

           var getJwt =  function (){
               var token =""
               axios.get('api/jwt').then(function(response){

                   token =  response.data;
               })
              return token;
           }
           return {
               login:getJwt

           }
       }
)()