/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Get ?user=XYZ parameter value
const urlParams = new URLSearchParams(window.location.search);
const parameterUsername = urlParams.get('user');

// URL must include ?user=XYZ parameter. If not, redirect to homepage.
if (!parameterUsername) {
    window.location.replace('/');
}

/** Sets the page title based on the URL parameter username. */
function setPageTitle() {
    document.title = parameterUsername + ' - Profile ';
}

/**
 * Shows the message form if the user is logged in and viewing their own page.
 */
function showMessageFormIfViewingSelf() {
    fetch('/login-status')
        .then((response) => {
            return response.json();
        })
        .then((loginStatus) => {
            if (loginStatus.isLoggedIn &&
                loginStatus.username == parameterUsername) {
                const messageForm = document.getElementById('message-form');
                messageForm.classList.remove('hidden');
                document.getElementById('about-me-form').classList.remove('hidden');
            }
        });
}

/** Fetches messages and add them to the page. */
function fetchMessages() {
    const url = '/messages?user=' + parameterUsername;
    fetch(url)
        .then((response) => {
            return response.json();
        })
        .then((messages) => {
            const messagesContainer = document.getElementById('message-container');
            if (messages.length == 0) {
                messagesContainer.innerHTML = '<p>This user has no posts yet.</p>';
            } else {
                messagesContainer.innerHTML = '';
            }
            messages.forEach((message) => {
                const messageDiv = buildMessageDiv(message);
                messagesContainer.appendChild(messageDiv);
            });
        });
}

/**
 * Builds an element that displays the message.
 * @param {Message} message
 * @return {Element}
 */
function buildMessageDiv(message) {
    const headerDiv = document.createElement('div');
    headerDiv.setAttribute("class", "v-card__title")

    const img = document.createElement('div');
    img.setAttribute("class", 'v-img');
    img.classList.add('elevation-6');
    img.setAttribute("src","https://image.flaticon.com/icons/png/512/97/97895.png");

    const avatar = document.createElement('v-list__tile__avatar');
    avatar.setAttribute("color","grey darken-3");
    avatar.appendChild(img);
    const userName = document.createElement('v-list__tile__content');
    userName.innerHTML = parameterUsername;
    headerDiv.appendChild(avatar);
    headerDiv.appendChild(userName);

    const text = document.createElement('div');
    text.setAttribute("class", 'v-card__text');
    text.innerHTML = message.text;

    const icon1 = document.createElement('i');
    icon1.setAttribute("class", "material-icons");
	icon1.setAttribute("aria-hidden", "true");
	icon1.setAttribute("large","true");
    icon1.setAttribute("left","true");
    icon1.innerHTML = "face"
    const content = document.createElement('div');
    content.setAttribute("class", 'v-list__tile__content');

    const title = document.createElement('div');
    title.setAttribute("class", "v-list__tile__title");
    title.appendChild(document.createTextNode(message.skill));
    content.appendChild(title);

    const layout = document.createElement('v-layout');
    layout.setAttribute("align-center","true");
    layout.setAttribute("justify-end","true");

    const icon2 = document.createElement('i');
    icon2.setAttribute("class", "material-icons");
	icon2.setAttribute("aria-hidden", "true");
	icon2.classList.add("left");
    icon2.classList.add("large");
    icon2.innerHTML = "favorite"
    layout.appendChild(icon2);
    const bodyDiv = document.createElement('div');
    bodyDiv.setAttribute("class", 'v-card__actions');
    bodyDiv.appendChild(icon1);
    bodyDiv.appendChild(content);
    bodyDiv.appendChild(layout);

    const padding = document.createElement('div');
    padding.setAttribute("class",'v-card');
    const messageDiv = document.createElement('div');
    messageDiv.setAttribute("class", 'v-card');
    messageDiv.setAttribute("color","#26c6da");
    messageDiv.setAttribute("light","true");
    messageDiv.setAttribute("max-width","400");
    messageDiv.classList.add('elevation-5');
    messageDiv.appendChild(headerDiv);
    messageDiv.appendChild(text);
    messageDiv.appendChild(bodyDiv);

    return messageDiv;
}

/** Fetches data and populates the UI of the page. */
function buildUI() {
    setPageTitle();
    showMessageFormIfViewingSelf();
    fetchMessages();
    fetchAboutMe();
    //blocking the image and quoting function
    const config = {removePlugins: ['ImageUpload']};
    ClassicEditor.create(document.getElementById('message-input'), config );
    ClassicEditor.create(document.getElementById('about-me-textarea'),config );
}

function fetchAboutMe() {
    const url = '/about?user=' + parameterUsername;
    fetch(url).then((response) => {
        return response.json();
    }).then((info) => {
        indexVue.user_name = info.name;
        indexVue.user_age=info.age;
        indexVue.user_email=info.email;
        indexVue.user_learncate=info.learnCate;
        indexVue.user_teachcate=info.teachCate;
        indexVue.user_level=info.skillLevel;
        indexVue.user_school=info.school;
        document.getElementById('about-me-container').innerHTML=info.aboutMe;
    });
}

