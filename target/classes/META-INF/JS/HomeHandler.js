/**
 * @Author Chase Staples
 * @DateCreated 03/18/21
 *
 *
 */


/**
 * 1.A
 */
liMap = new Map();
userInfoMap = new Map();

/**
* 1.C
*/

function get_list_items(){

    ulList = document.getElementsByTagName('ul');

    for(let i = 0; i < liList.length; i++){

        liList = ulList[i].getElementsByTagName('li');

        for(let j = 0; j < liList.length; j++){

            console.log('Menu: ${ulList[i] \n Submenu: ${liList[j].innerHTML} ');

            myMap.set('${ulList[i].class} ${lilist[i].innerhtml}', {url: 'urlhere', postType: 'GET'});
        }
    }

    console.log(myMap);
}

function getURL(url){

    console.log(url.srcElement.innerHTML);
    console.log(url.srcElement.parentElement.class);

    const key = '${url.srcElement.parentElement.class}${url.srcElement.innerHTML}';

    console.log(key);
    console.log(myMap.get(key));

}

/**
 * 1
 */

windows.onload = () => {

    get_list_items();

    let user = new UserInfo();

    let userID = user.getSTUDENT_ID_PATTERN();
    let userPrefix = user.getS_NUMBER_PREFIX();
    let userSuffix = user.getS_NUMBER_SUFFIX();

    userInfoMap.set(parseJson(userID));
    userInfoMap.set(parseJson(userPrefix));
    userInfoMap.set(parseJson(userSuffix));

    liList = document.getElementsByTagName('li');

    for(let i = 0; i < liList.length; i++){
        liList[i].onclick = getURL;
    }

    /**
     * 1.B
     */
    document.getElementsByIdName("data").innerHTML = userInfoMap;


    /**
     * 1.D
     */
    createManualFromForm();



}

/**
 * 2
 */

function createManualFromForm(){
    let form = document.createElement('form');

}

/**
 * 3
 */
function submitManualForm(){

}