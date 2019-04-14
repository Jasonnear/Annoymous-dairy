document.querySelector('.chat[data-chat=person2]').classList.add('active-chat');
document.querySelector('.person[data-chat=person2]').classList.add('active');

var friends = {
  list: document.querySelector('ul.people'),
  all: document.querySelectorAll('.chat_left .person'),
  name: '' },

chat = {
  container: document.querySelector('.chat_container .chat_right'),
  current: null,
  person: null,
  name: document.querySelector('.chat_container .chat_right .chat_top .chat_name') };


friends.all.forEach(function (f) {
  f.addEventListener('mousedown', function () {
    f.classList.contains('active') || setAciveChat(f);
  });
});

function setAciveChat(f) {
  friends.list.querySelector('.active').classList.remove('active');
  f.classList.add('active');
  chat.current = chat.container.querySelector('.active-chat');
  chat.person = f.getAttribute('data-chat');
  chat.current.classList.remove('active-chat');
  chat.container.querySelector('[data-chat="' + chat.person + '"]').classList.add('active-chat');
  friends.name = f.querySelector('.chat_name').innerText;
  chat.name.innerHTML = friends.name;
}