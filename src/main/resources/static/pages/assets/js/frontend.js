const toggles = document.querySelectorAll('.theme-toggle');
if (localStorage.getItem('frontend-theme') === 'dark') {
  document.body.classList.add('dark-mode');
}
toggles.forEach((toggle) => {
  toggle.addEventListener('click', () => {
    document.body.classList.toggle('dark-mode');
    localStorage.setItem('frontend-theme', document.body.classList.contains('dark-mode') ? 'dark' : 'light');
  });
});
