/**
 * Created by Zhukov on 23.10.2016.
 */

var userService = (
       function(){

           var getJwt =  function (){
               axios.get('api/jwt').then(function(response){
                   console.log(response.data);
               })
           }
           return {
               login:getJwt

           }
       }
)()