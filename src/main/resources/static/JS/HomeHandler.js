import * as SHARED from "./SharedHandler"; // ensures shared handler is imported

/**
 * Create new map for li tags
 */

let liMap = new Map();


/**
 * @function get_list_items
 *
 * Create a list of ul tags and list items
 * Sets nav bar id and li id to liMap
 * key: nav bar id (ie: SupervisorAddBooks)
 * Value: li id (ie: /AddBooks)
 */
function get_list_items(){

    let ulList = document.getElementsByTagName('ul');

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

/*
* Create maps for student and supervisor menus
*/
const studentNavMappings = ["patronDropDownUl", "patronDropDownCheckInCheckOutUl", "patronDropDownHomeUl"];

const supervisorNavMappings = [studentNavMappings[0], "supervisorDropDownUl", "supervisorDropDownHomeUl"];

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


/*
* @function setNavMappings
* generates nav bar mappings for student and supervisor menus
* generated from the li list
*
*/
export function setNavMappings(){

    // console.log("Navbar:", document.getElementByTagName('nav').id);
    // if((document.getElementByTagName('nav').id) === 'Student'){
    //     window.sessionStorage.setItem('nav', (document.getElementsByTagName('nav')[0].id));
    //     console.log("Student Menu");
    // }
    // else((document.getElementsByTagName('nav')[0].id) === 'Supervisor'){
    //     window.sessionStorage.setItem('nav', (document.getElementsByTagName('nav')[0].id));
    //     console.log("Supervisor Menu");
    // }


    // Create new li nav bar map
    const liNavMapPair = new Map();

    // Creating nav bar id from the view
    let navId = window.document.getElementById("nav").id;

    // If nav bar id is null throw error or if navid doesn't match 'student' or 'supervisor'
    if(navId === null)
        throw new Error("id value is not set in navId. Please revise -- cannot generate nav mappings");
    else if(navId !== "Student" || navId !== "Supervisor")
        throw new Error("id value does not equal student or supervisor; please revise");

    // Choose which map to generate based on nav bar id (Student or Supervisor)
    let mappingsToGenerate = navId === "Student" ? studentNavMappings : supervisorNavMappings;

    // integrity check that UL exist for mapping -- generate mappings
    for(let i = 0; i < mappingsToGenerate.length; i++) {

        if (!window.document.getElementById(mappingsToGenerate[i]))
            throw new Error(`Ul list with id {${mappingsToGenerate[i]}} does not exist in document.`);

        const ulToGenerateMappings = window.document.getElementById(mappingsToGenerate[i]);

        const liList = ulToGenerateMappings.getElementsByTagName("li").length;

        if(liList.length === 0)
            throw new Error(`UL list with id {${ulToGenerateMappings.id}} is empty`);

        for(let j = 0; j < liList; j++){

            // given: retains an anchor tag
            const anchorTag = liList[i].getElementsByTagName("a")[0];

            // asserts attribute (targetEndpoint) exist within an anchor tag
            if(!anchorTag.getAttribute("targetEndpoint"))
                throw new Error("Anchor tag, which is within an applicable nav item, does not have" +
                    "the custom attribute (targetEndpoint) set. Please set it to where it hold the" +
                    "url of the nav item's endpoint.");

            // sets li mapping pair
            // key: ul's id + li's id
            liNavMapPair.set(
                `${mappingsToGenerate[i]}${liList[i].id}`,
                {
                    url: anchorTag.getAttribute("targetEndpoint"),
                    methodType: "GET"
                }
            );

        }

    }

    // Set key value pairs into session storage for potential later use by the user
    window.sessionStorage.setItem(LI_NAV_PAIRS_KEY, JSON.stringify(liNavMapPair));

    /*
    let response = JSON.parse(window.getElementById("hiddenInput").value);
    let loginUserInfo = LoginUserInfo.createLoginUserInfoFromJson(response.LoginUserInfo);
    SessionStorage.setItem("loginUserInfo", loginUserInfo);
     */

}

/**
 * @function getLoginUserInfo
 * parses login user info response data from the controller
 *
 */

export function getLoginUserInfo(){

    // if login user is set in session map then extract, parse
    if(window.sessionStorage.getItem(loginUserInfoKey))
        return UserInfo.parseJson(JSON.parse(window.sessionStorage.getItem(loginUserInfoKey)));

    // extracts string json and convert to json object
    const json = JSON.parse(window.document.getElementById(`${formControllerResponseData}`).value);

    //If there was no login user info, throw an error message
    if(!json.LoginUserInfo)
        throw new Error("json does not retain a login user info.");
    //Returns login user info in json form
    return json.LoginUserInfo;

}

export const loginUserInfoKey = "loginInfoUserKey";
// sets login user info in session storage
function setLoginUserInfo(){

    // converts to vm equivalent & set in vm
    window.sessionStorage.setItem(loginUserInfoKey, UserInfo.parseJson(getLoginUserInfo()).createJsonForm());

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
 * @param {1} event
 * submitting the for made in createManualForm()
 * catching errors if something went wrong
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

// appends every nav item child (href's onclick) to trigger the manual form submission
function setOnClicksToNavItems() {
    // Creating nav bar id from the view
    let navId = window.document.getElementById("nav").id;

    // If nav bar id is null throw error or if navid doesn't match 'student' or 'supervisor'
    if (navId === null)
        throw new Error("id value is not set in navId. Please revise -- cannot generate nav mappings");
    else if (navId !== "Student" || navId !== "Supervisor")
        throw new Error("id value does not equal student or supervisor; please revise");

    // Choose which map to generate based on nav bar id (Student or Supervisor)
    let mappingsToGenerate = navId === "Student" ? studentNavMappings : supervisorNavMappings;

    // integrity check that UL exist for mapping -- generate mappings
    for (let i = 0; i < mappingsToGenerate.length; i++) {

        if (!window.document.getElementById(mappingsToGenerate[i]))
            throw new Error(`Ul list with id {${mappingsToGenerate[i]}} does not exist in document.`);

        const ulToGenerateMappings = window.document.getElementById(mappingsToGenerate[i]);

        const liList = ulToGenerateMappings.getElementsByTagName("li").length;

        if (liList.length === 0)
            throw new Error(`UL list with id {${ulToGenerateMappings.id}} is empty`);

        for (let j = 0; j < liList; j++) {

            // given: retains an anchor tag
            const anchorTag = liList[i].getElementsByTagName("a")[0];

            // sets on click
            anchorTag.onclick = createManualForm;

        }
    }
}

// single function that invokes the nav mappings, nav item on clicks, and sets the login user info in the
// session map
export function initializeSharedState(){
    setNavMappings();
    setOnClicksToNavItems();
    setLoginUserInfo();
}