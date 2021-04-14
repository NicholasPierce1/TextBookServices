/**
 * Create new map for li tags
 */

liMap = new Map();


/**
 * @function get_list_items
 *
 * Create a list of ul tags and list items
 * Sets nav bar id and li id to liMap
 * key: nav bar id (ie: SupervisorAddBooks)
 * Value: li id (ie: /AddBooks)
 */
function get_list_items(){

    ulList = document.getElementsByTagName('ul');

    for(let i = 0; i < ulList.length; i++){

        let menu = ulList[i].id;

        if(menu == undefined && menu.length == 0){
            console.log(`Menu: ${menu}`);
        }

        let liList = ulList[i].getElementsByTagName('li');

        for(let j = 0; j < liList.length; j++){

            let menu = window.sessionStorage.getItem('nav');;
            //console.log(menu);
            let submenu = liList[j].id;
            //console.log(submenu);

            let key = liList[j].id;
            let value = "/" + liList[j].id;

            //console.log(`Menu: ${menu} \n Submenu: ${submenu}`);

            liMap.set(`${menu}${key}`, `${value}`);
        }
    }

    console.log(liMap);
}

// todo: add docs
const studentNavMappings = ["patronDropDownUl"];

const supervisorNavMappings = [studentNavMappings[0], "supervisorDropDownUl"];

/**
 * @onload
 *
 * Determines if the user is a student or supervisor
 * Creates response and set the login user info
 * Creates input tag list for user and student
 * Calls function get_list_items to get the li map
 * For loop for each li to add an onclick listener to create manual forms
 */

// key-value for holding the li nav pairs
const LI_NAV_PAIRS_KEY = "liNavPairs";

const formControllerResponseData = "data";

function setNavMappings(){

    // console.log("Navbar:", document.getElementByTagName('nav').id);
    // if((document.getElementByTagName('nav').id) === 'Student'){
    //     window.sessionStorage.setItem('nav', (document.getElementsByTagName('nav')[0].id));
    //     console.log("Student Menu");
    // }
    // else((document.getElementsByTagName('nav')[0].id) === 'Supervisor'){
    //     window.sessionStorage.setItem('nav', (document.getElementsByTagName('nav')[0].id));
    //     console.log("Supervisor Menu");
    // }

    const liNavMapPair = new Map();

    let navId = window.document.getElementById("nav").id;

    if(navId === null)
        throw new Error("id value is not set in navId. Please revise -- cannot generate nav mappings");
    else if(navId !== "Student" || navId !== "Supervisor")
        throw new Error("id value does not equal student or supervisor; please revise");

    let mappingsToGenerate = navId === "Student" ? studentNavMappings : supervisorNavMappings;

    // integrity check that UL exist for mapping -- generate mappings
    for(let i = 0; i < mappingsToGenerate.length; i++) {

        if (window.document.getElementById(mappingsToGenerate[i]) === null)
            throw new Error(`Ul list with id {${mappingsToGenerate[i]}} does not exist in document.`);

        const ulToGenerateMappings = window.document.getElementById(mappingsToGenerate[i]);

        const liList = ulToGenerateMappings.getElementsByTagName("li").length;

        if(liList.length === 0)
            throw new Error(`UL list with id {${ulToGenerateMappings.id}} is empty`);

        for(let j = 0; j < liList; j++){

            // given: retains an anchor tag
            const anchorTag = liList[i].getElementsByTagName("a")[0];

            // sets li mapping pair
            // key: ul's id + li's id
            liNavMapPair.set(
                `${mappingsToGenerate[i]}${liList[i].id}`,
                {
                    url: anchorTag.href,
                    methodType: "GET"
                }
            );

        }

    }

    window.sessionStorage.setItem(LI_NAV_PAIRS_KEY, JSON.stringify(liNavMapPair));

    /*
    let response = JSON.parse(window.getElementById("hiddenInput").value);
    let loginUserInfo = LoginUserInfo.createLoginUserInfoFromJson(response.LoginUserInfo);
    SessionStorage.setItem("loginUserInfo", loginUserInfo);
     */

}

function getLoginUserInfo(){

    // extracts string json and convert to json object
    const json = JSON.parse(window.document.getElementById(`${formControllerResponseData}`).value);

    if(!json.LoginUserInfo)
        throw new Error("json does not retain a login user info.");

    return json.LoginUserInfo;

}

/**
 * @function createManualForm
 * Creates a form manually, sets and logs important information
 * (Name, Navbar ID, Li, Key, Value)
 *
 * @param {1} event
 * the anchor tag that generated this event
 */
function createManualForm(event){

/*
    const name = url.srcElement.innerHTML;
    const submenu = url.srcElement.parentElement.id;
    const navbar = window.sessionStorage.getItem('nav');
    const key = `${navbar}${submenu}`;

    console.log(`Name: ${name}`);
    console.log(`Navbar: ${navbar}`)
    console.log(`Submenu: ${submenu}`);
    console.log(`key: ${key}`);
    console.log('Value:', liMap.get(key));

    let toForm = liMap.get(key);

    let form = document.createElement('form');

    form.method = "GET";
    form.action = `${toForm}`;
    form.name = `${submenu}`;


    submitManualForm(form);

 */

    // sets key from event (anchor tag clicked)
    const liTagParent = event.target.parentElement;

    const liNavMapPairKey = `${liTagParent.parentElement.id}${liTagParent.id}`;

    // acquire url & method pairs from key
    const urlMethodPair = JSON.parse(
        window.sessionStorage.getItem(LI_NAV_PAIRS_KEY)
    ).get(liNavMapPairKey);

    if(!urlMethodPair)
        throw new Error(`Map does not retain the key {${liNavMapPairKey}`);

    // creates form object
    const form = window.document.createElement("form");

    // sets form state
    form.method = urlMethodPair.methodType;
    form.action = urlMethodPair.url;

    // sets form to login user info
    const loginUserInfo = getLoginUserInfo();

    if(!loginUserInfo._password || !loginUserInfo._username)
        throw new Error("login user info does not retain apt state. Please revise JSON for LoginUserInfo");

    // creates composite input elements to append to form
    const userNameInputElement = window.document.createElement("input");
    userNameInputElement.type = "text";
    userNameInputElement.name = "_username";
    userNameInputElement.value = loginUserInfo._username;

    const passwordInputElement = window.document.createElement("input");
    passwordInputElement.type = "password";
    passwordInputElement.name = "_password";
    passwordInputElement.value = loginUserInfo._password;

    // appends input elements to form
    form.appendChild(userNameInputElement);
    form.appendChild(passwordInputElement);


    return form;


}

/**
 * @function submitManualForm
 * submits the form
 * @param {1} event
 * the form
 */

function submitManualForm(event){

    try {
        createManualForm(event).submit();
    }
    catch (e) {
        console.log("internal error (home handler -- submit manual form)");
        printError(e.message);
    }

}