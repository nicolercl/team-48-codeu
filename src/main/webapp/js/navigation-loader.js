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

/**
 * Adds a login or logout link to the page, depending on whether the user is
 * already logged in.
 */
function addLoginOrLogoutLinkToNavigation() {
  const navigationElement = document.getElementById('navigation');
  if (!navigationElement) {
    console.warn('Navigation element not found!');
    return;
  }

  fetch('/login-status')
      .then((response) => {
        return response.json();
      })
      .then((loginStatus) => {
        if (loginStatus.isLoggedIn) {
          navigationElement.appendChild(createListItem(createLink(
              '/user-page.html?user=' + loginStatus.username, 'Your Page', 'person')));
          navigationElement.appendChild(createListItem(createLink(
                        '/skill-search.html', 'Skill Search', 'search')));
          navigationElement.appendChild(
              createListItem(createLink('/logout', 'Logout', 'person')));
        } else {
          navigationElement.appendChild(
              createListItem(createLink('/login', 'Login', 'person')));
        }
      });
}

/**
 * Creates an li element.
 * @param {Element} childElement
 * @return {Element} li element
 */
function createListItem(childElement) {;
  const listItemElement = document.createElement('div');
  listItemElement.setAttribute("role", "listitem");
  listItemElement.appendChild(childElement);
  return listItemElement;
}

/**
 * Creates an anchor element.
 * @param {string} url
 * @param {string} text
 * @return {Element} Anchor element
 */
function createLink(url, text, iconName) {
  const linkElement = document.createElement('a');
  linkElement.href = url;
  linkElement.setAttribute("class", "v-list__tile v-list__tile--link theme--light");
  const listAction = document.createElement('div');
  listAction.setAttribute("class", "v-list__tile__action");
  const listContent = document.createElement('div');
  listContent.setAttribute("class", "v-list__tile__content");

  const icon = document.createElement('i');
  icon.setAttribute("class", "v-icon material-icons theme--light");
  icon.setAttribute("aria-hidden", "true");
  icon.innerHTML = iconName;
  listAction.appendChild(icon);

  const textContent = document.createElement('div');
  textContent.setAttribute("class", "v-list__tile__title");
  textContent.innerHTML = text;
  listContent.appendChild(textContent);

  linkElement.appendChild(listAction);
  linkElement.appendChild(listContent);
  return linkElement;
}
